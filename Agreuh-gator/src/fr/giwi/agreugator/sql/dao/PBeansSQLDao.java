package fr.giwi.agreugator.sql.dao;

import javax.servlet.ServletContext;

import net.sourceforge.pbeans.Store;
import net.sourceforge.pbeans.StoreException;
import net.sourceforge.pbeans.servlet.ServletAccess;

public class PBeansSQLDao {
	private static PBeansSQLDao instance;
	private Store store;

	private PBeansSQLDao() {

	}

	public Store getStore() {
		return store;
	}

	public static PBeansSQLDao getInstance() {
		if (instance == null) {
			instance = new PBeansSQLDao();
		}
		return instance;
	}

	public void init(final ServletContext servletContext, final String storeName) throws StoreException {
		store = ServletAccess.getStore(servletContext, storeName, PBeansSQLDao.class.getClassLoader());
	}
}
