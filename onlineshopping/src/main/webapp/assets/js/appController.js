var app = angular.module('ShoppingApp', []);

app.controller('AppController', function($http) {
	
	var me = this;
		
	me.topSmartphone = [];
	me.topTablet = [];
	me.topLaptop = [];
	
	
	var jsonUrl = '';
	
	if (window.key == '') {
		if (window.categoryId == '') {
			jsonUrl = window.contextRoot + '/json/data/all/products';
		}
		else {
			jsonUrl = window.contextRoot + '/json/data/category/'+ window.categoryId +'/products';
		}
	} else {
		jsonUrl = window.contextRoot + '/json/data/search/'+ window.key +'/products';
	}
	
	
	me.fetchData = function() {
		
		
		$http.get('/onlineshopping/json/data/top/categoty/1')
			.then(function(response) {
				me.topSmartphone = response.data;
		});
		
		$http.get('/onlineshopping/json/data/top/categoty/2')
		.then(function(response) {
			me.topTablet = response.data;
		});
			
			
		$http.get('/onlineshopping/json/data/top/categoty/3')
		.then(function(response) {
			me.topLaptop = response.data;
		});
		
		$http.get(jsonUrl)
		.then(function(response) {
			me.listProducts = response.data;
		});
		
		$http.get('/onlineshopping/json/data/all/categorys')
		.then(function(response) {
			me.listCategoys = response.data;
		});
				
	}
	
});