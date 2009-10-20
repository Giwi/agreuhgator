package fr.giwi.agreugator.rss.dao;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import net.sourceforge.pbeans.StoreException;
import net.sourceforge.pbeans.data.ResultsIterator;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import fr.giwi.agreugator.helpers.HttPhelper;
import fr.giwi.agreugator.rss.bean.RSSEntry;
import fr.giwi.agreugator.sql.dao.PBeansSQLDao;

@WebService(endpointInterface = "fr.giwi.agreugator.rss.dao.RssEntryManageable", serviceName = "RssEntryManager")
public class RssEntryManager implements RssEntryManageable {

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.giwi.agreugator.rss.dao.RssEntryManageable#getEntry(int)
	 */
	public RSSEntry getEntry(final int id) throws StoreException {
		return (RSSEntry) PBeansSQLDao.getInstance().getStore().selectSingle(RSSEntry.class, "id", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.giwi.agreugator.rss.dao.RssEntryManageable#getRssEntries(int)
	 */
	@SuppressWarnings("unchecked")
	public List<RSSEntry> getRssEntries(final int limit) throws StoreException {
		final List<RSSEntry> rssEntries = new ArrayList<RSSEntry>();
		final ResultsIterator<RSSEntry> ri = PBeansSQLDao.getInstance().getStore().select(RSSEntry.class);
		try {
			while (ri.hasNext()) {
				rssEntries.add(ri.next());
			}
			if (limit > 0) {
				return rssEntries.subList(0, limit);
			}
		} finally {
			ri.close();
		}
		return rssEntries;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.giwi.agreugator.rss.dao.RssEntryManageable#addRssEntry(fr.giwi.agreugator
	 * .rss.bean.RSSEntry)
	 */
	public void addRssEntry(final RSSEntry be) throws StoreException {
		PBeansSQLDao.getInstance().getStore().insert(be);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.giwi.agreugator.rss.dao.RssEntryManageable#deleteRssEntry(int)
	 */
	public void deleteRssEntry(final int id) throws StoreException {
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		PBeansSQLDao.getInstance().getStore().delete(RSSEntry.class, params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.giwi.agreugator.rss.dao.RssEntryManageable#updateRssEntry(fr.giwi.
	 * agreugator.rss.bean.RSSEntry, int)
	 */
	public void updateRssEntry(final RSSEntry be, final int id) throws StoreException {
		PBeansSQLDao.getInstance().getStore().save(be);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.giwi.agreugator.rss.dao.RssEntryManageable#isExistRssFeed(java.lang
	 * .String)
	 */
	public boolean isExistRssFeed(final String url) throws StoreException {
		return PBeansSQLDao.getInstance().getStore().selectSingle(RSSEntry.class, "url", url) != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.giwi.agreugator.rss.dao.RssEntryManageable#isValidFeed(java.lang.String
	 * )
	 */
	public boolean isValidFeed(final String urlStr) throws ClientProtocolException, IOException {
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

		if (rsp.getStatusLine().getStatusCode() != 200 || !rsp.getHeaders("Content-Type")[0].getValue().startsWith("text/xml")) {
			return false;
		}
		return true;
	}
}
