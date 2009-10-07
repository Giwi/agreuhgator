package fr.giwi.agreugator.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hit;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedOutput;

import fr.giwi.agreugator.constantes.Constantes;
import fr.giwi.agreugator.helpers.Base64Helper;

/**
 * Servlet implementation class Rss
 */
public class Rss extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9158044188360033433L;
	private static final String DEFAULT_FEED_TYPE = "rss_2.0";
	private static final String MIME_TYPE = "application/xml; charset=UTF-8";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		final String indexName = Constantes.LucenePath;
		IndexSearcher searcher = null;
		Query query = null;
		Hits hits = null;
		String queryString = null;

		try {
			// TODO : Créer un Singleton
			searcher = new IndexSearcher(indexName); // create an indexSearcher
			// for our page
			// NOTE: this operation is slow for large
			// indices (much slower than the search itself)
			// so you might want to keep an IndexSearcher
			// open

		} catch (final Exception e) {
			request.setAttribute("ErrorMess", e.getMessage());
			final RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
			return;
		}

		queryString = request.getParameter("query");
		if (queryString == null) {
			throw new ServletException("no query specified");
		}
		final Analyzer analyzer = new StandardAnalyzer();
		try {
			final QueryParser qp = new MultiFieldQueryParser(Constantes.ALL_FIELDS, analyzer);
			query = qp.parse(Base64Helper.decodeString(queryString));
		} catch (final ParseException e) {
			request.setAttribute("ErrorMess", e.getMessage());
			final RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
			return;
		}
		hits = searcher.search(query); // run the query
		final Iterator<Hit> hitIt = hits.iterator();
		SyndFeed feed = null;
		try {
			feed = getFeed(DEFAULT_FEED_TYPE, hitIt, queryString, request);
		} catch (final java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	/**
	 * @param defaultFeedType
	 * @param hitIt
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws CorruptIndexException
	 * @throws java.text.ParseException
	 */
	private SyndFeed getFeed(final String defaultFeedType, final Iterator<Hit> hitIt, final String queryString, final HttpServletRequest request) throws CorruptIndexException, IOException, java.text.ParseException {
		final SyndFeed feed = new SyndFeedImpl();

		feed.setTitle("Agreuh Gator : " + Base64Helper.decodeString(queryString));
		feed.setLink(request.getRequestURL() + "?" + request.getQueryString());
		feed.setDescription("Agreuh Gator : purée de RSS");

		final List<SyndEntry> entries = new ArrayList<SyndEntry>();

		while (hitIt.hasNext()) {
			final Hit hit = hitIt.next();
			SyndEntry entry;
			SyndContent description;

			entry = new SyndEntryImpl();
			entry.setTitle(hit.get(Constantes.ItemTitle));
			entry.setLink(hit.get(Constantes.ItemLink));
			entry.setPublishedDate(Constantes.simpleDateFormat.parse(hit.get(Constantes.PubDate)));
			description = new SyndContentImpl();
			description.setType("text/html");
			description.setValue(hit.get(Constantes.ItemDescHTML));
			entry.setDescription(description);
			entries.add(entry);
		}

		feed.setEntries(entries);

		return feed;
	}

}
