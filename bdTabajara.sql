--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.2
-- Dumped by pg_dump version 9.5.2

-- Started on 2016-05-27 20:30:53

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE tabajara;
--
-- TOC entry 2185 (class 1262 OID 16393)
-- Name: tabajara; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE tabajara WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Portuguese_Brazil.1252' LC_CTYPE = 'Portuguese_Brazil.1252';


ALTER DATABASE tabajara OWNER TO postgres;

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
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 2186 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 1 (class 3079 OID 12355)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2188 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 183 (class 1259 OID 33580)
-- Name: aeronave; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE aeronave (
    identificacao character varying(50) NOT NULL,
    modelo character varying(50),
    "capacPassageiiros" integer,
    "capacCarga" double precision,
    tipo integer
);


ALTER TABLE aeronave OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 40987)
-- Name: aeroporto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE aeroporto (
    identificacao character varying(50) NOT NULL,
    nome character varying(55),
    cidade integer
);


ALTER TABLE aeroporto OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 33583)
-- Name: aviao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE aviao (
    "qtdTurbinas" integer,
    "capacCombustPorTurbina" real
)
INHERITS (aeronave);


ALTER TABLE aviao OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 33598)
-- Name: carro; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE carro (
    piloto character varying(255),
    "cidadeOrigem" integer,
    "cidadeDestino" integer,
    autonomia real
)
INHERITS (aeronave);


ALTER TABLE carro OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 40973)
-- Name: cidade; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cidade (
    identificacao integer NOT NULL,
    "nomeCidade" character varying(70),
    pais character varying(50),
    estado character varying(50)
);


ALTER TABLE cidade OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 16396)
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cliente (
    identificacao integer NOT NULL,
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


ALTER TABLE cliente OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 16394)
-- Name: cliente_cod_cliente_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cliente_cod_cliente_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cliente_cod_cliente_seq OWNER TO postgres;

--
-- TOC entry 2189 (class 0 OID 0)
-- Dependencies: 181
-- Name: cliente_cod_cliente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cliente_cod_cliente_seq OWNED BY cliente.identificacao;


--
-- TOC entry 185 (class 1259 OID 33589)
-- Name: helicoptero; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE helicoptero (
    "qtdHelices" integer
)
INHERITS (aeronave);


ALTER TABLE helicoptero OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 41027)
-- Name: passagem; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE passagem (
    identificacao integer NOT NULL,
    cliente integer,
    voo character varying(60),
    "dataVenda" date,
    "hroaVenda" time without time zone,
    "precoFinalViagem" double precision
);


ALTER TABLE passagem OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 33601)
-- Name: piloto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE piloto (
    identificacao character varying(255) NOT NULL,
    cpf character varying(15),
    "numeroBreve" character varying(50),
    logradouro character varying(255),
    numero character varying(50),
    cidade integer,
    telefone character varying
);


ALTER TABLE piloto OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 41017)
-- Name: tipoAeronave; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "tipoAeronave" (
    "idTipo" integer NOT NULL,
    tipo character varying(50)
)
WITH (autovacuum_enabled='true');


ALTER TABLE "tipoAeronave" OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 40997)
-- Name: voo; Type: TABLE; Schema: public; Owner: postgres
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


ALTER TABLE voo OWNER TO postgres;

--
-- TOC entry 2023 (class 2604 OID 16399)
-- Name: identificacao; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cliente ALTER COLUMN identificacao SET DEFAULT nextval('cliente_cod_cliente_seq'::regclass);


--
-- TOC entry 2171 (class 0 OID 33580)
-- Dependencies: 183
-- Data for Name: aeronave; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2177 (class 0 OID 40987)
-- Dependencies: 189
-- Data for Name: aeroporto; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO aeroporto (identificacao, nome, cidade) VALUES ('aero1', 'Guarulhos', 1);


