package fr.giwi.agreugator.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import fr.giwi.agreugator.blog.dao.RssEntryManager;

/**
 * Servlet implementation class DelNews
 */
public class DelRss extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3043131668648964052L;

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
		try {
			rssem.deleteRssEntry(Integer.parseInt(id));
		} catch (final Exception e) {
			request.setAttribute("erreoMess", "Id invalide");
			final RequestDispatcher rd = request.getRequestDispatcher("addrss.jsp");
			rd.forward(request, response);
			return;
		}
		request.setAttribute("okMess", "Rss effacé");
		final RequestDispatcher rd = request.getRequestDispatcher("addrss.jsp");
		rd.forward(request, response);
		return;
	}
}
