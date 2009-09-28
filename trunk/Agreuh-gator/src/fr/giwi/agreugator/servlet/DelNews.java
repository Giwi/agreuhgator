package fr.giwi.agreugator.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import fr.giwi.agreugator.blog.dao.BlogEntryManager;

/**
 * Servlet implementation class DelNews
 */
public class DelNews extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6779583914150831619L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		final String id = request.getParameter("id");
		if (StringUtils.isBlank(id)) {
			request.setAttribute("erreoMess", "Id vide");
			final RequestDispatcher rd = request.getRequestDispatcher("addnews.jsp");
			rd.forward(request, response);
			return;
		}
		final BlogEntryManager bem = new BlogEntryManager();
		try {
			bem.deleteBlogEntry(Integer.parseInt(id));
		} catch (final NumberFormatException e) {
			request.setAttribute("erreoMess", "Id invalide");
			final RequestDispatcher rd = request.getRequestDispatcher("addnews.jsp");
			rd.forward(request, response);
			return;
		} catch (final SQLException e) {
			request.setAttribute("erreoMess", "Erreur d'accès à la base de données");
			final RequestDispatcher rd = request.getRequestDispatcher("addnews.jsp");
			rd.forward(request, response);
			return;
		}
		request.setAttribute("okMess", "News effacée");
		final RequestDispatcher rd = request.getRequestDispatcher("addnews.jsp");
		rd.forward(request, response);
		return;
	}
}
