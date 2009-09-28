package fr.giwi.agreugator.wrapper;

import org.apache.lucene.document.Document;

public class RssDocWrapper {
	private Document document;
	private String uuId;

	public Document getDocument() {
		return document;
	}

	public void setDocument(final Document document) {
		this.document = document;
	}

	public String getUuId() {
		return uuId;
	}

	public void setUuId(final String uuId) {
		this.uuId = uuId;
	}
}
