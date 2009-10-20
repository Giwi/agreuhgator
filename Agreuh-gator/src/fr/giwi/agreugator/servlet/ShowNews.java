package fr.giwi.agreugator.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.pbeans.StoreException;

import org.apache.commons.lang.StringUtils;

import fr.giwi.agreugator.blog.bean.BlogEntry;
import fr.giwi.agreugator.blog.dao.BlogEntryManageable;
import fr.giwi.agreugator.blog.dao.BlogEntryManager;

/**
 * Servlet implementation class ShowNews
 */
public class ShowNews extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		final String id = request.getParameter("id");
		if (StringUtils.isBlank(id)) {
			request.setAttribute("erreoMess", "Id vide");
			final RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
			return;
		}
		final BlogEntryManageable bem = new BlogEntryManager();
		BlogEntry be = null;
		try {
			be = bem.getEntry(Integer.parseInt(id));
		} catch (final NumberFormatException e) {
			request.setAttribute("erreoMess", "Id invalide");
			final RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
			return;
		} catch (final StoreException e) {
			request.setAttribute("erreoMess", "Erreur d'accès à la base de données");
			final RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
			return;
		}
		request.setAttribute("blogentry", be);
		final RequestDispatcher rd = request.getRequestDispatcher("shownews.jsp");
		rd.forward(request, response);
		return;
	}

}
