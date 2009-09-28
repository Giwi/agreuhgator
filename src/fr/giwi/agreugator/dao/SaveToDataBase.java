package fr.giwi.agreugator.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.giwi.agreugator.sql.dao.AbstractSQLDAO;

public class SaveToDataBase implements Saveable {

	@Override
	public List<String> getItems() {
		final List<String> listOfUrl = new ArrayList<String>();
		Connection conn = null;
		String query = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = AbstractSQLDAO.beginTransaction(false);
			query = "SELECT url FROM rss_feeds";
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();

			// mapping du résultat
			while (rs.next()) {
				listOfUrl.add(rs.getString(1));
			}

			AbstractSQLDAO.commit(conn);
			AbstractSQLDAO.release(conn);
		} catch (final Exception e) {
			AbstractSQLDAO.rollback(conn);
			AbstractSQLDAO.release(conn);
			// TODO :
			e.printStackTrace();
		}

		return listOfUrl;
	}

	@Override
	public boolean saveItem(final String item) {
		if (getItems().contains(item)) {
			return false;
		}
		Connection conn = null;
		PreparedStatement stmt = null;
		final String query = "INSERT INTO rss_feeds (url) VALUES (\"" + item + "\")";
		try {
			conn = AbstractSQLDAO.beginTransaction(false);
			stmt = conn.prepareStatement(query);
			stmt.execute();
			AbstractSQLDAO.commit(conn);
			AbstractSQLDAO.release(conn);
		} catch (final Exception e) {
			AbstractSQLDAO.rollback(conn);
			AbstractSQLDAO.release(conn);
			// TODO :
			e.printStackTrace();
		}
		return true;
	}
}
