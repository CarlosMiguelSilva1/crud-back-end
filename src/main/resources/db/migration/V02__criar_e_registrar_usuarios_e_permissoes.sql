CREATE TABLE usuario (
	codigo BIGINT(20) PRIMARY KEY,
	nome VARCHAR(50) NOT NULL,
	senha VARCHAR(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE permissao (
	codigo BIGINT(20) PRIMARY KEY,
	descricao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuario_permissao (
	codigo_usuario BIGINT(20) NOT NULL,
	codigo_permissao BIGINT(20) NOT NULL,
	PRIMARY KEY (codigo_usuario, codigo_permissao),
	FOREIGN KEY (codigo_usuario) REFERENCES usuario(codigo),
	FOREIGN KEY (codigo_permissao) REFERENCES permissao(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO usuario (codigo, nome, senha) values (1, 'admin',  '$2a$10$sR9fji.yMxOQtoLnLsSJWOzBsMc4hAsrexeThL9HubZmvg/SUu1SG');
INSERT INTO usuario (codigo, nome, senha) values (2, 'comum',  '$2a$10$sR9fji.yMxOQtoLnLsSJWOzBsMc4hAsrexeThL9HubZmvg/SUu1SG');

INSERT INTO permissao (codigo, descricao) values (1, 'ROLE_CADASTRAR_CLIENTE');
INSERT INTO permissao (codigo, descricao) values (2, 'ROLE_REMOVER_CLIENTE');
INSERT INTO permissao (codigo, descricao) values (3, 'ROLE_PESQUISAR_CLIENTE');

-- admin
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 1);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 2);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 3);

-- comum
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 3);
