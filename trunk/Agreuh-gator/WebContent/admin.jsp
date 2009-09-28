<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><link href="css/default.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
if(session.getAttribute("user") == null) {
	%>
	<jsp:forward page="index.jsp"></jsp:forward>
	<%
}
%>
<title><%=application.getInitParameter("title")%> : Admin</title>
</head>
<body><jsp:include page="header.jsp" />
<div id="page"><!-- start content -->
<div id="content">
<div class="result">
	<div class="post">
		<h2 class="title">Admin</h2>
		<div class="entry">
<ul>
  <li><a href="addrss.jsp">Ajout d'un flux</a></li>
  <li><a href="addnews.jsp">Gestion des niouzes</a></li>
</ul>
<p></p>
        </div></div></div>
</div>
<jsp:include page="sidebar.jsp" />
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>