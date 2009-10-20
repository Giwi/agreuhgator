package fr.giwi.agreugator.blog.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import net.sourceforge.pbeans.StoreException;
import net.sourceforge.pbeans.data.ResultsIterator;
import fr.giwi.agreugator.blog.bean.BlogUser;
import fr.giwi.agreugator.sql.dao.PBeansSQLDao;

@WebService(endpointInterface = "fr.giwi.agreugator.blog.dao.BlogUserManageable", serviceName = "BlogUserManager")
public class UserManager implements BlogUserManageable {

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.giwi.agreugator.blog.dao.BlogUserManageable#getUser(int)
	 */
	public BlogUser getUserFromId(final int id) throws StoreException {
		return (BlogUser) PBeansSQLDao.getInstance().getStore().selectSingle(BlogUser.class, "id", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.giwi.agreugator.blog.dao.BlogUserManageable#getUser(java.lang.String)
	 */
	public BlogUser getUser(final String login) throws StoreException {
		return (BlogUser) PBeansSQLDao.getInstance().getStore().selectSingle(BlogUser.class, "login", login);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.giwi.agreugator.blog.dao.BlogUserManageable#getUsers(int)
	 */
	@SuppressWarnings("unchecked")
	public List<BlogUser> getUsers(final int limit) throws StoreException {
		final List<BlogUser> blogUsers = new ArrayList<BlogUser>();
		final ResultsIterator<BlogUser> ri = PBeansSQLDao.getInstance().getStore().select(BlogUser.class);
		try {
			while (ri.hasNext()) {
				blogUsers.add(ri.next());
			}
			if (limit > 0 && limit < blogUsers.size()) {
				return blogUsers.subList(0, limit);
			}
		} finally {
			ri.close();
		}
		return blogUsers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.giwi.agreugator.blog.dao.BlogUserManageable#addBlogUser(fr.giwi.agreugator
	 * .blog.bean.BlogUser)
	 */
	public void addBlogUser(final BlogUser bu) throws StoreException {
		PBeansSQLDao.getInstance().getStore().insert(bu);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.giwi.agreugator.blog.dao.BlogUserManageable#deleteBlogUser(int)
	 */
	public void deleteBlogUser(final int id) throws StoreException {
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		PBeansSQLDao.getInstance().getStore().delete(BlogUser.class, params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.giwi.agreugator.blog.dao.BlogUserManageable#updateBlogUser(fr.giwi
	 * .agreugator.blog.bean.BlogUser, int)
	 */
	public void updateBlogUser(final BlogUser bu, final int id) throws StoreException {
		PBeansSQLDao.getInstance().getStore().save(bu);
	}
}
