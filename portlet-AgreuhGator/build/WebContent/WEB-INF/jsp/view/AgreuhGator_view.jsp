<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>	
<%@page import="java.net.URL"%>
<%@page import="com.sun.syndication.io.SyndFeedInput"%>
<%@page import="com.sun.syndication.io.XmlReader"%>
<%@page import="com.sun.syndication.feed.synd.SyndFeed"%>
<%@page import="com.sun.syndication.feed.synd.SyndEntry"%>
<%@page import="fr.giwi.portlet.agreuhgator.Base64Helper"%>
<%@page import="fr.giwi.portlet.agreuhgator.Constantes"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.dao.search.SearchContainer"%>
<%@page import="java.util.Collections"%>
<%@ include file="../common/init.jsp"%>


<%@page import="com.sun.syndication.feed.synd.SyndEntryImpl"%>

<%
if(!"".equals(renderRequest.getPreferences().getValue("rss", ""))) {
	final String feedStr = renderRequest.getPreferences().getValue("rss", "")+"?query="+Base64Helper.encodeString(renderRequest.getPreferences().getValue("query", ""));
	final URL feedUrl = new URL(feedStr);
	final SyndFeedInput input = new SyndFeedInput();
	final SyndFeed rss = input.build(new XmlReader(feedUrl));
	 %>
	
<style type="text/css">
.minidate{
	font-size : 10px;
	color:#666666;
	font-weight: bold;
}
.copyright{
	font-size : 10px;
	color:#666666;
	font-weight: bold;
	text-align: right;
	width: 100%;
	margin: 5px;
	border-bottom: 1px solid #666666;
}
</style>


<div class="feed">
	<div class="journal-content-article">
		<h3><%=rss.getTitle() %></h3> 
		   <span class="minidate">Publié le <%= Constantes.simpleDateFormat.format(rss.getPublishedDate()) %></span>
		   <p><%=rss.getDescription()%></p>
	</div>	
	<% 
	int i = 0;
	for(SyndEntryImpl entry: (List<SyndEntryImpl>) rss.getEntries())  {%>
	<div class="feed-entry">
		<span class="feed-entry-title">
			<a href="javascript:fn<portlet:namespace/>toggle(<%= i %>);" class="entry-expander" ><img id="<portlet:namespace/>img<%= i %>" class="feed-entry-img" src='<%= themeDisplay.getPathThemeImage() %>/arrows/01_plus.png' />
		<%= entry.getTitle() %></a></span>
		<div class="feed-entry-content" style="display: none;" id="<portlet:namespace/>content<%= i %>">
             <span class="minidate">Publié le <%= Constantes.simpleDateFormat.format(entry.getPublishedDate()) %>
					<%= "".equals(entry.getAuthor())?"":" par " + entry.getAuthor() %></span>
			<p><%= entry.getDescription().getValue() %></p>
				<div class="copyright"><%= "".equals(rss.getCopyright())?"":"&copy;" + rss.getCopyright() %></div>
			
		</div>
	</div>
	<%
	i++;
	} %>
</div>
<script type="text/javascript">
var minusImage = '01_minus.png';
var plusImage = '01_plus.png';

function fn<portlet:namespace/>toggle(i) {
	var img = document.getElementById("<portlet:namespace/>img"+i);
	var content = document.getElementById("<portlet:namespace/>content"+i);  
	if(img.src.indexOf('plus.png') > -1) {
		img.src = img.src.replace(plusImage, minusImage); 
		jQuery(content).slideDown();
	} else {
		img.src = img.src.replace(minusImage, plusImage);
		jQuery(content).slideUp();
		
	}
}
</script><p>Nombre de flux : <%= renderRequest.getAttribute("nbOfFeeds") %></p>
<%}%>