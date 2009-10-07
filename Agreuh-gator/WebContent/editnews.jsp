<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<%@page import="fr.giwi.agreugator.blog.bean.BlogEntry"%>
<%@page import="fr.giwi.agreugator.blog.dao.BlogEntryManager"%>
<%@page import="net.fckeditor.FCKeditor"%>
<%@page import="fr.giwi.agreugator.constantes.Constantes"%><html>
<head>
<title><%=application.getInitParameter("title")%> : Edition d'une niouze</title>
<%
if(session.getAttribute("user") == null) {
	%>
	<jsp:forward page="index.jsp"></jsp:forward>
	<%
}
BlogEntry be = (BlogEntry) request.getAttribute("blogentry");
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

<div class="products_box">
	<h3 class="title">Modif d'une Niouze</h3>
<form name="search" action="<%=request.getContextPath()%>/editNews"	method="post">
<p>Titre : <input name="title" type="text" value="<%= be.getTitle() %>" /> <br />
<input name="id" type="hidden" value="<%= be.getId() %>" />
Type : <select name="type">
<option value="<%= Constantes.TYPE_ABOUT %>" <%= (be.getType() == Constantes.TYPE_ABOUT)?" Selected":"" %> >About</option>
<option value="<%= Constantes.TYPE_BLOG %>" <%= (be.getType() == Constantes.TYPE_BLOG)?" Selected":"" %>>Blog</option>
<option value="<%= Constantes.TYPE_CHANGE_LOG %>" <%= (be.getType() == Constantes.TYPE_CHANGE_LOG)?" Selected":"" %>>ChangeLog</option>
</select> </p>
<p>R&eacute;sum&eacute; : 
<%
	FCKeditor resume = new FCKeditor(request, "resume");
	resume.setValue(be.getResume());
	out.println(resume);
	%></p>
	<p>
Contenu : <%
	FCKeditor content = new FCKeditor(request, "content");
	content.setValue(be.getContent());
	out.println(content);
	%> 
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