--
-- TOC entry 2172 (class 0 OID 33583)
-- Dependencies: 184
-- Data for Name: aviao; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO aviao (identificacao, modelo, "capacPassageiiros", "capacCarga", "qtdTurbinas", "capacCombustPorTurbina", tipo) VALUES ('1', 'AviaoRapido', 12, 15, 2, 24, 1);


--
-- TOC entry 2174 (class 0 OID 33598)
-- Dependencies: 186
-- Data for Name: carro; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO carro (identificacao, modelo, "capacPassageiiros", "capacCarga", piloto, "cidadeOrigem", "cidadeDestino", autonomia, tipo) VALUES ('carro1', 'SuperCarro', 4, 12, '1', 1, 1, 12, 2);


--
-- TOC entry 2176 (class 0 OID 40973)
-- Dependencies: 188
-- Data for Name: cidade; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO cidade (identificacao, "nomeCidade", pais, estado) VALUES (1, 'Paracatu', 'Brasil', 'MInas Gerais');


--
-- TOC entry 2170 (class 0 OID 16396)
-- Dependencies: 182
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO cliente (identificacao, nome, logradouro, numero, bairro, estado, telefone, municipio, cep, cpf) VALUES (3, 'pedro', 'rua a', 110, 'novo rio', 'minas', '3679158552', 'paracatu', '68600000', NULL);
INSERT INTO cliente (identificacao, nome, logradouro, numero, bairro, estado, telefone, municipio, cep, cpf) VALUES (12313, '', 'asdf', 123, '', 'asdf', 'asdf', 'asdf', '321', '321321');
INSERT INTO cliente (identificacao, nome, logradouro, numero, bairro, estado, telefone, municipio, cep, cpf) VALUES (456, 'Nataniel', 'Rua 19', 405, 'Novo horizonte', 'MInas gRIAS', '3671*3595', 'Paracatu', '38600000', '12345679');


--
-- TOC entry 2190 (class 0 OID 0)
-- Dependencies: 181
-- Name: cliente_cod_cliente_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cliente_cod_cliente_seq', 3, true);


--
-- TOC entry 2173 (class 0 OID 33589)
-- Dependencies: 185
-- Data for Name: helicoptero; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO helicoptero (identificacao, modelo, "capacPassageiiros", "capacCarga", "qtdHelices", tipo) VALUES ('heli1', 'heliSuper', 2, 12, 4, 3);


--
-- TOC entry 2180 (class 0 OID 41027)
-- Dependencies: 192
-- Data for Name: passagem; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2175 (class 0 OID 33601)
-- Dependencies: 187
-- Data for Name: piloto; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO piloto (identificacao, cpf, "numeroBreve", logradouro, numero, cidade, telefone) VALUES ('1', '07314730601', '159', 'Rua Amelia G. Leite', '470', 1, '36719368');


--
-- TOC entry 2179 (class 0 OID 41017)
-- Dependencies: 191
-- Data for Name: tipoAeronave; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO "tipoAeronave" ("idTipo", tipo) VALUES (1, 'AVIAO');
INSERT INTO "tipoAeronave" ("idTipo", tipo) VALUES (2, 'CARRO');
INSERT INTO "tipoAeronave" ("idTipo", tipo) VALUES (3, 'HELICOPTERO');


--
-- TOC entry 2178 (class 0 OID 40997)
-- Dependencies: 190
-- Data for Name: voo; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2027 (class 2606 OID 33593)
-- Name: aeronave_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aeronave
    ADD CONSTRAINT aeronave_pkey PRIMARY KEY (identificacao);


--
-- TOC entry 2039 (class 2606 OID 40991)
-- Name: aeroporto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aeroporto
    ADD CONSTRAINT aeroporto_pkey PRIMARY KEY (identificacao);


--
-- TOC entry 2029 (class 2606 OID 33595)
-- Name: aviao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aviao
    ADD CONSTRAINT aviao_pkey PRIMARY KEY (identificacao);


--
-- TOC entry 2033 (class 2606 OID 40968)
-- Name: carro_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY carro
    ADD CONSTRAINT carro_pkey PRIMARY KEY (identificacao);


