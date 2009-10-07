package fr.giwi.agreugator.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import fr.giwi.agreugator.blog.dao.RssEntryManager;
import fr.giwi.agreugator.helpers.RSSHelper;
import fr.giwi.agreugator.rss.bean.RSSEntry;

/**
 * Servlet implementation class AddRss
 */
public class AddRss extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3072310376998408774L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		final String urlStr = request.getParameter("url");
		if (StringUtils.isBlank(urlStr)) {
			request.setAttribute("erreoMess", "Url vide");
			final RequestDispatcher rd = request.getRequestDispatcher("addrss.jsp");
			rd.forward(request, response);
			return;

		}
		final RssEntryManager rssem = new RssEntryManager();
		try {
			if (!rssem.isValidFeed(urlStr)) {
				request.setAttribute("erreoMess", "Url foireuse");
				final RequestDispatcher rd = request.getRequestDispatcher("addrss.jsp");
				rd.forward(request, response);
				return;
			}

			if (!rssem.isExistRssFeed(urlStr)) {
				final RSSEntry re = RSSHelper.getRssEntryObj(urlStr);
				rssem.addRssEntry(re);
				request.setAttribute("okMess", "Url enregistrée : " + re.getTitle());
			} else {
				request.setAttribute("erreoMess", "Url déjà présente");
			}
		} catch (final Exception e) {
			request.setAttribute("erreoMess", e.getMessage());
			final RequestDispatcher rd = request.getRequestDispatcher("addrss.jsp");
			rd.forward(request, response);
			return;
		}
		final RequestDispatcher rd = request.getRequestDispatcher("addrss.jsp");
		rd.forward(request, response);
		return;
	}
}
