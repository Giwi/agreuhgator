<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<%@page import="fr.giwi.agreugator.blog.bean.BlogEntry"%>
<%@page import="fr.giwi.agreugator.blog.dao.BlogEntryManager"%>

<%@page import="fr.giwi.agreugator.constantes.Constantes"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=application.getInitParameter("title")%> : Une niouze</title>
<%
BlogEntry be = (BlogEntry) request.getAttribute("blogentry");
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
				
<div class="post">
<h2 class="title"><%= be.getTitle() %></h2>
<p class="meta"><small>Le <%= Constantes.simpleDateFormat.format(be.getDate()) %></small></p>
<div class="entry"><p><%= be.getResume() %></p>
<p><%= be.getContent() %></p>
</div></div>


</div>
<jsp:include page="sidebar.jsp" />
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>