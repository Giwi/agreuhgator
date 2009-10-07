<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="fr.giwi.agreugator.blog.dao.RssEntryManager"%>
<%@page import="java.util.List"%>
<%@page import="fr.giwi.agreugator.rss.bean.RSSEntry"%><html>
<head>
<title><%=application.getInitParameter("title")%> : Ajout de nouveaux flux</title>
<%
if(session.getAttribute("user") == null) {
	%>
	<jsp:forward page="index.jsp"></jsp:forward>
	<%
}
%>
<jsp:include page="common/meta.jsp" />
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
	<h3 >Les flux RSS</h3>
	<div id="Pagination" class="pagination"></div>
	<br style="clear:both;" />
	<div id="Searchresult">This content will be replaced when pagination inits.</div>
	<div id="hiddenresult" style="display:none;">
		<%
		RssEntryManager rem = new RssEntryManager();
		List<RSSEntry> list = rem.getRssEntries(0);
		for(RSSEntry re : list) {
		%><div class="result">
			<p>
				<a href="<%=request.getContextPath()%>/editRss?id=<%=re.getId() %>" ><%= re.getTitle() %></a>
				<a href="<%=request.getContextPath()%>/delRss?id=<%=re.getId() %>"><img src="images/delete.png" border="0" alt="Delete" /></a>
			</p>
		</div>
		<%  } %>
	</div>
</div>
<div class="result">
	<div class="post">
		<h2 class="title">Ajout de nouveaux flux</h2>
		<div class="entry">
		<form name="search" action="<%=request.getContextPath()%>/addRss" method="post">
			<p>RSS à ajouter : <input name="url" size="44" /> 
			<input type="submit" value="Ajout" /></p>
		</form>
		</div>
	</div>
</div>
</div>
<jsp:include page="common/sidebar.jsp" />
</div>
<jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>