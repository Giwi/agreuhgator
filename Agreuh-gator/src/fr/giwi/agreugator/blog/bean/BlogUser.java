package fr.giwi.agreugator.blog.bean;

import java.io.Serializable;

import net.sourceforge.pbeans.annotations.PersistentClass;
import net.sourceforge.pbeans.annotations.PropertyIndex;
import net.sourceforge.pbeans.annotations.TransientProperty;

@PersistentClass(table = "BlogUser", indexes = @PropertyIndex(unique = true, propertyNames = "id"), autoIncrement = true, idField = "InternalUID", deleteFields = true, userManaged = false)
public class BlogUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1606979921629617554L;
	private int id;
	private String login;
	private String passwordHash;

	public String getLogin() {
		return login;
	}

	public void setLogin(final String login) {
		this.login = login;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(final String ph) {
		passwordHash = ph;
	}

	@TransientProperty
	public void setPassword(final String pwd) {
		passwordHash = createPasswordHash(pwd);
	}

	public boolean isPasswordMatch(final String pwd) {
		return createPasswordHash(pwd).equals(passwordHash);
	}

	private String createPasswordHash(final String password) {
		return String.valueOf(password.hashCode());
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

}
