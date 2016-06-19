CREATE TABLE `cidade` (
	`idcidade`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`cidade`	TEXT NOT NULL
);

CREATE TABLE `endereco` (
	`idpessoa`	INTEGER NOT NULL,
	`idcidade`	INTEGER NOT NULL,
	`logradouro`	TEXT NOT NULL,
	`numero`	TEXT NOT NULL
);

CREATE TABLE `pessoa` (
	`idpessoa`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`tipo`	INTEGER NOT NULL,
	`nome`	TEXT NOT NULL,
	`fone`	TEXT NOT NULL,
	`email`	TEXT NOT NULL,
	`numero`	INTEGER,
	`salario`	NUMERIC,
	`nascimento`	TEXT NOT NULL DEFAULT '1900-01-01'
);

Insert Into cidade (cidade) Values
('Maringa'), ('Cianorte'), ('Umuarama'), ('Paranavai');

Insert Into pessoa (tipo, nome, fone, email, numero, salario, nascimento)
Values
(1, 'Aluno 0001', '9999-1111', 'aluno1@test.com', 1234, null, '1999-05-13'),
(1, 'Aluno 0002', '9999-2222', 'aluno2@test.com', 2345, null, '1999-03-26'),
(1, 'Aluno 0003', '9999-3333', 'aluno3@test.com', 3456, null, '1998-04-01'),
(2, 'Professor 0001', '8888-1111', 'prof1@test.com', null, 1234.56, '1980-09-21'),
(2, 'Professor 0002', '8888-2222', 'prof2@test.com', null, 2345.67, '1987-11-02')
;

Insert Into endereco (idpessoa, idcidade, logradouro, numero) Values
(1, 1, 'Av Brasil', '205'),
(2, 2, 'Praca João Penha', '67-B'),
(3, 3, 'Rua com U no meio', '222 (Fundos)'),
(4, 2, 'Praça Mascarenhas', 'S/N'),
(5, 4, 'Avenida Brasilia', '1567')
;