package fr.giwi.agreugator.blog.dao;

import java.sql.SQLException;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import net.sourceforge.pbeans.StoreException;
import fr.giwi.agreugator.blog.bean.BlogEntry;

@WebService
public interface BlogEntryManageable {

	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 * @throws StoreException
	 */
	public abstract BlogEntry getEntry(@WebParam(name = "id") final int id) throws StoreException;

	/**
	 * @param limit
	 * @return
	 * @throws SQLException
	 * @throws StoreException
	 */
	public abstract List<BlogEntry> getBlogEntries(@WebParam(name = "limit") final int limit, @WebParam(name = "type") final int type) throws StoreException;

	/**
	 * @param be
	 * @throws SQLException
	 * @throws StoreException
	 */
	public abstract void addBlogEntry(@WebParam(name = "blogEntry") final BlogEntry be) throws StoreException;

	/**
	 * @param id
	 * @throws SQLException
	 * @throws StoreException
	 */
	public abstract void deleteBlogEntry(@WebParam(name = "id") final int id) throws StoreException;

	/**
	 * @param be
	 * @param id
	 * @throws SQLException
	 * @throws StoreException
	 */
	public abstract void updateBlogEntry(@WebParam(name = "blogEntry") final BlogEntry be, @WebParam(name = "id") final int id) throws StoreException;

}