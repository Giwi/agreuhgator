package fr.giwi.agreugator.dao;

import java.util.List;

public interface Saveable {

	public boolean saveItem(String item);

	public List<String> getItems();

}
