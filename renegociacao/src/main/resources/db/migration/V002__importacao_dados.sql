insert into contrato (id_produto, id_cliente, data_contrato) values (1, 1,  SYSDATE);
insert into parcela (id_contrato, data_vencimento, status, valor) values ((SELECT max(id_contrato) FROM contrato), SYSDATE(), 'PAGA', 1321.22);
insert into parcela (id_contrato, data_vencimento, status, valor) values ((SELECT max(id_contrato) FROM contrato), SYSDATE() + 30, 'PENDENTE', 1321.22);
insert into parcela (id_contrato, data_vencimento, status, valor) values ((SELECT max(id_contrato) FROM contrato), SYSDATE() + 60, 'PENDENTE', 1321.22);
insert into parcela (id_contrato, data_vencimento, status, valor) values ((SELECT max(id_contrato) FROM contrato), SYSDATE() + 90, 'PENDENTE', 1321.22);
insert into parcela (id_contrato, data_vencimento, status, valor) values ((SELECT max(id_contrato) FROM contrato), SYSDATE() + 120, 'PENDENTE', 1321.22);

insert into contrato (id_produto, id_cliente, data_contrato) values (1, 1,  SYSDATE);
insert into parcela (id_contrato, data_vencimento, status, valor) values ((SELECT max(id_contrato) FROM contrato), SYSDATE() - 90, 'ATRASADA', 1321.22);
insert into parcela (id_contrato, data_vencimento, status, valor) values ((SELECT max(id_contrato) FROM contrato), SYSDATE() - 60, 'ATRASADA', 1321.22);
insert into parcela (id_contrato, data_vencimento, status, valor) values ((SELECT max(id_contrato) FROM contrato), SYSDATE() - 30, 'ATRASADA', 1321.22);
insert into parcela (id_contrato, data_vencimento, status, valor) values ((SELECT max(id_contrato) FROM contrato), SYSDATE() + 0, 'PENDENTE', 1321.22);
insert into parcela (id_contrato, data_vencimento, status, valor) values ((SELECT max(id_contrato) FROM contrato), SYSDATE() + 30, 'PENDENTE', 1321.22);

insert into contrato (id_produto, id_cliente, data_contrato) values (2, 2,  SYSDATE);
insert into parcela (id_contrato, data_vencimento, status, valor) values ((SELECT max(id_contrato) FROM contrato), SYSDATE() + 30, 'PENDENTE', 950);
insert into parcela (id_contrato, data_vencimento, status, valor) values ((SELECT max(id_contrato) FROM contrato), SYSDATE() + 60, 'PENDENTE', 950);
insert into parcela (id_contrato, data_vencimento, status, valor) values ((SELECT max(id_contrato) FROM contrato), SYSDATE() + 90, 'PENDENTE', 950);