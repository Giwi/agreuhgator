package fr.giwi.agreugator.rss.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import net.sourceforge.pbeans.StoreException;

import org.apache.http.client.ClientProtocolException;

import fr.giwi.agreugator.rss.bean.RSSEntry;

@WebService
public interface RssEntryManageable {

	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 * @throws StoreException
	 */
	public abstract RSSEntry getEntry(@WebParam(name = "id") final int id) throws StoreException;

	/**
	 * @param limit
	 * @return
	 * @throws SQLException
	 * @throws StoreException
	 */
	public abstract List<RSSEntry> getRssEntries(@WebParam(name = "limit") final int limit) throws StoreException;

	/**
	 * @param be
	 * @throws SQLException
	 * @throws StoreException
	 */
	public abstract void addRssEntry(@WebParam(name = "rssEntry") final RSSEntry be) throws StoreException;

	/**
	 * @param id
	 * @throws SQLException
	 * @throws StoreException
	 */
	public abstract void deleteRssEntry(@WebParam(name = "id") final int id) throws StoreException;

	/**
	 * @param be
	 * @param id
	 * @throws SQLException
	 * @throws StoreException
	 */
	public abstract void updateRssEntry(@WebParam(name = "rssEntry") final RSSEntry be, @WebParam(name = "id") final int id) throws StoreException;

	/**
	 * @param url
	 * @return
	 * @throws SQLException
	 * @throws StoreException
	 */
	public abstract boolean isExistRssFeed(@WebParam(name = "url") final String url) throws StoreException;

	/**
	 * @param urlStr
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public abstract boolean isValidFeed(@WebParam(name = "url") final String urlStr) throws ClientProtocolException, IOException;

}