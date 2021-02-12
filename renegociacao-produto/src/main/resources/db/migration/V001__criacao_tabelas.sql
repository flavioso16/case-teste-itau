create table produto (
	id_produto bigint not null auto_increment,
	nome varchar(100) not null,
	tipo varchar(50) not null,
	descricao varchar(100) not null,
	taxa_anual decimal(5,2) not null,
	taxa_mensal decimal(5,2) not null,
	valor_multa_atraso decimal(10,2) not null,
	taxa_diaria_atraso decimal(5,2) not null,
	primary key (id_produto)
);
