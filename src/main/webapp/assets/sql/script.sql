# Criando Banco de Dados
CREATE DATABASE contato;

# Apagando tabela
DROP TABLE contatos;

# Definindo o Banco de Dados que será utilizado
USE contato;

# Criando a tabela do Banco de Dados
create table contatos(
    id int auto_increment,
    nome varchar(60) not null,
    telefone varchar(13) not null,
    celular varchar(14) not null,

    constraint id_habito primary key (id)
);

# Inserindo registros na tabela
insert into contatos (nome, telefone, celular) values ('Rodrigo Maiel Ferreira', '(65)3652-9852', '(65)99547-3572');
insert into contatos (nome, telefone, celular) values ('Maria Fernandes da Silva', '(65)3342-9823', '(65)99347-3234');
insert into contatos (nome, telefone, celular) values ('Victória Gomes de Souza', '(65)3452-3452', '(65)98754-3452');

# Consultando os Registros
Select * from contatos;
