<div class="container">

	<c:if test="${ not empty message}">
		<div class="alert aler-info">
			<h3 class="text-center">${message}</h3>
		</div>
	</c:if>

	<c:choose>
		<c:when test="${not empty orders}">
			<table id="cart" class="table table-hover table-condensed">
				<thead>
					<tr>
						<th style="width: 30%">Order Date</th>
						<th style="width: 10%">Order#</th>
						<th style="width: 10%">Quantity</th>
						<th style="width: 20%">Total Amount</th>
						<th style="width: 10%">Status</th>
						<th style="width: 10%"></th>
					</tr>
				</thead>
				<tbody>

					<c:forEach items="${orders}" var="order">
						<tr>
							<td data-th="Order Date">${order.orderDate}</td>
							<td data-th="Order#">${order.id}</td>
							<td data-th="Quantity">${order.orderCount}</td>
							<td data-th="Total Amount">&#8377; ${order.orderTotal}</td>
							<c:choose>
								<c:when test="${order.status == 0}">
									<td data-th="Status">Pending</td>
								</c:when>
								<c:otherwise>
									<td data-th="Status">Complete</td>
								</c:otherwise>
							</c:choose>
							<td class="actions" data-th=""><a
								href="${contextRoot}/orders/show/${order.id}/order"> View
									Detail </a></td>
						</tr>
					</c:forEach>


				</tbody>

			</table>
		</c:when>

		<c:otherwise>
			<div class="jumbotron">
				<div class="text-center">
					<h2>Your order is empty!</h2>
				</div>
			</div>
		</c:otherwise>
	</c:choose>

</div>