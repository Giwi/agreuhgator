package fr.giwi.agreugator.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;

import fr.giwi.agreugator.constantes.Constantes;

/**
 * Servlet implementation class Search
 */
public class Search extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2925417670756405709L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		final String indexName = Constantes.LucenePath;
		IndexSearcher searcher = null;
		Query query = null;
		Hits hits = null;
		int startindex = 0;
		int maxpage = 50;
		String queryString = null;
		final String startVal = null;
		String maxresults = null;
		final int thispage = 0;

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
		maxresults = request.getParameter("maxresults");
		try {
			maxpage = Integer.parseInt(maxresults);
			startindex = Integer.parseInt(startVal);
		} catch (final Exception e) {
		}

		if (queryString == null) {
			throw new ServletException("no query specified");
		}
		final Analyzer analyzer = new StandardAnalyzer();
		try {
			final QueryParser qp = new MultiFieldQueryParser(Constantes.ALL_FIELDS, analyzer);
			query = qp.parse(queryString);
		} catch (final ParseException e) {
			request.setAttribute("ErrorMess", e.getMessage());
			final RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
			return;
		}

		hits = searcher.search(query); // run the query

		request.setAttribute("Hits", hits);
		request.setAttribute("queryString", queryString);
		final RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
		rd.forward(request, response);
		return;
	}

}
