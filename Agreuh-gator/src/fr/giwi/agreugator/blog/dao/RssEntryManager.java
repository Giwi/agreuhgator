package fr.giwi.agreugator.blog.dao;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import fr.giwi.agreugator.helpers.HttPhelper;
import fr.giwi.agreugator.rss.bean.RSSEntry;
import fr.giwi.agreugator.sql.dao.AbstractSQLDAO;

public class RssEntryManager {

	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public RSSEntry getEntry(final int id) throws SQLException {
		RSSEntry be = null;
		final Connection conn = AbstractSQLDAO.beginTransaction(false);
		final String query = "SELECT date, title, description, id, url FROM rss_entries where id = ?";

		final PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, id);
		final ResultSet rs = stmt.executeQuery();

		// mapping du résultat
		while (rs.next()) {
			be = new RSSEntry();
			be.setDate(new Date(rs.getTime(1).getTime()));
			be.setTitle(rs.getString(2));
			be.setDescription(rs.getString(3));
			be.setId(rs.getInt(5));
			be.setUrl(rs.getString(6));
		}
		AbstractSQLDAO.commit(conn);
		AbstractSQLDAO.release(conn);
		return be;
	}

	/**
	 * @param limit
	 * @return
	 * @throws SQLException
	 */
	public List<RSSEntry> getRssEntries(final int limit) throws SQLException {
		final List<RSSEntry> blogEntries = new ArrayList<RSSEntry>();
		final Connection conn = AbstractSQLDAO.beginTransaction(false);
		String query = "SELECT date, title, description, url, id FROM rss_entries";

		query += " order by date desc";
		if (limit > 0) {
			query += " limit " + limit;
		}
		final PreparedStatement stmt = conn.prepareStatement(query);

		final ResultSet rs = stmt.executeQuery();

		// mapping du résultat
		while (rs.next()) {
			final RSSEntry be = new RSSEntry();
			be.setDate(new Date(rs.getTime(1).getTime()));
			be.setTitle(rs.getString(2));
			be.setDescription(rs.getString(3));
			be.setUrl(rs.getString(4));
			be.setId(rs.getInt(5));
			blogEntries.add(be);
		}
		AbstractSQLDAO.commit(conn);
		AbstractSQLDAO.release(conn);
		return blogEntries;
	}

	/**
	 * @param be
	 * @throws SQLException
	 */
	public void addRssEntry(final RSSEntry be) throws SQLException {
		final Connection conn = AbstractSQLDAO.beginTransaction(false);
		final String query = "INSERT into rss_entries (date, title, description, url) Values (NOW(), ?, ?, ?)";
		final PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, be.getTitle());
		stmt.setString(2, be.getDescription());
		stmt.setString(3, be.getUrl());
		stmt.execute();
		AbstractSQLDAO.commit(conn);
		AbstractSQLDAO.release(conn);
	}

	/**
	 * @param id
	 * @throws SQLException
	 */
	public void deleteRssEntry(final int id) throws SQLException {
		final Connection conn = AbstractSQLDAO.beginTransaction(false);
		final String query = "Delete from rss_entries where id = ?";
		final PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, id);
		stmt.execute();
		AbstractSQLDAO.commit(conn);
		AbstractSQLDAO.release(conn);
	}

	/**
	 * @param be
	 * @param id
	 * @throws SQLException
	 */
	public void updateRssEntry(final RSSEntry be, final int id) throws SQLException {
		final Connection conn = AbstractSQLDAO.beginTransaction(false);
		final String query = "update  rss_entries set date = NOW(), title = ?, description = ?, url = ? where id= ?";
		final PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, be.getTitle());
		stmt.setString(2, be.getDescription());
		stmt.setString(3, be.getUrl());
		stmt.setInt(5, id);
		stmt.execute();
		AbstractSQLDAO.commit(conn);
		AbstractSQLDAO.release(conn);
	}

	/**
	 * @param url
	 * @return
	 * @throws SQLException
	 */
	public boolean isExistRssFeed(final String url) throws SQLException {
		final Connection conn = AbstractSQLDAO.beginTransaction(false);
		final String query = "SELECT id from rss_entries where url = ?";
		final PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, url);
		final ResultSet rs = stmt.executeQuery();
		if (rs.getFetchSize() > 0) {
			return true;
		}
		return false;
	}

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
