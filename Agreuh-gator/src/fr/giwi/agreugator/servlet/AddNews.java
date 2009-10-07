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
 * Servlet implementation class AddNews
 */
public class AddNews extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7898736012802229218L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		final String title = request.getParameter("title");
		final String resume = request.getParameter("resume");
		final String content = request.getParameter("content");
		final String type = request.getParameter("type");
		request.setAttribute("title", title);
		request.setAttribute("resume", resume);
		request.setAttribute("content", content);
		request.setAttribute("type", type);
		if (StringUtils.isBlank(title) || StringUtils.isBlank(content)) {
			request.setAttribute("erreoMess", "Il vous manque des infos !!!");
			request.setAttribute("error", true);
			final RequestDispatcher rd = request.getRequestDispatcher("addnews.jsp");
			rd.forward(request, response);
			return;
		}
		final BlogEntryManager bem = new BlogEntryManager();
		final BlogEntry be = new BlogEntry();
		be.setTitle(title);
		be.setResume(resume == null ? "" : resume);
		be.setDate(new Date());
		be.setContent(content);
		be.setType(Integer.parseInt(type));
		try {
			bem.addBlogEntry(be);
			request.setAttribute("okMess", "Niouze enregistrée : " + be.getTitle());
		} catch (final SQLException e) {
			request.setAttribute("erreoMess", "Erreur de connexion à la base");
			final RequestDispatcher rd = request.getRequestDispatcher("addnews.jsp");
			rd.forward(request, response);
			return;
		}
		final RequestDispatcher rd = request.getRequestDispatcher("addnews.jsp");
		rd.forward(request, response);
		return;
	}
}
