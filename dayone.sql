--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.3
-- Dumped by pg_dump version 9.5.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: cards; Type: TABLE; Schema: public; Owner: Guest
--

CREATE TABLE cards (
    c_id integer NOT NULL,
    c_name character varying,
    cardfaction_id integer,
    deck_quantity integer,
    combat integer,
    trade integer,
    cost_to_buy integer,
    user_notes character varying,
    c_image_url character varying
);


ALTER TABLE cards OWNER TO "Guest";

--
-- Name: cards_c_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE cards_c_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cards_c_id_seq OWNER TO "Guest";

--
-- Name: cards_c_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE cards_c_id_seq OWNED BY cards.c_id;


--
-- Name: factions; Type: TABLE; Schema: public; Owner: Guest
--

CREATE TABLE factions (
    f_id integer NOT NULL,
    f_name character varying,
    f_image_url character varying
);


ALTER TABLE factions OWNER TO "Guest";

--
-- Name: factions_f_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE factions_f_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE factions_f_id_seq OWNER TO "Guest";

--
-- Name: factions_f_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE factions_f_id_seq OWNED BY factions.f_id;


--
-- Name: c_id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY cards ALTER COLUMN c_id SET DEFAULT nextval('cards_c_id_seq'::regclass);


--
-- Name: f_id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY factions ALTER COLUMN f_id SET DEFAULT nextval('factions_f_id_seq'::regclass);


--
-- Data for Name: cards; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY cards (c_id, c_name, cardfaction_id, deck_quantity, combat, trade, cost_to_buy, user_notes, c_image_url) FROM stdin;
51	Scfsdf	0	1	1	1	1	none	dfsafdsa
52	1	11	1	1	1	1	none	fdsafdsadsa
53	fffsadsdfasdf	11	1	1	1	1	none	dfsfdsfdas
54	dfsadfsfdsad	11	1	1	1	1	none	fdsfdsafdsa
\.


--
-- Name: cards_c_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('cards_c_id_seq', 54, true);


--
-- Data for Name: factions; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY factions (f_id, f_name, f_image_url) FROM stdin;
11	mercs	https://images-na.ssl-images-amazon.com/images/I/81wX2UKAbOL._SY450_.jpg
\.


--
-- Name: factions_f_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('factions_f_id_seq', 11, true);


--
-- Name: cards_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY cards
    ADD CONSTRAINT cards_pkey PRIMARY KEY (c_id);


--
-- Name: factions_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY factions
    ADD CONSTRAINT factions_pkey PRIMARY KEY (f_id);


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

