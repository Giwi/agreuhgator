package fr.giwi.agreugator.helpers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import fr.giwi.agreugator.constantes.Constantes;
import fr.giwi.agreugator.entities.RSSEntry;
import fr.giwi.agreugator.rss.dao.RssEntryManager;
import fr.giwi.agreugator.wrapper.RssDocWrapper;

public class RSSHelper {

	/**
	 * @return
	 * @throws IOException
	 * @throws FeedException
	 * @throws IllegalArgumentException
	 */
	public static List<RssDocWrapper> getRSSContent() throws IllegalArgumentException, FeedException, IOException {
		final List<RssDocWrapper> listOfDocs = new ArrayList<RssDocWrapper>();
		for (final RSSEntry rssFeed : new RssEntryManager().getRssEntries()) {
			listOfDocs.addAll(getListOfItems(rssFeed));
		}
		return listOfDocs;
	}

	/**
	 * @param urlStr
	 * @return
	 * @throws IOException
	 * @throws FeedException
	 * @throws IllegalArgumentException
	 */
	public static RSSEntry getRssEntryObj(final String urlStr) throws IllegalArgumentException, FeedException, IOException {
		final RSSEntry re = new RSSEntry();
		final URL feedUrl = new URL(urlStr);
		final SyndFeedInput input = new SyndFeedInput();
		final SyndFeed rss = input.build(new XmlReader(feedUrl));
		re.setPubDate(new Date());
		re.setDescription(rss.getDescription() == null ? "" : rss.getDescription());
		re.setTitle(rss.getTitle() == null ? urlStr : rss.getTitle());
		re.setUrl(urlStr);
		return re;
	}

	/**
	 * @param rssFeed
	 * @return
	 * @throws IOException
	 * @throws FeedException
	 * @throws IllegalArgumentException
	 */
	@SuppressWarnings("unchecked")
	private static List<RssDocWrapper> getListOfItems(final RSSEntry rssFeed) throws IllegalArgumentException, FeedException, IOException {
		final List<RssDocWrapper> listOfDocs = new ArrayList<RssDocWrapper>();

		final URL feedUrl = new URL(rssFeed.getUrl());
		final SyndFeedInput input = new SyndFeedInput();
		final SyndFeed rss = input.build(new XmlReader(feedUrl));

		for (final SyndEntry item : (List<SyndEntry>) rss.getEntries()) {
			final Document doc = new Document();
			final RssDocWrapper rssDoc = new RssDocWrapper();
			if (rss.getCopyright() != null) {
				doc.add(new Field(Constantes.Copyright, rss.getCopyright(), Field.Store.YES, Field.Index.ANALYZED));
			}
			doc.add(new Field(Constantes.Title, rss.getTitle(), Field.Store.YES, Field.Index.ANALYZED));

			doc.add(new Field(Constantes.Description, StringHelper.stripHtml(rss.getDescription()), Field.Store.YES, Field.Index.ANALYZED));
			doc.add(new Field(Constantes.Link, rss.getLink(), Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
			if (item.getPublishedDate() != null) {
				doc.add(new Field(Constantes.PubDate, Constantes.simpleDateFormat.format(item.getPublishedDate()), Field.Store.YES, Field.Index.NOT_ANALYZED));
			} else if (rss.getPublishedDate() != null) {
				doc.add(new Field(Constantes.PubDate, Constantes.simpleDateFormat.format(rss.getPublishedDate()), Field.Store.YES, Field.Index.NOT_ANALYZED));
			} else {
				doc.add(new Field(Constantes.PubDate, Constantes.simpleDateFormat.format(new Date()), Field.Store.YES, Field.Index.NOT_ANALYZED));
			}
			if (item.getAuthor() != null) {
				doc.add(new Field(Constantes.Author, item.getAuthor(), Field.Store.YES, Field.Index.ANALYZED));
			}
			if (item.getDescription() != null) {
				doc.add(new Field(Constantes.ItemDesc, StringHelper.stripHtml(item.getDescription().getValue()), Field.Store.YES, Field.Index.ANALYZED));
				doc.add(new Field(Constantes.ItemDescHTML, item.getDescription().getValue(), Field.Store.YES, Field.Index.NOT_ANALYZED));
			}
			doc.add(new Field(Constantes.ItemLink, item.getLink(), Field.Store.YES, Field.Index.NOT_ANALYZED));
			doc.add(new Field(Constantes.UUID, Base64Helper.encodeString(item.getLink()), Field.Store.YES, Field.Index.NOT_ANALYZED));
			doc.add(new Field(Constantes.ItemTitle, item.getTitle(), Field.Store.YES, Field.Index.ANALYZED));
			rssDoc.setDocument(doc);
			rssDoc.setUuId(Base64Helper.encodeString(item.getLink()));
			listOfDocs.add(rssDoc);
		}

		return listOfDocs;
	}
}
