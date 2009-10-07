package fr.giwi.agreugator.blog.bean;

import java.io.Serializable;
import java.util.List;

public class Blog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1016493522058329183L;
	private String title;
	private List<BlogEntry> blogEntries;

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public List<BlogEntry> getBlogEntries() {
		return blogEntries;
	}

	public void setBlogEntries(final List<BlogEntry> blogEntries) {
		this.blogEntries = blogEntries;
	}

}
