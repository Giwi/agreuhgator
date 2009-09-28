package fr.giwi.agreugator.dao;

import java.util.ResourceBundle;

public class SaveFactory {
	public static Saveable getSaveObject() {
		Saveable saveObj = null;
		final ResourceBundle rb = ResourceBundle.getBundle("appli");
		final String saveMethod = rb.getString("save.method").trim();
		if ("file".equals(saveMethod)) {
			saveObj = new SaveToFile();
		} else if ("db".equals(saveMethod)) {
			saveObj = new SaveToDataBase();
		}
		return saveObj;
	}
}
