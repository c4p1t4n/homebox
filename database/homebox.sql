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
    token CHAR(16) NOT NULL,
    TYPE ENUM("worker", "customer") NOT NULL,
    picture VARCHAR(250) NOT NULL,
    cep CHAR(8) NOT NULL
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
    message TEXT NOT NULL,
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
    fk_user int,
    fk_category int,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    reference_price DECIMAL(7, 2),
    FOREIGN KEY (fk_category) REFERENCES category(id_category),
    FOREIGN KEY (fk_user) REFERENCES user(id_user),
    PRIMARY KEY (fk_user, fk_category)
);

CREATE TABLE service_has_tag (
    fk_tag int,
    fk_user int,
    fk_category int,
    FOREIGN KEY (fk_user) REFERENCES service(fk_user),
    FOREIGN KEY (fk_category) REFERENCES service(fk_category),
    FOREIGN KEY (fk_tag) REFERENCES tag(id_tag),
    PRIMARY KEY (fk_tag, fk_user, fk_category)
);

CREATE TABLE scheduling (
    id_scheduling int PRIMARY KEY AUTO_INCREMENT,
    fk_customer int NOT NULL,
    fk_worker int NOT NULL,
    fk_category int NOT NULL,
    service_date DATETIME NOT NULL,
    service_status ENUM(
        "scheduled",
        "done",
        "worker-cancelled",
        "customer-cancelled",
        "not-executed",
        "rated"
    ) NOT NULL,
    FOREIGN KEY (fk_category) REFERENCES service(fk_category),
    FOREIGN KEY (fk_customer) REFERENCES user(id_user),
    FOREIGN KEY (fk_worker) REFERENCES service(fk_user)
);

CREATE TABLE accomplished_service (
    fk_scheduling int,
    price DECIMAL(7, 2) NOT NULL,
    description TEXT NOT NULL,
    service_date DATETIME NOT NULL,
    FOREIGN KEY (fk_scheduling) REFERENCES scheduling(id_scheduling),
    PRIMARY KEY (fk_scheduling)
);

CREATE TABLE rating (
    fk_accomplished_service int,
    rating int NOT NULL,
    description TEXT NOT NULL,
    FOREIGN KEY (fk_accomplished_service) REFERENCES accomplished_service(fk_scheduling),
    PRIMARY KEY (fk_accomplished_service)
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