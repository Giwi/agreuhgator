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
	<div class="post">
		<h2 class="title">Agreuh Gator v0.1</h2>
		<div class="entry">
<p>R&eacute;alis&eacute; par &copy; <a href="http://giwi.free.fr" target="_blank">Giwi Soft</a> 2009</p>
<p>Agreuh Gator indexe toutes les heures les flux RSS de son annuaire et vous propose d'effectuer des recherches par mots clef. Vous pourrez r&eacute;cup&eacute;rer le flux RSS de ces r&eacute;sultats de recherches.</p>
<p>Agreuh Gator utilise : </p>
<ul>
  <li><a href="http://lucene.apache.org" target="_blank">Apache Lucene</a></li>
  <li><a href="http://www.opensymphony.com/quartz/" target="_blank">Quartz</a></li>
  <li><a href="https://rome.dev.java.net" target="_blank">Rome</a></li>
  <li><a href="http://jquery.com" target="_blank">JQuery</a></li>
  <li><a href="http://java.fckeditor.net" target="_blank">FCKEditor</a></li>
</ul>
        </div></div>
        <div class="post">
<h2 class="title">ChangeLog</h2>
<p class="meta"><small>Le 27/09/2009</small></p>
<div class="entry">
<p>Version 0.1</p>
<ul>
	<li>Ajout de flux</li>
    <li>Indexation des flux toutes les heures</li>
    <li>Moteur de recherche et export de la requête en RSS</li>
    <li>Micro système de gestion d'utilisateurs</li>
    <li>Micro système de blog</li>
    </ul>
</div></div>
</div>
<jsp:include page="sidebar.jsp" />
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>