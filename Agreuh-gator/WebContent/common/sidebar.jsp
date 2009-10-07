<%@page import="fr.giwi.agreugator.helpers.RSSHelper"%>
<%@page import="java.util.List"%>
<%@page import="fr.giwi.agreugator.blog.dao.BlogEntryManager"%>
<%@page import="fr.giwi.agreugator.blog.bean.BlogEntry"%>
<%@page import="fr.giwi.agreugator.constantes.Constantes"%>

<div id="left_nav"><div id="rss">
	<h2> <a href="<%=request.getContextPath()%>/blogRss" target="_blank">
		<img src="<%=request.getContextPath()%>/images/rss.png" border="0" />
	</a> Les niouzes</h2>	
	<div id="newsfeed"></div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$('#newsfeed').rssReader({
 		targeturl: "<%=request.getContextPath()%>/blogRss",
		items: 4,
 		Maxlength:80,
     	loadingImg: 'images/ajax-loader.gif'
	});
});
</script>