CREATE TABLE telefone (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	telefone BIGINT(20)	NOT NULL,
	tipo VARCHAR(20) NOT NULL,
	codigo_cliente BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_cliente) REFERENCES cliente(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO telefone (telefone, tipo, codigo_cliente) values ( 6133747767, 'RESIDENCIAL', 1 );
INSERT INTO telefone (telefone, tipo, codigo_cliente) values ( 61993962333, 'CELULAR', 1 );
INSERT INTO telefone (telefone, tipo, codigo_cliente) values ( 61993962111, 'CELULAR', 2 );
