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
    TYPE varchar(25),
    path varchar(250)
);

CREATE TABLE user (
    id_user int PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    gender VARCHAR(50) NOT NULL,
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

CREATE TABLE user_has_chat (
    id_user_has_chat int PRIMARY KEY AUTO_INCREMENT,
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
    service_status VARCHAR(28),
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
        'Masculino',
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
        'Masculino',
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
        'Feminino',
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
        'Masculino',
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
        'Feminino',
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
        'Masculino',
        'claudio@gmail.com',
        SHA2("ExSenha1", 256),
        '12345678900',
        NULL,
        'customer',
        NULL,
        DATE('1978-01-01'),
        '01310200',
        'y'
    ),
    (
        NULL,
        'user1',
        'Masculino',
        'email@email.com',
        SHA2("senha123", 256),
        '12345678900',
        NULL,
        'customer',
        NULL,
        DATE('1978-01-01'),
        '01310200',
        'y'
    ),
    (
        NULL,
        'user2',
        'Feminino',
        'email2@email.com',
        SHA2("ExSenha1", 256),
        '12345678900',
        NULL,
        'worker',
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
    (5, 1),(5, 2),(5, 3),(5, 4),(5, 5),(5, 6),
    (5, 4),(5, 3),(5, 1),(5, 2),
    
    (5, 6),(5, 5),(5, 4),(5, 3),(5, 2),(5, 1),
    (5, 2), (5, 4),(6, 1),(6, 2),

    (6, 3),(6, 4),(6, 5),(6, 6),(6, 2),(6, 6),
    (6, 4),(5, 3),(5, 6),(5, 4),

    (5, 2),(5, 1),(5, 3),(5, 5),(5, 2),(5, 1),
    (6, 2),(6, 1),(6, 5),(6, 5),
    
    (6, 4),(6, 1),(6, 2),(6, 2),(6, 6),(6, 3),
    (6, 1),(6, 2),(6, 3),(6, 4),
    
    (6, 5),(6, 6),(6, 1),(6, 2),(6, 6),(6, 1),
    (6, 2),(6, 2),(6, 2),(6, 2),

    (6, 1),(6, 4),(6, 3),(5, 4),(5, 2),(5, 2),
    (5, 2),(5, 3),(5, 4),(5, 5),
    
    (5, 6),(5, 1),(5, 5),(5, 3),(5, 4),(5, 1),
    (5, 1),(5, 3),(5, 2),(5, 2),
    
    (5, 1);

INSERT INTO
    scheduling_status(service_status, status_date, scheduling_id_scheduling)
VALUES
    ('scheduled', '2022-01-10 14:15:47', 1),('scheduled', '2022-01-10 13:11:47', 2),('scheduled', '2022-01-10 11:11:47', 3),        
    ('scheduled', '2022-02-11 01:22:47', 4),('scheduled', '2022-02-11 18:20:47', 5),('scheduled', '2022-02-11 19:12:47', 6),        
    ('scheduled', '2022-02-12 12:41:47', 7),('scheduled', '2022-03-12 18:19:47', 8),('scheduled', '2022-03-12 12:13:47', 9),        
    ('scheduled', '2022-04-13 11:31:47', 10),  

    ('done', '2022-05-10 14:14:47', 11),('done', '2022-05-13 13:17:47', 12),('done', '2022-05-11 10:17:47', 13),        
    ('done', '2022-06-11 16:13:47', 14),('done', '2022-06-18 16:17:47', 15),('done', '2022-06-19 11:17:47', 16),        
    ('done', '2022-07-12 18:12:47', 17),('done', '2022-07-18 19:17:47', 18),('done', '2022-07-12 12:17:47', 19),        
    ('done', '2022-08-13 12:11:47', 20),    

    ('worker-cancelled', '2022-09-14 13:17:47', 21),('worker-cancelled', '2022-09-14 13:17:47', 22),('worker-cancelled', '2022-09-13 17:17:47', 23),        
    ('worker-cancelled', '2022-10-01 14:17:47', 24),('worker-cancelled', '2022-10-12 12:17:47', 25),('worker-cancelled', '2022-10-13 17:17:47', 26),        
    ('worker-cancelled', '2022-11-12 15:17:47', 27),('worker-cancelled', '2022-11-11 17:17:47', 28),('worker-cancelled', '2022-11-13 17:17:47', 29),        
    ('worker-cancelled', '2022-12-11 16:17:47', 30),  

    ('customer-cancelled', '2022-02-11 11:17:47', 31),('customer-cancelled', '2022-02-10 11:17:47', 32),('customer-cancelled', '2022-02-11 18:17:47', 33),        
    ('customer-cancelled', '2022-03-19 14:17:47', 34),('customer-cancelled', '2022-03-11 10:17:47', 35),('customer-cancelled', '2022-03-14 16:17:47', 36),        
    ('customer-cancelled', '2022-04-12 18:17:47', 37),('customer-cancelled', '2022-04-13 23:17:47', 38),('customer-cancelled', '2022-04-13 11:17:47', 39),        
    ('customer-cancelled', '2022-05-13 19:17:47', 40),  

    ('not-executed', '2022-06-13 11:17:47', 41),('not-executed', '2022-06-13 13:17:47', 42),('not-executed', '2022-10-10 16:17:47', 43),        
    ('not-executed', '2022-07-19 14:17:47', 44),('not-executed', '2022-07-13 12:17:47', 45),('not-executed', '2022-10-11 12:17:47', 46),        
    ('not-executed', '2022-08-11 17:17:47', 47),('not-executed', '2022-08-13 11:17:47', 48),('not-executed', '2022-10-12 15:17:47', 49),        
    ('not-executed', '2022-09-12 20:17:47', 50),  

    ('rated', '2022-10-12 17:17:47', 51),('rated', '2022-10-15 17:17:47', 52),('rated', '2022-10-11 17:17:47', 53),        
    ('rated', '2022-10-13 17:17:47', 54),('rated', '2022-10-16 17:17:47', 55),('rated', '2022-10-12 17:17:47', 56),        
    ('rated', '2022-10-14 17:17:47', 57),('rated', '2022-10-10 17:17:47', 58),('rated', '2022-10-13 17:17:47', 59),        
    ('rated', '2022-10-15 17:17:47', 60),  

    ('scheduled', '2022-10-14 17:17:47', 61),('scheduled', '2022-10-11 17:17:47', 62),('scheduled', '2022-10-14 17:17:47', 63),        
    ('scheduled', '2022-10-15 17:17:47', 64),('scheduled', '2022-10-12 17:17:47', 65),('scheduled', '2022-10-15 17:17:47', 66),        
    ('scheduled', '2022-10-16 17:17:47', 67),('scheduled', '2022-10-13 17:17:47', 68),('scheduled', '2022-10-16 17:17:47', 69),        
    ('scheduled', '2022-10-10 17:17:47', 70), 

    ('done', '2022-10-10 17:17:47', 71),('done', '2022-10-15 17:17:47', 72),('done', '2022-10-11 17:17:47', 73),        
    ('done', '2022-10-11 17:17:47', 74),('done', '2022-10-16 17:17:47', 75),('done', '2022-10-11 17:17:47', 76),        
    ('done', '2022-10-12 17:17:47', 77),('done', '2022-10-10 17:17:47', 78),('done', '2022-10-12 17:17:47', 79),        
    ('done', '2022-10-13 17:17:47', 80),
    ('done', '2022-10-14 17:17:47', 81);

INSERT INTO
    accomplished_service(
        scheduling_id_scheduling,
        price,
        description,
        service_date
    )
VALUES
    (1, 10.0, "Rua tal 1", "2022-09-06"),
    (2, 10.0, "Rua tal 1", "2022-09-01"),
    (3, 10.0, "Rua tal 1", "2022-09-03"),
    (4, 10.0, "Rua tal 2", "2022-09-05"),
    (5, 10.0, "Rua tal 2", "2022-09-02"),
    (6, 10.0, "Rua tal 2", "2022-09-02"),
    (7, 10.0, "Rua tal 3", "2022-09-02"),
    (8, 10.0, "Rua tal 3", "2022-09-03"),
    (9, 10.0, "Rua tal 3", "2022-09-03"),
    (10, 10.0, "Rua tal 4", "2022-09-05"),
    (11, 10.0, "Rua tal 4", "2022-09-04"),
    (12, 10.0, "Rua tal 4", "2022-09-04"),
    (13, 10.0, "Rua tal 5", "2022-09-07"),
    (14, 10.0, "Rua tal 5", "2022-09-05"),
    (15, 10.0, "Rua tal 5", "2022-09-05"),
    (16, 10.0, "Rua tal 6", "2022-09-06"),
    (17, 10.0, "Rua tal 6", "2022-09-06"),
    (18, 10.0, "Rua tal 6", "2022-09-06"),
    (19, 10.0, "Rua tal 7", "2022-09-01"),
    (20, 10.0, "Rua tal 7", "2022-09-07"),
    (21, 10.0, "Rua tal 7", "2022-09-07"),
    (22, 10.0, "Rua tal 8", "2022-09-04"),
    (23, 10.0, "Rua tal 8", "2022-09-08"),
    (24, 10.0, "Rua tal 8", "2022-09-08"),
    (25, 10.0, "Rua tal 9", "2022-09-03"),
    (26, 10.0, "Rua tal 9", "2022-09-02"),
    (27, 10.0, "Rua tal 9", "2022-09-04"),
    (28, 10.0, "Rua tal 10", "2022-09-02"),
    (29, 10.0, "Rua tal 10", "2022-09-03"),
    (30, 10.0, "Rua tal 10", "2022-09-30"),
    (31, 10.0, "Rua tal 11", "2022-09-01"),
    (32, 10.0, "Rua tal 11", "2022-09-01"),
    (33, 10.0, "Rua tal 11", "2022-09-01"),
    (34, 10.0, "Rua tal 12", "2022-09-06"),
    (35, 10.0, "Rua tal 12", "2022-09-02"),
    (36, 10.0, "Rua tal 12", "2022-09-02"),
    (37, 10.0, "Rua tal 13", "2022-09-05"),
    (38, 10.0, "Rua tal 13", "2022-09-03"),
    (39, 10.0, "Rua tal 13", "2022-09-03"),
    (40, 10.0, "Rua tal 14", "2022-09-04"),
    (41, 10.0, "Rua tal 14", "2022-09-04"),
    (42, 10.0, "Rua tal 14", "2022-09-04"),
    (43, 10.0, "Rua tal 15", "2022-09-03"),
    (44, 10.0, "Rua tal 15", "2022-09-05"),
    (45, 10.0, "Rua tal 15", "2022-09-05"),
    (46, 10.0, "Rua tal 16", "2022-09-02"),
    (47, 10.0, "Rua tal 16", "2022-09-16"),
    (48, 10.0, "Rua tal 16", "2022-09-06"),
    (49, 10.0, "Rua tal 17", "2022-09-11"),
    (50, 10.0, "Rua tal 17", "2022-09-17"),
    (51, 10.0, "Rua tal 17", "2022-09-07"),
    (52, 10.0, "Rua tal 18", "2022-09-10"),
    (53, 10.0, "Rua tal 18", "2022-09-18"),
    (54, 10.0, "Rua tal 18", "2022-09-08"),
    (55, 10.0, "Rua tal 19", "2022-09-09"),
    (56, 10.0, "Rua tal 19", "2022-09-19"),
    (57, 10.0, "Rua tal 19", "2022-09-09"),
    (58, 10.0, "Rua tal 20", "2022-09-08"),
    (59, 10.0, "Rua tal 20", "2022-09-27"),
    (60, 10.0, "Rua tal 20", "2022-09-11"),
    (61, 10.0, "Rua tal 21", "2022-09-07"),
    (62, 10.0, "Rua tal 21", "2022-09-28"),
    (63, 10.0, "Rua tal 21", "2022-09-21"),
    (64, 10.0, "Rua tal 22", "2022-09-06"),
    (65, 10.0, "Rua tal 22", "2022-09-03"),
    (66, 10.0, "Rua tal 22", "2022-09-30"),
    (67, 10.0, "Rua tal 23", "2022-09-05"),
    (68, 10.0, "Rua tal 23", "2022-09-04"),
    (69, 10.0, "Rua tal 23", "2022-09-02"),
    (70, 10.0, "Rua tal 24", "2022-09-04"),
    (71, 10.0, "Rua tal 24", "2022-09-15"),
    (72, 10.0, "Rua tal 24", "2022-09-12"),
    (73, 10.0, "Rua tal 25", "2022-09-03"),
    (74, 10.0, "Rua tal 25", "2022-09-03"),
    (75, 10.0, "Rua tal 25", "2022-09-22"),
    (76, 10.0, "Rua tal 26", "2022-09-02"),
    (77, 10.0, "Rua tal 26", "2022-09-04"),
    (78, 10.0, "Rua tal 26", "2022-09-02"),
    (79, 10.0, "Rua tal 27", "2022-09-01"),
    (80, 10.0, "Rua tal 27", "2022-09-01"),
    (81, 10.0, "Rua tal 27", "2022-09-09");

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


insert into msg values(null,"Ola eu sou o Jose, como posso te ajudar?",'y',1),
		      (null,"Ola eu sou o Robson, como posso te ajudar?",'y',2),
		      (null,"Ola eu sou a Larissa, como posso te ajudar?",'y',3),
		      (null,"Ola eu sou a Pedro, como posso te ajudar?",'y',4);


insert into chat values(null,'2022-09-06 00:00:00');
insert into user_has_chat values(null,1,7),(null,1,8);

insert into tag values
(null, "limpeza"),
(null, "limpeza"),
(null, "limpeza"),
(null, "limpeza"),
(null, "limpeza");

insert into interest_access values
(null, 1, 1, NOW()),
(null, 1, 1, NOW()),
(null, 1, 1, NOW()),
(null, 1, 1, NOW()),
(null, 1, 1, NOW());
