<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- DataTable Bootstrap Script -->
<script src="${js}/angular.js"></script>

<!-- DataTable Bootstrap Script -->
<script src="${js}/appController.js"></script>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" >
	<div class="container" >
		<a class="navbar-brand" href="${contextRoot}/">Online Shopping</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		
		<form id="search-site" action="${contextRoot}/search" method="get">
            <input class="topinput" id="search-keyword" name="key" type="text" placeholder="What do you want?" maxlength="50" />
            <button type="submit"><span class="glyphicon glyphicon-search"></span></button>
        </form>

		<div class="collapse navbar-collapse" id="navbarResponsive">
			
			<ul class="navbar-nav ml-auto" >
				
				<c:forEach var="category" items="${categories}">
				<li>
					<a class="nav-link" href="${contextRoot}/show/category/${category.id}/products">
                		<img src="${images}/${category.imageURL}.png" class="icon-nav"></img>
 						${category.name}
            		</a>
				</li>
				</c:forEach>	
				
				<security:authorize access="hasAuthority('ADMIN')">
					<li id="manageProducts">
						<a class="nav-link" href="${contextRoot}/manage/products">
						<img src="${images}/management.png" class="icon-nav"></img>
						Manage Products
						</a>
					</li>
					<li id="manageOrders">
						<a class="nav-link" href="${contextRoot}/manage/orders">
						<img src="${images}/order.png" class="icon-nav"></img>
						Manage Orders
						</a>
					</li>
				</security:authorize>
			</ul>
			
			<ul class="nav navbar-nav navbar-right">
				<security:authorize access="isAnonymous()">
					<li id="register">
						<a class="nav-link" href="${contextRoot}/register">Sign Up</a>
					</li>
					<li id="login">
						<a class="nav-link" href="${contextRoot}/login">Login</a>
					</li>
				</security:authorize>
				
				<security:authorize access="isAuthenticated()">
					<li class="dropdown" id="userCart">
						<a class="btn btn-default dropdown-toggle icon-tex" href="javascript:void(0)" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
							    ${userModel.fullName}
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<security:authorize access="hasAuthority('USER')">
								<li>
									<a href="${contextRoot}/cart/show">
										<span class="glyphicon glyphicon-shopping-cart"></span>
										<span class="badge">${userModel.cart.cartLines}</span> - &#8377; ${userModel.cart.grandTotal}
									</a>
								</li>
								<li>
									<a href="${contextRoot}/orders/show">
										Purchase history
									</a>
								</li>
							</security:authorize>
							<li class="divider" role="separator"> </li>
							<li>
								<a href="${contextRoot}/perform-logout">Logout</a>
							</li>
						</ul>
					</li>
				</security:authorize>
			</ul>
		</div>
	</div>
</nav>

<script>
	window.userRole = '${userModel.role}';
</script>
