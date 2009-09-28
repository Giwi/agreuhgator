<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<%@page import="fr.giwi.agreugator.blog.bean.BlogEntry"%>
<%@page import="fr.giwi.agreugator.blog.dao.BlogEntryManager"%>
<%@page import="net.fckeditor.FCKeditor"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=application.getInitParameter("title")%> : Edition d'une niouze</title>
<%
if(session.getAttribute("user") == null) {
	%>
	<jsp:forward page="index.jsp"></jsp:forward>
	<%
}
BlogEntry be = (BlogEntry) request.getAttribute("blogentry");
%>
<link href="css/default.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
function FCKeditor_OnComplete(editorInstance) {
	window.status = editorInstance.Description;
}

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
	<h2 class="title">La Niouze</h2>
	
	<div class="entry">
<form name="search" action="<%=request.getContextPath()%>/editNews"
	method="post">
	
<p><input name="id" type="hidden" value="<%= be.getId() %>" />

Titre : <input name="title" type="text" value="<%= be.getTitle() %>" /> <br />
R&eacute;sum&eacute; : 
<%
	FCKeditor resume = new FCKeditor(request, "resume");
	resume.setValue(be.getResume());
	out.println(resume);
	%> <br />
	
Contenu : <%
	FCKeditor content = new FCKeditor(request, "content");
	content.setValue(be.getContent());
	out.println(content);
	%> 
</p>
<p><input type="submit" value="Mettre à jour" /></p>
</form>
</div></div>


</div></div>
<jsp:include page="sidebar.jsp" />
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>