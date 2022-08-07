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
    value VARCHAR(100) NOT NULL
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
    birth_date DATE NOT NULL,
    cep CHAR(8) NOT NULL,
    authenticated ENUM("y", "n", "p") NOT NULL
);

CREATE TABLE notification (
    id_notification int PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    message TEXT NOT NULL
);

CREATE TABLE user_has_notification (
    id_user_has_notification int PRIMARY KEY AUTO_INCREMENT,
    notification_id_notification int NOT NULL,
    user_id_user int NOT NULL,
    notification_date DATETIME NOT NULL,
    seen ENUM("y", "n") NOT NULL,
    FOREIGN KEY(notification_id_notification) REFERENCES notification(id_notification),
    FOREIGN KEY(user_id_user) REFERENCES user(id_user)
);

CREATE TABLE msg (
    id_msg int PRIMARY KEY AUTO_INCREMENT,
    message TEXT NOT NULL,
    automatic ENUM("y", "n") NOT NULL,
    user_id_user int NOT NULL,
    FOREIGN KEY(user_id_user) REFERENCES user(id_user)
);

CREATE TABLE msg_has_media (
    id_msg_has_media int PRIMARY KEY AUTO_INCREMENT,
    msg_id_msg int NOT NULL,
    media_id_media int NOT NULL,
    FOREIGN KEY(media_id_media) REFERENCES media(id_media),
    FOREIGN KEY(msg_id_msg) REFERENCES msg(id_msg)
);

CREATE TABLE chat (
    id_chat int PRIMARY KEY AUTO_INCREMENT,
    opening_date DATETIME NOT NULL
);

CREATE TABLE chat_has_msg (
    id_chat_has_msg int PRIMARY KEY AUTO_INCREMENT,
    msg_id_msg int NOT NULL,
    chat_id_chat int NOT NULL,
    send_date DATETIME NOT NULL,
    seen ENUM("y", "n") NOT NULL,
    FOREIGN KEY(msg_id_msg) REFERENCES msg(id_msg),
    FOREIGN KEY(chat_id_chat) REFERENCES chat(id_chat)
);

CREATE TABLE chat_has_user (
    id_chat_has_user int PRIMARY KEY AUTO_INCREMENT,
    chat_id_chat int NOT NULL,
    user_id_user int NOT NULL,
    FOREIGN KEY(chat_id_chat) REFERENCES chat(id_chat),
    FOREIGN KEY(user_id_user) REFERENCES user(id_user)
);

