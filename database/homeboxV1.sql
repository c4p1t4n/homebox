DROP DATABASE IF EXISTS homebox;

CREATE DATABASE IF NOT EXISTS homebox;

USE homebox;

CREATE TABLE staff (
    id_staff int PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha CHAR(64) NOT NULL
);

CREATE TABLE usuario (
    id_usuario int PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha CHAR(64) NOT NULL,
    cpf CHAR(11) NOT NULL,
    token CHAR(16) NOT NULL,
    tipo ENUM('prestador', 'cliente') NOT NULL,
    foto VARCHAR(250) NOT NULL
);

CREATE TABLE notificacao (
    id_notificacao int PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL,
    mensagem TEXT NOT NULL
);

CREATE TABLE usuario_notificacao (
    fk_notificacao int NOT NULL,
    fk_usuario int NOT NULL,
    data_notificacao DATETIME NOT NULL,
    lido ENUM('s', 'n') NOT NULL,
    FOREIGN KEY(fk_notificacao) REFERENCES notificacao(id_notificacao),
    FOREIGN KEY(fk_usuario) REFERENCES usuario(id_usuario),
    PRIMARY KEY(fk_notificacao, fk_usuario)
);


CREATE TABLE categoria (
    id_categoria int PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL
);

CREATE TABLE servico (
    fk_usuario int,
    fk_categoria int,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    preco_referencia DECIMAL(7, 2),
    FOREIGN KEY (fk_categoria) REFERENCES categoria(id_categoria),
    FOREIGN KEY (fk_usuario) REFERENCES usuario(id_usuario),
    PRIMARY KEY (fk_usuario, fk_categoria)
);

CREATE TABLE agendamento (
    id_agendamento int PRIMARY KEY AUTO_INCREMENT,
    fk_cliente int NOT NULL,
    fk_prestador int NOT NULL,
    fk_categoria int NOT NULL,
    data_servico DATETIME NOT NULL,
    status_servico ENUM('agendado', 'executado', 'cancelado') NOT NULL,
    FOREIGN KEY (fk_categoria) REFERENCES servico(fk_categoria),
    FOREIGN KEY (fk_cliente) REFERENCES usuario(id_usuario),
    FOREIGN KEY (fk_prestador) REFERENCES servico(fk_usuario)
);

CREATE TABLE avaliacao (
    fk_agendamento int,
    valor int NOT NULL,
    descricao TEXT,
    FOREIGN KEY (fk_agendamento) REFERENCES agendamento(id_agendamento),
    PRIMARY KEY (fk_agendamento)
);

INSERT INTO
    staff
VALUES
    (
        NULL,
        'Ana Freitas',
        'ana.freitas@sptech.school',
        SHA2("ExSenha1", 256)
    ),
    (
        NULL,
        'Eduardo Dantas',
        'eduardo.oliveira@sptech.school',
        SHA2("ExSenha1", 256)
    ),
    (
        NULL,
        'Leo Igor',
        'leo.oliveira@sptech.school',
        SHA2("ExSenha1", 256)
    ),
    (
        NULL,
        'Nikolas Virionis',
        'nikolas.virionis@sptech.school',
        SHA2("ExSenha1", 256)
    ),
    (
        NULL,
        'Rodrigo Garcez',
        'rodrigo.hermann@sptech.school',
        SHA2("ExSenha1", 256)
    );