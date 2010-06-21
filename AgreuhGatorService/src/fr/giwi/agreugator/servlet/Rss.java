package fr.giwi.agreugator.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
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
	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_FEED_TYPE = "rss_2.0";
	private static final String MIME_TYPE = "application/xml; charset=UTF-8";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String queryString = request.getParameter("query");

		if (queryString == null) {
			throw new ServletException("no query specified");
		}

		try {
			final Directory directory = FSDirectory.open(new File(Constantes.LucenePath));
			final IndexReader reader = IndexReader.open(directory, true);

			Analyzer analyzer = new FrenchAnalyzer();
			// QueryParser parser = new QueryParser(Constantes.ItemDesc,
			// analyzer);
			final QueryParser qp = new MultiFieldQueryParser(Constantes.ALL_FIELDS, analyzer);
			String qsrtr = Base64Helper.decodeString(queryString);
			final Query query = qp.parse(qsrtr);
			int hitsPerPage = 10;
			IndexSearcher searcher = new IndexSearcher(directory, true);
			TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
			searcher.search(query, collector);
			ScoreDoc[] hits = collector.topDocs().scoreDocs;

			// searcher.search(query, null, 30);
			final List<Document> listOfResult = new ArrayList<Document>();
			// for (int i = 0; i < searcher.maxDoc(); i++) {
			for (int i = 0; i < hits.length; ++i) {
				int docId = hits[i].doc;
				listOfResult.add(searcher.doc(docId));
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
	 * @param queryString
	 * @param request
	 * @return
	 * @throws CorruptIndexException
	 * @throws IOException
	 * @throws java.text.ParseException
	 */
	private SyndFeed getFeed(final String defaultFeedType, final List<Document> listOfResult, final String queryString, final HttpServletRequest request) throws CorruptIndexException, IOException,
			java.text.ParseException {

		final SyndFeed feed = new SyndFeedImpl();

		feed.setDescription("AgreuhGator, mots recherchés : " + Base64Helper.decodeString(queryString));
		feed.setLink(request.getRequestURL() + "?" + request.getQueryString());
		feed.setTitle("AgreuhGator : purée de RSS");
		feed.setPublishedDate(new Date());

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
			entry.setAuthor(doc.get(Constantes.Author) + " (" + doc.get(Constantes.Copyright) + ")");
			entries.add(entry);
		}

		feed.setEntries(entries);

		return feed;
	}
}
