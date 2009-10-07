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

import fr.giwi.agreugator.blog.bean.BlogEntry;
import fr.giwi.agreugator.blog.dao.BlogEntryManager;

/**
 * Servlet implementation class DelNews
 */
public class EditNews extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4527143868690247507L;

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
		BlogEntry be = null;
		try {
			be = bem.getEntry(Integer.parseInt(id));
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
		request.setAttribute("blogentry", be);
		final RequestDispatcher rd = request.getRequestDispatcher("editnews.jsp");
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
		final String resume = request.getParameter("resume");
		final String content = request.getParameter("content");
		final String id = request.getParameter("id");
		final String type = request.getParameter("type");
		request.setAttribute("title", title);
		request.setAttribute("resume", resume);
		request.setAttribute("content", content);
		request.setAttribute("type", type);
		final BlogEntry be = new BlogEntry();
		be.setId(Integer.parseInt(id));
		be.setTitle(title == null ? "" : title);
		be.setResume(resume == null ? "" : resume);
		be.setDate(new Date());
		be.setContent(content == null ? "" : content);
		be.setType(Integer.parseInt(type));
		request.setAttribute("blogentry", be);
		if (StringUtils.isBlank(title) || StringUtils.isBlank(content)) {
			request.setAttribute("erreoMess", "Il vous manque des infos !!!");
			request.setAttribute("error", true);
			final RequestDispatcher rd = request.getRequestDispatcher("editnews.jsp");
			rd.forward(request, response);
			return;
		}
		final BlogEntryManager bem = new BlogEntryManager();

		try {
			bem.updateBlogEntry(be, Integer.parseInt(id));
			request.setAttribute("okMess", "Niouze enregistrée : " + be.getTitle());
		} catch (final SQLException e) {
			request.setAttribute("erreoMess", "Erreur de connexion à la base");
			final RequestDispatcher rd = request.getRequestDispatcher("editnews.jsp");
			rd.forward(request, response);
			return;
		}
		final RequestDispatcher rd = request.getRequestDispatcher("editnews.jsp");
		rd.forward(request, response);
		return;
	}
}
