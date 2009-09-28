<div id="header">
	<form id="search" action="<%= request.getContextPath() %>/search" method="post">
		<fieldset>
		<legend>Recherche</legend>
		<input id="query" type="text" name="query" value="<%=(request.getParameter("query")!=null)?request.getParameter("query"):"" %>" />
		<input id="searchsubmit" type="submit" value="Agreuh!" />
		</fieldset>
	</form>
	<h1><a href="index.jsp">Agreuh Gator</a></h1>
	<h2>Pur&eacute;e de RSS</h2>
</div>
