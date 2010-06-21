<%@ include file="../common/init.jsp"%>
<form action="<portlet:actionURL><portlet:param name="action" value="prefs"/></portlet:actionURL>" method="POST">
<liferay-ui:success
	key='<%= portletConfig.getPortletName() + ".configOk" %>'
	message="conf.configOk" /> 
<h1>Param&eacute;trage de l'AgreuhGatorService</h1>
AgreuGator URL : <input type="text" name="url" value="<%= request.getAttribute("url") %>" />
<br />
<input type="submit" value="Valider" />
</form>