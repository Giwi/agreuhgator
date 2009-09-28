<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="fr.giwi.agreugator.helpers.StringHelper"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%= application.getInitParameter("title") %> : Erreur !!</title><link href="css/default.css" rel="stylesheet" type="text/css">
</head>
<body><jsp:include page="header.jsp" />
<div id="page">
	<!-- start content -->
	<div id="content"><%=StringHelper.escapeHTML((String)request.getAttribute("ErrorMess"))%></div></div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>