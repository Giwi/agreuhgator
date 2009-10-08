<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="fr.giwi.agreugator.blog.dao.BlogEntryManager"%>
<%@page import="java.util.List"%>
<%@page import="fr.giwi.agreugator.blog.bean.BlogEntry"%>
<%@page import="fr.giwi.agreugator.constantes.Constantes"%>

<%
BlogEntryManager bem = new BlogEntryManager();
%>


<%@page import="fr.giwi.agreugator.blog.dao.RssEntryManager"%><html>
<head>
<jsp:include page="common/meta.jsp" />

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
    	   <h2>Bienvenue sur Agreuh Gator</h2>
    	<table cellpadding="0" cellspacing="10" border="0" width="100%"><tr><td valign="top">
		<div class="search_box">
			<form id="search" action="<%= request.getContextPath() %>/search" method="post">
				<div class="search_title">Recherche</div>
				<input id="query" type="text" name="query" class="search_input" value="<%=(request.getParameter("query")!=null)?request.getParameter("query"):"" %>" />
				<!-- <input id="searchsubmit" type="submit" value="Agreuh!" "/> -->
				 <input type="image" src="images/search.gif" class="submit" />
		        <div class="subsearch">Recherchez parmis les <%= new RssEntryManager().getRssEntries(0).size() %> flux RSS</div>
			</form>
		</div><%
		
		List<BlogEntry> list = bem.getBlogEntries(1, Constantes.TYPE_BLOG);
		for(BlogEntry be : list) {
			
		%>
		<div class="news">
        <div class="news_icon"><img src="images/type<%= be.getType() %>.png" alt="" /></div>
		<div class="news_content"> <h3><%= be.getTitle() %></h3>
			<p class="small">Le <%= Constantes.simpleDateFormat.format(be.getDate()) %></p>
			<p><%= be.getResume() %></p>
			<div class="read_more_link">
			<a href="<%=request.getContextPath()%>/showNews?id=<%=be.getId() %>">Lire la suite</a>
			</div>
		</div>
	</div>
		<%  } %>
		</td><td valign="top">
		<div class="news_icon"><img src="images/type1.png" alt="" /></div><h3>ChangeLog</h3>
		<br />
		<% List<BlogEntry> listCL =  bem.getBlogEntries(1, Constantes.TYPE_CHANGE_LOG); 
		for(BlogEntry be : listCL) {
		%>
			<div class="news_content"> <h4><%= be.getTitle() %></h4>
			<p class="small" >Le <%= Constantes.simpleDateFormat.format(be.getDate()) %></p>
			<p><%= be.getResume() %></p>
			<div class="read_more_link">
			<a href="<%=request.getContextPath()%>/showNews?id=<%=be.getId() %>">Lire la suite</a>
			</div>
		</div>
		<%  } %>
		</td></tr>
		<tr><td valign="top">
		
		
		
		
	    </td>
	    <td></td>
	    </tr></table>
	</div>
    <!--end of right content-->
    </div>
    
<jsp:include page="common/footer.jsp" />
</div>
</body>
</html>