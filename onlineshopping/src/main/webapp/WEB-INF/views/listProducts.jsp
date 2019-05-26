<!-- DataTable Bootstrap Script -->
<script src="${js}/angular.js"></script>

<!-- DataTable Bootstrap Script -->
<script src="${js}/appController.js"></script>

<div class="container" ng-app="ShoppingApp" ng-controller="AppController as aCtrl" >

	<div class="row" ng-init="aCtrl.fetchData()">
	
	<div class="row">

		<div class="col-md-12">
			<div class="row">
				<div class="col-lg-12">
					<c:if test="${userClickAllProducts == true}">

						<script>
							window.categoryId = '';
						</script>

						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="${contextRoot}/">Home</a></li>
							<li class="breadcrumb-item active">All Products</li>
						</ol>
					</c:if>

					<c:if test="${userClickCategoryProducts == true}">

						<script>
							window.categoryId = '${category.id}';
							window.key = '';
						</script>

						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="${contextRoot}/">Home</a></li>
							<li class="breadcrumb-item active">Category</li>
							<li class="breadcrumb-item active">${category.name}</li>
						</ol>
					</c:if>
					
					<c:if test="${userClickSearch == true}">

						<script>
							window.key = '${key}';
						</script>

					</c:if>
					
				</div>
			</div>

			<div class="row">
				<div class="col-lg-3 col-md-4 mb-3" ng-repeat="product in aCtrl.listProducts">
					<div class="card h-100">
						<a ng-href="${contextRoot}/show/{{product.id}}/product">
							<img ng-src="${images}/{{product.code}}.jpg" alt="{{product.name}}" class="landingImg">	
						</a>
						<div class="card-body">
							<h4 class="card-title">
								<a ng-href="${contextRoot}/show/{{product.id}}/product">{{product.name}}</a>
							</h4>
							<h5>&#8377; {{product.unitPrice}}</h5>
							<p class="card-text giveMeEllipsis">{{product.description}}</p>
						</div>
					</div>
				</div>
			</div>
			
		</div>
	</div>
</div>