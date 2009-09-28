<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@page import="fr.giwi.agreugator.blog.dao.BlogEntryManager"%>
<%@page import="java.util.List"%>
<%@page import="fr.giwi.agreugator.blog.bean.BlogEntry"%>
<%@page import="fr.giwi.agreugator.constantes.Constantes"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=application.getInitParameter("title")%> : Recherche</title>
<link href="css/default.css" />
<link href="css/default.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="header.jsp" />
<div id="page"><!-- start content -->
<div id="content">
<div class="post">
<h2 class="title">Bienvenue sur Agreuh Gator!</h2>


<div class="entry">
<h2>Le salad-Mix du RSS</h2>
<p>Agreuh Gator indexe toutes les heures les flux RSS de son annuaire et vous propose d'effectuer des recherches par mots clef. Vous pourrez r&eacute;cup&eacute;rer le flux RSS de ces r&eacute;sultats de recherches.</p>
</div>
</div>

<div style="clear: both;">&nbsp;</div>
<h2>Les dernières niouzes : </h2>
<%
		BlogEntryManager bem = new BlogEntryManager();
		List<BlogEntry> list = bem.getBlogEntries(3);
		for(BlogEntry be : list) {
		%><div class="post">
<h2 class="title"><%= be.getTitle() %></h2>
<p class="meta"><small>Le <%= Constantes.simpleDateFormat.format(be.getDate()) %></small></p>
<div class="entry"><p><%= be.getResume() %></p>
<p class="meta"><small><a href="<%=request.getContextPath()%>/showNews?id=<%=be.getId() %>">Lire la suite</small></p>
</div></div>
		<%  } %>

</div>
<jsp:include page="sidebar.jsp" />
</div>
<jsp:include page="footer.jsp" />
</body>
</html>