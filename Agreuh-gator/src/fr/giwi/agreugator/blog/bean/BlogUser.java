package fr.giwi.agreugator.blog.bean;

import java.io.Serializable;

public class BlogUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1606979921629617554L;
	private int id;
	private String login;
	private String password;

	public String getLogin() {
		return login;
	}

	public void setLogin(final String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

}
