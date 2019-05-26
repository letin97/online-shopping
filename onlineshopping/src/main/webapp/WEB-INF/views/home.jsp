<!-- DataTable Bootstrap Script -->
<script src="${js}/angular.js"></script>

<!-- DataTable Bootstrap Script -->
<script src="${js}/appController.js"></script>

<div class="container" ng-app="ShoppingApp"
	ng-controller="AppController as aCtrl">

	<div ng-init="aCtrl.fetchData()">

		<div class="row">
			<div class="col-lg-8">
				<div id="carouselExampleIndicators" class="carousel slide my-4"
					data-ride="carousel">
					<ol class="carousel-indicators">
						<li data-target="#carouselExampleIndicators" data-slide-to="0"
							class="active"></li>
						<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
						<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
						<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
					</ol>
					<div class="carousel-inner" role="listbox">
						<div class="carousel-item active">
							<img class="d-block img-fluid" src="${images}/banner1.png"
								alt="First slide">
						</div>
						<div class="carousel-item">
							<img class="d-block img-fluid" src="${images}/banner2.png"
								alt="Second slide">
						</div>
						<div class="carousel-item">
							<img class="d-block img-fluid" src="${images}/banner3.png"
								alt="Third slide">
						</div>
						<div class="carousel-item">
							<img class="d-block img-fluid" src="${images}/banner4.png"
								alt="Third slide">
						</div>
					</div>
					<a class="carousel-control-prev" href="#carouselExampleIndicators"
						role="button" data-slide="prev"> <span
						class="carousel-control-prev-icon" aria-hidden="true"></span> <span
						class="sr-only">Previous</span>
					</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
						role="button" data-slide="next"> <span
						class="carousel-control-next-icon" aria-hidden="true"></span> <span
						class="sr-only">Next</span>
					</a>
				</div>
			</div>
			<div class="col-lg-4">
				<div class="row" style="padding-top: 25px">
					<img class="img-promotion" src="${images}/promotion1.png"
						alt="Third slide">
					</image>
					<img class="img-promotion" src="${images}/promotion2.png"
						alt="Third slide">
					</image>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-xs-12">
				<h3>Hot Smartphone</h3>
				<hr />
			</div>
		</div>

		<div class="row">
			<div class="col-lg-3 col-md-4 mb-3"
				ng-repeat="product in aCtrl.topSmartphone">
				<div class="card h-100">
					<a ng-href="${contextRoot}/show/{{product.id}}/product"> <img
						ng-src="${images}/{{product.code}}.jpg" alt="{{product.name}}"
						class="landingImg">
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

		<div class="row">
			<div class="col-xs-12">
				<h3>Hot Tablet</h3>
				<hr />
			</div>
		</div>

		<div class="row">
			<div class="col-lg-3 col-md-4 mb-3"
				ng-repeat="product in aCtrl.topTablet">
				<div class="card h-100">
					<a ng-href="${contextRoot}/show/{{product.id}}/product"> <img
						ng-src="${images}/{{product.code}}.jpg" alt="{{product.name}}"
						class="landingImg">
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

		<div class="row">
			<div class="col-xs-12">
				<h3>Hot Laptop</h3>
				<hr />
			</div>
		</div>

		<div class="row is-table-row">

			<div class="col-lg-3 col-md-4 mb-3"
				ng-repeat="product in aCtrl.topLaptop">
				<div class="card h-100">
					<a ng-href="${contextRoot}/show/{{product.id}}/product"> <img
						ng-src="${images}/{{product.code}}.jpg" alt="{{product.name}}"
						class="landingImg">
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
		<!-- /.row -->

	</div>


</div>