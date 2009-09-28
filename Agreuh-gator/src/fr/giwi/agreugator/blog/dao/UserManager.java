package fr.giwi.agreugator.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.giwi.agreugator.blog.bean.BlogUser;
import fr.giwi.agreugator.sql.dao.AbstractSQLDAO;

public class UserManager {

	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public BlogUser getUser(final int id) throws SQLException {
		BlogUser bu = null;
		final Connection conn = AbstractSQLDAO.beginTransaction(false);
		final String query = "SELECT id, login, password FROM blog_users where id = ?";
		final PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, id);
		final ResultSet rs = stmt.executeQuery();

		// mapping du résultat
		while (rs.next()) {
			bu = new BlogUser();
			bu.setId(rs.getInt(1));
			bu.setLogin(rs.getString(2));
			bu.setPassword(rs.getString(3));
		}
		AbstractSQLDAO.commit(conn);
		AbstractSQLDAO.release(conn);
		return bu;
	}

	/**
	 * @param login
	 * @return
	 * @throws SQLException
	 */
	public BlogUser getUser(final String login) throws SQLException {
		BlogUser bu = null;
		final Connection conn = AbstractSQLDAO.beginTransaction(false);
		final String query = "SELECT id, login, password FROM blog_users where login = ?";
		final PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, login);
		final ResultSet rs = stmt.executeQuery();

		// mapping du résultat
		while (rs.next()) {
			bu = new BlogUser();
			bu.setId(rs.getInt(1));
			bu.setLogin(rs.getString(2));
			bu.setPassword(rs.getString(3));
		}
		AbstractSQLDAO.commit(conn);
		AbstractSQLDAO.release(conn);
		return bu;
	}

	/**
	 * @param limit
	 * @return
	 * @throws SQLException
	 */
	public List<BlogUser> getUsers(final int limit) throws SQLException {
		final List<BlogUser> blogUsers = new ArrayList<BlogUser>();
		final Connection conn = AbstractSQLDAO.beginTransaction(false);
		String query = "SELECT id, login FROM blog_users";
		if (limit > 0) {
			query += " limit " + limit;
		}
		final PreparedStatement stmt = conn.prepareStatement(query);

		final ResultSet rs = stmt.executeQuery();

		// mapping du résultat
		while (rs.next()) {
			final BlogUser bu = new BlogUser();
			bu.setId(rs.getInt(1));
			bu.setLogin(rs.getString(2));
			blogUsers.add(bu);
		}
		AbstractSQLDAO.commit(conn);
		AbstractSQLDAO.release(conn);
		return blogUsers;
	}

	/**
	 * @param be
	 * @throws SQLException
	 */
	public void addBlogUser(final BlogUser be) throws SQLException {
		final Connection conn = AbstractSQLDAO.beginTransaction(false);
		final String query = "INSERT into blog_users set (login = ?, password = ?)";
		final PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, be.getLogin());
		stmt.setString(2, be.getPassword());
		stmt.execute();
		AbstractSQLDAO.commit(conn);
		AbstractSQLDAO.release(conn);
	}

	/**
	 * @param id
	 * @throws SQLException
	 */
	public void deleteBlogUser(final int id) throws SQLException {
		final Connection conn = AbstractSQLDAO.beginTransaction(false);
		final String query = "Delete from blog_users where id = ?";
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
	public void updateBlogUser(final BlogUser be, final int id) throws SQLException {
		final Connection conn = AbstractSQLDAO.beginTransaction(false);
		final String query = "update  blog_users set (login = ?, password= ?) where id= ?";
		final PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, be.getLogin());
		stmt.setString(2, be.getPassword());
		stmt.setInt(3, id);
		stmt.execute();
		AbstractSQLDAO.commit(conn);
		AbstractSQLDAO.release(conn);
	}
}