--
-- TOC entry 2037 (class 2606 OID 40986)
-- Name: cidade_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cidade
    ADD CONSTRAINT cidade_pkey PRIMARY KEY (identificacao);


--
-- TOC entry 2031 (class 2606 OID 33597)
-- Name: helicoptero_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY helicoptero
    ADD CONSTRAINT helicoptero_pkey PRIMARY KEY (identificacao);


--
-- TOC entry 2025 (class 2606 OID 24587)
-- Name: idcliente; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT idcliente PRIMARY KEY (identificacao);


--
-- TOC entry 2045 (class 2606 OID 41031)
-- Name: passagem_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY passagem
    ADD CONSTRAINT passagem_pkey PRIMARY KEY (identificacao);


--
-- TOC entry 2035 (class 2606 OID 40972)
-- Name: piloto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY piloto
    ADD CONSTRAINT piloto_pkey PRIMARY KEY (identificacao);


--
-- TOC entry 2043 (class 2606 OID 41021)
-- Name: tipoAeronave_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "tipoAeronave"
    ADD CONSTRAINT "tipoAeronave_pkey" PRIMARY KEY ("idTipo");


--
-- TOC entry 2041 (class 2606 OID 41001)
-- Name: voo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY voo
    ADD CONSTRAINT voo_pkey PRIMARY KEY (identificacao);


--
-- TOC entry 2046 (class 2606 OID 41022)
-- Name: aeronave_tipo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aeronave
    ADD CONSTRAINT aeronave_tipo_fkey FOREIGN KEY (tipo) REFERENCES "tipoAeronave"("idTipo");


--
-- TOC entry 2048 (class 2606 OID 40992)
-- Name: aeroporto_cidade_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aeroporto
    ADD CONSTRAINT aeroporto_cidade_fkey FOREIGN KEY (cidade) REFERENCES cidade(identificacao);


--
-- TOC entry 2053 (class 2606 OID 41032)
-- Name: passagem_cliente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY passagem
    ADD CONSTRAINT passagem_cliente_fkey FOREIGN KEY (cliente) REFERENCES cliente(identificacao);


--
-- TOC entry 2054 (class 2606 OID 41037)
-- Name: passagem_voo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY passagem
    ADD CONSTRAINT passagem_voo_fkey FOREIGN KEY (voo) REFERENCES voo(identificacao);


--
-- TOC entry 2047 (class 2606 OID 41051)
-- Name: piloto_cidade_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY piloto
    ADD CONSTRAINT piloto_cidade_fkey FOREIGN KEY (cidade) REFERENCES cidade(identificacao);


--
-- TOC entry 2052 (class 2606 OID 41061)
-- Name: voo_aeronave_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY voo
    ADD CONSTRAINT voo_aeronave_fkey FOREIGN KEY (aeronave) REFERENCES aeronave(identificacao);


--
-- TOC entry 2050 (class 2606 OID 41012)
-- Name: voo_aeroportoChegada_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY voo
    ADD CONSTRAINT "voo_aeroportoChegada_fkey" FOREIGN KEY ("aeroportoChegada") REFERENCES aeroporto(identificacao);


--
-- TOC entry 2049 (class 2606 OID 41007)
-- Name: voo_aeroportoPartida_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY voo
    ADD CONSTRAINT "voo_aeroportoPartida_fkey" FOREIGN KEY ("aeroportoPartida") REFERENCES aeroporto(identificacao);


--
-- TOC entry 2051 (class 2606 OID 41056)
-- Name: voo_tipoAeronave_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY voo
    ADD CONSTRAINT "voo_tipoAeronave_fkey" FOREIGN KEY ("tipoAeronave") REFERENCES "tipoAeronave"("idTipo");


--
-- TOC entry 2187 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2016-05-27 20:30:53

--
-- PostgreSQL database dump complete
--

