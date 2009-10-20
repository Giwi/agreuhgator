package fr.giwi.agreugator.blog.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import net.sourceforge.pbeans.StoreException;
import net.sourceforge.pbeans.data.ResultsIterator;
import fr.giwi.agreugator.blog.bean.BlogEntry;
import fr.giwi.agreugator.sql.dao.PBeansSQLDao;

@WebService(endpointInterface = "fr.giwi.agreugator.blog.dao.BlogEntryManageable", serviceName = "BlogEntryManager")
public class BlogEntryManager implements BlogEntryManageable {

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.giwi.agreugator.blog.dao.BlogEntryManageable#getEntry(int)
	 */
	public BlogEntry getEntry(final int id) throws StoreException {
		return (BlogEntry) PBeansSQLDao.getInstance().getStore().selectSingle(BlogEntry.class, "id", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.giwi.agreugator.blog.dao.BlogEntryManageable#getBlogEntries(int,
	 * int)
	 */
	@SuppressWarnings("unchecked")
	public List<BlogEntry> getBlogEntries(final int limit, final int type) throws StoreException {
		final List<BlogEntry> blogEntries = new ArrayList<BlogEntry>();
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", type);
		final ResultsIterator<BlogEntry> ri = PBeansSQLDao.getInstance().getStore().select(BlogEntry.class, params);
		try {
			while (ri.hasNext()) {
				blogEntries.add(ri.next());
			}
			if (limit > 0 && limit < blogEntries.size()) {
				return blogEntries.subList(0, limit);
			}
		} finally {
			ri.close();
		}
		return blogEntries;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.giwi.agreugator.blog.dao.BlogEntryManageable#addBlogEntry(fr.giwi.
	 * agreugator.blog.bean.BlogEntry)
	 */
	public void addBlogEntry(final BlogEntry be) throws StoreException {
		PBeansSQLDao.getInstance().getStore().insert(be);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.giwi.agreugator.blog.dao.BlogEntryManageable#deleteBlogEntry(int)
	 */
	public void deleteBlogEntry(final int id) throws StoreException {
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		PBeansSQLDao.getInstance().getStore().delete(BlogEntry.class, params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.giwi.agreugator.blog.dao.BlogEntryManageable#updateBlogEntry(fr.giwi
	 * .agreugator.blog.bean.BlogEntry, int)
	 */
	public void updateBlogEntry(final BlogEntry be, final int id) throws StoreException {
		PBeansSQLDao.getInstance().getStore().save(be);
	}
}
