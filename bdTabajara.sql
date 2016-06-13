--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.2
-- Dumped by pg_dump version 9.5.2

-- Started on 2016-06-13 20:35:27

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE tabajara;
--
-- TOC entry 2197 (class 1262 OID 16393)
-- Name: tabajara; Type: DATABASE; Schema: -; Owner: -
--

CREATE DATABASE tabajara WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Portuguese_Brazil.1252' LC_CTYPE = 'Portuguese_Brazil.1252';


\connect tabajara

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 6 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA public;


--
-- TOC entry 2198 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 1 (class 3079 OID 12355)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2199 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_with_oids = false;

--
-- TOC entry 183 (class 1259 OID 33580)
-- Name: aeronave; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE aeronave (
    identificacao character varying(50) NOT NULL,
    modelo character varying(50),
    capac_passageiros integer,
    capac_carga double precision,
    tipo integer
);


--
-- TOC entry 189 (class 1259 OID 40987)
-- Name: aeroporto; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE aeroporto (
    idaeroporto character varying(50) NOT NULL,
    nome character varying(55),
    cidade integer
);


--
-- TOC entry 184 (class 1259 OID 33583)
-- Name: aviao; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE aviao (
    qtd_turbinas integer,
    capac_combust_por_turbina real
)
INHERITS (aeronave);


--
-- TOC entry 186 (class 1259 OID 33598)
-- Name: carro; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE carro (
    piloto character varying(255),
    cidade_origem integer,
    cidade_destino integer,
    autonomia real
)
INHERITS (aeronave);


--
-- TOC entry 193 (class 1259 OID 41077)
-- Name: cidade_id; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE cidade_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 987979879798787212
    CACHE 1;


--
-- TOC entry 188 (class 1259 OID 40973)
-- Name: cidade; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE cidade (
    idcidade integer DEFAULT nextval('cidade_id'::regclass) NOT NULL,
    municipio character varying(70),
    pais character varying(50),
    estado character varying(50)
);


--
-- TOC entry 182 (class 1259 OID 16396)
-- Name: cliente; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE cliente (
    idcliente integer NOT NULL,
    nome character varying(50),
    logradouro character varying(150),
    numero integer,
    bairro character varying(150),
    estado character varying(30),
    telefone character varying(30),
    municipio character varying(50),
    cep character varying(50),
    cpf character varying(15)
);


--
-- TOC entry 181 (class 1259 OID 16394)
-- Name: cliente_cod_cliente_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE cliente_cod_cliente_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2200 (class 0 OID 0)
-- Dependencies: 181
-- Name: cliente_cod_cliente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE cliente_cod_cliente_seq OWNED BY cliente.idcliente;


--
-- TOC entry 185 (class 1259 OID 33589)
-- Name: helicoptero; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE helicoptero (
    qtd_helices integer
)
INHERITS (aeronave);


--
-- TOC entry 192 (class 1259 OID 41027)
-- Name: passagem; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE passagem (
    identificacao integer NOT NULL,
    cliente integer,
    voo character varying(60),
    "dataVenda" date,
    "hroaVenda" time without time zone,
    "precoFinalViagem" double precision
);


--
-- TOC entry 194 (class 1259 OID 41098)
-- Name: piloto_id; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE piloto_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9898989898999
    CACHE 1;


--
-- TOC entry 187 (class 1259 OID 33601)
-- Name: piloto; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE piloto (
    identificacao integer DEFAULT nextval('piloto_id'::regclass) NOT NULL,
    cpf character varying(15),
    numerodobreve character varying(50),
    logradouro character varying(255),
    numero character varying(50),
    telefone character varying,
    nome character varying(255),
    identidade character varying(15),
    cidade integer
);


--
-- TOC entry 191 (class 1259 OID 41017)
-- Name: tipoAeronave; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE "tipoAeronave" (
    "idTipo" integer NOT NULL,
    tipo character varying(50)
)
WITH (autovacuum_enabled='true');


--
-- TOC entry 190 (class 1259 OID 40997)
-- Name: voo; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE voo (
    identificacao character varying(60) NOT NULL,
    "tipoAeronave" integer NOT NULL,
    aeronave character varying(60) NOT NULL,
    "aeroportoPartida" character varying(50) NOT NULL,
    "dataPartida" date NOT NULL,
    "horaPartida" time without time zone NOT NULL,
    "aeroportoChegada" character varying(50) NOT NULL,
    "dataChegada" date NOT NULL,
    "horaChegada" time without time zone NOT NULL,
    lotacao integer NOT NULL,
    "pesoCargaEmbarcada" double precision NOT NULL,
    "precoViagem" double precision NOT NULL
);


--
-- TOC entry 2027 (class 2604 OID 41066)
-- Name: idcliente; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY cliente ALTER COLUMN idcliente SET DEFAULT nextval('cliente_cod_cliente_seq'::regclass);


