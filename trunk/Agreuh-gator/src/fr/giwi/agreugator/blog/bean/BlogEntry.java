package fr.giwi.agreugator.blog.bean;

import java.util.Date;

public class BlogEntry {
	private String title;
	private Date date;
	private int id;

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
