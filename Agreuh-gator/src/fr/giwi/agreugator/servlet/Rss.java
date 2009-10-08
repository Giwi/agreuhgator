package fr.giwi.agreugator.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.fr.FrenchAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

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
		final String queryString = request.getParameter("query");

		if (queryString == null) {
			throw new ServletException("no query specified");
		}

		try {
			final Analyzer analyzer = new FrenchAnalyzer();
			final Directory directory = FSDirectory.getDirectory(new File(Constantes.LucenePath));
			final IndexReader reader = IndexReader.open(directory, true);

			final Searcher searcher = new IndexSearcher(reader);
			final QueryParser qp = new MultiFieldQueryParser(Constantes.ALL_FIELDS, analyzer);
			final Query query = qp.parse(Base64Helper.decodeString(queryString));

			searcher.search(query, null, 30);
			final List<Document> listOfResult = new ArrayList<Document>();
			for (int i = 0; i < searcher.maxDoc(); i++) {
				listOfResult.add(searcher.doc(i));
			}
			SyndFeed feed = null;
			try {
				feed = getFeed(DEFAULT_FEED_TYPE, listOfResult, queryString, request);
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
		} catch (final Exception e) {
			request.setAttribute("ErrorMess", e.getMessage());
			final RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
			return;
		}

	}

	/**
	 * @param defaultFeedType
	 * @param listOfResult
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws CorruptIndexException
	 * @throws java.text.ParseException
	 */
	private SyndFeed getFeed(final String defaultFeedType, final List<Document> listOfResult, final String queryString, final HttpServletRequest request) throws CorruptIndexException, IOException, java.text.ParseException {
		final SyndFeed feed = new SyndFeedImpl();

		feed.setTitle("Agreuh Gator : " + Base64Helper.decodeString(queryString));
		feed.setLink(request.getRequestURL() + "?" + request.getQueryString());
		feed.setDescription("Agreuh Gator : purée de RSS");

		final List<SyndEntry> entries = new ArrayList<SyndEntry>();

		for (final Document doc : listOfResult) {
			SyndEntry entry;
			SyndContent description;

			entry = new SyndEntryImpl();
			entry.setTitle(doc.get(Constantes.ItemTitle));
			entry.setLink(doc.get(Constantes.ItemLink));
			entry.setPublishedDate(Constantes.simpleDateFormat.parse(doc.get(Constantes.PubDate)));
			description = new SyndContentImpl();
			description.setType("text/html");
			description.setValue(doc.get(Constantes.ItemDescHTML));
			entry.setDescription(description);
			entries.add(entry);
		}

		feed.setEntries(entries);

		return feed;
	}

}