--
-- TOC entry 2181 (class 0 OID 33580)
-- Dependencies: 183
-- Data for Name: aeronave; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2187 (class 0 OID 40987)
-- Dependencies: 189
-- Data for Name: aeroporto; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO aeroporto (idaeroporto, nome, cidade) VALUES ('aero2', 'Kenndy', 1);


--
-- TOC entry 2182 (class 0 OID 33583)
-- Dependencies: 184
-- Data for Name: aviao; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO aviao (identificacao, modelo, capac_passageiros, capac_carga, qtd_turbinas, capac_combust_por_turbina, tipo) VALUES ('1', 'AviaoRapido', 12, 15, 2, 24, 1);
INSERT INTO aviao (identificacao, modelo, capac_passageiros, capac_carga, qtd_turbinas, capac_combust_por_turbina, tipo) VALUES ('aviao1', 'Bios', 159, 456, 2, 456, NULL);


--
-- TOC entry 2184 (class 0 OID 33598)
-- Dependencies: 186
-- Data for Name: carro; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO carro (identificacao, modelo, capac_passageiros, capac_carga, piloto, cidade_origem, cidade_destino, autonomia, tipo) VALUES ('carro1', 'SuperCarro', 4, 12, '1', 1, 1, 12, 2);


--
-- TOC entry 2186 (class 0 OID 40973)
-- Dependencies: 188
-- Data for Name: cidade; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO cidade (idcidade, municipio, pais, estado) VALUES (4, 'Rio de Janeiro', 'Brasil', 'RJ');
INSERT INTO cidade (idcidade, municipio, pais, estado) VALUES (1, 'Sao Paulo', 'Basil', 'SP');


--
-- TOC entry 2201 (class 0 OID 0)
-- Dependencies: 193
-- Name: cidade_id; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('cidade_id', 4, true);


--
-- TOC entry 2180 (class 0 OID 16396)
-- Dependencies: 182
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO cliente (idcliente, nome, logradouro, numero, bairro, estado, telefone, municipio, cep, cpf) VALUES (8, 'A', 'A', 1, 'Novo', 'Minas', '123', 'Paraatu', '386', '654');
INSERT INTO cliente (idcliente, nome, logradouro, numero, bairro, estado, telefone, municipio, cep, cpf) VALUES (7, 'Larney', 'Rua B', 147, 'NovoHorizonte', 'Floria', '123456', 'Paracatu', '3860000', '987645');


--
-- TOC entry 2202 (class 0 OID 0)
-- Dependencies: 181
-- Name: cliente_cod_cliente_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('cliente_cod_cliente_seq', 8, true);


--
-- TOC entry 2183 (class 0 OID 33589)
-- Dependencies: 185
-- Data for Name: helicoptero; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO helicoptero (identificacao, modelo, capac_passageiros, capac_carga, qtd_helices, tipo) VALUES ('heli1', 'heliSuper', 2, 12, 4, 3);
INSERT INTO helicoptero (identificacao, modelo, capac_passageiros, capac_carga, qtd_helices, tipo) VALUES ('helix', 'HUI', 15, 23, 24, NULL);


--
-- TOC entry 2190 (class 0 OID 41027)
-- Dependencies: 192
-- Data for Name: passagem; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2185 (class 0 OID 33601)
-- Dependencies: 187
-- Data for Name: piloto; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO piloto (identificacao, cpf, numerodobreve, logradouro, numero, telefone, nome, identidade, cidade) VALUES (9, '321', '321', 'Rua 321', '321', '321', 'Santos', '123', 1);


--
-- TOC entry 2203 (class 0 OID 0)
-- Dependencies: 194
-- Name: piloto_id; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('piloto_id', 9, true);


--
-- TOC entry 2189 (class 0 OID 41017)
-- Dependencies: 191
-- Data for Name: tipoAeronave; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO "tipoAeronave" ("idTipo", tipo) VALUES (1, 'AVIAO');
INSERT INTO "tipoAeronave" ("idTipo", tipo) VALUES (2, 'CARRO');
INSERT INTO "tipoAeronave" ("idTipo", tipo) VALUES (3, 'HELICOPTERO');


--
-- TOC entry 2188 (class 0 OID 40997)
-- Dependencies: 190
-- Data for Name: voo; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2033 (class 2606 OID 33593)
-- Name: aeronave_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY aeronave
    ADD CONSTRAINT aeronave_pkey PRIMARY KEY (identificacao);


--
-- TOC entry 2046 (class 2606 OID 40991)
-- Name: aeroporto_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY aeroporto
    ADD CONSTRAINT aeroporto_pkey PRIMARY KEY (idaeroporto);


--
-- TOC entry 2036 (class 2606 OID 33595)
-- Name: aviao_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY aviao
    ADD CONSTRAINT aviao_pkey PRIMARY KEY (identificacao);


