-- DROP TABLE [avaliacao];
-- DROP TABLE [agendamento];
-- DROP TABLE [servico];
-- DROP TABLE [categoria];
-- DROP TABLE [usuario_notificacao];
-- DROP TABLE [notificacao];
-- DROP TABLE [usuario];
-- DROP TABLE [staff];
CREATE TABLE staff (
    id_staff int PRIMARY KEY IDENTITY(1, 1),
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha CHAR(64) NOT NULL
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

CREATE TABLE categoria (
    id_categoria int PRIMARY KEY IDENTITY(1, 1),
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
            'nao-sucedido',
            'avaliado'
        )
    ) NOT NULL,
    FOREIGN KEY (fk_categoria) REFERENCES servico(fk_categoria),
    FOREIGN KEY (fk_cliente) REFERENCES usuario(id_usuario),
    FOREIGN KEY (fk_prestador) REFERENCES servico(fk_usuario)
);

CREATE TABLE servico_prestado (
    fk_agendamento int,
    preco DECIMAL(7, 2) NOT NULL,
    descricao TEXT NOT NULL,
    data_servico DATETIME NOT NULL,
    FOREIGN KEY (fk_agendamento) REFERENCES agendamento(id_agendamento),
    PRIMARY KEY (fk_agendamento)
);

CREATE TABLE avaliacao (
    fk_servico_prestado int,
    valor int NOT NULL,
    descricao TEXT NOT NULL,
    FOREIGN KEY (fk_servico_prestado) REFERENCES servico_prestado(fk_agendamento),
    PRIMARY KEY (fk_servico_prestado)
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