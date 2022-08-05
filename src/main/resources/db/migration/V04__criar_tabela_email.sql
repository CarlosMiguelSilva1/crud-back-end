ALTER TABLE cliente DROP COLUMN email;

CREATE TABLE email (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	email VARCHAR(40) NOT NULL,
	codigo_cliente BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_cliente) REFERENCES cliente(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO email (email, codigo_cliente) values ( 'charles@gmail.com',  1 );
INSERT INTO email (email,  codigo_cliente) values ( 'a@gmail.com',  1 );
INSERT INTO email (email,  codigo_cliente) values ( 'b@gmail.com',  2 );