CREATE TABLE category (
    id_category int PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE category_has_tag (
    id_category_has_tag int PRIMARY KEY AUTO_INCREMENT,
    tag_id_tag int,
    category_id_category int,
    FOREIGN KEY (category_id_category) REFERENCES category(id_category),
    FOREIGN KEY (tag_id_tag) REFERENCES tag(id_tag)
);

CREATE TABLE user_has_tag (
    id_user_has_tag int PRIMARY KEY AUTO_INCREMENT,
    tag_id_tag int,
    user_id_user int,
    FOREIGN KEY (user_id_user) REFERENCES user(id_user),
    FOREIGN KEY (tag_id_tag) REFERENCES tag(id_tag)
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
    id_sevice_has_tag int PRIMARY KEY AUTO_INCREMENT,
    tag_id_tag int,
    service_id_service int,
    FOREIGN KEY (service_id_service) REFERENCES service(id_service),
    FOREIGN KEY (tag_id_tag) REFERENCES tag(id_tag)
);

CREATE TABLE scheduling (
    id_scheduling int PRIMARY KEY AUTO_INCREMENT,
    customer_id_user int NOT NULL,
    service_id_service int NOT NULL,
    FOREIGN KEY (service_id_service) REFERENCES service(id_service),
    FOREIGN KEY (customer_id_user) REFERENCES user(id_user)
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
    scheduling_id_scheduling int,
    FOREIGN KEY (scheduling_id_scheduling) REFERENCES scheduling(id_scheduling)
);

CREATE TABLE accomplished_service (
    id_accomplished_service int PRIMARY KEY AUTO_INCREMENT,
    scheduling_id_scheduling int,
    price DECIMAL(7, 2) NOT NULL,
    description TEXT NOT NULL,
    service_date DATETIME NOT NULL,
    FOREIGN KEY (scheduling_id_scheduling) REFERENCES scheduling(id_scheduling)
);

CREATE TABLE rating (
    id_rating int PRIMARY KEY AUTO_INCREMENT,
    fk_accomplished_service int,
    rating int NOT NULL,
    description TEXT,
    FOREIGN KEY (fk_accomplished_service) REFERENCES accomplished_service(scheduling_id_scheduling)
);

CREATE TABLE search (
    id_search int PRIMARY KEY AUTO_INCREMENT,
    value VARCHAR(100) NOT NULL
);

CREATE TABLE user_has_search (
    id_user_has_search int PRIMARY KEY AUTO_INCREMENT,
    search_is_search int,
    user_id_user int,
    search_date DATETIME NOT NULL,
    FOREIGN KEY (search_is_search) REFERENCES search(id_search),
    FOREIGN KEY (user_id_user) REFERENCES user(id_user)
);

CREATE TABLE interest_access (
    id_interest_access int PRIMARY KEY AUTO_INCREMENT,
    user_id_user int,
    tag_id_tag int,
    access_date DATETIME NOT NULL,
    FOREIGN KEY (user_id_user) REFERENCES user(id_user),
    FOREIGN KEY (tag_id_tag) REFERENCES tag(id_tag)
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
        'https://images.unsplash.com/photo-1468218457742-ee484fe2fe4c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=873&q=80',
        DATE('1965-01-01'),
        '01327900',
        'y'
    ),
    (
        NULL,
        'Robson',
        'robson@gmail.com',
        SHA2("ExSenha1", 256),
        '12345678900',
        NULL,
        'worker',
        'https://images.unsplash.com/photo-1522529599102-193c0d76b5b6?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80',
        DATE('1975-01-01'),
        '01414001',
        'y'
    ),
    (
        NULL,
        'Larissa',
        'lari@gmail.com',
        SHA2("ExSenha1", 256),
        '12345678900',
        NULL,
        'worker',
        'https://images.pexels.com/photos/7218004/pexels-photo-7218004.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1',
        DATE('1989-01-01'),
        '03314030',
        'y'
    ),
    (
        NULL,
        'Pedro',
        'pedro@gmail.com',
        SHA2("ExSenha1", 256),
        '12345678900',
        NULL,
        'worker',
        'https://images.pexels.com/photos/8005401/pexels-photo-8005401.jpeg?auto=compress&cs=tinysrgb&w=600',
        DATE('1980-01-01'),
        '56513000',
        'n'
    ),
    (
        NULL,
        'Bruna',
        'bruna@gmail.com',
        SHA2("ExSenha1", 256),
        '12345678900',
        NULL,
        'customer',
        NULL,
        DATE('1993-01-01'),
        '40320615',
        'y'
    ),
    (
        NULL,
        'Claudio',
        'claudio@gmail.com',
        SHA2("ExSenha1", 256),
        '12345678900',
        NULL,
        'customer',
        NULL,
        DATE('1978-01-01'),
        '01310200',
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

INSERT INTO
    scheduling(customer_id_user, service_id_service)
VALUES
    (5, 1),
    (5, 2),
    (5, 3),
    (5, 4),
    (5, 5),
    (5, 6),
    (5, 4),
    (5, 3),
    (5, 1),
    (5, 2),
    (5, 6),
    (5, 5),
    (5, 4),
    (5, 3),
    (5, 2),
    (5, 1),
    (5, 2),
    (5, 4),
    (6, 1),
    (6, 2),
    (6, 3),
    (6, 4),
    (6, 5),
    (6, 6),
    (6, 2),
    (6, 6),
    (6, 4),
    (5, 3),
    (5, 6),
    (5, 4),
    (5, 2),
    (5, 1),
    (5, 3),
    (5, 5),
    (5, 2),
    (5, 1),
    (6, 2),
    (6, 1),
    (6, 5),
    (6, 5),
    (6, 4),
    (6, 1),
    (6, 2),
    (6, 2),
    (6, 6),
    (6, 3),
    (6, 1),
    (6, 2),
    (6, 3),
    (6, 4),
    (6, 5),
    (6, 6),
    (6, 1),
    (6, 2),
    (6, 6),
    (6, 1),
    (6, 2),
    (6, 2),
    (6, 2),
    (6, 2),
    (6, 1),
    (6, 4),
    (6, 3),
    (5, 4),
    (5, 2),
    (5, 2),
    (5, 2),
    (5, 3),
    (5, 4),
    (5, 5),
    (5, 6),
    (5, 1),
    (5, 5),
    (5, 3),
    (5, 4),
    (5, 1),
    (5, 1),
    (5, 3),
    (5, 2),
    (5, 2),
    (5, 1);

INSERT INTO
    accomplished_service(
        scheduling_id_scheduling,
        price,
        description,
        service_date
    )
VALUES
    (1, 10.0, "DESCRIÇÃO 1", "2022-05-12"),
    (2, 10.0, "DESCRIÇÃO 1", "2022-05-01"),
    (3, 10.0, "DESCRIÇÃO 1", "2022-05-21"),
    (4, 10.0, "DESCRIÇÃO 2", "2022-04-15"),
    (5, 10.0, "DESCRIÇÃO 2", "2022-04-02"),
    (6, 10.0, "DESCRIÇÃO 2", "2022-09-22"),
    (7, 10.0, "DESCRIÇÃO 3", "2022-07-02"),
    (8, 10.0, "DESCRIÇÃO 3", "2022-05-03"),
    (9, 10.0, "DESCRIÇÃO 3", "2022-01-23"),
    (10, 10.0, "DESCRIÇÃO 4", "2022-01-25"),
    (11, 10.0, "DESCRIÇÃO 4", "2022-01-04"),
    (12, 10.0, "DESCRIÇÃO 4", "2022-03-24"),
    (13, 10.0, "DESCRIÇÃO 5", "2022-02-07"),
    (14, 10.0, "DESCRIÇÃO 5", "2022-02-05"),
    (15, 10.0, "DESCRIÇÃO 5", "2022-02-25"),
    (16, 10.0, "DESCRIÇÃO 6", "2022-05-11"),
    (17, 10.0, "DESCRIÇÃO 6", "2022-05-06"),
    (18, 10.0, "DESCRIÇÃO 6", "2022-01-26"),
    (19, 10.0, "DESCRIÇÃO 7", "2022-04-01"),
    (20, 10.0, "DESCRIÇÃO 7", "2022-04-07"),
    (21, 10.0, "DESCRIÇÃO 7", "2022-12-27"),
    (22, 10.0, "DESCRIÇÃO 8", "2022-09-04"),
    (23, 10.0, "DESCRIÇÃO 8", "2022-09-08"),
    (24, 10.0, "DESCRIÇÃO 8", "2022-10-28"),
    (25, 10.0, "DESCRIÇÃO 9", "2022-10-03"),
    (26, 10.0, "DESCRIÇÃO 9", "2022-10-09"),
    (27, 10.0, "DESCRIÇÃO 9", "2022-11-29"),
    (28, 10.0, "DESCRIÇÃO 10", "2022-11-02"),
    (29, 10.0, "DESCRIÇÃO 10", "2022-11-10"),
    (30, 10.0, "DESCRIÇÃO 10", "2022-09-30"),
    (31, 10.0, "DESCRIÇÃO 11", "2022-12-01"),
    (32, 10.0, "DESCRIÇÃO 11", "2022-12-11"),
    (33, 10.0, "DESCRIÇÃO 11", "2022-04-01"),
    (34, 10.0, "DESCRIÇÃO 12", "2022-10-16"),
    (35, 10.0, "DESCRIÇÃO 12", "2022-10-12"),
    (36, 10.0, "DESCRIÇÃO 12", "2022-01-02"),
    (37, 10.0, "DESCRIÇÃO 13", "2022-04-15"),
    (38, 10.0, "DESCRIÇÃO 13", "2022-04-13"),
    (39, 10.0, "DESCRIÇÃO 13", "2022-03-03"),
    (40, 10.0, "DESCRIÇÃO 14", "2022-02-14"),
    (41, 10.0, "DESCRIÇÃO 14", "2022-02-14"),
    (42, 10.0, "DESCRIÇÃO 14", "2022-07-04"),
    (43, 10.0, "DESCRIÇÃO 15", "2022-09-13"),
    (44, 10.0, "DESCRIÇÃO 15", "2022-09-15"),
    (45, 10.0, "DESCRIÇÃO 15", "2022-08-05"),
    (46, 10.0, "DESCRIÇÃO 16", "2022-07-12"),
    (47, 10.0, "DESCRIÇÃO 16", "2022-05-16"),
    (48, 10.0, "DESCRIÇÃO 16", "2022-01-06"),
    (49, 10.0, "DESCRIÇÃO 17", "2022-08-11"),
    (50, 10.0, "DESCRIÇÃO 17", "2022-05-17"),
    (51, 10.0, "DESCRIÇÃO 17", "2022-02-07"),
    (52, 10.0, "DESCRIÇÃO 18", "2022-05-10"),
    (53, 10.0, "DESCRIÇÃO 18", "2022-07-18"),
    (54, 10.0, "DESCRIÇÃO 18", "2022-04-08"),
    (55, 10.0, "DESCRIÇÃO 19", "2022-05-09"),
    (56, 10.0, "DESCRIÇÃO 19", "2022-02-19"),
    (57, 10.0, "DESCRIÇÃO 19", "2022-05-09"),
    (58, 10.0, "DESCRIÇÃO 20", "2022-05-08"),
    (59, 10.0, "DESCRIÇÃO 20", "2022-01-27"),
    (60, 10.0, "DESCRIÇÃO 20", "2022-07-11"),
    (61, 10.0, "DESCRIÇÃO 21", "2022-02-07"),
    (62, 10.0, "DESCRIÇÃO 21", "2022-03-28"),
    (63, 10.0, "DESCRIÇÃO 21", "2022-08-21"),
    (64, 10.0, "DESCRIÇÃO 22", "2022-01-06"),
    (65, 10.0, "DESCRIÇÃO 22", "2022-09-03"),
    (66, 10.0, "DESCRIÇÃO 22", "2022-09-30"),
    (67, 10.0, "DESCRIÇÃO 23", "2022-04-05"),
    (68, 10.0, "DESCRIÇÃO 23", "2022-08-04"),
    (69, 10.0, "DESCRIÇÃO 23", "2022-01-02"),
    (70, 10.0, "DESCRIÇÃO 24", "2022-03-04"),
    (71, 10.0, "DESCRIÇÃO 24", "2022-03-15"),
    (72, 10.0, "DESCRIÇÃO 24", "2022-07-12"),
    (73, 10.0, "DESCRIÇÃO 25", "2022-01-03"),
    (74, 10.0, "DESCRIÇÃO 25", "2022-02-03"),
    (75, 10.0, "DESCRIÇÃO 25", "2022-07-22"),
    (76, 10.0, "DESCRIÇÃO 26", "2022-09-02"),
    (77, 10.0, "DESCRIÇÃO 26", "2022-05-04"),
    (78, 10.0, "DESCRIÇÃO 26", "2022-07-02"),
    (79, 10.0, "DESCRIÇÃO 27", "2022-05-01"),
    (80, 10.0, "DESCRIÇÃO 27", "2022-01-01"),
    (81, 10.0, "DESCRIÇÃO 27", "2022-07-09");

INSERT INTO
    rating(
        fk_accomplished_service,
        rating
    )
VALUES
    (1, 1),
    (2, 5),
    (3, 2),
    (4, 5),
    (5, 3),
    (6, 1),
    (7, 4),
    (8, 2),
    (9, 5),
    (10, 2),
    (11, 4),
    (12, 3),
    (13, 4),
    (14, 4),
    (15, 5),
    (16, 5),
    (17, 1),
    (18, 1),
    (19, 3),
    (20, 3),
    (21, 4),
    (22, 3),
    (23, 5),
    (24, 4),
    (25, 1),
    (26, 5),
    (27, 2),
    (28, 4),
    (29, 2),
    (30, 5),
    (31, 2),
    (32, 1),
    (33, 3),
    (34, 2),
    (35, 4),
    (36, 3),
    (37, 5),
    (38, 1),
    (39, 1),
    (40, 1),
    (41, 2),
    (42, 2),
    (43, 3),
    (44, 3),
    (45, 4),
    (46, 4),
    (47, 2),
    (48, 2),
    (49, 5),
    (50, 3),
    (51, 1),
    (52, 4),
    (53, 2),
    (54, 5),
    (55, 3),
    (56, 3),
    (57, 3),
    (58, 4),
    (59, 4),
    (60, 5),
    (61, 5),
    (62, 1),
    (63, 4),
    (64, 2),
    (65, 4),
    (66, 4),
    (67, 3),
    (68, 5),
    (69, 4),
    (70, 1),
    (71, 5),
    (72, 3),
    (73, 1),
    (74, 5),
    (75, 5),
    (76, 2),
    (77, 1),
    (78, 3),
    (79, 2),
    (80, 4),
    (81, 2);