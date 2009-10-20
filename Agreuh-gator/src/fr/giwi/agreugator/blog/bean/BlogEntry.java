package fr.giwi.agreugator.blog.bean;

import java.io.Serializable;
import java.util.Date;

import net.sourceforge.pbeans.annotations.PersistentClass;
import net.sourceforge.pbeans.annotations.PropertyIndex;

@PersistentClass(table = "BlogEntry", indexes = @PropertyIndex(unique = true, propertyNames = "id"), autoIncrement = true, idField = "InternalUID", deleteFields = true, userManaged = false)
public class BlogEntry implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2452174581828669426L;
	private String title;
	private Date date;
	private int id;
	private int type;

	public int getType() {
		return type;
	}

	public void setType(final int type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

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

	public String getResume() {
		return resume;
	}

	public void setResume(final String resume) {
		this.resume = resume;
	}

	public String getContent() {
		return content;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	private String resume;
	private String content;
}
