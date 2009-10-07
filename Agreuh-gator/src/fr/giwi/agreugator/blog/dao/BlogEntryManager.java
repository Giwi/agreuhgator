package fr.giwi.agreugator.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.giwi.agreugator.blog.bean.BlogEntry;
import fr.giwi.agreugator.sql.dao.AbstractSQLDAO;

public class BlogEntryManager {

	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public BlogEntry getEntry(final int id) throws SQLException {
		BlogEntry be = null;
		final Connection conn = AbstractSQLDAO.beginTransaction(false);
		final String query = "SELECT date, title, resume, content, id, type FROM blog_entries where id = ?";

		final PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, id);
		final ResultSet rs = stmt.executeQuery();

		// mapping du résultat
		while (rs.next()) {
			be = new BlogEntry();
			be.setDate(rs.getDate(1));
			be.setTitle(rs.getString(2));
			be.setResume(rs.getString(3));
			be.setContent(rs.getString(4));
			be.setId(rs.getInt(5));
			be.setType(rs.getInt(6));
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
	public List<BlogEntry> getBlogEntries(final int limit, final int type) throws SQLException {
		final List<BlogEntry> blogEntries = new ArrayList<BlogEntry>();
		final Connection conn = AbstractSQLDAO.beginTransaction(false);
		String query = "SELECT date, title, resume, content, id, type FROM blog_entries";
		if (type > 0) {
			query += " WHERE type= " + type;
		}
		query += " order by date desc";
		if (limit > 0) {
			query += " limit " + limit;
		}
		final PreparedStatement stmt = conn.prepareStatement(query);

		final ResultSet rs = stmt.executeQuery();

		// mapping du résultat
		while (rs.next()) {
			final BlogEntry be = new BlogEntry();
			be.setDate(rs.getDate(1));
			be.setTitle(rs.getString(2));
			be.setResume(rs.getString(3));
			be.setContent(rs.getString(4));
			be.setId(rs.getInt(5));
			be.setType(rs.getInt(6));
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
	public void addBlogEntry(final BlogEntry be) throws SQLException {
		final Connection conn = AbstractSQLDAO.beginTransaction(false);
		final String query = "INSERT into blog_entries (date, title, resume, content, type) Values (NOW(), ?, ?, ?, ?)";
		final PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, be.getTitle());
		stmt.setString(2, be.getResume());
		stmt.setString(3, be.getContent());
		stmt.setInt(4, be.getType());
		stmt.execute();
		AbstractSQLDAO.commit(conn);
		AbstractSQLDAO.release(conn);
	}

	/**
	 * @param id
	 * @throws SQLException
	 */
	public void deleteBlogEntry(final int id) throws SQLException {
		final Connection conn = AbstractSQLDAO.beginTransaction(false);
		final String query = "Delete from blog_entries where id = ?";
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
	public void updateBlogEntry(final BlogEntry be, final int id) throws SQLException {
		final Connection conn = AbstractSQLDAO.beginTransaction(false);
		final String query = "update  blog_entries set date = NOW(), title = ?, resume = ?, content = ?, type = ? where id= ?";
		final PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, be.getTitle());
		stmt.setString(2, be.getResume());
		stmt.setString(3, be.getContent());
		stmt.setInt(5, id);
		stmt.setInt(4, be.getType());
		stmt.execute();
		AbstractSQLDAO.commit(conn);
		AbstractSQLDAO.release(conn);
	}
}
