package fr.giwi.agreugator.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import fr.giwi.agreugator.blog.bean.BlogUser;
import fr.giwi.agreugator.blog.dao.UserManager;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1031699591306297845L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		final String login = request.getParameter("login");
		final String mdp = request.getParameter("password");
		request.setAttribute("login", login);
		request.setAttribute("password", mdp);
		BlogUser bu = null;
		if (StringUtils.isBlank(login) || StringUtils.isBlank(mdp)) {
			request.setAttribute("erreoMess", "Il vous manque des infos !!!");
			request.setAttribute("error", true);
			final RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
			return;
		}
		final UserManager um = new UserManager();
		try {
			bu = um.getUser(login);
		} catch (final SQLException e) {
			request.setAttribute("erreoMess", "Erreur de connexion à la base");
			final RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
			return;
		}
		if (bu == null) {
			request.setAttribute("erreoMess", "Le login n'existe pas");
			final RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
			return;
		}
		if (!mdp.equals(bu.getPassword())) {
			request.setAttribute("erreoMess", "Le mot de passe n'est pas bon");
			final RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
			return;
		}
		request.getSession().setAttribute("user", bu);
		final RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
		rd.forward(request, response);
		return;

	}

}
