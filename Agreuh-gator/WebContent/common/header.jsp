<div id="header">
	<div id="logo"><a href="index.jsp"><img src="images/logo.png" border="0" /></a> Pur&eacute;e de RSS</div>
	<!--  <div id="menu">
		<ul>
			<li><a href="index.jsp">Accueil</a></li>
			<li></li>
		</ul>
	</div> -->
</div>

<div class="green_box">

	<div class="clock"><img src="images/clock.png" alt="" /></div>
	<div class="text_content">
		<h1 class="title">Bienvenue sur Agreuh Gator!</h1>
		<p class="green"><b>Le salad-Mix du RSS</b> <br />
			Agreuh Gator indexe toutes les heures les flux RSS de son annuaire et
			vous propose d'effectuer des recherches par mots clef. Vous pourrez
			r&eacute;cup&eacute;rer le flux RSS de ces r&eacute;sultats de
			recherches.</p>
	</div>
	<div id="right_nav">
	<ul>
    <li><a href="index.jsp"><span>Accueil</span></a></li>
     <li><a href="allNews.jsp"><span>Les Niouzes</span></a></li>
    </ul>
		<%
		if (session.getAttribute("user") != null) {
		%>
			<ul>
  <li><a href="addrss.jsp">Gestion des flux</a></li>
  <li><a href="addnews.jsp">Gestion des niouzes</a></li>
  <li><a href="logout.jsp"><span>D&eacute;connexion</span></a></li>
</ul>
		<%
		}
		%>
	</div>
</div><!--end of green box-->

