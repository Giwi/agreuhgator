<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.Iterator"%>
<%@page import="fr.giwi.agreugator.constantes.Constantes"%>
<%@page import="fr.giwi.agreugator.helpers.StringHelper"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="fr.giwi.agreugator.helpers.Base64Helper"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.lucene.document.Document"%><html>
<head>
<%
List<Document> hits = ( List<Document>) request.getAttribute("Hits");
String query = (String) request.getAttribute("queryString");
	
%>
<title><%=application.getInitParameter("title")%> : Résultats</title>
<jsp:include page="common/meta.jsp" />
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
<div id="main_container">
<jsp:include page="common/header.jsp" />
	<div id="main_content">
    	<div id="left_content">
			<jsp:include page="common/sidebar.jsp" />
		</div><!--end of left content-->
		
    	<div id="right_content">
<jsp:include page="common/messages.jsp" />
<div class="products_box">
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
 		<% 	if (hits.isEmpty()) { %><div class="result">Nada !</div><%	}
 		for(Document doc : hits) {
			%>
			<div class="result">
		 <h3>
						<a href="<%=doc.getField(Constantes.ItemLink).stringValue()%>">
							<%=StringHelper.colorize(doc.getField(Constantes.ItemTitle).stringValue(), query)%>
						</a>
					</h3>
					<p class="small">
					Le <%=doc.getField(Constantes.PubDate).stringValue()%>
					</p>
					
					
					<% if(doc.getField(Constantes.ItemDescHTML) != null) { %>
						<p><%=StringHelper.colorize(StringEscapeUtils.unescapeHtml(doc.getField(Constantes.ItemDescHTML).stringValue()), query)%></p>
						<% } %>
						<p class="small">Provient du flux : <a href="<%=doc.getField(Constantes.Link).stringValue()%>"><%=StringHelper.colorize(doc.getField(Constantes.Title).stringValue(), query)%></a>
						<% if(doc.getField(Constantes.Copyright) != null && !"".equals(doc.getField(Constantes.Copyright).stringValue())) { %>
						&copy; <%=  doc.getField(Constantes.Copyright).stringValue() %>
						<% } %>
						 <% if(doc.getField(Constantes.Author) != null && !"".equals(doc.getField(Constantes.Author).stringValue())) { %>
						par <%=  doc.getField(Constantes.Author).stringValue() %>
						<% } %>
						</p>
				</div>
		<%	} %>
		</div>
	</div>
	
	<div style="clear:both;"></div></div>
    <!--end of right content-->
    </div>
    
<jsp:include page="common/footer.jsp" />
</div>
</body>
</html>