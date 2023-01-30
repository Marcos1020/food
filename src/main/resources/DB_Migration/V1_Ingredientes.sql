create table TB_INGREDIENTES(
  ID_INGREDIENTE			numeric(100) not null,
  NOME_INGREDIENTE                   varchar(60) not null,
  VALOR           float(30)not null,
  DT_REGISTRO                  timestamp without time zone,
  DT_ALTERACAO     timestamp without time zone,
primary key (ID_INGREDIENTE));
create sequence sq_tb_ingrediente;