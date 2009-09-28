<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><link href="css/default.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=application.getInitParameter("title")%> : A propos</title>
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
		<h2 class="title">Login</h2>
		<div class="entry" align="right">
<form name="login" action="<%=request.getContextPath()%>/login"
	method="post">
<p>Login : <input name="login" type="text" /><br />
Mot de passe : <input name="password" type="password" /><br />  
<input
	type="submit" value="Go !" /></p>
</form>





        </div></div></div>
</div>
<jsp:include page="sidebar.jsp" />
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>