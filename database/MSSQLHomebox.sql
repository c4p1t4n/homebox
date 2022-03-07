-- DROP TABLE [pesquisa_usuario];
-- DROP TABLE [pesquisa];
-- DROP TABLE [avaliacao];
-- DROP TABLE [agendamento];
-- DROP TABLE [tag_servico];
-- DROP TABLE [servico];
-- DROP TABLE [tag_usuario];
-- DROP TABLE [tag_categoria];
-- DROP TABLE [categoria];
-- DROP TABLE [chat_usuario];
-- DROP TABLE [msg_chat];
-- DROP TABLE [chat];
-- DROP TABLE [midia_msg];
-- DROP TABLE [msg];
-- DROP TABLE [usuario_notificacao];
-- DROP TABLE [notificacao];
-- DROP TABLE [usuario];
-- DROP TABLE [midia];
-- DROP TABLE [tag];
-- DROP TABLE [staff];
CREATE TABLE staff (
    id_staff int PRIMARY KEY IDENTITY(1, 1),
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha CHAR(64) NOT NULL
);

CREATE TABLE tag (
    id_tag int PRIMARY KEY IDENTITY(1, 1),
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE midia (
    id_midia int PRIMARY KEY IDENTITY(1, 1),
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE usuario (
    id_usuario int PRIMARY KEY IDENTITY(1, 1),
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha CHAR(64) NOT NULL,
    cpf CHAR(11) NOT NULL,
    token CHAR(16) NOT NULL,
    tipo VARCHAR(9) CHECK(tipo IN ('prestador', 'cliente')) NOT NULL,
    foto VARCHAR(250) NOT NULL,
    cep CHAR(8) NOT NULL
);

CREATE TABLE notificacao (
    id_notificacao int PRIMARY KEY IDENTITY(1, 1),
    titulo VARCHAR(100) NOT NULL,
    mensagem TEXT NOT NULL
);

CREATE TABLE usuario_notificacao (
    fk_notificacao int NOT NULL,
    fk_usuario int NOT NULL,
    data_notificacao DATETIME NOT NULL,
    lido CHAR(1) CHECK(lido IN ('s', 'n')) NOT NULL,
    FOREIGN KEY(fk_notificacao) REFERENCES notificacao(id_notificacao),
    FOREIGN KEY(fk_usuario) REFERENCES usuario(id_usuario),
    PRIMARY KEY(fk_notificacao, fk_usuario)
);

CREATE TABLE msg (
    id_msg int PRIMARY KEY IDENTITY(1, 1),
    mensagem TEXT NOT NULL,
    automatico CHAR(1) CHECK(lido IN ('s', 'n')) NOT NULL,
    fk_usuario int NOT NULL,
    FOREIGN KEY(fk_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE midia_msg (
    fk_msg int,
    fk_midia int NOT NULL,
    FOREIGN KEY(fk_midia) REFERENCES midia(id_midia),
    FOREIGN KEY(fk_msg) REFERENCES msg(id_msg),
    PRIMARY KEY(fk_msg, fk_midia)
);

CREATE TABLE chat (
    id_chat int PRIMARY KEY IDENTITY(1, 1),
    data_abertura DATETIME NOT NULL
);

CREATE TABLE msg_chat (
    fk_msg int NOT NULL,
    fk_chat int NOT NULL,
    mensagem TEXT NOT NULL,
    data_envio DATETIME NOT NULL,
    lido CHAR(1) CHECK(lido IN ('s', 'n')) NOT NULL,
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
    id_categoria int PRIMARY KEY IDENTITY(1, 1),
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
    fk_usuario int,
    fk_categoria int,
    FOREIGN KEY (fk_usuario) REFERENCES servico(fk_usuario),
    FOREIGN KEY (fk_categoria) REFERENCES servico(fk_categoria),
    FOREIGN KEY (fk_tag) REFERENCES tag(id_tag),
    PRIMARY KEY (fk_tag, fk_usuario, fk_categoria)
);

CREATE TABLE agendamento (
    id_agendamento int PRIMARY KEY IDENTITY(1, 1),
    fk_cliente int NOT NULL,
    fk_prestador int NOT NULL,
    fk_categoria int NOT NULL,
    data_servico DATETIME NOT NULL,
    status_servico VARCHAR(9) CHECK(
        status_servico IN (
            'agendado',
            'executado',
            'cancelado-prestador',
            'cancelado-cliente',
            'nao-sucedida'
        )
    ) NOT NULL,
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

CREATE TABLE pesquisa (
    id_pesquisa int PRIMARY KEY IDENTITY(1, 1),
    valor VARCHAR(100) NOT NULL
);

CREATE TABLE pesquisa_usuario (
    fk_pesquisa int,
    fk_usuario int,
    data_pesquisa DATETIME NOT NULL
);

INSERT INTO
    staff
VALUES
    (
        NULL,
        'Ana Freitas',
        'ana.freitas@sptech.school',
        HASHBYTES("SHA2_256", "ExSenha1")
    ),
    (
        NULL,
        'Eduardo Dantas',
        'eduardo.oliveira@sptech.school',
        HASHBYTES("SHA2_256", "ExSenha1")
    ),
    (
        NULL,
        'Leo Igor',
        'leo.oliveira@sptech.school',
        HASHBYTES("SHA2_256", "ExSenha1")
    ),
    (
        NULL,
        'Nikolas Virionis',
        'nikolas.virionis@sptech.school',
        HASHBYTES("SHA2_256", "ExSenha1")
    ),
    (
        NULL,
        'Rodrigo Garcez',
        'rodrigo.hermann@sptech.school',
        HASHBYTES("SHA2_256", "ExSenha1")
    );