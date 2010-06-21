package fr.giwi.portlet.agreuhgator;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.xml.namespace.QName;

public class Constantes {
	public static final QName SERVICE_NAME = new QName("http://giwi.fr/AgreuhGatorservice/RssEntryManager", "RssEntryManager");
	public final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.FRANCE);
}
