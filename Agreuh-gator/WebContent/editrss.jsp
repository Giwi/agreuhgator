<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<%@page import="net.fckeditor.FCKeditor"%>
<%@page import="fr.giwi.agreugator.constantes.Constantes"%>
<%@page import="fr.giwi.agreugator.rss.bean.RSSEntry"%><html>
<head>
<title><%=application.getInitParameter("title")%> : Edition d'un flux RSS</title>
<%
if(session.getAttribute("user") == null) {
	%>
	<jsp:forward page="index.jsp"></jsp:forward>
	<%
}
RSSEntry re = (RSSEntry) request.getAttribute("rssentry");
%><jsp:include page="common/meta.jsp" />


<script type="text/javascript">
function FCKeditor_OnComplete(editorInstance) {
	window.status = editorInstance.Description;
}
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
	<h3 class="title">Modif d'un flux RSS</h3>
<form name="search" action="<%=request.getContextPath()%>/editRss"	method="post">
<p>Titre : <input name="title" type="text" value="<%= re.getTitle() %>" /> <br />
<input name="id" type="hidden" value="<%= re.getId() %>" />
</p>
<p>Description : 
<%
	FCKeditor description = new FCKeditor(request, "description");
description.setValue(re.getDescription());
	out.println(description);
	%></p>
	<p>
Url : <input name="url" type="text" value="<%= re.getUrl() %>" /> 
<a href="<%= re.getUrl() %>" target="_blank"><img src="images/rss.png" align="middle" border="0" /></a>
</p>
<p><input type="submit" value="Mettre à jour" /></p>
</form>
</div><div style=" clear:both;"></div></div>
    <!--end of right content-->
    </div>
    
<jsp:include page="common/footer.jsp" />
</div>
</body>
</html>