--
-- TOC entry 2040 (class 2606 OID 40968)
-- Name: carro_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY carro
    ADD CONSTRAINT carro_pkey PRIMARY KEY (identificacao);


--
-- TOC entry 2044 (class 2606 OID 40986)
-- Name: cidade_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY cidade
    ADD CONSTRAINT cidade_pkey PRIMARY KEY (idcidade);


--
-- TOC entry 2038 (class 2606 OID 33597)
-- Name: helicoptero_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY helicoptero
    ADD CONSTRAINT helicoptero_pkey PRIMARY KEY (identificacao);


--
-- TOC entry 2031 (class 2606 OID 24587)
-- Name: idcliente; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT idcliente PRIMARY KEY (idcliente);


--
-- TOC entry 2053 (class 2606 OID 41031)
-- Name: passagem_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY passagem
    ADD CONSTRAINT passagem_pkey PRIMARY KEY (identificacao);


--
-- TOC entry 2042 (class 2606 OID 41090)
-- Name: piloto_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY piloto
    ADD CONSTRAINT piloto_pkey PRIMARY KEY (identificacao);


--
-- TOC entry 2051 (class 2606 OID 41021)
-- Name: tipoAeronave_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "tipoAeronave"
    ADD CONSTRAINT "tipoAeronave_pkey" PRIMARY KEY ("idTipo");


--
-- TOC entry 2049 (class 2606 OID 41001)
-- Name: voo_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY voo
    ADD CONSTRAINT voo_pkey PRIMARY KEY (identificacao);


--
-- TOC entry 2047 (class 1259 OID 41129)
-- Name: fki_cidade_aeroporto; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_cidade_aeroporto ON aeroporto USING btree (cidade);


--
-- TOC entry 2034 (class 1259 OID 41135)
-- Name: fki_tipo_aeronave; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_tipo_aeronave ON aeronave USING btree (tipo);


--
-- TOC entry 2054 (class 2606 OID 41022)
-- Name: aeronave_tipo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY aeronave
    ADD CONSTRAINT aeronave_tipo_fkey FOREIGN KEY (tipo) REFERENCES "tipoAeronave"("idTipo");


--
-- TOC entry 2057 (class 2606 OID 40992)
-- Name: aeroporto_cidade_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY aeroporto
    ADD CONSTRAINT aeroporto_cidade_fkey FOREIGN KEY (cidade) REFERENCES cidade(idcidade);


--
-- TOC entry 2058 (class 2606 OID 41124)
-- Name: cidade_aeroporto; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY aeroporto
    ADD CONSTRAINT cidade_aeroporto FOREIGN KEY (cidade) REFERENCES cidade(idcidade);


--
-- TOC entry 2056 (class 2606 OID 41119)
-- Name: cidade_piloto; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY piloto
    ADD CONSTRAINT cidade_piloto FOREIGN KEY (cidade) REFERENCES cidade(idcidade);


--
-- TOC entry 2063 (class 2606 OID 41032)
-- Name: passagem_cliente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY passagem
    ADD CONSTRAINT passagem_cliente_fkey FOREIGN KEY (cliente) REFERENCES cliente(idcliente);


--
-- TOC entry 2064 (class 2606 OID 41037)
-- Name: passagem_voo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY passagem
    ADD CONSTRAINT passagem_voo_fkey FOREIGN KEY (voo) REFERENCES voo(identificacao);


--
-- TOC entry 2055 (class 2606 OID 41130)
-- Name: tipo_aeronave; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY aeronave
    ADD CONSTRAINT tipo_aeronave FOREIGN KEY (tipo) REFERENCES "tipoAeronave"("idTipo");


--
-- TOC entry 2062 (class 2606 OID 41061)
-- Name: voo_aeronave_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY voo
    ADD CONSTRAINT voo_aeronave_fkey FOREIGN KEY (aeronave) REFERENCES aeronave(identificacao);


--
-- TOC entry 2060 (class 2606 OID 41012)
-- Name: voo_aeroportoChegada_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY voo
    ADD CONSTRAINT "voo_aeroportoChegada_fkey" FOREIGN KEY ("aeroportoChegada") REFERENCES aeroporto(idaeroporto);


--
-- TOC entry 2059 (class 2606 OID 41007)
-- Name: voo_aeroportoPartida_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY voo
    ADD CONSTRAINT "voo_aeroportoPartida_fkey" FOREIGN KEY ("aeroportoPartida") REFERENCES aeroporto(idaeroporto);


--
-- TOC entry 2061 (class 2606 OID 41056)
-- Name: voo_tipoAeronave_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY voo
    ADD CONSTRAINT "voo_tipoAeronave_fkey" FOREIGN KEY ("tipoAeronave") REFERENCES "tipoAeronave"("idTipo");


-- Completed on 2016-06-13 20:35:28

--
-- PostgreSQL database dump complete
--

