<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.apache.lucene.search.Hits"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.apache.lucene.search.Hit"%>
<%@page import="fr.giwi.agreugator.constantes.Constantes"%>
<%@page import="fr.giwi.agreugator.helpers.StringHelper"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="fr.giwi.agreugator.helpers.Base64Helper"%><html>
<head>
<%
	Hits hits = (Hits) request.getAttribute("Hits");
	String query = (String) request.getAttribute("queryString");
	Iterator<Hit> hitIt = hits.iterator();
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=application.getInitParameter("title")%> : Résultats</title>
<link href="css/default.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="css/pagination.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.pagination.js"></script>
<script type="text/javascript">
function pageselectCallback(page_index, jq){
    // Get number of elements per pagionation page from form
	var num_entries = $('#hiddenresult div.result').length;
    var items_per_page = 5;
    var max_elem = Math.min((page_index+1) * items_per_page, num_entries);
    var newcontent = '';
	$('#Searchresult').empty();
    // Iterate through a selection of the content and build an HTML string
    for(var i= page_index * items_per_page; i < max_elem; i++)
    {
		 newcontent = $('#hiddenresult div.result:eq('+i+')').clone();
		$('#Searchresult').append(newcontent);
      
    }
    return false;
}
function initPagination() {
var num_entries = $('#hiddenresult div.result').length;

// Create pagination element
$("#Pagination").pagination(num_entries, {
num_edge_entries: 2,
num_display_entries: 10,
callback: pageselectCallback,
items_per_page:5,
next_text:"Suivant",
prev_text:"P&eacute;c&eacute;dent"


});
}
        
$(document).ready(function(){      
    initPagination();
});
</script>
</head>
<body>
<jsp:include page="header.jsp" />
<div id="page"><!-- start content -->
	<div id="content">
		<h2>
			<a	href="<%=request.getContextPath()%>/rss?query=<%=Base64Helper.encodeString(query)%>" target="_blank">
	 			<img src="<%=request.getContextPath()%>/images/rss.png" border="0" />
	 		</a> 
	 		Résultats
	 	</h2>
	 	<div id="Pagination" class="pagination"></div>
		<br style="clear:both;" />
		<div id="Searchresult">
			This content will be replaced when pagination inits.
		</div>
		<div id="hiddenresult" style="display:none;">
 		<% 	if (hits.length() == 0) { %><div class="result">Nada !</div><%	}
 		while (hitIt.hasNext()) {
			Hit hit = hitIt.next();	%>
			<div class="result">
				<div class="post">
					<h2 class="title">
						<a href="<%=hit.getDocument().getField(Constantes.ItemLink).stringValue()%>">
							<%=StringHelper.colorize(hit.getDocument().getField(Constantes.ItemTitle).stringValue(), query)%>
						</a>
					</h2>
					<p class="meta"><small>Score : <%= hit.getScore() %> / 
					Le <%=hit.getDocument().getField(Constantes.PubDate).stringValue()%>
					</small></p>
					
					<div class="entry">
					<% if(hit.getDocument().getField(Constantes.ItemDescHTML) != null) { %>
						<p><%=StringHelper.colorize(StringEscapeUtils.unescapeHtml(hit.getDocument().getField(Constantes.ItemDescHTML).stringValue()), query)%></p>
						<% } %>
						<p class="meta"><small>Provient du flux : <a href="<%=hit.getDocument().getField(Constantes.Link).stringValue()%>"><%=StringHelper.colorize(hit.getDocument().getField(Constantes.Title).stringValue(), query)%></a>
						<% if(hit.getDocument().getField(Constantes.Copyright) != null && !"".equals(hit.getDocument().getField(Constantes.Copyright).stringValue())) { %>
						&copy; <%=  hit.getDocument().getField(Constantes.Copyright).stringValue() %>
						<% } %>
						 <% if(hit.getDocument().getField(Constantes.Author) != null && !"".equals(hit.getDocument().getField(Constantes.Author).stringValue())) { %>
						par <%=  hit.getDocument().getField(Constantes.Author).stringValue() %>
						<% } %>
						</small></p>
					</div>
					
				</div>
			</div>
		<%	} %>
		</div>
	</div>
	<jsp:include page="sidebar.jsp" />
</div>
<jsp:include page="footer.jsp" />
</body>
</html>