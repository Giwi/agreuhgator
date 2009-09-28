package fr.giwi.agreugator.helpers;

import org.apache.commons.lang.StringEscapeUtils;

public class StringHelper {
	public static String escapeHTML(String s) {
		s = s.replaceAll("&", "&amp;");
		s = s.replaceAll("<", "&lt;");
		s = s.replaceAll(">", "&gt;");
		s = s.replaceAll("\"", "&quot;");
		s = s.replaceAll("'", "&apos;");
		return s;
	}

	public static String stripHtml(final String text) {
		// 1 : convertir les caractères encodés en code html :
		return StringEscapeUtils.unescapeHtml(text).replaceAll("\\<.*?>", " ");
	}

	public static String colorize(String text, final String search) {
		for (final String what : search.split(" ")) {
			text = text.replaceAll(what, "<span style=\"background-color:yellow;\">" + what + "</span>");
		}
		return text;
	}

}
