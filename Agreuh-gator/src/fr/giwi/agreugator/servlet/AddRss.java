package fr.giwi.agreugator.servlet;

import java.io.IOException;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import fr.giwi.agreugator.dao.SaveFactory;
import fr.giwi.agreugator.helpers.HttPhelper;

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
		final String urlStr = request.getParameter("rssUrl");
		if (StringUtils.isBlank(urlStr)) {
			request.setAttribute("erreoMess", "Url vide");
			final RequestDispatcher rd = request.getRequestDispatcher("addrss.jsp");
			rd.forward(request, response);
			return;

		}
		final DefaultHttpClient httpclient = HttPhelper.getHttpClient();
		final URL url = new URL(urlStr);
		final HttpHost target = new HttpHost(url.getHost(), url.getPort(), url.getProtocol());
		HttpGet req = null;
		if (!StringUtils.isBlank(url.getQuery())) {
			req = new HttpGet(url.getPath() + "?" + url.getQuery());
		} else {
			req = new HttpGet(url.getPath());
		}
		final HttpResponse rsp = httpclient.execute(target, req);
		if (rsp.getStatusLine().getStatusCode() != 200) {
			request.setAttribute("erreoMess", "Url foireuse");
			final RequestDispatcher rd = request.getRequestDispatcher("addrss.jsp");
			rd.forward(request, response);
			return;
		}
		if (SaveFactory.getSaveObject().saveItem(urlStr)) {

			request.setAttribute("okMess", "Url enregistrée");
		} else {
			request.setAttribute("erreoMess", "Url déjà présente");
		}
		final RequestDispatcher rd = request.getRequestDispatcher("addrss.jsp");
		rd.forward(request, response);
		return;
	}

}
