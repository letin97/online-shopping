<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<div class="container">
	<div class="row">

		<div class="col-md-12">
			<div class="invoice-title">
				<h2>Invoice</h2>
				<h3 class="pull-right">Order #${orderDetail.id}</h3>
			</div>
			<hr>
			<div class="row">
				<div class="col-md-3">
					<address>
						<strong>Billed To:</strong><br> ${orderDetail.user.firstName}
						${orderDetail.user.lastName}<br>
						${orderDetail.billing.addressLineOne}<br>
						${orderDetail.billing.addressLineTwo}<br>
						${orderDetail.billing.city} - ${orderDetail.billing.postalCode}<br>
						${orderDetail.billing.state} - ${orderDetail.billing.country}
					</address>
				</div>
				<div class="col-md-3 text-right">
					<address>
						<strong>Shipped To:</strong><br>
						${orderDetail.user.firstName} ${orderDetail.user.lastName}<br>
						${orderDetail.shipping.addressLineOne}<br>
						${orderDetail.shipping.addressLineTwo}<br>
						${orderDetail.shipping.city} - ${orderDetail.shipping.postalCode}<br>
						${orderDetail.shipping.state} - ${orderDetail.shipping.country}
					</address>
				</div>
				<div class="col-md-3">
					<address>
						<strong>Payment Method:</strong><br> Card Payment <br>
						${orderDetail.user.email}
					</address>
				</div>
				<div class="col-md-3 text-right">
					<address>
						<strong>Order Date:</strong><br> ${orderDetail.orderDate}<br>
						<br>
					</address>
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						<strong>Order summary</strong>
					</h3>
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-condensed">
							<thead>
								<tr>
									<td><strong>Item</strong></td>
									<td class="text-center"><strong>Price</strong></td>
									<td class="text-center"><strong>Quantity</strong></td>
									<td class="text-right"><strong>Totals</strong></td>
								</tr>
							</thead>
							<tbody>
								<!-- foreach ($order->lineItems as $line) or some such thing here -->
								<c:forEach items="${orderDetail.orderItems}" var="orderItem">
									<tr>
										<td>${orderItem.product.name}</td>
										<td class="text-center">&#8377; ${orderItem.buyingPrice}</td>
										<td class="text-center">${orderItem.productCount}</td>
										<td class="text-right">&#8377; ${orderItem.total}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

	<security:authorize access="hasAuthority('ADMIN')">
		<a href="${contextRoot}/manage/order/${orderDetail.id}/cancel"
			class="btn btn-danger" style="float: right;"> <span
			class="glyphicon glyphicon-remove-circle"></span> Cancel
		</a>
		<c:choose>
			<c:when test="${orderDetail.status > 0}">
				<a href="javascript:void(0)" class="btn btn-success disabled"
					style="float: right;"><span
					class="glyphicon glyphicon-ok-circle"></span>Completed </a>
			</c:when>
			<c:otherwise>
				<a href="${contextRoot}/manage/order/${orderDetail.id}/activation"
					class="btn btn-success" style="float: right;"> <span
					class="glyphicon glyphicon-ok-circle"></span>Completed
				</a>
			</c:otherwise>

		</c:choose>
	</security:authorize>
</div>