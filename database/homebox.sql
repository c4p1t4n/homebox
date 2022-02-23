DROP IF EXISTS DATABASE homebox;

CREATE DATABASE IF NOT EXISTS homebox;

CREATE TABLE staff (
    id_staff int PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha CHAR(32) NOT NULL
);

CREATE TABLE tag (
    id_tag int PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE midia (
    id_midia int PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE usuario (
    id_usuario int PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha CHAR(32) NOT NULL,
    cpf CHAR(11) NOT NULL,
    token CHAR(16) NOT NULL,
    tipo CHECK(
        tipo = 'prestador'
        OR tipo = 'cliente'
    ) NOT NULL,
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
    lido CHECK(
        lido = 's'
        OR lido = 'n'
    ) NOT NULL,
    FOREIGN KEY(fk_notificacao) REFERENCES notificacao(id_notificacao),
    FOREIGN KEY(fk_usuario) REFERENCES usuario(id_usuario),
    PRIMARY KEY(fk_notificacao, fk_usuario)
);

CREATE TABLE msg (
    id_msg int PRIMARY KEY AUTO_INCREMENT,
    mensagem TEXT NOT NULL,
    automatico CHECK(
        automatico = 's'
        OR automatico = 'n'
    ) NOT NULL,
    fk_usuario int NOT NULL,
    FOREIGN KEY(fk_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE midia_msg (
    fk_msg int PRIMARY KEY AUTO_INCREMENT,
    fk_midia int NOT NULL,
    FOREIGN KEY(fk_midia) REFERENCES midia(id_midia),
    FOREIGN KEY(fk_msg) REFERENCES msg(id_msg),
    PRIMARY KEY(fk_msg, fk_midia)
);

CREATE TABLE chat (
    id_chat int PRIMARY KEY AUTO_INCREMENT,
    data_abertura DATETIME NOT NULL
);

CREATE TABLE msg_chat (
    fk_msg int NOT NULL,
    fk_chat int NOT NULL,
    mensagem TEXT NOT NULL,
    data_envio DATETIME NOT NULL,
    lido CHECK(
        lido = 's'
        OR lido = 'n'
    ) NOT NULL,
    FOREIGN KEY(fk_msg) REFERENCES msg(id_msg),
    FOREIGN KEY(fk_chat) REFERENCES chat(id_chat),
    PRIMARY KEY(fk_msg, fk_chat)
);

CREATE TABLE chat_usuario (
    fk_chat int NOT NULL,
    fk_usuario int NOT NULL,
    FOREIGN KEY(fk_chat) REFERENCES chat(id_chat),
    FOREIGN KEY(fk_usuario) REFERENCES usuario(id_usuario),
    PRIMARY KEY(fk_usuario, fk_chat)
);

CREATE TABLE categoria (
    id_categoria int PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL
);

CREATE TABLE tag_categoria (
    fk_tag int,
    fk_categoria int,
    FOREIGN KEY (fk_categoria) REFERENCES categoria(id_categoria),
    FOREIGN KEY (fk_tag) REFERENCES tag(id_tag),
    PRIMARY KEY (fk_tag, fk_categoria)
);

CREATE TABLE tag_usuario (
    fk_tag int,
    fk_usuario int,
    FOREIGN KEY (fk_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (fk_tag) REFERENCES tag(id_tag),
    PRIMARY KEY (fk_tag, fk_usuario)
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

CREATE TABLE tag_servico (
    fk_tag int,
    fk_servico int,
    FOREIGN KEY (fk_servico) REFERENCES servico(id_servico),
    FOREIGN KEY (fk_tag) REFERENCES tag(id_tag),
    PRIMARY KEY (fk_tag, fk_servico)
);

CREATE TABLE servico_prestado (
    id_servico_prestado int PRIMARY KEY AUTO_INCREMENT,
    fk_cliente int,
    fk_prestador int,
    fk_categoria int,
    data_servico DATETIME NOT NULL,
    status_servico CHECK(
        status_servico = 'agendado'
        OR status_servico = 'executado'
        OR status_servico = 'cancelado'
    ),
    FOREIGN KEY (fk_categoria) REFERENCES servico(fk_categoria),
    FOREIGN KEY (fk_cliente) REFERENCES usuario(id_usuario),
    FOREIGN KEY (fk_prestador) REFERENCES servico(fk_usuario)
);

CREATE TABLE avaliacao (
    fk_servico_prestado int,
    valor int CHECK(
        valor <= 5
        AND valor >= 1
    ),
    descricao TEXT,
    FOREIGN KEY (fk_servico_prestado) REFERENCES servico_prestado(id_servico_prestado),
    PRIMARY KEY (fk_servico_prestado)
);

INSERT INTO
    staff
VALUES
    (
        NULL,
        'Ana Freitas',
        'ana.freitas@sptech.school',
        MD5("ExSenha1")
    ),
    (
        NULL,
        'Eduardo Dantas',
        'eduardo.oliveira@sptech.school',
        MD5("ExSenha1")
    ),
    (
        NULL,
        'Leo Igor',
        'leo.oliveira@sptech.school',
        MD5("ExSenha1")
    ),
    (
        NULL,
        'Nikolas Virionis',
        'nikolas.virionis@sptech.school',
        MD5("ExSenha1")
    ),
    (
        NULL,
        'Rodrigo Garcez',
        'rodrigo.hermann@sptech.school',
        MD5("ExSenha1")
    );