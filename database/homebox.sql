DROP DATABASE IF EXISTS homebox;

CREATE DATABASE IF NOT EXISTS homebox;

USE homebox;

CREATE TABLE staff (
    id_staff int PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    PASSWORD CHAR(64) NOT NULL
);

CREATE TABLE tag (
    id_tag int PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE media (
    id_media int PRIMARY KEY AUTO_INCREMENT,
    TYPE ENUM("picture", "video", "audio") NOT NULL,
    path varchar(250)
);

CREATE TABLE user (
    id_user int PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    PASSWORD CHAR(64) NOT NULL,
    cpf CHAR(11) NOT NULL,
    token CHAR(16),
    TYPE ENUM("worker", "customer") NOT NULL,
    picture VARCHAR(250),
    cep CHAR(8) NOT NULL,
    authenticated ENUM("y", "n", "p") NOT NULL
);

CREATE TABLE notification (
    id_notification int PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    message TEXT NOT NULL
);

CREATE TABLE user_has_notification (
    fk_notification int NOT NULL,
    fk_user int NOT NULL,
    notification_date DATETIME NOT NULL,
    seen ENUM("y", "n") NOT NULL,
    FOREIGN KEY(fk_notification) REFERENCES notification(id_notification),
    FOREIGN KEY(fk_user) REFERENCES user(id_user),
    PRIMARY KEY(fk_notification, fk_user)
);

CREATE TABLE msg (
    id_msg int PRIMARY KEY AUTO_INCREMENT,
    message TEXT NOT NULL,
    automatic ENUM("y", "n") NOT NULL,
    fk_user int NOT NULL,
    FOREIGN KEY(fk_user) REFERENCES user(id_user)
);

CREATE TABLE msg_has_media (
    fk_msg int,
    fk_media int NOT NULL,
    FOREIGN KEY(fk_media) REFERENCES media(id_media),
    FOREIGN KEY(fk_msg) REFERENCES msg(id_msg),
    PRIMARY KEY(fk_msg, fk_media)
);

CREATE TABLE chat (
    id_chat int PRIMARY KEY AUTO_INCREMENT,
    opening_date DATETIME NOT NULL
);

CREATE TABLE chat_has_msg (
    fk_msg int NOT NULL,
    fk_chat int NOT NULL,
    send_date DATETIME NOT NULL,
    seen ENUM("y", "n") NOT NULL,
    FOREIGN KEY(fk_msg) REFERENCES msg(id_msg),
    FOREIGN KEY(fk_chat) REFERENCES chat(id_chat),
    PRIMARY KEY(fk_msg, fk_chat)
);

CREATE TABLE chat_has_user (
    fk_chat int NOT NULL,
    fk_user int NOT NULL,
    FOREIGN KEY(fk_chat) REFERENCES chat(id_chat),
    FOREIGN KEY(fk_user) REFERENCES user(id_user),
    PRIMARY KEY(fk_user, fk_chat)
);

CREATE TABLE category (
    id_category int PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE category_has_tag (
    fk_tag int,
    fk_category int,
    FOREIGN KEY (fk_category) REFERENCES category(id_category),
    FOREIGN KEY (fk_tag) REFERENCES tag(id_tag),
    PRIMARY KEY (fk_tag, fk_category)
);

CREATE TABLE user_has_tag (
    fk_tag int,
    fk_user int,
    FOREIGN KEY (fk_user) REFERENCES user(id_user),
    FOREIGN KEY (fk_tag) REFERENCES tag(id_tag),
    PRIMARY KEY (fk_tag, fk_user)
);

CREATE TABLE service (
    id_service INT PRIMARY KEY AUTO_INCREMENT,
    worker_id_user int,
    category_id_category int,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    reference_price DECIMAL(7, 2),
    FOREIGN KEY(category_id_category) REFERENCES category(id_category),
    FOREIGN KEY(worker_id_user) REFERENCES user(id_user)
);

CREATE TABLE service_has_tag (
    fk_tag int,
    fk_service int,
    FOREIGN KEY (fk_service) REFERENCES service(id_service),
    FOREIGN KEY (fk_tag) REFERENCES tag(id_tag),
    PRIMARY KEY (fk_tag, fk_service)
);

CREATE TABLE scheduling (
    id_scheduling int PRIMARY KEY AUTO_INCREMENT,
    fk_customer int NOT NULL,
    fk_service int NOT NULL,
    FOREIGN KEY (fk_service) REFERENCES service(id_service),
    FOREIGN KEY (fk_customer) REFERENCES user(id_user)
);

CREATE TABLE scheduling_status (
    id_scheduling_status int PRIMARY KEY AUTO_INCREMENT,
    service_status ENUM(
        "scheduled",
        "done",
        "worker-cancelled",
        "customer-cancelled",
        "not-executed",
        "rated"
    ) NOT NULL,
    status_date DATETIME NOT NULL,
    fk_scheduling int,
    FOREIGN KEY (fk_scheduling) REFERENCES scheduling(id_scheduling)
);

CREATE TABLE accomplished_service (
    id_accomplished_service int PRIMARY KEY AUTO_INCREMENT,
    fk_scheduling int,
    price DECIMAL(7, 2) NOT NULL,
    description TEXT NOT NULL,
    service_date DATETIME NOT NULL,
    FOREIGN KEY (fk_scheduling) REFERENCES scheduling(id_scheduling)
);

CREATE TABLE rating (
    id_rating int PRIMARY KEY AUTO_INCREMENT,
    fk_accomplished_service int,
    rating int NOT NULL,
    description TEXT NOT NULL,
    FOREIGN KEY (fk_accomplished_service) REFERENCES accomplished_service(fk_scheduling)
);

CREATE TABLE search (
    id_search int PRIMARY KEY AUTO_INCREMENT,
    value VARCHAR(100) NOT NULL
);

CREATE TABLE search_user (
    fk_search int,
    fk_user int,
    search_date DATETIME NOT NULL,
    FOREIGN KEY (fk_search) REFERENCES search(id_search),
    FOREIGN KEY (fk_user) REFERENCES user(id_user),
    PRIMARY KEY (fk_search, fk_user)
);

CREATE TABLE interest_access (
    fk_user int,
    fk_tag int,
    access_date DATETIME NOT NULL,
    FOREIGN KEY (fk_user) REFERENCES user(id_user),
    FOREIGN KEY (fk_tag) REFERENCES tag(id_tag),
    PRIMARY KEY (fk_user, fk_tag)
);

INSERT INTO
    staff
VALUES
    (
        NULL,
        "Ana Freitas",
        "ana.freitas@sptech.school",
        SHA2("ExSenha1", 256)
    ),
    (
        NULL,
        "Eduardo Dantas",
        "eduardo.oliveira@sptech.school",
        SHA2("ExSenha1", 256)
    ),
    (
        NULL,
        "Leo Igor",
        "leo.oliveira@sptech.school",
        SHA2("ExSenha1", 256)
    ),
    (
        NULL,
        "Nikolas Virionis",
        "nikolas.virionis@sptech.school",
        SHA2("ExSenha1", 256)
    ),
    (
        NULL,
        "Rodrigo Garcez",
        "rodrigo.hermann@sptech.school",
        SHA2("ExSenha1", 256)
    );

INSERT INTO
    category
VALUES
    (NULL, "Encanamento"),
    (NULL, "Elétrica"),
    (NULL, "Montagem de móveis"),
    (NULL, "Pintura"),
    (NULL, "Limpeza");

INSERT INTO
    user
VALUES
    (
        NULL,
        'José',
        'jose@gmail.com',
        SHA2("ExSenha1", 256),
        '12345678900',
        NULL,
        'worker',
        NULL,
        '12345678',
        'y'
    ),
    (
        NULL,
        'Robson',
        'jose@gmail.com',
        SHA2("ExSenha1", 256),
        '12345678900',
        NULL,
        'worker',
        NULL,
        '12345678',
        'y'
    ),
    (
        NULL,
        'Pedro',
        'jose@gmail.com',
        SHA2("ExSenha1", 256),
        '12345678900',
        NULL,
        'worker',
        NULL,
        '12345678',
        'n'
    ),
    (
        NULL,
        'Bruna',
        'jose@gmail.com',
        SHA2("ExSenha1", 256),
        '12345678900',
        NULL,
        'customer',
        NULL,
        '12345678',
        'y'
    ),
    (
        NULL,
        'Claudio',
        'jose@gmail.com',
        SHA2("ExSenha1", 256),
        '12345678900',
        NULL,
        'customer',
        NULL,
        '12345678',
        'y'
    );

INSERT INTO
    service (
        worker_id_user,
        category_id_category,
        name,
        description,
        reference_price
    )
VALUES
    (1, 1, "Troca de cano", "Troca de cano", 100.00),
    (
        1,
        1,
        "Desentupimento",
        "Desentupimento de calhas e ralos",
        200.00
    ),
    (2, 3, "Mesa", "Montagem de mesa", 50.00),
    (2, 3, "Armario", "Montagem de armario", 160.00),
    (
        3,
        4,
        "Pintura de parede",
        "Pintura de 1 parede",
        400.00
    ),
    (
        3,
        4,
        "Pintura de casa",
        "Pintura de mais de 7 paredes",
        5000.00
    );