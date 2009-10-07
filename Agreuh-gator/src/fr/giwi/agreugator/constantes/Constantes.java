package fr.giwi.agreugator.constantes;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Constantes {
	public static final String Title = "title";
	public static final String Copyright = "copyright";
	public static final String Link = "link";
	public static final String Description = "description";
	public static final String PubDate = "pubDate";
	public static final String Author = "author";
	public static final String ItemDesc = "ItemDesc";
	public static final String ItemLink = "ItemLink";
	public static final String ItemTitle = "ItemTitle";
	public static final String UUID = "uuid";
	public static String LucenePath = "WEB-INF/Indexes";
	public static final String ItemDescHTML = "ItemDescHTML";
	public static final String[] ALL_FIELDS = { Title, Description, Author, ItemDesc, ItemTitle };
	public static final String CONF_BDD = "AgreuhGatorConfBDD.xml";
	public final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.FRANCE);

	public static final int TYPE_TOUT = 0;
	public static final int TYPE_CHANGE_LOG = 1;
	public static final int TYPE_BLOG = 2;
	public static final int TYPE_ABOUT = 3;

	public static String getType(final int i) {
		switch (i) {
		case 1:
			return "Change Log";
		case 2:
			return "Blog";
		case 3:
			return "A propos";
		default:
			return "";
		}
	}

}
