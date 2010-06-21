<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ include file="../common/init.jsp"%>
<!-- Liste des flux -->
<%@page import="java.util.List"%>
<%@page import="fr.giwi.agreuhgatorservice.rssentrymanager.RssEntry"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<script type="text/javascript">

function <portlet:namespace/>addFeed() {
	document.getElementById("<portlet:namespace/>action").value="add";
	document.getElementById("<portlet:namespace/>fn").submit();
}

function <portlet:namespace/>delFeed(id) {
	document.getElementById("<portlet:namespace/>action").value="del";
	document.getElementById("<portlet:namespace/>id").value=id;
	document.getElementById("<portlet:namespace/>fn").submit();
}
</script>
<img src="<%=renderResponse.encodeURL(logoUrl.toString())%>" />
<h3>Administration des flux</h3>
<% List<RssEntry> listOffFeeds = (List<RssEntry>) request.getAttribute("listOfFeeds");
int delta = ParamUtil.getInteger(request, "delta");
if (delta == 0) {
	delta = 5;
}
if(listOffFeeds != null) {
%>
<form action="<portlet:actionURL></portlet:actionURL>" 
method="POST" name="<portlet:namespace/>fn" id="<portlet:namespace/>fn" >
<input type="hidden"  name="action" value="del" id="<portlet:namespace/>action"/>
<input type="hidden" name="id" value="" id="<portlet:namespace/>id"/> 

<liferay-ui:search-container emptyResultsMessage="noDataFound" delta="<%=delta%>" >
	<liferay-ui:search-container-results><%
		pageContext.setAttribute("results", listOffFeeds.subList(searchContainer.getStart(), Math.min(listOffFeeds.size(),  searchContainer.getEnd())));
		pageContext.setAttribute("total", listOffFeeds.size());
	%>
	</liferay-ui:search-container-results>
	<liferay-ui:search-container-row
		className="fr.giwi.agreuhgatorservice.rssentrymanager.RssEntry"
		modelVar="rss"
		keyProperty="id"
		>
		<%
			String action = "<input type='button' onclick='javascript:"+renderResponse.getNamespace()+"delFeed("+rss.getId()+");' value='Effacer' />";
		%>
		<liferay-ui:search-container-column-text
				name="titre"
				property="title"
			/>
		<liferay-ui:search-container-column-text
				name="description"
				property="description"
			/>
			<%
			String rssLink = "<a href=\""+rss.getUrl()+"\" title=\"Flux RSS\" target=\"_blank\"><img src=\""+renderResponse.encodeURL(rssUrl.toString())+"\" border=\"0\"/></a>";
			%>
		<liferay-ui:search-container-column-text
				name="url"
				value="<%= rssLink %>"
			/>
			<liferay-ui:search-container-column-text
				name="action"
				value="<%=action %>"
			/>
	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator />
	
</liferay-ui:search-container>
<input type="text" name="url" value="" id="<portlet:namespace/>url"/>
<input type='button' onclick='javascript:<portlet:namespace/>addFeed();' value='Ajouter' />
</form>
<% } %>