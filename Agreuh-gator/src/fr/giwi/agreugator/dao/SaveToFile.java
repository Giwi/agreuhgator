package fr.giwi.agreugator.dao;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SaveToFile implements Saveable {

	@Override
	public List<String> getItems() {
		final List<String> listItems = new ArrayList<String>();
		final ResourceBundle rb = ResourceBundle.getBundle("appli");
		final File f = new File(rb.getString("save.file.path"));

		try {
			if (!f.exists()) {
				f.createNewFile();
			}
			final DataInputStream bis = new DataInputStream(new FileInputStream(f));
			while (bis.available() != 0) {
				listItems.add(bis.readLine());
			}
			bis.close();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listItems;
	}

	@Override
	public boolean saveItem(final String item) {
		final ResourceBundle rb = ResourceBundle.getBundle("appli");
		final File f = new File(rb.getString("save.file.path"));

		try {
			if (!f.exists()) {
				f.createNewFile();
			}
			final List<String> listItems = getItems();
			if (!listItems.contains(item)) {
				listItems.add(item);
				final FileOutputStream dos = new FileOutputStream(f);
				for (final String line : listItems) {
					dos.write((line + "\n").getBytes());
				}
				dos.close();
				return true;
			}
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
