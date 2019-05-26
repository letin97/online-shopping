<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="css" value="/resources/css"></spring:url>
<spring:url var="js" value="/resources/js"></spring:url>
<spring:url var="images" value="/resources/images"></spring:url>

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">

<title>Online Shopping - ${title}</title>

<script>
	window.menu = '${title}';
	window.contextRoot = '${contextRoot}';	
</script>

<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">
<link href="${css}/glyphicon.css" rel="stylesheet">

<!-- Bootstrap DataTable -->
<link href="${css}/dataTables.bootstrap4.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${css}/myapp.css" rel="stylesheet">

</head>

<body>

	<div class="wrapper">

		<!-- Navigation -->
		<%@include file="./shared/navbar.jsp"%>

		<!-- Page Content -->
		<div class="content">

			<!-- Loading the home content -->
			<c:if test="${userClickHome == true }">
				<%@include file="home.jsp"%>
			</c:if>

			<!-- User click about -->
			<c:if test="${userClickAbout == true }">
				<%@include file="about.jsp"%>
			</c:if>

			<!--  User click contact -->
			<c:if test="${userClickContact == true }">
				<%@include file="contact.jsp"%>
			</c:if>

			<!--  User click view all product or category-->
			<c:if
				test="${userClickAllProducts == true or userClickCategoryProducts == true or userClickSearch == true}">
				<%@include file="listProducts.jsp"%>
			</c:if>
			
			<!--  User click view product-->
			<c:if
				test="${userClickShowProduct == true}">
				<%@include file="singleProduct.jsp"%>
			</c:if>
			
			<!--  User click manage product-->
			<c:if
				test="${userClickManageProducts == true}">
				<%@include file="manageProducts.jsp"%>
			</c:if>
			
			<!--  User click show cart-->
			<c:if
				test="${userClickShowCart == true}">
				<%@include file="cart.jsp"%>
			</c:if>
			
			<!--  User click show order-->
			<c:if
				test="${userClickShowOrder == true}">
				<%@include file="order.jsp"%>
			</c:if>
			
			<!--  User click show order detail-->
			<c:if
				test="${userClickShowOrderItem == true}">
				<%@include file="singleOrder.jsp"%>
			</c:if>
			
			<!--  User click manage order-->
			<c:if
				test="${userClickManageOrders == true}">
				<%@include file="manageOrders.jsp"%>
			</c:if>

		</div>

		<!-- Footer -->
		<%@include file="./shared/footer.jsp"%>

		<!-- Bootstrap core JavaScript -->
		<script src="${js}/jquery.min.js"></script>
		<script src="${js}/jquery.validate.js"></script>
		
		<script src="${js}/bootstrap.bundle.min.js"></script>

		<!-- DataTable -->
		<script src="${js}/jquery.dataTables.js"></script>
		<script src="${js}/dataTables.bootstrap4.js"></script>
		
		<!-- Bootbox -->
		<script src="${js}/bootbox.min.js"></script>

		<script src="${js}/myapp.js"></script>
	</div>
</body>

</html>
