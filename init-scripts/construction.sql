create sequence engenheiros_id_seq;

alter sequence engenheiros_id_seq owner to postgres;

create sequence admins_id_seq;

alter sequence admins_id_seq owner to postgres;

create sequence etapas_id_seq;

alter sequence etapas_id_seq owner to postgres;

create sequence orcamentos_id_seq;

alter sequence orcamentos_id_seq owner to postgres;

create sequence reclamacoes_id_seq;

alter sequence reclamacoes_id_seq owner to postgres;

create sequence engenheiro_id_seq
	as integer;

alter sequence engenheiro_id_seq owner to postgres;

create sequence admin_id_seq
	as integer;

alter sequence admin_id_seq owner to postgres;

create sequence orcamento_id_seq
	as integer;

alter sequence orcamento_id_seq owner to postgres;

create sequence etapa_id_seq
	as integer;

alter sequence etapa_id_seq owner to postgres;

create sequence reclamacao_id_seq
	as integer;

alter sequence reclamacao_id_seq owner to postgres;

create table engenheiro
(
	id integer generated always as identity
		constraint engenheiros_pk
			primary key,
	nome varchar not null,
	email varchar not null
		constraint engenheiros_pk_2
			unique,
	telemovel integer not null
		constraint engenheiros_pk_3
			unique
);

alter table engenheiro owner to postgres;

alter sequence engenheiros_id_seq owned by engenheiro.id;

create table equipa
(
	id integer generated always as identity
		constraint equipa_pk
			primary key,
	chefe integer not null
);

alter table equipa owner to postgres;

create table material
(
	id integer generated always as identity
		constraint "Material_pk"
			primary key,
	valor_unidade double precision not null,
	quantidade integer not null
);

alter table material owner to postgres;

create table obreiro
(
	id integer generated always as identity
		constraint obreiro_pk
			primary key,
	equipa integer
		constraint obreiro_equipa_id_fk
			references equipa,
	nome varchar not null,
	email varchar not null
		constraint obreiro_pk_2
			unique,
	telemovel integer not null
		constraint obreiro_pk_3
			unique
);

alter table obreiro owner to postgres;

alter table equipa
	add constraint equipa_obreiro_id_fk
		foreign key (chefe) references obreiro;

create table admin
(
	id integer generated always as identity
		constraint admin_pk_2
			primary key,
	nome varchar not null,
	email varchar not null
		constraint admin_pk
			unique,
	password varchar not null
);

alter table admin owner to postgres;

alter sequence admins_id_seq owned by admin.id;

create table cod_postal
(
	cod_postal integer not null
		constraint id_pk
			primary key,
	distrito varchar not null
);

alter table cod_postal owner to postgres;

create table estado
(
	id integer generated always as identity
		constraint estado_pk
			primary key,
	estado varchar not null,
	descricao text
);

alter table estado owner to postgres;

create table tipo_cliente
(
	id integer generated always as identity
		constraint tipo_cliente_pk
			primary key,
	tipo varchar
);

alter table tipo_cliente owner to postgres;

create table cliente
(
	id integer generated always as identity
		constraint clientes_pk
			primary key,
	nome varchar not null,
	email varchar not null
		constraint clientes_pk_2
			unique,
	telemovel integer not null
		constraint clientes_pk_3
			unique,
	password varchar not null
		constraint clientes_pk_4
			unique,
	rua varchar not null,
	numero_porta integer not null,
	cod_postal integer not null
		constraint clientes_cod_postal_cod_postal_fk
			references cod_postal
				on update cascade,
	tipo_cliente integer
		constraint clientes_tipo_cliente_id_fk
			references tipo_cliente
);

alter table cliente owner to postgres;

create table orcamento
(
	id integer generated always as identity
		constraint orcamentos_pk
			primary key,
	cliente integer not null
		constraint orcamentos_clientes_id_fk
			references cliente,
	engenheiro integer not null
		constraint orcamentos_engenheiros_id_fk
			references engenheiro
);

alter table orcamento owner to postgres;

alter sequence orcamentos_id_seq owned by orcamento.id;

create table etapa
(
	id integer generated always as identity
		constraint etapas_pk
			primary key,
	orcamento integer not null
		constraint etapas_orcamentos_id_fk
			references orcamento,
	estado integer not null
		constraint etapas_estado_id_fk
			references estado,
	descricao text not null
);

alter table etapa owner to postgres;

alter sequence etapas_id_seq owned by etapa.id;

create table obra
(
	id integer generated always as identity
		constraint obra_pk
			primary key,
	equipa integer not null
		constraint obra_equipa_id_fk
			references equipa,
	orcamento integer not null
		constraint obra_orcamentos_id_fk
			references orcamento,
	etapa integer,
	concluida boolean default false
);

alter table obra owner to postgres;

