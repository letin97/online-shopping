<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<div class="container">

	<h3>Order Management</h3>
	<hr />

	<div class="row">

		<c:if test="${not empty message}">
			<div class="col-xs-12">
				<div class="alert alert-success alert-dismissible">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					${message}
				</div>
			</div>
		</c:if>

		<div class="col-md-12">
			<div style="overflow: auto">
				<table id="adminOrdersTable" class="table table-striped">
					<thead>
						<tr>
							<th>Order</th>
							<th>Email</th>
							<th>Date</th>
							<th>Status</th>
							<th>Ship to</th>
							<th>Total</th>
							<th>&#160;</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th>Order</th>
							<th>Email</th>
							<th>Date</th>
							<th>Status</th>
							<th>Ship to</th>
							<th>Total</th>
							<th>&#160;</th>
						</tr>
					</tfoot>
				</table>
			</div>

		</div>
	</div>
</div>