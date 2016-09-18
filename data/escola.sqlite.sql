CREATE TABLE `cidade` (
	`idcidade`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`cidade`	TEXT NOT NULL
);

CREATE TABLE `endereco` (
	`idendereco`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
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
	`nascimento`	TEXT NOT NULL DEFAULT '1900-01-01',
	`senha`	TEXT
);

Insert Into cidade (cidade) Values
('Maringa'), ('Cianorte'), ('Umuarama'), ('Paranavai');

-- Alunos
Insert Into pessoa (tipo, nome, fone, email, numero, nascimento)
Values
(1, 'Aluno 0001', '11912345678', 'aluno1@test.com', 1234, '1999-05-13'),
(1, 'Aluno 0002', '12912345678', 'aluno2@test.com', 2345, '1999-03-26'),
(1, 'Aluno 0003', '13912345678', 'aluno3@test.com', 3456, '1998-04-01')
;

-- Professores
Insert Into pessoa (tipo, nome, fone, email, salario, nascimento)
Values
(2, 'Professor 0001', '21912345678', 'prof1@test.com', 1234.56, '1980-09-21'),
(2, 'Professor 0002', '22912345678', 'prof2@test.com', 2345.67, '1987-11-02')
;

-- Usuarios
Insert Into pessoa (tipo, nome, fone, email, senha, nascimento)
Values
(3, 'Usuário 0001', '31912345678', 'user1@test.com', '123456', '1980-09-21'),
(3, 'Usuário 0002', '32912345678', 'user2@test.com', '123456', '1987-11-02')
;

Insert Into endereco (idpessoa, idcidade, logradouro, numero) Values
(1, 1, 'Av Brasil', '205'),
(2, 2, 'Praca João Penha', '67-B'),
(3, 3, 'Rua com U no meio', '222 (Fundos)'),
(4, 2, 'Praça Mascarenhas', 'S/N'),
(5, 4, 'Avenida Brasilia', '1567'),
(6, 3, 'Avenida Aracaju', '580'),
(7, 3, 'Rododia 323', 'KM 100'),
(7, 2, 'Travessa Itororó', '12')
;