create table reclamacao
(
	id integer generated always as identity
		constraint reclamacoes_pk
			primary key,
	cliente integer not null
		constraint reclamacoes_clientes_id_fk
			references cliente,
	obra integer not null
		constraint reclamacoes_obra_id_fk
			references obra
);

alter table reclamacao owner to postgres;

alter sequence reclamacoes_id_seq owned by reclamacao.id;

create table fatura
(
	id integer generated always as identity
		constraint fatura_pk
			primary key,
	etapa integer not null
		constraint fatura_etapas_id_fk
			references etapa,
	valor_total double precision not null,
	cliente integer not null
		constraint fatura_clientes_id_fk
			references cliente,
	data_emissao date not null,
	pago boolean default false
);

alter table fatura owner to postgres;

create table material_etapa
(
	etapa integer not null
		constraint material_etapas_etapas_id_fk
			references etapa,
	material integer not null
		constraint material_etapas_material_id_fk
			references material,
	quantidade integer not null,
	constraint material_etapas_pk
		primary key (material, etapa)
);

alter table material_etapa owner to postgres;

create view vw_detalhes_orcamentos(id_orcamento, nome_cliente, nome_engenheiro, etapas) as
	SELECT orcamento.id    AS id_orcamento,
       cliente.nome    AS nome_cliente,
       engenheiro.nome AS nome_engenheiro,
       count(etapa.id) AS etapas
FROM orcamento
         JOIN cliente ON orcamento.cliente = cliente.id
         JOIN engenheiro ON orcamento.engenheiro = engenheiro.id
         JOIN etapa ON orcamento.id = etapa.orcamento
GROUP BY orcamento.id, cliente.nome, engenheiro.nome;

alter table vw_detalhes_orcamentos owner to postgres;

create view vw_obras_concluidas(id_obra, chefe, cliente, engenheiro, etapas, concluida) as
	SELECT obra.id         AS id_obra,
       obreiro.nome    AS chefe,
       cliente.nome    AS cliente,
       engenheiro.nome AS engenheiro,
       count(etapa.id) AS etapas,
       obra.concluida
FROM obra
         JOIN orcamento ON obra.orcamento = orcamento.id
         JOIN cliente ON orcamento.cliente = cliente.id
         JOIN equipa ON obra.equipa = equipa.id
         JOIN obreiro ON equipa.chefe = obreiro.id
         JOIN engenheiro ON orcamento.engenheiro = engenheiro.id
         JOIN etapa ON orcamento.id = etapa.orcamento
WHERE obra.concluida = true
GROUP BY obra.id, obreiro.nome, cliente.nome, engenheiro.nome, obra.concluida;

alter table vw_obras_concluidas owner to postgres;

create function atualizar_obra_para_concluida() returns trigger
	language plpgsql
as $$
DECLARE
  total_etapas INTEGER;
  etapas_completas INTEGER;
BEGIN
  SELECT COUNT(*) INTO total_etapas
  FROM public.etapa
  WHERE orcamento = NEW.orcamento;

  SELECT COUNT(*) INTO etapas_completas
  FROM public.etapa
  WHERE orcamento = NEW.orcamento AND estado = 2;

  IF etapas_completas = total_etapas THEN
    UPDATE public.obra
    SET concluida = true
    WHERE id = (SELECT id FROM public.obra WHERE obra.orcamento = NEW.orcamento);
  ELSE
    UPDATE public.obra
    SET etapa = (SELECT MIN(id) FROM public.etapa WHERE orcamento = NEW.orcamento AND id > NEW.id)
    WHERE id = (SELECT id FROM public.obra WHERE public.obra.orcamento = NEW.orcamento);
  END IF;

  RETURN NEW;
END;
$$;

alter function atualizar_obra_para_concluida() owner to postgres;

create trigger trig_atualizar_obra_para_concluida
	after update
	on etapa
	for each row
	when (new.estado = 2)
	execute procedure atualizar_obra_para_concluida();

create function verificar_material_insuficiente() returns trigger
	language plpgsql
as $$
BEGIN
  IF (SELECT quantidade FROM material WHERE id = NEW.material) < NEW.quantidade THEN
     RAISE EXCEPTION 'Material insuficiente para a etapa %', NEW.etapa;
  END IF;
  RETURN NEW;
END;
$$;

alter function verificar_material_insuficiente() owner to postgres;

create function atualizar_valor_total_fatura() returns trigger
	language plpgsql
as $$
BEGIN
  UPDATE fatura
  SET valor_total = (SELECT SUM(material.valor_unidade * material_etapa.quantidade)
                    FROM material_etapa
                    JOIN material ON material_etapa.material = material.id
                    WHERE material_etapa.etapa = NEW.etapa)
  WHERE fatura.etapa = NEW.etapa;
  RETURN NEW;
END;
$$;

alter function atualizar_valor_total_fatura() owner to postgres;

