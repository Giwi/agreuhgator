package fr.giwi.agreugator.rss.bean;

import java.io.Serializable;
import java.util.List;

public class RSS implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1088696325116208404L;
	private String title;
	private List<RSSEntry> rssEntries;

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public List<RSSEntry> getRssEntries() {
		return rssEntries;
	}

	public void setRssEntries(final List<RSSEntry> rssEntries) {
		this.rssEntries = rssEntries;
	}

}
