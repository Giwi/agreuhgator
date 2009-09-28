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
}
