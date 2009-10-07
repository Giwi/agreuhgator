<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<%@page import="fr.giwi.agreugator.constantes.Constantes"%>
<%@page import="fr.giwi.agreugator.blog.bean.BlogEntry"%>
<%@page import="fr.giwi.agreugator.blog.dao.BlogEntryManager"%>
<%
BlogEntryManager bem = new BlogEntryManager();
%>

<html>
<head><jsp:include page="common/meta.jsp" />
<title><%=application.getInitParameter("title")%> : A propos</title>
</head>
<body>

<div id="main_container">
<jsp:include page="common/header.jsp" />
	<div id="main_content">
    	<div id="left_content"> 
			<jsp:include page="common/sidebar.jsp" />
		</div><!--end of left content-->
		
    	<div id="right_content">
    	<table cellpadding="0" cellspacing="10" border="0" width="100%"><tr><td valign="top">
    	<%
		List<BlogEntry> list = bem.getBlogEntries(1, Constantes.TYPE_ABOUT);
		for(BlogEntry be : list) {
		%><div class="news">
		<div class="news_icon"><img src="images/type<%= be.getType() %>.png" alt="" /></div>
			 <h2><%= be.getTitle() %></h2>
			<div class="news_content">
			<p class="small">Le <%= Constantes.simpleDateFormat.format(be.getDate()) %></p>
			<p><%= be.getResume() %></p>
			<p><%= be.getContent() %></p>
		</div></div>
		<%  } %></td><td valign="top">

<div id="rss">
		<h2>ChangeLog</h2>
		<% List<BlogEntry> listCL =  bem.getBlogEntries(5, Constantes.TYPE_CHANGE_LOG); 
		for(BlogEntry be : listCL) {
		%>
      
        
         <span><%= be.getTitle() %></span>
			<p>Le <%= Constantes.simpleDateFormat.format(be.getDate()) %></p>
			<p><%= be.getResume() %></p>
			<div class="read_more_link">
			<a href="<%=request.getContextPath()%>/showNews?id=<%=be.getId() %>">Lire la suite</a>
			</div>
		
		<%  } %>
</div>
</td></tr></table>

  <div style=" clear:both;"></div>
	</div>
    <!--end of right content-->
    </div>
    
<jsp:include page="common/footer.jsp" />
</div>
</body>
</html>