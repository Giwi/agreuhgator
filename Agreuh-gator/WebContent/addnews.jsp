<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<%@page import="fr.giwi.agreugator.blog.bean.BlogEntry"%>
<%@page import="fr.giwi.agreugator.blog.dao.BlogEntryManager"%>
<%@page import="net.fckeditor.FCKeditor"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=application.getInitParameter("title")%> : Ajout d'une niouze</title>
<%
if(session.getAttribute("user") == null) {
	%>
	<jsp:forward page="index.jsp"></jsp:forward>
	<%
}
%>
<link href="css/default.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="css/pagination.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.pagination.js"></script>
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
<body><jsp:include page="header.jsp" />
<div id="page"><!-- start content -->
<div id="content">


<table width="100%" border="0" cellpadding="5">
	<%
		String erreoMess = (String) request.getAttribute("erreoMess");
		if (erreoMess != null) {
	%>
	<tr>
		<td class="errMess">!! <%=erreoMess%> !!</td>
	</tr>
	<%
		}
	%>
	<%
		String okMess = (String) request.getAttribute("okMess");
		if (okMess != null) {
	%>
	<tr>
		<td class="okMess"><%=okMess%></td>
	</tr>
	<%
		}
	%>
</table>
				<div class="post">
					<h2 class="title">Les Niouzes</h2>
					
					<div class="entry">
			<div id="Pagination" class="pagination"></div>
		<br style="clear:both;" />
		<div id="Searchresult">
			This content will be replaced when pagination inits.
		</div>
		<div id="hiddenresult" style="display:none;">
		<%
		BlogEntryManager bem = new BlogEntryManager();
		List<BlogEntry> list = bem.getBlogEntries(-1);
		for(BlogEntry be : list) {
		%><div class="result"><p>
		<a href="<%=request.getContextPath()%>/editNews?id=<%=be.getId() %>" ><%= be.getTitle() %></a>
		<a href="<%=request.getContextPath()%>/delNews?id=<%=be.getId() %>"><img src="images/delete.png" border="0" alt="Delete" /></a>
		</p></div>
		<%  } %>
		</div>
		</div>
</div>
				<div class="post">
					<h2 class="title">Ajout d'une Niouze</h2>
					
					<div class="entry">
<form name="search" action="<%=request.getContextPath()%>/addNews"
	method="post">
<p>Titre : <input name="title" type="text" /> <br />
R&eacute;sum&eacute; : 
<%
	FCKeditor resume = new FCKeditor(request, "resume");
	resume.setValue("");
	out.println(resume);
	%> <br />
	
Contenu : <%
	FCKeditor content = new FCKeditor(request, "content");
	content.setValue("");
	out.println(content);
	%> 
</p>
<p><input type="submit" value="Ajout" /></p>
</form>
</div></div>


</div></div>
<jsp:include page="sidebar.jsp" />
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>