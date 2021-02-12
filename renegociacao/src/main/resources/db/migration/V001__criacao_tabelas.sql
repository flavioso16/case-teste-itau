create table contrato (
	id_contrato bigint not null auto_increment,
	id_produto bigint not null,
	id_cliente bigint not null,
	data_contrato datetime not null,
	primary key (id_contrato)
);

create table parcela (
	id_parcela bigint not null auto_increment,
	id_contrato bigint not null,
	data_vencimento datetime not null,
	status varchar(10) not null,
	valor decimal(10,2) not null,
	primary key (id_parcela),
	constraint fk_parcela_contrato foreign key (id_contrato) references contrato (id_contrato)
);