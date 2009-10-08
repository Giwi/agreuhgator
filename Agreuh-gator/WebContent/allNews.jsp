<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<%@page import="fr.giwi.agreugator.blog.bean.BlogEntry"%>
<%@page import="fr.giwi.agreugator.blog.dao.BlogEntryManager"%>
<%@page import="net.fckeditor.FCKeditor"%>
<%@page import="fr.giwi.agreugator.constantes.Constantes"%><html>
<head>
<title><%=application.getInitParameter("title")%> : Toutes les niouze</title>
<jsp:include page="common/meta.jsp" />


<script type="text/javascript">
function pageselectCallback(page_index, jq){
    // Get number of elements per pagionation page from form
	var num_entries = $('#hiddenresult div.result').length;
    var items_per_page = 5;
    var max_elem = Math.min((page_index+1) * items_per_page, num_entries);
    var newcontent = '';
	$('#Searchresult').empty();
    // Iterate through a selection of the content and build an HTML string
    for(var i= page_index * items_per_page; i < max_elem; i++)
    {
		 newcontent = $('#hiddenresult div.result:eq('+i+')').clone();
		$('#Searchresult').append(newcontent);
      
    }
    return false;
}
function initPagination() {
var num_entries = $('#hiddenresult div.result').length;

// Create pagination element
$("#Pagination").pagination(num_entries, {
num_edge_entries: 2,
num_display_entries: 10,
callback: pageselectCallback,
items_per_page:5,
next_text:"Suivant",
prev_text:"P&eacute;c&eacute;dent"


});
}
        
$(document).ready(function(){      
    initPagination();
});
</script>
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
			<div class="products_box">
					<h3 >Les Niouzes</h3>
			<div id="Pagination" class="pagination"></div>
		<br style="clear:both;" />
		<div id="Searchresult">
			This content will be replaced when pagination inits.
		</div>
		<div id="hiddenresult" style="display:none;">
		<%
		BlogEntryManager bem = new BlogEntryManager();
		List<BlogEntry> list = bem.getBlogEntries(0, Constantes.TYPE_TOUT);
		for(BlogEntry be : list) {
		%><div class="result">
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
		</div>
		<%  } %>
		</div>
		</div>
<div style=" clear:both;"></div></div>
    <!--end of right content-->
    </div>
    
<jsp:include page="common/footer.jsp" />
</div>
</body>
</html>