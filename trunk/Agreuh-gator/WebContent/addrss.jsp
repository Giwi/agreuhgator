<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=application.getInitParameter("title")%> : Ajout de
nouveaux flux</title>
<%
if(session.getAttribute("user") == null) {
	%>
	<jsp:forward page="index.jsp"></jsp:forward>
	<%
}
%>
<link href="css/default.css" rel="stylesheet" type="text/css">
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
<div class="result">
	<div class="post">
		<h2 class="title">Ajout de nouveaux flux</h2>
		<div class="entry">

<form name="search" action="<%=request.getContextPath()%>/addRss"
	method="post">
<p>RSS à ajouter : <input name="rssUrl" size="44" /> <input
	type="submit" value="Ajout" /></p>
</form></div></div></div>
</div>
<jsp:include page="sidebar.jsp" />
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>