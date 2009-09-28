/**
 * Copyright : GICM - 9 octobre 2006
 */
package fr.giwi.agreugator.sql.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import fr.giwi.agreugator.constantes.Constantes;

/**
 * The Class AbstractSQLDAO.
 * 
 * @author Capgemini
 */
public class AbstractSQLDAO {

	/** Log. */
	private static final Logger log = Logger.getLogger(AbstractSQLDAO.class.getName());

	/** The factory. */
	private static XmlBeanFactory factory;

	static {
		try {
			factory = new XmlBeanFactory(new ClassPathResource(Constantes.CONF_BDD));
		} catch (final Exception e) {
			log.fatal("Une erreur s'est produite lors de la fabrication du pool de connection à la base de données : " + e.getMessage(), e);
		}
	}

	/**
	 * Begin transaction.
	 * 
	 * @param autocommit
	 *            the autocommit
	 * @return the connection
	 * 
	 */
	public static Connection beginTransaction(final boolean autocommit) {
		try {
			final DataSource ds = (DataSource) factory.getBean("dataSourceWebStore");
			final Connection conn = ds.getConnection();
			conn.setAutoCommit(autocommit);
			return conn;
		} catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Close.
	 * 
	 * @param rs
	 *            the rs
	 */
	public static void close(final ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (final SQLException e) {
				log.error("Erreur fermeture resultset : " + e.getMessage(), e);
			}
		}
	}

	/**
	 * Close.
	 * 
	 * @param stmt
	 *            the stmt
	 */
	public static void close(final Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (final SQLException e) {
				log.error("Erreur fermeture statement : " + e.getMessage(), e);
			}
		}
	}

	/**
	 * Commit.
	 * 
	 * @param conn
	 *            the conn
	 * 
	 * @throws SQLException
	 *             the SQL exception
	 */
	public static void commit(final Connection conn) throws SQLException {
		if (conn != null) {
			conn.commit();
		}
	}

	/**
	 * Release.
	 * 
	 * @param conn
	 *            the conn
	 */
	public static void release(final Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (final SQLException e) {
				log.error("Erreur fermeture connexion : " + e.getMessage(), e);
			}
		}
	}

	/**
	 * Rollback.
	 * 
	 * @param conn
	 *            the conn
	 */
	public static void rollback(final Connection conn) {
		if (conn != null) {
			try {
				conn.rollback();
			} catch (final SQLException e) {
				log.error("Erreur RollBack : " + e.getMessage(), e);
			}
		}
	}
}
