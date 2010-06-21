package fr.giwi.agreugator.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlType;

/**
 * The persistent class for the RSSEntry database table.
 * 
 */
@Entity
@XmlType(namespace = "http://giwi.fr/AgreuhGatorservice/RssEntryManager")
public class RSSEntry implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String description;
	private Date pubDate;
	private String title;
	private String url;

	public RSSEntry() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Lob()
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getPubDate() {
		return this.pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}