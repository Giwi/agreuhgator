package fr.giwi.agreugator.helpers;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.StaleReaderException;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermEnum;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;

import fr.giwi.agreugator.constantes.Constantes;
import fr.giwi.agreugator.wrapper.RssDocWrapper;

public class LuceneHelper {

	public static void index(final List<RssDocWrapper> rssContent) {
		try {
			final IndexWriter writer = new IndexWriter(Constantes.LucenePath, new StandardAnalyzer(), true, new IndexWriter.MaxFieldLength(1000000));
			final IndexReader reader = IndexReader.open(Constantes.LucenePath);
			final TermEnum uidIter = reader.terms(new Term(Constantes.UUID, ""));
			for (final RssDocWrapper rssDoc : rssContent) {
				final String uid = rssDoc.getUuId();
				if (uidIter.term() != null && uidIter.term().field() == Constantes.UUID && uidIter.term().text().compareTo(uid) == 0) {
					uidIter.next(); // keep matching docs
				} else { // add new docs
					final Document doc = rssDoc.getDocument();
					writer.addDocument(doc);
				}
				uidIter.close();
			}
			writer.optimize();
			writer.close();

			reader.close();
		} catch (final CorruptIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final LockObtainFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void purgeIndex() throws StaleReaderException, CorruptIndexException, LockObtainFailedException, IOException {
		final Directory directory = FSDirectory.getDirectory(Constantes.LucenePath);
		final IndexReader reader = IndexReader.open(directory, false);
		for (int i = 0; i < reader.maxDoc(); i++) {
			reader.deleteDocument(i);
		}
		reader.close();
		directory.close();

	}
}
