<div class="search_title"><img src="images/lock.png" align="top" /> Connexion</div>
<form name="login" action="<%=request.getContextPath()%>/login"	method="post">
<table width="100%" border="0" cellpadding="0" class="loginForm">
	<tr>
		<td>Login : </td>
		<td><input name="login" type="text" class="login_input"  /></td>
	</tr>
	<tr>
		<td>Mot de passe : </td>
		<td><input name="password" type="password" class="login_input" /></td>
	</tr>
	<tr>
		<td></td>
		<td><input type="submit" value="Go !" class="login_sublit"/></td>
	</tr>
</table>
</form>

