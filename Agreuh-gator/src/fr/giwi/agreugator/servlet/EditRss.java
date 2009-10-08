package fr.giwi.agreugator.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import fr.giwi.agreugator.rss.bean.RSSEntry;
import fr.giwi.agreugator.rss.dao.RssEntryManager;

/**
 * Servlet implementation class DelNews
 */
public class EditRss extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5152560623866841326L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		final String id = request.getParameter("id");
		if (StringUtils.isBlank(id)) {
			request.setAttribute("erreoMess", "Id vide");
			final RequestDispatcher rd = request.getRequestDispatcher("addrss.jsp");
			rd.forward(request, response);
			return;
		}
		final RssEntryManager rssem = new RssEntryManager();
		RSSEntry re = null;
		try {
			re = rssem.getEntry(Integer.parseInt(id));
		} catch (final Exception e) {
			request.setAttribute("erreoMess", "Id invalide");
			final RequestDispatcher rd = request.getRequestDispatcher("addrss.jsp");
			rd.forward(request, response);
			return;
		}
		request.setAttribute("rssentry", re);
		final RequestDispatcher rd = request.getRequestDispatcher("editrss.jsp");
		rd.forward(request, response);
		return;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		final String title = request.getParameter("title");
		final String description = request.getParameter("description");
		final String url = request.getParameter("url");
		final String id = request.getParameter("id");
		request.setAttribute("title", title);
		request.setAttribute("description", description);
		request.setAttribute("url", url);
		final RSSEntry re = new RSSEntry();
		re.setId(Integer.parseInt(id));
		re.setTitle(title == null ? "" : title);
		re.setDescription(description == null ? "" : description);
		re.setDate(new Date());
		re.setUrl(url == null ? "" : url);
		request.setAttribute("rssentry", re);
		if (StringUtils.isBlank(title) || StringUtils.isBlank(url)) {
			request.setAttribute("erreoMess", "Il vous manque des infos !!!");
			request.setAttribute("error", true);
			final RequestDispatcher rd = request.getRequestDispatcher("editrss.jsp");
			rd.forward(request, response);
			return;
		}
		final RssEntryManager rssem = new RssEntryManager();

		try {
			rssem.updateRssEntry(re, Integer.parseInt(id));
		} catch (final SQLException e) {
			request.setAttribute("erreoMess", "Erreur de connexion à la base");
			final RequestDispatcher rd = request.getRequestDispatcher("editnews.jsp");
			rd.forward(request, response);
			return;
		}
		final RequestDispatcher rd = request.getRequestDispatcher("addrss.jsp");
		rd.forward(request, response);
		return;
	}
}
