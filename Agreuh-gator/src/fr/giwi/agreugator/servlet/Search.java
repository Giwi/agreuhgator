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
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

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
			final Query query = qp.parse(queryString);

			searcher.search(query, null, 30);
			final List<Document> listOfResult = new ArrayList<Document>();
			for (int i = 0; i < searcher.maxDoc(); i++) {
				listOfResult.add(searcher.doc(i));
			}
			request.setAttribute("Hits", listOfResult);
			request.setAttribute("queryString", queryString);
			final RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
			rd.forward(request, response);
			return;
		} catch (final Exception e) {
			request.setAttribute("ErrorMess", e.getMessage());
			final RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
			return;
		}

	}
}
