<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="br.com.projeto.model.Contato"%>
<%@ page import="java.util.ArrayList"%>
<%
	@SuppressWarnings("unchecked")
	ArrayList<Contato> lista = (ArrayList<Contato>) request.getAttribute("contatos");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
	<meta charset="utf-8"/>
	<title>Contatos Web - Agenda</title>
	<link rel="icon" href="assets/img/icon-cadastro.png">
	<link rel="stylesheet" href="assets/css/style.css">
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<div class="align_elements_center">
			<h1>Agenda de Contatos</h1>
		</div>
	
		<table class="table table-bordered border-primary">
			<thead>
				<tr>
					<th scope="col" class="align_elements_center">Id</th>
					<th scope="col">Nome</th>
					<th scope="col">Telefone</th>
					<th scope="col">Contato</th>
					<th scope="col" class="align_elements_center">Opções</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (int i = 0; i < lista.size(); i++) {
				%>
				<tr>
					<td class="align_elements_center"><%=lista.get(i).getId()%></td>
					<td><%=lista.get(i).getNome()%></td>
					<td><%=lista.get(i).getTelefone()%></td>
					<td><%=lista.get(i).getCelular()%></td>
					<td class="col-md-2 align_elements_center">
						<a href="vieweditar?id=<%=lista.get(i).getId()%>" class="btn btn-primary">Editar</a> 
						<a href="javascript: confirmar(<%=lista.get(i).getId()%>)" class="btn btn-danger">Excluir</a>
					</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		<div class="row marging_10px">
			<div class="col align_elements_right">
				<a href="viewnovo" class="btn btn-primary">Novo</a>
				<a href="impressao" class="btn btn-secondary">Relatório</a>
			</div>
		</div>
	</div>
	<script src="assets/js/confirmador.js"></script>
</body>
</html>