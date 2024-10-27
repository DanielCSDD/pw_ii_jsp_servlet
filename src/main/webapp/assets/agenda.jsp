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
	<title>Cadastro de Contatos</title>
	<link rel="icon" href="../assets/img/icon-cadastro.png"/>
	<link rel="stylesheet" href="../assets/css/style.css"/>
</head>
<body>
	<h1>Agenda de Contatos</h1>
	<a href="pages/cadastro/novo.html" class="Botao1">Novo contato</a>
	<a href="report" class="Botao2">Relatório</a>
	<table id="tabela">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Telefone</th>
				<th>Contato</th>
				<th>Opções</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (int i = 0; i < lista.size(); i++) {
			%>
			<tr>
				<td><%=lista.get(i).getId()%></td>
				<td><%=lista.get(i).getNome()%></td>
				<td><%=lista.get(i).getTelefone()%></td>
				<td><%=lista.get(i).getCelular()%></td>
				<td>
					<a href="select?id=<%=lista.get(i).getId()%>" class="Botao1">Editar</a> 
					<a href="javascript: confirmar(<%=lista.get(i).getId()%>)" class="Botao2">Excluir</a>
				</td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
	<script src="../assets/js/confirmador.js"></script>
</body>
</html>