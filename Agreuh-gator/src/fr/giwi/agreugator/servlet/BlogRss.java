package fr.giwi.agreugator.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.pbeans.StoreException;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedOutput;

import fr.giwi.agreugator.blog.bean.BlogEntry;
import fr.giwi.agreugator.blog.dao.BlogEntryManageable;
import fr.giwi.agreugator.blog.dao.BlogEntryManager;
import fr.giwi.agreugator.constantes.Constantes;

/**
 * Servlet implementation class BlogRss
 */
public class BlogRss extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8246545175291753435L;
	private static final String DEFAULT_FEED_TYPE = "rss_2.0";
	private static final String MIME_TYPE = "application/xml; charset=UTF-8";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		SyndFeed feed = null;
		try {
			feed = getFeed(DEFAULT_FEED_TYPE, request);
		} catch (final StoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		feed.setFeedType(DEFAULT_FEED_TYPE);
		response.setContentType(MIME_TYPE);
		final SyndFeedOutput output = new SyndFeedOutput();
		try {
			output.output(feed, response.getWriter());
		} catch (final FeedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private SyndFeed getFeed(final String defaultFeedType, final HttpServletRequest request) throws StoreException {
		final SyndFeed feed = new SyndFeedImpl();

		feed.setTitle("Agreuh Gator : Les niouzes");
		feed.setLink(request.getRequestURL() + "?" + request.getQueryString());
		feed.setDescription("Agreuh Gator : purée de RSS");
		final List<SyndEntry> entries = new ArrayList<SyndEntry>();
		final BlogEntryManageable bem = new BlogEntryManager();
		final List<BlogEntry> list = bem.getBlogEntries(5, Constantes.TYPE_BLOG);
		for (final BlogEntry be : list) {

			SyndEntry entry;
			SyndContent description;

			entry = new SyndEntryImpl();
			entry.setTitle(be.getTitle());
			entry.setLink(request.getContextPath() + "/showNews?id=" + be.getId());
			entry.setPublishedDate(be.getDate());
			description = new SyndContentImpl();
			description.setType("text/html");
			description.setValue(be.getResume());
			entry.setDescription(description);
			entries.add(entry);
		}
		feed.setEntries(entries);
		return feed;
	}

}
