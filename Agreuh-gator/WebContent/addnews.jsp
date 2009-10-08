<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<%@page import="fr.giwi.agreugator.blog.bean.BlogEntry"%>
<%@page import="fr.giwi.agreugator.blog.dao.BlogEntryManager"%>
<%@page import="net.fckeditor.FCKeditor"%>
<%@page import="fr.giwi.agreugator.constantes.Constantes"%><html>
<head>
<title><%=application.getInitParameter("title")%> : Ajout d'une niouze</title>
<%
if(session.getAttribute("user") == null) {
	%>
	<jsp:forward page="index.jsp"></jsp:forward>
	<%
}
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
					<h2>Les Niouzes</h2>
		<a title="Ajout de news" 
		href="#TB_inline?height=600&width=600&inlineId=addNewsBox&modal=false" 
		class="thickbox" ><img alt="Ajout de news" src="images/news_subscribe.png" border="0" /></a>
		<br /><br />
			<div id="Pagination" class="pagination"></div>
		<br style="clear:both;" />
		<div id="Searchresult">
			This content will be replaced when pagination inits.
		</div>
		<div id="hiddenresult" style="display:none;">
		<%
		BlogEntryManager bem = new BlogEntryManager();
		List<BlogEntry> list = bem.getBlogEntries(0, Constantes.TYPE_TOUT);
		for(BlogEntry be : list) {
		%><div class="result"><p>
		<a href="<%=request.getContextPath()%>/delNews?id=<%=be.getId() %>">
		<img src="images/delete.png" border="0" alt="Delete" align="absmiddle" /></a>
		&nbsp;
		<a href="<%=request.getContextPath()%>/editNews?id=<%=be.getId() %>" ><%= be.getTitle() %></a>
		</p></div>
		<%  } %>
		</div>
		</div>

		
<div id="addNewsBox" class="products_box" style="visibility:hidden; position:absolute;" >
	<h2 class="title">Ajout / modif d'une Niouze</h2>
<form name="search" action="<%=request.getContextPath()%>/addNews"	method="post">
<p>Titre : <input name="title" type="text" /> <br />
Type : <select name="type">
<option value="<%= Constantes.TYPE_ABOUT %>" >About</option>
<option value="<%= Constantes.TYPE_BLOG %>" >Blog</option>
<option value="<%= Constantes.TYPE_CHANGE_LOG %>" >ChangeLog</option>
</select> </p>
<p>R&eacute;sum&eacute; : 
<%
	FCKeditor resume = new FCKeditor(request, "resume");
	resume.setValue("");
	out.println(resume);
	%></p>
	<p>
Contenu : <%
	FCKeditor content = new FCKeditor(request, "content");
	content.setValue("");
	out.println(content);
	%> 
</p>
<p><input type="submit" value="Ajout" /></p>
</form>
</div><div style="clear:both;"></div></div>
    <!--end of right content-->
    </div>
    
<jsp:include page="common/footer.jsp" />
</div>
</body>
</html>