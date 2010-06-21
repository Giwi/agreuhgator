package fr.giwi.portlet.agreuhgator.view;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.commons.lang.StringUtils;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;

import fr.giwi.agreuhgatorservice.rssentrymanager.RssEntryManageable;
import fr.giwi.agreuhgatorservice.rssentrymanager.RssEntryManager;
import fr.giwi.portlet.agreuhgator.Constantes;

/**
 * AgreuhGator Portlet Class
 * 
 * @author xavier
 */
public class AgreuhGator extends GenericPortlet {

	@Override
	public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		String url = request.getPreferences().getValue("url", "");
		if (StringUtils.isNotBlank(url)) {
			URL wsdlURL = new URL(url);
			RssEntryManager ss = new RssEntryManager(wsdlURL, Constantes.SERVICE_NAME);
			RssEntryManageable port = ss.getRssEntryManagerPort();
			request.setAttribute("nbOfFeeds", port.getRssEntries().size());
		}
		response.setContentType("text/html");

		PortletRequestDispatcher dispatcher = getPortletContext().getRequestDispatcher("/WEB-INF/jsp/view/AgreuhGator_view.jsp");
		dispatcher.include(request, response);

	}

	@Override
	public void doEdit(RenderRequest request, RenderResponse response) throws PortletException, IOException {

		final String action = request.getParameter("action");
		response.setContentType("text/html");
		request.setAttribute("url", request.getPreferences().getValue("url", ""));
		request.setAttribute("rss", request.getPreferences().getValue("rss", ""));
		request.setAttribute("query", request.getPreferences().getValue("query", ""));
		if ("prefs".equals(action)) {
			SessionMessages.clear(request);
			SessionErrors.clear(request);
			SessionMessages.add(request, this.getPortletConfig().getPortletName() + ".configOk");
		}

		PortletRequestDispatcher dispatcher = getPortletContext().getRequestDispatcher("/WEB-INF/jsp/view/AgreuhGator_edit.jsp");
		dispatcher.include(request, response);

	}

	@Override
	public void processAction(ActionRequest request, ActionResponse response) throws PortletException, IOException {
		final String action = request.getParameter("action");
		if ("prefs".equals(action)) {
			request.setAttribute("action", action);
			final String url = request.getParameter("url");
			final String query = request.getParameter("query");
			final String rss = request.getParameter("rss");
			if (StringUtils.isNotBlank(url)) {
				request.getPreferences().setValue("url", url);
				request.setAttribute("url", url);
				request.getPreferences().setValue("rss", rss);
				request.setAttribute("rss", rss);
				request.getPreferences().setValue("query", query);
				request.setAttribute("query", query);
			}
			request.getPreferences().store();
		}
	}

	@Override
	public void serveResource(final ResourceRequest request, final ResourceResponse response) throws PortletException, IOException {
		String path = "";
		final String resourceID = request.getResourceID();
		if (resourceID.equals("logo")) {
			response.setContentType("image/png");
			path = "/images/logo.png";
		}
		if (resourceID.equals("rss")) {
			response.setContentType("image/png");
			path = "/images/rss2.png";
		}
		if (StringUtils.isNotBlank(path)) {
			response.getPortletOutputStream().write(this.getImage(path));
		}
	}

	/**
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public byte[] getImage(final String path) throws IOException {
		final InputStream is = this.getPortletContext().getResourceAsStream(path);
		final File file = new File(this.getPortletContext().getRealPath(path));
		// Get the size of the file
		final long length = file.length();

		if (length > Integer.MAX_VALUE) {
			// File is too large
		}

		// Create the byte array to hold the data
		final byte[] bytes = new byte[(int) length];

		// Read in the bytes
		int offset = 0;
		int numRead = 0;
		while ((offset < bytes.length) && ((numRead = is.read(bytes, offset, bytes.length - offset)) >= 0)) {
			offset += numRead;
		}

		// Ensure all the bytes have been read in
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file " + file.getName());
		}

		// Close the input stream and return bytes
		is.close();
		return bytes;
	}
}
