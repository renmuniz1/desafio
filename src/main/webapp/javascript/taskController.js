/*
* Controller utilizado pelo angular.js
*/

var taskControllerApp = angular.module("taskControllerApp",[]);

taskControllerApp.controller("taskController",function($scope, $window, $http){
	
	
	
	$scope.id = null;
	$scope.titulo = null;
	$scope.descricao = null;
	$scope.status = null;
	$scope.tasks = new Array();
	$scope.reloadPage = function(){$window.location.reload();}
	
	$scope.salvarRegistro = function(){
		var task = new Object();
		task.id = $scope.id;
		task.titulo = $scope.titulo;
		task.status = $scope.status;
		task.descricao = $scope.descricao;
		
		var response = $http.post("salvar",task);
		response.success(function(data, status, headers, config){
			
		});
		
		$scope.carregarLista();
		
		$scope.reloadPage();
	}
	
	$scope.carregarLista = function(){
		
		var response = $http.get("carregaLista");
		
		response.success(function(data, status, headers, config){
			$scope.tasks = data;
		});
	}
	
	$scope.alteraTask = function(id){
		var response = $http.get("carregaTask/" + id);
		
		response.success(function(data, status, headers, config){
			$scope.id = data.id;
			$scope.titulo = data.titulo;
			$scope.status = data.status;
			$scope.descricao = data.descricao;
			
		});
		
	}
	
	$scope.removerTask = function(id){
		var response = $http.get("removerTask/" + id);
		
		response.success(function(data, status, headers, config){
			$scope.id = null;
			$scope.titulo = null;
			$scope.status = null;
			$scope.descricao = null;
			
		});
		
		$scope.carregarLista();
		
		$scope.reloadPage();
		
	}
	
	$scope.carregarLista();
	
})