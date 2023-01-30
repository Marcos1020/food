create table TB_CARDAPIO(
  ID			numeric(100) not null,
  NOME                  varchar(60) not null,
  COMPOSICAO         VARCHAR(400)not null,
  VALOR           float(30)not null,
  DT_REGISTRO                  timestamp without time zone,
  DT_ALTERACAO     timestamp without time zone,
primary key (ID));
create sequence sq_tb_lanches;