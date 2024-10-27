<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Contatos Web - Novo</title>
	<link rel="icon" href="assets/img/icon-cadastro.png">
	<link rel="stylesheet" href="assets/css/style.css">
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<h1>Adicionar Contato</h1>
		<form name="frmContato" action="inserir">
			<div class="row mb-1">
				<label for="nome" class="col-sm-1 col-form-label">Nome</label>
				<div class="col-sm-5">
					<input type="text" name="nome" class="form-control" placeholder="Nome" required>
					<div class="invalid-feedback">
				        Por favor, informe corretamente seu NOME.
				    </div>
				</div>
			</div>			
		    <div class="row mb-1">
				<label for="telefone" class="col-sm-1 col-form-label">Telefone</label>
				<div class="col-sm-2">
					<input type="text" name="telefone" class="form-control" placeholder="Telefone" required>
					<div class="invalid-feedback">
				        Por favor, informe corretamente seu número de TELEFONE.
				    </div>
				</div>
			</div>
		    
			<div class="row mb-1">
				<label for="celular" class="col-sm-1 col-form-label">Celular</label>
				<div class="col-sm-2">
					<input type="text" name="celular" class="form-control" placeholder="Celular" required>
					<div class="invalid-feedback">
				        Por favor, informe corretamente seu número de CELULAR.
				    </div>
				</div>
			</div>

			<input type="submit" value="Adicionar" class="btn btn-primary">
		</form>
	</div>
	<script src="assets/js/validador.js"></script>
</body>
</html>