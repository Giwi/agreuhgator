package fr.giwi.agreugator.blog.dao;

import java.sql.SQLException;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import net.sourceforge.pbeans.StoreException;
import fr.giwi.agreugator.blog.bean.BlogUser;

@WebService
public interface BlogUserManageable {

	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 * @throws StoreException
	 */
	public abstract BlogUser getUserFromId(@WebParam(name = "id") final int id) throws StoreException;

	/**
	 * @param login
	 * @return
	 * @throws SQLException
	 * @throws StoreException
	 */
	public abstract BlogUser getUser(@WebParam(name = "login") final String login) throws StoreException;

	/**
	 * @param limit
	 * @return
	 * @throws SQLException
	 * @throws StoreException
	 */
	public abstract List<BlogUser> getUsers(@WebParam(name = "limit") final int limit) throws StoreException;

	/**
	 * @param be
	 * @throws SQLException
	 * @throws StoreException
	 */
	public abstract void addBlogUser(@WebParam(name = "blogUser") final BlogUser bu) throws StoreException;

	/**
	 * @param id
	 * @throws SQLException
	 * @throws StoreException
	 */
	public abstract void deleteBlogUser(@WebParam(name = "id") final int id) throws StoreException;

	/**
	 * @param be
	 * @param id
	 * @throws SQLException
	 * @throws StoreException
	 */
	public abstract void updateBlogUser(@WebParam(name = "blogUser") final BlogUser bu, @WebParam(name = "id") final int id) throws StoreException;

}