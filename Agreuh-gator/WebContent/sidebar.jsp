<%@page import="fr.giwi.agreugator.helpers.RSSHelper"%>
<%@page import="java.util.List"%>
<%@page import="fr.giwi.agreugator.dao.SaveFactory"%>

<%@page import="fr.giwi.agreugator.blog.dao.BlogEntryManager"%>
<%@page import="fr.giwi.agreugator.blog.bean.BlogEntry"%>
<%@page import="fr.giwi.agreugator.constantes.Constantes"%>
<div id="sidebar">
<ul>

	<li>
	<h2>Les niouzes</h2>
	<ul>
	
	<%
		BlogEntryManager bem = new BlogEntryManager();
		List<BlogEntry> list = bem.getBlogEntries(5);
		for(BlogEntry be : list) {
		%><li>
		<a href="<%=request.getContextPath()%>/showNews?id=<%=be.getId() %>" ><%= be.getTitle() %> 
		
		(<%= Constantes.simpleDateFormat.format(be.getDate()).substring(0, 10) %>)
		
		</a>
		</li>
		<%  } %>
	</ul>
	</li>
	<li>
	<h2>Flux répertoriés</h2>
	<ul>
		<li><%= SaveFactory.getSaveObject().getItems().size()%> Flux inscrits</li>
	</ul>
	</li>
	<%
if(session.getAttribute("user") != null) {
	%>
	<li>
	<h2>Menu admin</h2>
	<ul>
  <li><a href="addrss.jsp">Ajout d'un flux</a></li>
  <li><a href="addnews.jsp">Gestion des niouzes</a></li>
</ul>
	</li>
	<%
}
%>
</ul>
</div>