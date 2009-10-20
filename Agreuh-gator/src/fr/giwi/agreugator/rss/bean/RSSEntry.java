package fr.giwi.agreugator.rss.bean;

import java.io.Serializable;
import java.util.Date;

import net.sourceforge.pbeans.annotations.PersistentClass;
import net.sourceforge.pbeans.annotations.PropertyIndex;

@PersistentClass(table = "RSSEntry", indexes = @PropertyIndex(unique = true, propertyNames = "id"), autoIncrement = true, idField = "InternalUID", deleteFields = true, userManaged = false)
public class RSSEntry implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6806731622485617940L;
	private String title;
	private Date date;
	private int id;
	private String description;
	private String url;

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

}
