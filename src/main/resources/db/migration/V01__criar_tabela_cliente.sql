CREATE TABLE cliente (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	cpf BIGINT(20) NOT NULL,
	logradouro VARCHAR(30),
	numero VARCHAR(30),
	complemento VARCHAR(30),
	bairro VARCHAR(30),
	cep INT(8),
	cidade VARCHAR(30),
	uf VARCHAR(2),
	email VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO cliente (nome, cpf, logradouro, numero, complemento, bairro, cep, cidade, uf, email) values ('João Silva', '12345678909', 'Rua do Abacaxi', '10', null, 'Brasil', '38400120', 'Uberlândia', 'MG', 'joao@gmail.com');
INSERT INTO cliente (nome, cpf, logradouro, numero, complemento, bairro, cep, cidade, uf, email) values ('Maria Rita', '12345678990','Rua do Sabiá', '110', 'Apto 101', 'Colina', '11400120', 'Ribeirão Preto', 'SP', 'maria@gmail.com');
