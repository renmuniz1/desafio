<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
	<head>
		<title>Desafio Task Manager</title>
		
			<spring:url value="css/app.css" var="appCss" />
			<spring:url value="css/bootstrap.min.css" var="bootstrapCss" />
			<link rel="stylesheet" type="text/css"  href="${bootstrapCss}">
			<link rel="stylesheet" type="text/css"  href="${appCss}">	
	</head>
	<body ng-app="taskControllerApp">
		<div class="generic-container" ng-controller="taskController">
			<div class="panel panel-default">
				<div class="panel-heading"><span class="lead">Task Manager Formulário </span></div>
				<div class="formcontainer">
					<form name="myForm" ng-submit="salvarRegistro()"  class="form-horizontal">
						<input type="hidden" ng-model="id" />
						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-1 control-lable" for="titulo-model">Título</label>
								<div class="col-md-7">
									<input type="text" id="titulo-model" ng-model="titulo" class="form-control input-sm" placeholder="Prencha o título"/>
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-1 control-lable" for="status-model">Descrição</label>
								<div class="col-md-7">
									<input type="text"  id="descricao-model" ng-model="descricao"  class="form-control input-sm" placeholder="Prencha a descrição"/>
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-1 control-lable" for="status-model">Status</label>
								<div class="col-md-7">
									<select class="form-control input-sm" ng-model="status">
										<option value="finalizada">Finalizada</option>
  										<option value="em andamaento">Andamento</option>
									</select>
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="form-actions floatLeft">
								<input type="submit" value="{{!id ? 'Salvar' : 'Alterar'}}" class="btn btn-warning btn-sm">
							</div>
						</div>
					</form>
				</div>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"><span class="lead">Lista de Tasks</span></div>
				<div class="tablecontainer">
					<table class="table table-hover">
						<thead>
							<tr>
							  <th>ID</th>
                              <th>Titulo</th>
                              <th>Descrição</th>
                              <th>Status</th>
							</tr>
						</thead>
						<tbody>
							<tr	ng-repeat="u in tasks">
							  <td><span>{{ u.id }}</span></td>
                              <td><span>{{ u.titulo }}</span></td>
                              <td><span>{{ u.titulo }}</span></td>
                              <td><span>{{ u.status }}</span></td>
                              <td>
                              	<button type="button" ng-click="alteraTask(u.id)" class="btn btn-success custom-width">Editar</button>  <button type="button" ng-click="removerTask(u.id)" class="btn btn-danger custom-width">Deletar</button>
                              </td>
                              <td>
                              </td>
							</tr>
						</tbody>
					</table>
					
				</div>
			</div>
		</div>	
		<spring:url value="javascript/taskController.js" var="taskControllerJs" />
		<spring:url value="javascript/angular.min.js" var="angularJs" />
		<script type="text/javascript" src="${angularJs}"></script>
		<script type="text/javascript" src="${taskControllerJs}"></script>
	</body>
</html>