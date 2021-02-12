insert into contrato (id_contrato, id_produto, id_cliente, data_contrato) values (1, 1, 1,  SYSDATE());
insert into parcela (id_contrato, data_vencimento, status, valor) values (1, SYSDATE(), 'PAGA', 1321.22);
insert into parcela (id_contrato, data_vencimento, status, valor) values (1, SYSDATE(), 'PENDENTE', 1321.22);
insert into parcela (id_contrato, data_vencimento, status, valor) values (1, SYSDATE(), 'PENDENTE', 1321.22);
insert into parcela (id_contrato, data_vencimento, status, valor) values (1, SYSDATE(), 'PENDENTE', 1321.22);
insert into parcela (id_contrato, data_vencimento, status, valor) values (1, SYSDATE(), 'PENDENTE', 1321.22);

insert into contrato (id_contrato, id_produto, id_cliente, data_contrato) values (2, 1, 1,  SYSDATE());
insert into parcela (id_contrato, data_vencimento, status, valor) values (2, SYSDATE(), 'ATRASADA', 1321.22);
insert into parcela (id_contrato, data_vencimento, status, valor) values (2, SYSDATE(), 'ATRASADA', 1321.22);
insert into parcela (id_contrato, data_vencimento, status, valor) values (2, SYSDATE(), 'ATRASADA', 1321.22);
insert into parcela (id_contrato, data_vencimento, status, valor) values (2, SYSDATE(), 'PENDENTE', 1321.22);
insert into parcela (id_contrato, data_vencimento, status, valor) values (2, SYSDATE(), 'PENDENTE', 1321.22);

insert into contrato (id_contrato, id_produto, id_cliente, data_contrato) values (3, 2, 2,  SYSDATE());
insert into parcela (id_contrato, data_vencimento, status, valor) values (3, SYSDATE(), 'PENDENTE', 950);
insert into parcela (id_contrato, data_vencimento, status, valor) values (3, SYSDATE(), 'PENDENTE', 950);
insert into parcela (id_contrato, data_vencimento, status, valor) values (3, SYSDATE(), 'PENDENTE', 950);


