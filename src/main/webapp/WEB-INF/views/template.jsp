<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${page}</title>

	<%@ include file="include/styles.jsp" %>
</head>
 <body class="page-container-bg-solid page-md">

	<div class="page-header">
  			<%@ include file="include/header.jsp" %>
  	</div>
  	
    <jsp:include page="module/${page}.jsp" />
	
   	<%@ include file="include/footer.jsp" %>
   <%@ include file="include/scripts.jsp" %>
   
   	<script src="/resources/js/${page}.js" type="text/javascript"></script>
   		
   	<script>
   		$(document).ready(function()
   		{
   			$('.nav-bar li').removeClass('active');
   			$('.${page}').addClass('active');
   			
   		});
   	</script>
   	 <script src="/resources/assets/pages/scripts/components-multi-select.js" type="text/javascript"></script>
   	  
</body>
</html>	