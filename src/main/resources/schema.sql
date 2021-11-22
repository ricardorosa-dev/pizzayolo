--CREATE SCHEMA IF NOT EXISTS pizzayolo;
--SET search_path = pizzayolo;
DROP TABLE IF EXISTS pizza;
CREATE TABLE pizza (
	id INTEGER PRIMARY KEY, 
	nome VARCHAR(100) NOT NULL, 
	descricao VARCHAR(255) NOT NULL,  
	preco FLOAT NOT NULL);