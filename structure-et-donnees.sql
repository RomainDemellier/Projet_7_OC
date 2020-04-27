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
-- Name: batch_job_execution_seq; Type: SEQUENCE; Schema: public; Owner: romain
--

CREATE SEQUENCE public.batch_job_execution_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.batch_job_execution_seq OWNER TO romain;

--
-- Name: batch_job_seq; Type: SEQUENCE; Schema: public; Owner: romain
--

CREATE SEQUENCE public.batch_job_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.batch_job_seq OWNER TO romain;

--
-- Name: batch_step_execution_seq; Type: SEQUENCE; Schema: public; Owner: romain
--

CREATE SEQUENCE public.batch_step_execution_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.batch_step_execution_seq OWNER TO romain;

--
-- Name: emprunt; Type: TABLE; Schema: public; Owner: romain
--

CREATE TABLE public.emprunt (
    id bigint NOT NULL,
    actif boolean,
    date_emprunt timestamp without time zone,
    date_retour timestamp without time zone,
    prolonge boolean,
    livre_id bigint NOT NULL,
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
-- Name: livre; Type: TABLE; Schema: public; Owner: romain
--

CREATE TABLE public.livre (
    id bigint NOT NULL,
    genre character varying(255),
    nbre_exemplaires integer NOT NULL,
    titre character varying(255),
    auteur_id bigint NOT NULL,
    full_name_auteur character varying(255)
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
5	Gary	Romain
6	Zola	Emile
7	Hugo	Victor
\.


--
-- Data for Name: emprunt; Type: TABLE DATA; Schema: public; Owner: romain
--

COPY public.emprunt (id, actif, date_emprunt, date_retour, prolonge, livre_id, usager_id) FROM stdin;
1	f	2020-03-17 00:00:00	2020-04-14 00:00:00	f	5	8
2	f	2020-03-17 00:00:00	2020-04-14 00:00:00	f	8	8
3	f	2020-03-18 00:00:00	2020-04-15 00:00:00	f	6	8
5	f	2020-03-18 00:00:00	2020-04-15 00:00:00	f	5	8
4	f	2020-03-18 00:00:00	2020-05-13 00:00:00	t	8	8
6	f	2020-03-18 00:00:00	2020-05-13 00:00:00	t	6	8
7	f	2020-03-19 00:00:00	2020-05-14 00:00:00	t	5	8
9	t	2020-03-31 00:00:00	2020-05-26 00:00:00	t	25	8
8	f	2020-03-25 00:00:00	2020-05-20 00:00:00	t	24	8
11	t	2020-04-07 00:00:00	2020-06-02 00:00:00	t	27	9
19	f	2020-04-11 00:00:00	2020-05-09 00:00:00	f	25	9
18	t	2020-04-11 00:00:00	2020-06-06 00:00:00	t	8	9
20	t	2020-04-14 00:00:00	2020-06-09 00:00:00	t	25	9
21	t	2020-04-16 00:00:00	2020-05-14 00:00:00	f	26	8
10	f	2020-04-03 00:00:00	2020-05-01 00:00:00	f	8	8
12	t	2020-04-11 00:00:00	2020-06-06 00:00:00	t	27	8
22	t	2020-04-16 00:00:00	2020-05-14 00:00:00	f	8	8
26	t	2020-04-23 00:00:00	2020-05-21 00:00:00	f	28	8
23	f	2020-04-22 00:00:00	2020-05-20 00:00:00	f	7	14
24	f	2020-04-23 00:00:00	2020-05-21 00:00:00	f	27	14
28	t	2020-04-26 00:00:00	2020-05-24 00:00:00	f	26	14
25	f	2020-04-23 00:00:00	2020-05-21 00:00:00	f	24	14
27	f	2020-04-26 00:00:00	2020-05-24 00:00:00	f	28	14
29	t	2020-04-27 00:00:00	2020-05-25 00:00:00	f	24	14
30	t	2020-04-27 00:00:00	2020-05-25 00:00:00	f	28	14
\.


--
-- Data for Name: livre; Type: TABLE DATA; Schema: public; Owner: romain
--

COPY public.livre (id, genre, nbre_exemplaires, titre, auteur_id, full_name_auteur) FROM stdin;
10	Autobiographie	0	Les confessions	3	Jean-Jacques Rousseau
9	aventure	0	Les trois mousquetaires	2	Alexandre Dumas
6	science-fiction	4	Vingt mille lieues sous les mers	1	Jules Verne
5	science-fiction	3	Voyage au centre de la terre	1	Jules Verne
8	aventure	2	La reine Margot	2	Alexandre Dumas
29	Roman	5	Notre dame de Paris	7	Victor Hugo
25	Roman	2	La vie devant soi	5	Romain Gary
7	science-fiction	3	De la terre à la lune	1	Jules Verne
27	Drame	6	Germinal	6	Emile Zola
26	Aventure	2	Vingt ans après	2	Alexandre Dumas
24	Aventure	4	Le comte de Monte-Cristo	2	Alexandre Dumas
28	Roman	4	Les misérables	7	Victor Hugo
\.


--
-- Data for Name: usager; Type: TABLE DATA; Schema: public; Owner: romain
--

COPY public.usager (id, email, nom, password, prenom, role) FROM stdin;
8	romain.demellier@gmail.com	Demellier	$2a$10$6wnsrsI4wLmq2mXvcpyPqe/5gQ.DRrCtRz3gjhqMlusAymkbyh7mq	Romain	USER
12	antoine.demellier@gmail.com	Demellier	$2a$10$bHm.qp/kosyrLLXDKLUBHeht4owb2P32qfxaIK/VVbYdwN2QubSPq	Antoine	USER
13	xavier.dumazel@gmail.com	Dumazel	$2a$10$hhOHVFRt0t1QSmO6O4AE/uVyIrDu5arRNjOEoV3LeLJdA4ili.Hhe	Xavier	ADMIN
10	muriel.demellier@gmail.com	Demellier	$2a$10$9mhmYkL821Ft332uChOWoewbt2zIiiEAnnfUebk6iAtwi6thoboyG	Muriel	USER
9	romain.demellier2@gmail.com	Demellier	$2a$10$S86Q3f.zb3pT.rClhHnlXuVF1R5ZPI.ZrC75ZT0qM.mw8C/RygIG.	Romain	ADMIN
14	martin.dupont@gmail.com	Dupont	$2a$10$xnVkv/4nTDwk9nrCisN0p.l3Nz/V4kEj9fno4oV42PlXrJ911ScnK	Martin	USER
11	daniel.demellier@gmail.com	Demellier	$2a$10$ub2N5nTT1oV5DrS4tsc/JuadTuLivKSfL3Pb0lPlRIQ8HZ7x5v8rW	Daniel	USER
\.


--
-- Name: auteur_id_seq; Type: SEQUENCE SET; Schema: public; Owner: romain
--

SELECT pg_catalog.setval('public.auteur_id_seq', 7, true);


--
-- Name: batch_job_execution_seq; Type: SEQUENCE SET; Schema: public; Owner: romain
--

SELECT pg_catalog.setval('public.batch_job_execution_seq', 124, true);


--
-- Name: batch_job_seq; Type: SEQUENCE SET; Schema: public; Owner: romain
--

SELECT pg_catalog.setval('public.batch_job_seq', 124, true);


--
-- Name: batch_step_execution_seq; Type: SEQUENCE SET; Schema: public; Owner: romain
--

SELECT pg_catalog.setval('public.batch_step_execution_seq', 123, true);


--
-- Name: emprunt_id_seq; Type: SEQUENCE SET; Schema: public; Owner: romain
--

SELECT pg_catalog.setval('public.emprunt_id_seq', 30, true);


--
-- Name: livre_id_seq; Type: SEQUENCE SET; Schema: public; Owner: romain
--

SELECT pg_catalog.setval('public.livre_id_seq', 29, true);


--
-- Name: usager_id_seq; Type: SEQUENCE SET; Schema: public; Owner: romain
--

SELECT pg_catalog.setval('public.usager_id_seq', 14, true);


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
-- Name: emprunt fkjnn7ll8vl64xhmb6779svt7c; Type: FK CONSTRAINT; Schema: public; Owner: romain
--

ALTER TABLE ONLY public.emprunt
    ADD CONSTRAINT fkjnn7ll8vl64xhmb6779svt7c FOREIGN KEY (livre_id) REFERENCES public.livre(id);


--
-- PostgreSQL database dump complete
--

