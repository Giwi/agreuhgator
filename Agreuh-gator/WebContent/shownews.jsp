<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<%@page import="fr.giwi.agreugator.blog.bean.BlogEntry"%>
<%@page import="fr.giwi.agreugator.blog.dao.BlogEntryManager"%>

<%@page import="fr.giwi.agreugator.constantes.Constantes"%><html>
<head>
<title><%=application.getInitParameter("title")%> : Une niouze</title>
<%
BlogEntry be = (BlogEntry) request.getAttribute("blogentry");
%>
<head><jsp:include page="common/meta.jsp" />
<title><%=application.getInitParameter("title")%></title>
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
<div class="news">
<div class="news_icon"><img src="images/type<%= be.getType() %>.png" alt="" /></div>
			 <h2><%= be.getTitle() %></h2>
			<div class="news_content">
			<p class="small">
			Le <%= Constantes.simpleDateFormat.format(be.getDate()) %> 
			<%= "".equals(Constantes.getType(be.getType()))?"":" | " +Constantes.getType(be.getType()) %>
			</p>
			<p><%= be.getResume() %></p>
			<p><%= be.getContent() %></p>
		</div></div>
		<div style=" clear:both;"></div>
	</div>
    <!--end of right content-->
    </div>
    
<jsp:include page="common/footer.jsp" />
</div>
</body>
</html>