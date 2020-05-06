--
-- PostgreSQL database dump
--

-- Dumped from database version 10.12 (Ubuntu 10.12-0ubuntu0.18.04.1)
-- Dumped by pg_dump version 10.12 (Ubuntu 10.12-0ubuntu0.18.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
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


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: auteur; Type: TABLE; Schema: public; Owner: romain
--

CREATE TABLE public.auteur (
    id bigint NOT NULL,
    nom character varying(255),
    prenom character varying(255)
);


ALTER TABLE public.auteur OWNER TO romain;

--
-- Name: auteur_id_seq; Type: SEQUENCE; Schema: public; Owner: romain
--

CREATE SEQUENCE public.auteur_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.auteur_id_seq OWNER TO romain;

--
-- Name: auteur_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: romain
--

ALTER SEQUENCE public.auteur_id_seq OWNED BY public.auteur.id;


--
-- Name: emprunt; Type: TABLE; Schema: public; Owner: romain
--

CREATE TABLE public.emprunt (
    id bigint NOT NULL,
    actif boolean,
    date_emprunt date,
    date_retour date,
    prolonge boolean,
    exemplaire_id bigint NOT NULL,
    usager_id bigint NOT NULL
);


ALTER TABLE public.emprunt OWNER TO romain;

--
-- Name: emprunt_id_seq; Type: SEQUENCE; Schema: public; Owner: romain
--

CREATE SEQUENCE public.emprunt_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.emprunt_id_seq OWNER TO romain;

--
-- Name: emprunt_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: romain
--

ALTER SEQUENCE public.emprunt_id_seq OWNED BY public.emprunt.id;


--
-- Name: exemplaire; Type: TABLE; Schema: public; Owner: romain
--

CREATE TABLE public.exemplaire (
    id bigint NOT NULL,
    etat character varying(255),
    livre_id bigint NOT NULL
);


ALTER TABLE public.exemplaire OWNER TO romain;

--
-- Name: exemplaire_id_seq; Type: SEQUENCE; Schema: public; Owner: romain
--

CREATE SEQUENCE public.exemplaire_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.exemplaire_id_seq OWNER TO romain;

--
-- Name: exemplaire_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: romain
--

ALTER SEQUENCE public.exemplaire_id_seq OWNED BY public.exemplaire.id;


--
-- Name: livre; Type: TABLE; Schema: public; Owner: romain
--

CREATE TABLE public.livre (
    id bigint NOT NULL,
    genre character varying(255),
    nbre_exemplaires integer,
    titre character varying(255),
    auteur_id bigint NOT NULL
);


ALTER TABLE public.livre OWNER TO romain;

--
-- Name: livre_id_seq; Type: SEQUENCE; Schema: public; Owner: romain
--

CREATE SEQUENCE public.livre_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.livre_id_seq OWNER TO romain;

--
-- Name: livre_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: romain
--

ALTER SEQUENCE public.livre_id_seq OWNED BY public.livre.id;


--
-- Name: usager; Type: TABLE; Schema: public; Owner: romain
--

CREATE TABLE public.usager (
    id bigint NOT NULL,
    email character varying(255),
    nom character varying(20),
    password character varying(255),
    prenom character varying(20),
    role character varying(255)
);


ALTER TABLE public.usager OWNER TO romain;

--
-- Name: usager_id_seq; Type: SEQUENCE; Schema: public; Owner: romain
--

CREATE SEQUENCE public.usager_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usager_id_seq OWNER TO romain;

--
-- Name: usager_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: romain
--

ALTER SEQUENCE public.usager_id_seq OWNED BY public.usager.id;


--
-- Name: auteur id; Type: DEFAULT; Schema: public; Owner: romain
--

ALTER TABLE ONLY public.auteur ALTER COLUMN id SET DEFAULT nextval('public.auteur_id_seq'::regclass);


--
-- Name: emprunt id; Type: DEFAULT; Schema: public; Owner: romain
--

ALTER TABLE ONLY public.emprunt ALTER COLUMN id SET DEFAULT nextval('public.emprunt_id_seq'::regclass);


--
-- Name: exemplaire id; Type: DEFAULT; Schema: public; Owner: romain
--

ALTER TABLE ONLY public.exemplaire ALTER COLUMN id SET DEFAULT nextval('public.exemplaire_id_seq'::regclass);


--
-- Name: livre id; Type: DEFAULT; Schema: public; Owner: romain
--

ALTER TABLE ONLY public.livre ALTER COLUMN id SET DEFAULT nextval('public.livre_id_seq'::regclass);


--
-- Name: usager id; Type: DEFAULT; Schema: public; Owner: romain
--

ALTER TABLE ONLY public.usager ALTER COLUMN id SET DEFAULT nextval('public.usager_id_seq'::regclass);


--
-- Data for Name: auteur; Type: TABLE DATA; Schema: public; Owner: romain
--

COPY public.auteur (id, nom, prenom) FROM stdin;
1	Verne	Jules
2	Dumas	Alexandre
3	Rousseau	Jean-Jacques
4	Gary	Romain
5	Zola	Emile
6	Hugo	Victor
\.


--
-- Data for Name: emprunt; Type: TABLE DATA; Schema: public; Owner: romain
--

COPY public.emprunt (id, actif, date_emprunt, date_retour, prolonge, exemplaire_id, usager_id) FROM stdin;
2	f	2020-05-05	2020-06-02	f	31	2
4	t	2020-05-06	2020-06-03	f	14	2
7	t	2020-05-06	2020-06-03	f	36	3
5	f	2020-05-06	2020-06-03	f	23	3
8	f	2020-05-06	2020-06-03	f	38	4
3	t	2020-03-17	2020-04-14	f	32	2
10	t	2020-03-21	2020-04-18	f	26	4
9	t	2020-05-06	2020-07-01	t	29	4
6	f	2020-03-19	2020-04-16	f	34	3
11	t	2020-03-21	2020-04-18	f	34	3
\.


--
-- Data for Name: exemplaire; Type: TABLE DATA; Schema: public; Owner: romain
--

COPY public.exemplaire (id, etat, livre_id) FROM stdin;
12	neuf	1
13	neuf	1
14	neuf	1
15	vieux	1
16	vieux	1
17	neuf	3
18	moyen	3
19	vieux	3
20	vieux	3
21	vieux	4
22	moyen	4
23	moyen	5
24	neuf	5
25	vieux	5
26	vieux	6
27	moyen	6
28	moyen	7
29	neuf	7
30	neuf	7
31	vieux	8
32	vieux	9
33	moyen	9
34	moyen	10
35	moyen	11
36	neuf	11
37	vieux	11
38	moyen	12
39	vieux	12
40	vieux	13
41	neuf	13
\.


--
-- Data for Name: livre; Type: TABLE DATA; Schema: public; Owner: romain
--

COPY public.livre (id, genre, nbre_exemplaires, titre, auteur_id) FROM stdin;
3	Science-fiction	4	Vingt mille lieues sous les mers	1
4	Science-fiction	2	Voyage au centre de la Terre	1
13	Roman	2	Notre dame de Paris	6
9	Autobiographie	1	Les confessions	3
8	Aventure	1	Vingt ans après	2
1	Science-fiction	4	De la Terre à la Lune	1
11	Drame	2	Germinal	5
7	Aventure	2	Les trois mousquetaires	2
6	Aventure	1	Le comte de Monte-Cristo	2
5	Aventure	3	La reine Margot	2
12	Roman	2	Les misérables	6
10	Roman	0	La vie devant soi	4
\.


--
-- Data for Name: usager; Type: TABLE DATA; Schema: public; Owner: romain
--

COPY public.usager (id, email, nom, password, prenom, role) FROM stdin;
1	romain.demellier@gmail.com	Demellier	$2a$10$motIfR2mf/o7bKoYHEl5U.g5NbdnNbDxYgrBCTOJDInn1KJfr6Fke	Romain	ADMIN
2	martin.dupont@gmail.com	Dupont	$2a$10$IWClF5DS748zT48KZ8QB/.BUgGTyUxAYerQdDUk8HO4M6H/7qq14q	Martin	USER
3	robert.durand@gmail.com	Durand	$2a$10$P5rvjnN0F5OCki/wl5ObCer7z2UTlcMZ1aMBjdamPeZVZcBqPm6sy	Robert	USER
4	genevieve.lamotte@gmail.com	Lamotte	$2a$10$T2g2gqw7wKl0p06.wB95IeuitUQLcwE/DCK5IGOObpvjRCI5PHyPO	Geneviève	USER
\.


--
-- Name: auteur_id_seq; Type: SEQUENCE SET; Schema: public; Owner: romain
--

SELECT pg_catalog.setval('public.auteur_id_seq', 6, true);


--
-- Name: emprunt_id_seq; Type: SEQUENCE SET; Schema: public; Owner: romain
--

SELECT pg_catalog.setval('public.emprunt_id_seq', 11, true);


--
-- Name: exemplaire_id_seq; Type: SEQUENCE SET; Schema: public; Owner: romain
--

SELECT pg_catalog.setval('public.exemplaire_id_seq', 41, true);


--
-- Name: livre_id_seq; Type: SEQUENCE SET; Schema: public; Owner: romain
--

SELECT pg_catalog.setval('public.livre_id_seq', 13, true);


--
-- Name: usager_id_seq; Type: SEQUENCE SET; Schema: public; Owner: romain
--

SELECT pg_catalog.setval('public.usager_id_seq', 4, true);


--
-- Name: auteur auteur_pkey; Type: CONSTRAINT; Schema: public; Owner: romain
--

ALTER TABLE ONLY public.auteur
    ADD CONSTRAINT auteur_pkey PRIMARY KEY (id);


--
-- Name: emprunt emprunt_pkey; Type: CONSTRAINT; Schema: public; Owner: romain
--

ALTER TABLE ONLY public.emprunt
    ADD CONSTRAINT emprunt_pkey PRIMARY KEY (id);


--
-- Name: exemplaire exemplaire_pkey; Type: CONSTRAINT; Schema: public; Owner: romain
--

ALTER TABLE ONLY public.exemplaire
    ADD CONSTRAINT exemplaire_pkey PRIMARY KEY (id);


--
-- Name: livre livre_pkey; Type: CONSTRAINT; Schema: public; Owner: romain
--

ALTER TABLE ONLY public.livre
    ADD CONSTRAINT livre_pkey PRIMARY KEY (id);


--
-- Name: usager uk_dqf4vbwkwu9ky0mn0d29i0fmt; Type: CONSTRAINT; Schema: public; Owner: romain
--

ALTER TABLE ONLY public.usager
    ADD CONSTRAINT uk_dqf4vbwkwu9ky0mn0d29i0fmt UNIQUE (email);


--
-- Name: usager usager_pkey; Type: CONSTRAINT; Schema: public; Owner: romain
--

ALTER TABLE ONLY public.usager
    ADD CONSTRAINT usager_pkey PRIMARY KEY (id);


--
-- Name: emprunt fk6atl8ahwhjb2775lpoofi0mju; Type: FK CONSTRAINT; Schema: public; Owner: romain
--

ALTER TABLE ONLY public.emprunt
    ADD CONSTRAINT fk6atl8ahwhjb2775lpoofi0mju FOREIGN KEY (usager_id) REFERENCES public.usager(id);


--
-- Name: livre fkh0pb6pxv3ubtgo1s3ev4gebgj; Type: FK CONSTRAINT; Schema: public; Owner: romain
--

ALTER TABLE ONLY public.livre
    ADD CONSTRAINT fkh0pb6pxv3ubtgo1s3ev4gebgj FOREIGN KEY (auteur_id) REFERENCES public.auteur(id);


--
-- Name: emprunt fkj7uf2osy43jrxo78f2l217ar4; Type: FK CONSTRAINT; Schema: public; Owner: romain
--

ALTER TABLE ONLY public.emprunt
    ADD CONSTRAINT fkj7uf2osy43jrxo78f2l217ar4 FOREIGN KEY (exemplaire_id) REFERENCES public.exemplaire(id);


--
-- Name: exemplaire fkkcraqrinp6mtrkkg9rigi6len; Type: FK CONSTRAINT; Schema: public; Owner: romain
--

ALTER TABLE ONLY public.exemplaire
    ADD CONSTRAINT fkkcraqrinp6mtrkkg9rigi6len FOREIGN KEY (livre_id) REFERENCES public.livre(id);


--
-- PostgreSQL database dump complete
--

