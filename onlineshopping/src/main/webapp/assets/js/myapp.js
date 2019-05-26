$(function() {
	
	// solving the active menu problem
	switch (menu) {

	case 'Home':
		$('#home').addClass('active');
		break;
	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	case 'All Products':
		$('#listProducts').addClass('active');
		break;
	case 'User Cart':
		$('#userCart').addClass('active');
		break;
	case 'Manage Products':
		$('#manageProducts').addClass('active');
		break;
	case 'Manage Orders':
		$('#manageOrders').addClass('active');
		break;
	default:
		$('#listProducts').addClass('active');
		$('#a_' + menu).addClass('active');
		break;
	}
	
	// for handling CSRF token
	var token = $('meta[name="_csrf"]').attr('content');
	var header = $('meta[name="_csrf_header"]').attr('content');
	
	if((token!=undefined && header !=undefined) && (token.length > 0 && header.length > 0)) {		
		// set the token header for the ajax request
		$(document).ajaxSend(function(e, xhr, options) {			
			xhr.setRequestHeader(header,token);			
		});				
	}
	
	// code for jquery dataTable
	var $table = $('#productListTable');
	
	if ($table.length) {

		var jsonUrl = '';
		if (window.categoryId == '') {
			jsonUrl = window.contextRoot + '/json/data/all/products';
		}
		else {
			jsonUrl = window.contextRoot + '/json/data/category/'+ window.categoryId +'/products';
		}
		$table.DataTable({
			lengthMenu: [[3,5,10,-1], ['3 Records', '5 Records', '10 Records', 'ALL']],
			pageLength: 5,
			ajax: {
				url: jsonUrl,
				dataSrc: ''
			},
			columns: [
				{
					data: 'code',
					bSortable: false,
					mRender: function(data, type, row) {
						return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="dataTableImg"/>';
					}
				},
				{
					data: 'name',
				},
				{
					data: 'brand',
				},
				{
					data: 'unitPrice',
					mRender: function(data, type, row) {
						return '&#8377; ' + data;
					}
				},
				{
					data: 'quantity',
					mRender: function(data, type, row) {
						if (data < 1){
							return '<span style="color:red">Out of Stock!</span>';
						}
						return data;
					}
				},
				{
					data: 'id',
					bSortable: false,
					mRender: function(data, type, row) {
						var str = '';
						str += '<a href="'+window.contextRoot+'/show/'+data+'/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a>';
						if (window.userRole == 'ADMIN'){
							str += '<a href="'+window.contextRoot+'/manage/'+data+'/product" class="btn btn-warning"><span class="glyphicon glyphicon-pencil"></span></a>';
						} 
						else {
							if (row.quantity < 1) {
								str += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
							}
							else {
								str += '<a href="'+window.contextRoot+'/cart/add/'+data+'/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
							}
						}						
						return str;
					}
				}
			]
		});
	}
	
	//dismiss alert after 3 seconds
	var $alert = $('.alert');
	if ($alert.length) {
		setTimeout(function() {
			$alert.fadeOut('slow');
		}, 3000)
	}
	
	
	// dataTable admin
	// code for jquery dataTable
	var $adminProductsTable = $('#adminProductsTable');
	
	if ($adminProductsTable.length) {
		

		var jsonUrl = window.contextRoot + '/json/data/admin/all/products';
		
		$adminProductsTable.DataTable({
			lengthMenu: [[10,30,50,-1], ['10 Records', '30 Records', '50 Records', 'ALL']],
			pageLength: 10,
			ajax: {
				url: jsonUrl,
				dataSrc: ''
			},
			columns: [
				{
					data: 'id'
				},
				{
					data: 'code',
					bSortable: false,
					mRender: function(data, type, row) {
						return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="adminDataTableImg"/>';
					}
				},
				{
					data: 'name',
				},
				{
					data: 'brand',
				},
				{
					data: 'quantity',
					mRender: function(data, type, row) {
						if (data < 1){
							return '<span style="color:red">Out of Stock!</span>';
						}
						return data;
					}
				},
				{
					data: 'unitPrice',
					mRender: function(data, type, row) {
						return '&#8377; ' + data;
					}
				},
				{
					data: 'active',
					bSortable: false,
					mRender: function(data, type, row) {
						var str = '';
						str += '<label class="switch">';
						if (data) {
							str += '<input type="checkbox" checked="checked" value="'+row.id+'"/>';
						}
						else {
							str += '<input type="checkbox" value="'+row.id+'"/>';
						}
						
						str +=	'<div class="slider"/></label>';
						return str;
					}
				},
				{
					data: 'id',
					bSortable: false,
					mRender: function(data, type, row) {
						var str = '';
						str += '<a href="'+window.contextRoot+'/manage/' +data+ '/product" class="btn btn-warning">';
						str += '<span class="glyphicon glyphicon-pencil"></span></a>';
						return str;
					}
				}
			],
			initComplete: function() {
				var api = this.api();
				api.$('.switch input[type="checkbox"]').on('change', function() {
					var checkbox = $(this);
					var checked = checkbox.prop('checked');
					var dMsg = (checked)? 'You want to activate the prodcut?':
										  'You want to deactivate the product?';
					var value = checkbox.prop('value');
					
					bootbox.confirm({
						size: 'medium',
						title: 'Prodcut Activate & Deactivate',
						message: dMsg,
						callback: function(confirm) {
							if (confirm) {
								var activationUrl = window.contextRoot + '/manage/product/'+value+'/activation';
								$.post(activationUrl, function(data) {
									bootbox.alert({
										size: 'medium',
										title: 'Information',
										message: data						
									});
								})
							}
							else {
								checkbox.prop('checked', !checked);
							}
						}
					})	
				})
			}
		});
	}
	
	// validation category
	var $categoryForm = $('#categoryForm');
	
	if ($categoryForm.length) {
		$categoryForm.validate({
			rules: {
				name: {
					required: true,
					minlength: 2
				},
				description: {
					required: true
				}		
			},
			messages: {
				name: {
					required: 'Please add the category name!',
					minlength: 'Category name should not be less than 2 characters'
				},
				description: {
					required: 'Please add a description for this category!'
				}
			},
			errorElement: 'em',
			errorPlacement: function(error, element) {
				error.addClass('help-block');
				error.insertAfter(element);
			}
		})
	}
	
	
	// validation login
	var $loginForm = $('#loginForm');
	
	if ($loginForm.length) {
		$loginForm.validate({
			rules: {
				username: {
					required: true,
					email: 2
				},
				password: {
					required: true
				}		
			},
			messages: {
				username: {
					required: 'Please enter the email!',
					email: 'Please enter valid email address!'
				},
				password: {
					required: 'Please enter password!'
				}
			},
			errorElement: 'em',
			errorPlacement: function(error, element) {
				error.addClass('help-block');
				error.insertAfter(element);
			}
		})
	}
	
	
	//----------------------------------------
	// handling click refresh cart button
	$('button[name="refreshCart"]').click(function() {
		
		var cartLineId = $(this).attr('value');
		var countElement = $('#count_' + cartLineId);
		
		var originalCount = countElement.attr('value');
		var currentCount = countElement.val();
		
		if (currentCount !== originalCount) {
			if (currentCount < 1 || currentCount > 3){
				countElement.val(originalCount);
				bootbox.alert({
					size: 'medium',
					title: 'Error',
					message: 'Product count should be minimum 1 and maxium 3!'
				});
			} else {
				var updateUrl = window.contextRoot + '/cart/' + cartLineId + '/update?count=' + currentCount;
				window.location.href = updateUrl;
			}
		}
	})
	
	// TABLE MANAGE ORDER
	// code for jquery dataTable
	var $adminOrdersTable = $('#adminOrdersTable');
	
	if ($adminOrdersTable.length) {
		

		var jsonUrl = window.contextRoot + '/json/data/admin/all/orders';
		
		$adminOrdersTable.DataTable({
			lengthMenu: [[10,30,50,-1], ['10 Records', '30 Records', '50 Records', 'ALL']],
			pageLength: 10,
			ajax: {
				url: jsonUrl,
				dataSrc: ''
			},
			columns: [
				{
					data: 'id',
					mRender: function(data, type, row) {
						return '&#35;' + data + ' ' + row.user.firstName + ' ' + row.user.lastName;
					}
				},
				{
					data: 'user',
					mRender: function(data, type, row) {
						return data.email;
					}
				},
				{
					data: 'orderDate',
				},
				{
					data: 'status',
					mRender: function(data, type, row) {
						if (data === 1) {
							return '<span class="glyphicon glyphicon-ok" style="color:green"></span>';
						}
						else if (data === 0) {
							return '<span class="glyphicon glyphicon-inbox" style="color:yellow"></span> ';
						}
						else if (data === -1) {
							return '<span class="glyphicon glyphicon-remove" style="color:red"></span> ';
						}
					}
				},
				{
					data: 'shipping',
					mRender: function(data, type, row) {
						return data.addressLineOne + ", " + data.addressLineTwo + ", " + data.city + ", " + data.country;
					}
				},
				{
					data: 'orderTotal',
					mRender: function(data, type, row) {
						return '&#8377; ' + data;
					}
				},
				
				{
					data: 'id',
					bSortable: false,
					mRender: function(data, type, row) {
						var str = '';
						str += '<a href="'+window.contextRoot+'/orders/show/' +data+ '/order">';
						str += 'View Detail</a>';
						return str;
					}
				}
			]
		});
	}
		
});