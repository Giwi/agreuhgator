package fr.giwi.agreugator.blog.bean;

import java.util.List;

public class Blog {

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
