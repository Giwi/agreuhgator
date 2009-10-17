<%@page import="fr.giwi.agreugator.helpers.RSSHelper"%>
<%@page import="java.util.List"%>
<%@page import="fr.giwi.agreugator.blog.dao.BlogEntryManager"%>
<%@page import="fr.giwi.agreugator.blog.bean.BlogEntry"%>
<%@page import="fr.giwi.agreugator.constantes.Constantes"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>


<%@page import="java.net.URL"%>
<%@page import="com.sun.syndication.io.SyndFeedInput"%>
<%@page import="com.sun.syndication.feed.synd.SyndFeed"%>
<%@page import="com.sun.syndication.io.XmlReader"%>
<%@page import="com.sun.syndication.feed.synd.SyndEntry"%>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/animatedcollapse.js" ></script>
<div id="left_nav">
	<h2> <a href="<%=request.getContextPath()%>/blogRss" target="_blank">
		<img src="<%=request.getContextPath()%>/images/rss.png" border="0" />
	</a> Les niouzes</h2>
	

<%
final URL feedUrl = new URL("http://localhost:8080/"+request.getContextPath() + "/blogRss");
final SyndFeedInput input = new SyndFeedInput();
final SyndFeed rss = input.build(new XmlReader(feedUrl));
int i = 0;
for (final SyndEntry item : (List<SyndEntry>) rss.getEntries()) {
%><div class="newsSideBar">
        <div class="news_contentSideBar">
        	<span>
        		<a href="#" rel="toggle[<%= "RssEntry"+i %>]" ><%= item.getTitle() %></a>
        	</span>
<script type="text/javascript">
	animatedcollapse.addDiv('<%= "RssEntry"+i %>', 'fade=0,speed=400,group=rssFeed,hide=1');
</script>
<div id="<%= "RssEntry"+i %>">
<p class="small">
	Le <%= Constantes.simpleDateFormat.format(item.getPublishedDate()) %>
</p>
<p><%= item.getDescription().getValue() %></p>
<div class="read_more_link">
	<a href="<%= item.getLink() %>" target="_blank" style="float: right">En savoir plus</a>
</div>
</div>
</div>
</div>
<% 
i++;
} %>
	</div>
<script type="text/javascript">
$(document).ready(function(){
	$('#newsfeed').rssReader({
 		targeturl: "<%=request.getContextPath()%>/blogRss",
		items: 4,
 		Maxlength:80,
     	loadingImg: 'images/ajax-loader.gif'
	});
	animatedcollapse.init();
});
</script>