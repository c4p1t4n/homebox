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
    deleted CHAR(1),
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
INSERT INTO staff
VALUES (
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
INSERT INTO category
VALUES (NULL, "Encanamento"),
    (NULL, "Eletrica"),
    (NULL, "Montagem de moveis"),
    (NULL, "Pintura"),
    (NULL, "Limpeza");
INSERT INTO user
VALUES (
        NULL,
        'Jose',
        'Masculino',
        'jose@gmail.com',
        SHA2("ExSenha1", 256),
        '12345678900',
        NULL,
        'worker',
        'https://images.unsplash.com/photo-1468218457742-ee484fe2fe4c?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1173&q=80',
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
        'https://images.unsplash.com/photo-1522529599102-193c0d76b5b6?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80',
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
        'https://images.unsplash.com/photo-1601934066701-718da32cd34d?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80',
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
        'https://images.unsplash.com/photo-1535643302794-19c3804b874b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80',
        DATE('1980-01-01'),
        '03314030',
        'n'
    ),
    (
        NULL,
        'Bruna',
        'Feminino',
        'bruna@gmail.com',
        SHA2("ExSenha1", 256),
        '08225070',
        NULL,
        'customer',
        "https://images.unsplash.com/photo-1546961333-449e0af7b5db?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80",
        DATE('1993-01-01'),
        '08225070',
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
        "https://images.unsplash.com/photo-1489980557514-251d61e3eeb6?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80",
        DATE('1978-01-01'),
        '01120010',
        'y'
    ),
    (
        NULL,
        'Ana',
        'Feminino',
        'email@email.com',
        SHA2("senha123", 256),
        '12345678900',
        NULL,
        'customer',
        NULL,
        DATE('1978-01-01'),
        '04132010',
        'y'
    ),
    (
        NULL,
        'André',
        'Masculino',
        'email2@email.com',
        SHA2("ExSenha1", 256),
        '12345678900',
        NULL,
        'worker',
        NULL,
        DATE('1978-01-01'),
        '05790290',
        'y'
    ),(
        NULL,
        'Veronica',
        'Feminino',
        'email@email.com',
        SHA2("ExSenha1", 256),
        '04634000',
        NULL,
        'customer',
        NULL,
        DATE('1978-01-01'),
        '02040050',
        'y'
    );

INSERT INTO user(
        name,
        gender,
        email,
        password,
        cpf,
        type,
        birth_date,
        cep,
        authenticated,
        picture
    )
VALUES (
        'Elisa',
        'Feminino',
        'elisaworker48566@gmail.com',
        SHA2('ExSenha1', 256),
        '04789132650',
        'worker',
        '1996-11-26',
        '03034070',
        'n',
        'https://images.unsplash.com/photo-1506863530036-1efeddceb993?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=744&q=80'
    ),
('Rafael', 'Masculino', 'rafaelcustomer69811@gmail.com', SHA2('ExSenha1', 256), '09587143205', 'customer', '1978-06-02', '02112000', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Danilo', 'Masculino', 'danilocustomer60803@gmail.com', SHA2('ExSenha1', 256), '83017425690', 'customer', '1994-01-29', '01047904', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Ana Laura', 'Feminino', 'anacustomer87967@gmail.com', SHA2('ExSenha1', 256), '05169437820', 'customer', '1999-05-09', '01047905', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Gustavo Henrique', 'Masculino', 'gustavoworker49521@gmail.com', SHA2('ExSenha1', 256), '90537216812', 'worker', '1997-09-25', '01014020', 'n', 'https://images.unsplash.com/photo-1492562080023-ab3db95bfbce?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=848&q=80'),
('Isis', 'Feminino', 'isisworker12584@gmail.com', SHA2('ExSenha1', 256), '63589140224', 'worker', '1983-12-04', '04007004', 'n', 'https://images.unsplash.com/photo-1492627223639-6e980361988c?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1032&q=80'),
('Bárbara', 'Feminino', 'bárbaraworker99757@gmail.com', SHA2('ExSenha1', 256), '36580419270', 'worker', '2003-03-17', '02034900', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Vicente', 'Masculino', 'vicenteworker84539@gmail.com', SHA2('ExSenha1', 256), '40392658747', 'worker', '1994-05-24', '04263000', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Alana', 'Feminino', 'alanacustomer6768@gmail.com', SHA2('ExSenha1', 256), '60473829592', 'customer', '1981-05-16', '01047910', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Renan', 'Masculino', 'renancustomer84679@gmail.com', SHA2('ExSenha1', 256), '40928167313', 'customer', '1977-10-04', '01047911', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Davi Luiz', 'Masculino', 'daviworker15997@gmail.com', SHA2('ExSenha1', 256), '28361470590', 'worker', '2021-04-28', '02034901', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('João Gabriel', 'Masculino', 'joãocustomer16009@gmail.com', SHA2('ExSenha1', 256), '37561429061', 'customer', '2009-11-04', '01050901', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Matheus', 'Masculino', 'matheusworker68796@gmail.com', SHA2('ExSenha1', 256), '58327604171', 'worker', '1993-01-05', '01050903', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Vinicius', 'Masculino', 'viniciuscustomer39532@gmail.com', SHA2('ExSenha1', 256), '80971623422', 'customer', '2002-07-26', '01050904', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Mirella', 'Feminino', 'mirellaworker11181@gmail.com', SHA2('ExSenha1', 256), '09274835132', 'worker', '1995-12-14', '01222000', 'n', 'https://images.unsplash.com/photo-1525186492356-0fe09a5831df?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=743&q=80'),
('Francisco', 'Masculino', 'franciscocustomer95390@gmail.com', SHA2('ExSenha1', 256), '42653198070', 'customer', '1995-09-06', '01222001', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Luiz Gustavo', 'Masculino', 'luizworker50575@gmail.com', SHA2('ExSenha1', 256), '18243095705', 'worker', '1996-02-22', '01222901', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Enzo', 'Masculino', 'enzoworker74045@gmail.com', SHA2('ExSenha1', 256), '93708641566', 'worker', '2009-03-30', '01222902', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Maria Cecília', 'Feminino', 'mariacustomer55516@gmail.com', SHA2('ExSenha1', 256), '29518706476', 'customer', '1987-04-10', '01246100', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Luiz Miguel', 'Masculino', 'luizworker46071@gmail.com', SHA2('ExSenha1', 256), '68432917591', 'worker', '2012-04-27', '01322010', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Lara', 'Feminino', 'laraworker64906@gmail.com', SHA2('ExSenha1', 256), '58461237080', 'worker', '2004-02-15', '01322900', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Alana', 'Feminino', 'alanaworker48216@gmail.com', SHA2('ExSenha1', 256), '73892506400', 'worker', '1982-06-02', '01323070', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Olivia', 'Feminino', 'oliviacustomer2636@gmail.com', SHA2('ExSenha1', 256), '25473069800', 'customer', '2002-04-08', '01324000', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Isaac', 'Masculino', 'isaaccustomer90021@gmail.com', SHA2('ExSenha1', 256), '53921807441', 'customer', '1992-08-19', '01324001', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Raul', 'Masculino', 'raulcustomer81463@gmail.com', SHA2('ExSenha1', 256), '64238509170', 'customer', '2002-12-05', '01324900', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Pedro', 'Masculino', 'pedrocustomer98173@gmail.com', SHA2('ExSenha1', 256), '12389645089', 'customer', '1995-06-30', '01324901', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Noah', 'Masculino', 'noahcustomer75788@gmail.com', SHA2('ExSenha1', 256), '53829016433', 'customer', '2005-10-25', '01327000', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Anthony', 'Masculino', 'anthonyworker41832@gmail.com', SHA2('ExSenha1', 256), '14720638562', 'worker', '1993-02-27', '01327001', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Luiz Gustavo', 'Masculino', 'luizcustomer62747@gmail.com', SHA2('ExSenha1', 256), '72349601803', 'customer', '1992-04-29', '01327002', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Carlos Eduardo', 'Masculino', 'carlosworker92003@gmail.com', SHA2('ExSenha1', 256), '72635401934', 'worker', '2017-03-24', '01327900', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Giovanna', 'Feminino', 'giovannacustomer8879@gmail.com', SHA2('ExSenha1', 256), '19548620324', 'customer', '2003-11-01', '01327901', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Ana Vitória', 'Feminino', 'anacustomer65785@gmail.com', SHA2('ExSenha1', 256), '83059147639', 'customer', '1977-01-23', '01327902', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Felipe', 'Masculino', 'felipecustomer54650@gmail.com', SHA2('ExSenha1', 256), '58306421744', 'customer', '1990-07-14', '01327905', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Maysa', 'Feminino', 'maysaworker28805@gmail.com', SHA2('ExSenha1', 256), '97436120506', 'worker', '1976-01-02', '01327970', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Alexandre', 'Masculino', 'alexandrecustomer53294@gmail.com', SHA2('ExSenha1', 256), '69017328487', 'customer', '2011-05-26', '01501050', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Carolina', 'Feminino', 'carolinacustomer79326@gmail.com', SHA2('ExSenha1', 256), '65912483070', 'customer', '1998-06-15', '01536040', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Lucas', 'Masculino', 'lucasworker20813@gmail.com', SHA2('ExSenha1', 256), '70851236480', 'worker', '1980-04-03', '02035000', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Vinicius', 'Masculino', 'viniciuscustomer6562@gmail.com', SHA2('ExSenha1', 256), '98620713469', 'customer', '1995-12-13', '02036013', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Ana Sophia', 'Feminino', 'anaworker92482@gmail.com', SHA2('ExSenha1', 256), '43087126535', 'worker', '2015-04-25', '02046070', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Amanda', 'Feminino', 'amandaworker36817@gmail.com', SHA2('ExSenha1', 256), '10532498704', 'worker', '2015-05-12', '02052000', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Lucas', 'Masculino', 'lucasworker16718@gmail.com', SHA2('ExSenha1', 256), '64895310701', 'worker', '1981-11-21', '02052001', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Ana Luiza', 'Feminino', 'anaworker34118@gmail.com', SHA2('ExSenha1', 256), '26704519858', 'worker', '1981-05-23', '02052030', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Maria Cecília', 'Feminino', 'mariacustomer69288@gmail.com', SHA2('ExSenha1', 256), '35204176934', 'customer', '1992-05-19', '02060010', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Vitor', 'Masculino', 'vitorcustomer34288@gmail.com', SHA2('ExSenha1', 256), '35420879123', 'customer', '2002-02-09', '02075090', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Carlos Eduardo', 'Masculino', 'carlosworker96539@gmail.com', SHA2('ExSenha1', 256), '75189603202', 'worker', '1998-06-14', '02110020', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Samuel', 'Masculino', 'samuelworker2364@gmail.com', SHA2('ExSenha1', 256), '76590834229', 'worker', '1977-03-18', '02142030', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('João Gabriel', 'Masculino', 'joãoworker40909@gmail.com', SHA2('ExSenha1', 256), '34527986074', 'worker', '1993-04-01', '02143020', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Lucas Gabriel', 'Masculino', 'lucasworker91511@gmail.com', SHA2('ExSenha1', 256), '09264785167', 'worker', '2020-09-15', '02143030', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Marcelo', 'Masculino', 'marcelocustomer6223@gmail.com', SHA2('ExSenha1', 256), '26905187402', 'customer', '2021-02-22', '02143063', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Sofia', 'Feminino', 'sofiaworker33493@gmail.com', SHA2('ExSenha1', 256), '64718350948', 'worker', '1987-01-20', '02143065', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Caio', 'Masculino', 'caiocustomer70606@gmail.com', SHA2('ExSenha1', 256), '38164790222', 'customer', '2019-10-07', '02144010', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Fernando', 'Masculino', 'fernandocustomer82222@gmail.com', SHA2('ExSenha1', 256), '28569713428', 'customer', '2010-12-31', '02144020', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Valentina', 'Feminino', 'valentinaworker40622@gmail.com', SHA2('ExSenha1', 256), '60853792186', 'worker', '1973-10-22', '02144070', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Ana Laura', 'Feminino', 'anacustomer57661@gmail.com', SHA2('ExSenha1', 256), '90314285741', 'customer', '2021-12-26', '02146020', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Lucca', 'Masculino', 'luccacustomer53433@gmail.com', SHA2('ExSenha1', 256), '63172940543', 'customer', '2014-04-14', '02147050', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Raul', 'Masculino', 'raulcustomer51892@gmail.com', SHA2('ExSenha1', 256), '70145936252', 'customer', '2012-01-09', '02147060', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('João Felipe', 'Masculino', 'joãocustomer66384@gmail.com', SHA2('ExSenha1', 256), '61354792882', 'customer', '1988-01-19', '02147070', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Erick', 'Masculino', 'erickworker37348@gmail.com', SHA2('ExSenha1', 256), '78369124500', 'worker', '1994-07-12', '02147080', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Stephany', 'Feminino', 'stephanyworker21531@gmail.com', SHA2('ExSenha1', 256), '95031684739', 'worker', '2000-07-12', '02147902', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Joana', 'Feminino', 'joanacustomer65477@gmail.com', SHA2('ExSenha1', 256), '43876501253', 'customer', '2004-12-22', '02176000', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Lucca', 'Masculino', 'luccacustomer99013@gmail.com', SHA2('ExSenha1', 256), '90516482785', 'customer', '2004-09-24', '02176010', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Luiz Henrique', 'Masculino', 'luizworker61360@gmail.com', SHA2('ExSenha1', 256), '59142308615', 'worker', '2012-03-09', '02176040', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Camila', 'Feminino', 'camilaworker5514@gmail.com', SHA2('ExSenha1', 256), '04186975230', 'worker', '2004-08-23', '02176050', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Otávio', 'Masculino', 'otávioworker54250@gmail.com', SHA2('ExSenha1', 256), '54816207317', 'worker', '2018-07-19', '02176070', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Bryan', 'Masculino', 'bryancustomer23465@gmail.com', SHA2('ExSenha1', 256), '94802635133', 'customer', '1989-04-03', '02177070', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Murilo', 'Masculino', 'muriloworker85621@gmail.com', SHA2('ExSenha1', 256), '21508346933', 'worker', '1982-01-07', '02178040', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Fernando', 'Masculino', 'fernandocustomer75290@gmail.com', SHA2('ExSenha1', 256), '71629083577', 'customer', '1980-06-18', '02179010', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Lívia', 'Feminino', 'líviacustomer84981@gmail.com', SHA2('ExSenha1', 256), '07843961240', 'customer', '2019-07-10', '02179050', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Fernanda', 'Feminino', 'fernandaworker20425@gmail.com', SHA2('ExSenha1', 256), '81206753986', 'worker', '1992-01-28', '02180010', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Ian', 'Masculino', 'ianworker85258@gmail.com', SHA2('ExSenha1', 256), '29815706330', 'worker', '2022-03-01', '02180040', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Thales', 'Masculino', 'thalesworker96380@gmail.com', SHA2('ExSenha1', 256), '26783501930', 'worker', '2017-06-25', '02186020', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Luiz Felipe', 'Masculino', 'luizworker9716@gmail.com', SHA2('ExSenha1', 256), '83765402117', 'worker', '1971-09-04', '02186050', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Luigi', 'Masculino', 'luigicustomer96211@gmail.com', SHA2('ExSenha1', 256), '17308654290', 'customer', '2009-07-08', '02186060', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('João Pedro', 'Masculino', 'joãoworker61309@gmail.com', SHA2('ExSenha1', 256), '89231045741', 'worker', '2019-12-15', '02186080', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Felipe', 'Masculino', 'felipeworker43654@gmail.com', SHA2('ExSenha1', 256), '89603125415', 'worker', '2009-04-12', '02186090', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Maria Alice', 'Feminino', 'mariaworker3764@gmail.com', SHA2('ExSenha1', 256), '28071456390', 'worker', '2013-09-04', '02187010', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Isabel', 'Feminino', 'isabelworker88273@gmail.com', SHA2('ExSenha1', 256), '05174639206', 'worker', '2002-12-03', '02187020', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Davi Lucca', 'Masculino', 'daviworker97606@gmail.com', SHA2('ExSenha1', 256), '67193524828', 'worker', '1993-03-12', '02187050', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Arthur', 'Masculino', 'arthurworker59637@gmail.com', SHA2('ExSenha1', 256), '09168247567', 'worker', '1983-11-24', '02187070', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Thales', 'Masculino', 'thalescustomer21467@gmail.com', SHA2('ExSenha1', 256), '65802349700', 'customer', '2017-11-15', '02188010', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Julia', 'Feminino', 'juliaworker15090@gmail.com', SHA2('ExSenha1', 256), '34018729669', 'worker', '2018-03-16', '02188090', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Rebeca', 'Feminino', 'rebecacustomer56@gmail.com', SHA2('ExSenha1', 256), '01649253761', 'customer', '1970-05-10', '02189070', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Nina', 'Feminino', 'ninacustomer88316@gmail.com', SHA2('ExSenha1', 256), '06753219803', 'customer', '2019-10-18', '02190010', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Bruno', 'Masculino', 'brunocustomer22242@gmail.com', SHA2('ExSenha1', 256), '26319045833', 'customer', '2016-07-11', '02202000', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Lívia', 'Feminino', 'líviacustomer90382@gmail.com', SHA2('ExSenha1', 256), '98104725360', 'customer', '1998-06-30', '02206000', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Catarina', 'Feminino', 'catarinaworker70709@gmail.com', SHA2('ExSenha1', 256), '03726985492', 'worker', '2014-07-29', '02206001', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Letícia', 'Feminino', 'letíciaworker13267@gmail.com', SHA2('ExSenha1', 256), '47903218514', 'worker', '1974-02-21', '02206002', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Arthur', 'Masculino', 'arthurworker23605@gmail.com', SHA2('ExSenha1', 256), '08473192613', 'worker', '2021-04-02', '02221100', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Isabelly', 'Feminino', 'isabellyworker50480@gmail.com', SHA2('ExSenha1', 256), '37146052826', 'worker', '2012-03-03', '02232000', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Luiz Miguel', 'Masculino', 'luizworker66048@gmail.com', SHA2('ExSenha1', 256), '57403619820', 'worker', '1970-02-23', '02237070', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Rafael', 'Masculino', 'rafaelcustomer3069@gmail.com', SHA2('ExSenha1', 256), '67842910340', 'customer', '2016-12-20', '02241050', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Davi Luiz', 'Masculino', 'daviworker68941@gmail.com', SHA2('ExSenha1', 256), '87364509100', 'worker', '1990-08-18', '02250210', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Juan', 'Masculino', 'juanworker31449@gmail.com', SHA2('ExSenha1', 256), '73425081608', 'worker', '2000-12-31', '02254190', 'n', 'https://images.unsplash.com/photo-1494708001911-679f5d15a946?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80'),
('João Gabriel', 'Masculino', 'joãoworker21615@gmail.com', SHA2('ExSenha1', 256), '32610879431', 'worker', '1993-05-02', '02268030', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Isabel', 'Feminino', 'isabelcustomer137@gmail.com', SHA2('ExSenha1', 256), '38925467119', 'customer', '1987-10-07', '02268040', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Carlos Eduardo', 'Masculino', 'carloscustomer66398@gmail.com', SHA2('ExSenha1', 256), '86239057177', 'customer', '1995-08-11', '02279050', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('João Guilherme', 'Masculino', 'joãoworker96685@gmail.com', SHA2('ExSenha1', 256), '63274815900', 'worker', '2013-12-12', '02309050', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Kaique', 'Masculino', 'kaiquecustomer32373@gmail.com', SHA2('ExSenha1', 256), '23457916080', 'customer', '1999-02-24', '02322100', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Giovanna', 'Feminino', 'giovannaworker12991@gmail.com', SHA2('ExSenha1', 256), '36281950712', 'worker', '2001-01-12', '02323270', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Isabella', 'Feminino', 'isabellacustomer35324@gmail.com', SHA2('ExSenha1', 256), '17638520408', 'customer', '1979-01-13', '02323280', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png');

-- inserindo mais 150 customers
INSERT INTO user(
        name,
        gender,
        email,
        password,
        cpf,
        type,
        birth_date,
        cep,
        authenticated,
        picture
    ) VALUES
('Davi Luiz', 'Masculino', 'davicustomer48996@gmail.com', SHA2('ExSenha1', 256), '83204657965', 'customer', '1976-04-13', '05468130', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Lucas', 'Masculino', 'lucascustomer79080@gmail.com', SHA2('ExSenha1', 256), '48095621749', 'customer', '2019-02-06', '04152080', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Ana Sophia', 'Feminino', 'anacustomer54951@gmail.com', SHA2('ExSenha1', 256), '47316920599', 'customer', '2005-05-27', '02378300', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Evelyn', 'Feminino', 'evelyncustomer62396@gmail.com', SHA2('ExSenha1', 256), '42168573964', 'customer', '1988-06-08', '04424019', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Heitor', 'Masculino', 'heitorcustomer21670@gmail.com', SHA2('ExSenha1', 256), '76035294170', 'customer', '1975-05-02', '05887510', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('João Lucas', 'Masculino', 'joãocustomer31297@gmail.com', SHA2('ExSenha1', 256), '09123654716', 'customer', '1973-08-21', '04477055', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Bernardo', 'Masculino', 'bernardocustomer29903@gmail.com', SHA2('ExSenha1', 256), '93201485624', 'customer', '2006-11-05', '03931030', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Erick', 'Masculino', 'erickcustomer77214@gmail.com', SHA2('ExSenha1', 256), '26517984364', 'customer', '2013-03-16', '03912170', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Pedro', 'Masculino', 'pedrocustomer19358@gmail.com', SHA2('ExSenha1', 256), '72615430980', 'customer', '1978-12-06', '04626030', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Davi Lucas', 'Masculino', 'davicustomer97560@gmail.com', SHA2('ExSenha1', 256), '05627438126', 'customer', '1998-01-05', '04836386', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Ana Vitória', 'Feminino', 'anacustomer69132@gmail.com', SHA2('ExSenha1', 256), '80674951301', 'customer', '1976-03-25', '05804900', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Isadora', 'Feminino', 'isadoracustomer40343@gmail.com', SHA2('ExSenha1', 256), '63149275873', 'customer', '1985-11-25', '05639040', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Thiago', 'Masculino', 'thiagocustomer77176@gmail.com', SHA2('ExSenha1', 256), '29356017859', 'customer', '1985-10-12', '02842000', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Davi', 'Masculino', 'davicustomer29901@gmail.com', SHA2('ExSenha1', 256), '60124859305', 'customer', '1987-07-08', '03552110', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Cauã', 'Masculino', 'cauãcustomer28125@gmail.com', SHA2('ExSenha1', 256), '52741906858', 'customer', '1973-03-04', '05396080', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Antônio', 'Masculino', 'antôniocustomer18259@gmail.com', SHA2('ExSenha1', 256), '64807153226', 'customer', '2018-11-01', '04406175', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Maria Cecília', 'Feminino', 'mariacustomer86191@gmail.com', SHA2('ExSenha1', 256), '96745238128', 'customer', '1971-10-02', '01306902', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Fernanda', 'Feminino', 'fernandacustomer95798@gmail.com', SHA2('ExSenha1', 256), '56843970265', 'customer', '2002-12-11', '03237120', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Agatha', 'Feminino', 'agathacustomer77792@gmail.com', SHA2('ExSenha1', 256), '21576493873', 'customer', '1990-10-07', '08382560', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Miguel', 'Masculino', 'miguelcustomer3264@gmail.com', SHA2('ExSenha1', 256), '09186473204', 'customer', '2000-05-12', '04845205', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Luigi', 'Masculino', 'luigicustomer8903@gmail.com', SHA2('ExSenha1', 256), '74051832988', 'customer', '2005-10-14', '04137040', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Rodrigo', 'Masculino', 'rodrigocustomer15958@gmail.com', SHA2('ExSenha1', 256), '40982135750', 'customer', '1988-10-09', '08210710', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Anthony', 'Masculino', 'anthonycustomer23051@gmail.com', SHA2('ExSenha1', 256), '67915308475', 'customer', '2005-11-24', '04826170', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Pietro', 'Masculino', 'pietrocustomer41488@gmail.com', SHA2('ExSenha1', 256), '97841325088', 'customer', '1977-06-25', '02927070', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Levi', 'Masculino', 'levicustomer58322@gmail.com', SHA2('ExSenha1', 256), '25378164044', 'customer', '2006-10-29', '02220440', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Ana Beatriz', 'Feminino', 'anacustomer39035@gmail.com', SHA2('ExSenha1', 256), '69258014758', 'customer', '2003-04-02', '05749350', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Carlos Eduardo', 'Masculino', 'carloscustomer43610@gmail.com', SHA2('ExSenha1', 256), '32659704856', 'customer', '1978-01-25', '05366080', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Sabrina', 'Feminino', 'sabrinacustomer44815@gmail.com', SHA2('ExSenha1', 256), '76034251826', 'customer', '2005-10-01', '05174450', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Evelyn', 'Feminino', 'evelyncustomer99453@gmail.com', SHA2('ExSenha1', 256), '47396158001', 'customer', '1981-02-07', '05642908', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Sophia', 'Feminino', 'sophiacustomer78827@gmail.com', SHA2('ExSenha1', 256), '84706123526', 'customer', '2019-10-15', '04408095', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Kevin', 'Masculino', 'kevincustomer76084@gmail.com', SHA2('ExSenha1', 256), '23769014561', 'customer', '1982-09-20', '03410070', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Catarina', 'Feminino', 'catarinacustomer63466@gmail.com', SHA2('ExSenha1', 256), '32085146970', 'customer', '1988-10-05', '05180300', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Ana Júlia', 'Feminino', 'anacustomer55279@gmail.com', SHA2('ExSenha1', 256), '29471653819', 'customer', '1993-08-25', '05060060', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Isaac', 'Masculino', 'isaaccustomer12962@gmail.com', SHA2('ExSenha1', 256), '87365104244', 'customer', '1987-08-23', '04864040', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Thomas', 'Masculino', 'thomascustomer69674@gmail.com', SHA2('ExSenha1', 256), '48260571994', 'customer', '2016-04-14', '04459260', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Antônio', 'Masculino', 'antôniocustomer41016@gmail.com', SHA2('ExSenha1', 256), '92043857150', 'customer', '2016-06-04', '05856010', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Júlia', 'Feminino', 'júliacustomer470@gmail.com', SHA2('ExSenha1', 256), '75642038974', 'customer', '2021-01-16', '04851602', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Rafael', 'Masculino', 'rafaelcustomer15919@gmail.com', SHA2('ExSenha1', 256), '76291043599', 'customer', '2019-02-23', '03302000', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Catarina', 'Feminino', 'catarinacustomer21381@gmail.com', SHA2('ExSenha1', 256), '53197804600', 'customer', '2021-08-31', '03929150', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Enzo Gabriel', 'Masculino', 'enzocustomer43345@gmail.com', SHA2('ExSenha1', 256), '63498201506', 'customer', '1994-10-03', '04696190', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Maria Eduarda', 'Feminino', 'mariacustomer78432@gmail.com', SHA2('ExSenha1', 256), '54120637999', 'customer', '1995-04-22', '08371449', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Pietra', 'Feminino', 'pietracustomer76053@gmail.com', SHA2('ExSenha1', 256), '89432607178', 'customer', '2003-02-09', '05628090', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Otávio', 'Masculino', 'otáviocustomer35617@gmail.com', SHA2('ExSenha1', 256), '43601597225', 'customer', '1979-02-26', '03813100', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Gabrielly', 'Feminino', 'gabriellycustomer67567@gmail.com', SHA2('ExSenha1', 256), '94571306857', 'customer', '2007-09-06', '03806020', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Natália', 'Feminino', 'natáliacustomer49526@gmail.com', SHA2('ExSenha1', 256), '74256390170', 'customer', '2019-04-24', '03105901', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('João', 'Masculino', 'joãocustomer33610@gmail.com', SHA2('ExSenha1', 256), '49150637207', 'customer', '1982-01-17', '08150670', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Noah', 'Masculino', 'noahcustomer28840@gmail.com', SHA2('ExSenha1', 256), '90487126530', 'customer', '2009-10-30', '08485330', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Vicente', 'Masculino', 'vicentecustomer96308@gmail.com', SHA2('ExSenha1', 256), '57862014308', 'customer', '2001-07-17', '04844251', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Natália', 'Feminino', 'natáliacustomer67028@gmail.com', SHA2('ExSenha1', 256), '13982047650', 'customer', '1980-06-06', '03273115', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Igor', 'Masculino', 'igorcustomer82192@gmail.com', SHA2('ExSenha1', 256), '32651048933', 'customer', '2017-05-05', '04417020', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Pedro', 'Masculino', 'pedrocustomer2453@gmail.com', SHA2('ExSenha1', 256), '26541893098', 'customer', '1972-12-02', '03609090', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Ana Beatriz', 'Feminino', 'anacustomer71231@gmail.com', SHA2('ExSenha1', 256), '86327910440', 'customer', '2015-08-05', '04728060', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Ian', 'Masculino', 'iancustomer39824@gmail.com', SHA2('ExSenha1', 256), '78146029396', 'customer', '2012-06-09', '04205001', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Marcelo', 'Masculino', 'marcelocustomer75937@gmail.com', SHA2('ExSenha1', 256), '17209835695', 'customer', '2018-11-30', '05432030', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Isabella', 'Feminino', 'isabellacustomer61808@gmail.com', SHA2('ExSenha1', 256), '19463875255', 'customer', '2020-06-10', '04116140', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Pedro Lucas', 'Masculino', 'pedrocustomer76516@gmail.com', SHA2('ExSenha1', 256), '80791643557', 'customer', '1975-02-17', '05207180', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Bernardo', 'Masculino', 'bernardocustomer70600@gmail.com', SHA2('ExSenha1', 256), '96382457191', 'customer', '2008-07-21', '01438040', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Ana Lívia', 'Feminino', 'anacustomer39382@gmail.com', SHA2('ExSenha1', 256), '50826913415', 'customer', '2000-09-09', '04928150', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Isabella', 'Feminino', 'isabellacustomer49195@gmail.com', SHA2('ExSenha1', 256), '95347862092', 'customer', '2021-01-21', '01048903', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Brenda', 'Feminino', 'brendacustomer80631@gmail.com', SHA2('ExSenha1', 256), '06528143951', 'customer', '2004-09-04', '08220211', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Luiz Fernando', 'Masculino', 'luizcustomer97928@gmail.com', SHA2('ExSenha1', 256), '23876154090', 'customer', '2002-10-23', '08220211', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Ana', 'Feminino', 'anacustomer69036@gmail.com', SHA2('ExSenha1', 256), '34201658708', 'customer', '2019-06-21', '03667030', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Julia', 'Feminino', 'juliacustomer1648@gmail.com', SHA2('ExSenha1', 256), '57102348690', 'customer', '1995-12-25', '03193030', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Thiago', 'Masculino', 'thiagocustomer15844@gmail.com', SHA2('ExSenha1', 256), '75021968467', 'customer', '1970-06-02', '03923030', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Davi Luiz', 'Masculino', 'davicustomer42382@gmail.com', SHA2('ExSenha1', 256), '18962304740', 'customer', '2001-12-08', '05283040', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Samuel', 'Masculino', 'samuelcustomer86845@gmail.com', SHA2('ExSenha1', 256), '97260154306', 'customer', '2014-12-04', '08450530', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Maria Fernanda', 'Feminino', 'mariacustomer47595@gmail.com', SHA2('ExSenha1', 256), '39285410723', 'customer', '2012-09-19', '05115130', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Davi Luiz', 'Masculino', 'davicustomer94491@gmail.com', SHA2('ExSenha1', 256), '35740692199', 'customer', '2005-06-21', '04870445', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Caio', 'Masculino', 'caiocustomer93833@gmail.com', SHA2('ExSenha1', 256), '46017285335', 'customer', '2014-07-27', '05888130', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Isabelly', 'Feminino', 'isabellycustomer35068@gmail.com', SHA2('ExSenha1', 256), '04839265151', 'customer', '1995-08-25', '04537060', 'n', 'https://images.unsplash.com/photo-1532170579297-281918c8ae72?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1484&q=80'),
('Guilherme', 'Masculino', 'guilhermecustomer93114@gmail.com', SHA2('ExSenha1', 256), '38725964038', 'customer', '1999-11-13', '08341370', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Alice', 'Feminino', 'alicecustomer80237@gmail.com', SHA2('ExSenha1', 256), '56387410207', 'customer', '2006-04-27', '02177050', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Elisa', 'Feminino', 'elisacustomer35767@gmail.com', SHA2('ExSenha1', 256), '25197846364', 'customer', '2002-07-07', '08452375', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Gabrielly', 'Feminino', 'gabriellycustomer39174@gmail.com', SHA2('ExSenha1', 256), '32549680774', 'customer', '2005-11-01', '03649020', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Clarice', 'Feminino', 'claricecustomer10424@gmail.com', SHA2('ExSenha1', 256), '39240817603', 'customer', '2016-05-23', '04894090', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Ana Beatriz', 'Feminino', 'anacustomer12223@gmail.com', SHA2('ExSenha1', 256), '62419073878', 'customer', '1998-01-20', '05875200', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Pedro Lucas', 'Masculino', 'pedrocustomer37170@gmail.com', SHA2('ExSenha1', 256), '60715423916', 'customer', '1988-12-15', '05831050', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Gabriela', 'Feminino', 'gabrielacustomer82310@gmail.com', SHA2('ExSenha1', 256), '65431270826', 'customer', '1980-06-23', '05303030', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('João Guilherme', 'Masculino', 'joãocustomer72189@gmail.com', SHA2('ExSenha1', 256), '75892160386', 'customer', '1970-04-11', '04786033', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('João Vitor', 'Masculino', 'joãocustomer12301@gmail.com', SHA2('ExSenha1', 256), '19563427025', 'customer', '2018-10-14', '08121730', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Ana Júlia', 'Feminino', 'anacustomer63741@gmail.com', SHA2('ExSenha1', 256), '68074531244', 'customer', '2006-01-02', '05761320', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Clara', 'Feminino', 'claracustomer66182@gmail.com', SHA2('ExSenha1', 256), '01254367853', 'customer', '2007-11-22', '04510010', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Larissa', 'Feminino', 'larissacustomer84789@gmail.com', SHA2('ExSenha1', 256), '86214597364', 'customer', '2012-02-19', '02765100', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Ana Lívia', 'Feminino', 'anacustomer88734@gmail.com', SHA2('ExSenha1', 256), '03957826195', 'customer', '1999-04-24', '04845110', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Luiz Otávio', 'Masculino', 'luizcustomer45982@gmail.com', SHA2('ExSenha1', 256), '07436518208', 'customer', '1988-04-09', '02861170', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Fernanda', 'Feminino', 'fernandacustomer17003@gmail.com', SHA2('ExSenha1', 256), '17460598267', 'customer', '1971-01-18', '02317035', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Emanuella', 'Feminino', 'emanuellacustomer97559@gmail.com', SHA2('ExSenha1', 256), '61930427840', 'customer', '2004-05-03', '05088090', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Yasmin', 'Feminino', 'yasmincustomer77767@gmail.com', SHA2('ExSenha1', 256), '60814795285', 'customer', '1982-07-03', '02045970', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Rebeca', 'Feminino', 'rebecacustomer16201@gmail.com', SHA2('ExSenha1', 256), '91865723002', 'customer', '1974-10-07', '04756060', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Julia', 'Feminino', 'juliacustomer74901@gmail.com', SHA2('ExSenha1', 256), '27509368456', 'customer', '1988-05-13', '04942090', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Ian', 'Masculino', 'iancustomer89226@gmail.com', SHA2('ExSenha1', 256), '03918675475', 'customer', '1982-10-02', '03361001', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Carlos Eduardo', 'Masculino', 'carloscustomer57795@gmail.com', SHA2('ExSenha1', 256), '45209863115', 'customer', '1974-12-18', '02225090', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Luiz Gustavo', 'Masculino', 'luizcustomer51242@gmail.com', SHA2('ExSenha1', 256), '42387610571', 'customer', '1995-06-05', '05329030', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Maria Julia', 'Feminino', 'mariacustomer913@gmail.com', SHA2('ExSenha1', 256), '94051862720', 'customer', '2008-03-09', '05101902', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Lara', 'Feminino', 'laracustomer3394@gmail.com', SHA2('ExSenha1', 256), '13264079534', 'customer', '1990-11-14', '02551040', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Otávio', 'Masculino', 'otáviocustomer74673@gmail.com', SHA2('ExSenha1', 256), '82061739431', 'customer', '2006-04-08', '04256280', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Francisco', 'Masculino', 'franciscocustomer19128@gmail.com', SHA2('ExSenha1', 256), '75629438182', 'customer', '1981-10-07', '08122200', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('João Pedro', 'Masculino', 'joãocustomer13237@gmail.com', SHA2('ExSenha1', 256), '04683179296', 'customer', '1994-10-04', '08110430', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Ana Vitória', 'Feminino', 'anacustomer81829@gmail.com', SHA2('ExSenha1', 256), '05284139704', 'customer', '1980-12-18', '05617904', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Emanuel', 'Masculino', 'emanuelcustomer41564@gmail.com', SHA2('ExSenha1', 256), '48315607235', 'customer', '2003-03-08', '08430865', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Maysa', 'Feminino', 'maysacustomer81676@gmail.com', SHA2('ExSenha1', 256), '91438072678', 'customer', '1986-12-23', '01001000', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Maria Vitória', 'Feminino', 'mariacustomer16613@gmail.com', SHA2('ExSenha1', 256), '82934071669', 'customer', '2009-08-04', '01001001', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Maria Cecília', 'Feminino', 'mariacustomer40650@gmail.com', SHA2('ExSenha1', 256), '65182740930', 'customer', '2003-02-26', '01001900', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Brenda', 'Feminino', 'brendacustomer5544@gmail.com', SHA2('ExSenha1', 256), '20349167869', 'customer', '1974-08-15', '01001901', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Gabrielly', 'Feminino', 'gabriellycustomer13946@gmail.com', SHA2('ExSenha1', 256), '23597640125', 'customer', '1995-09-24', '01043000', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Kevin', 'Masculino', 'kevincustomer11900@gmail.com', SHA2('ExSenha1', 256), '04685793200', 'customer', '1980-10-03', '01043900', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Enzo', 'Masculino', 'enzocustomer6198@gmail.com', SHA2('ExSenha1', 256), '35678910493', 'customer', '1971-08-30', '01043901', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Enzo', 'Masculino', 'enzocustomer49607@gmail.com', SHA2('ExSenha1', 256), '31456920707', 'customer', '2010-10-03', '01043902', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Lucca', 'Masculino', 'luccacustomer80989@gmail.com', SHA2('ExSenha1', 256), '85092341670', 'customer', '1982-06-15', '01043903', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Lavínia', 'Feminino', 'lavíniacustomer72076@gmail.com', SHA2('ExSenha1', 256), '27548619049', 'customer', '1995-07-17', '01043904', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Maria Sophia', 'Feminino', 'mariacustomer59153@gmail.com', SHA2('ExSenha1', 256), '92536071812', 'customer', '2018-07-08', '01043905', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Isadora', 'Feminino', 'isadoracustomer36506@gmail.com', SHA2('ExSenha1', 256), '52981643746', 'customer', '2012-12-22', '01043906', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Maria Alice', 'Feminino', 'mariacustomer62444@gmail.com', SHA2('ExSenha1', 256), '35789641075', 'customer', '2005-11-17', '01043907', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Nina', 'Feminino', 'ninacustomer64402@gmail.com', SHA2('ExSenha1', 256), '02578314950', 'customer', '2001-04-28', '01043908', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Davi Luiz', 'Masculino', 'davicustomer24637@gmail.com', SHA2('ExSenha1', 256), '93425870114', 'customer', '2021-01-17', '01043909', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Ana Beatriz', 'Feminino', 'anacustomer60429@gmail.com', SHA2('ExSenha1', 256), '41537268007', 'customer', '2021-07-26', '01044000', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Vitor', 'Masculino', 'vitorcustomer9705@gmail.com', SHA2('ExSenha1', 256), '36204785117', 'customer', '1996-10-12', '01044901', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Marcos Vinicius', 'Masculino', 'marcoscustomer31800@gmail.com', SHA2('ExSenha1', 256), '96584302105', 'customer', '1972-11-22', '01044902', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('João Pedro', 'Masculino', 'joãocustomer96154@gmail.com', SHA2('ExSenha1', 256), '21563084708', 'customer', '2015-04-08', '01044903', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Erick', 'Masculino', 'erickcustomer96599@gmail.com', SHA2('ExSenha1', 256), '13849625060', 'customer', '1979-05-05', '01044904', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Mariane', 'Feminino', 'marianecustomer61074@gmail.com', SHA2('ExSenha1', 256), '32045896170', 'customer', '1984-10-20', '01044905', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Milena', 'Feminino', 'milenacustomer5861@gmail.com', SHA2('ExSenha1', 256), '46950287194', 'customer', '1972-09-08', '01044906', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Vitor Hugo', 'Masculino', 'vitorcustomer68292@gmail.com', SHA2('ExSenha1', 256), '59708132497', 'customer', '2022-05-19', '01044907', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Pietra', 'Feminino', 'pietracustomer85141@gmail.com', SHA2('ExSenha1', 256), '48172630913', 'customer', '2007-07-10', '01044908', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Lívia', 'Feminino', 'líviacustomer19742@gmail.com', SHA2('ExSenha1', 256), '56407182930', 'customer', '2001-02-12', '01044909', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Breno', 'Masculino', 'brenocustomer75723@gmail.com', SHA2('ExSenha1', 256), '91324806524', 'customer', '2009-02-24', '01103070', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Cecília', 'Feminino', 'cecíliacustomer44443@gmail.com', SHA2('ExSenha1', 256), '06921538451', 'customer', '1971-10-15', '01109080', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Theo', 'Masculino', 'theocustomer39214@gmail.com', SHA2('ExSenha1', 256), '29745380105', 'customer', '2018-12-02', '01120010', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Lucca', 'Masculino', 'luccacustomer13759@gmail.com', SHA2('ExSenha1', 256), '39028645160', 'customer', '1980-01-17', '01133000', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Ana Carolina', 'Feminino', 'anacustomer84618@gmail.com', SHA2('ExSenha1', 256), '63952104752', 'customer', '2001-09-10', '01134000', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Heloísa', 'Feminino', 'heloísacustomer13146@gmail.com', SHA2('ExSenha1', 256), '12576394043', 'customer', '1991-02-23', '01134901', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Cauã', 'Masculino', 'cauãcustomer31396@gmail.com', SHA2('ExSenha1', 256), '21879634546', 'customer', '1981-08-23', '01134902', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Rodrigo', 'Masculino', 'rodrigocustomer44261@gmail.com', SHA2('ExSenha1', 256), '05387964210', 'customer', '2014-07-27', '01214000', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Maria Eduarda', 'Feminino', 'mariacustomer64018@gmail.com', SHA2('ExSenha1', 256), '34152067934', 'customer', '1990-11-02', '01214100', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Ana Vitória', 'Feminino', 'anacustomer69476@gmail.com', SHA2('ExSenha1', 256), '30165842717', 'customer', '2011-12-19', '01214999', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Vitor Hugo', 'Masculino', 'vitorcustomer74431@gmail.com', SHA2('ExSenha1', 256), '73245610807', 'customer', '2000-11-03', '01223020', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('João Gabriel', 'Masculino', 'joãocustomer25826@gmail.com', SHA2('ExSenha1', 256), '89017543205', 'customer', '1978-12-28', '01326000', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Nicole', 'Feminino', 'nicolecustomer70495@gmail.com', SHA2('ExSenha1', 256), '89531407223', 'customer', '1984-08-22', '01327000', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Vitor', 'Masculino', 'vitorcustomer65786@gmail.com', SHA2('ExSenha1', 256), '78456210307', 'customer', '1988-08-13', '01327001', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Sophie', 'Feminino', 'sophiecustomer12602@gmail.com', SHA2('ExSenha1', 256), '02175948676', 'customer', '1987-05-26', '01327002', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Stephany', 'Feminino', 'stephanycustomer25533@gmail.com', SHA2('ExSenha1', 256), '63749810222', 'customer', '1993-04-30', '01327900', 'n', 'https://images.unsplash.com/photo-1526724663981-439e8b32ed6e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80'),
('Maria Clara', 'Feminino', 'mariacustomer52904@gmail.com', SHA2('ExSenha1', 256), '90312567499', 'customer', '2010-09-05', '01327901', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Eloah', 'Feminino', 'eloahcustomer86257@gmail.com', SHA2('ExSenha1', 256), '28574936065', 'customer', '2003-06-11', '01327902', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Julia', 'Feminino', 'juliacustomer92331@gmail.com', SHA2('ExSenha1', 256), '50269134743', 'customer', '1992-08-18', '01327905', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Cecília', 'Feminino', 'cecíliacustomer74856@gmail.com', SHA2('ExSenha1', 256), '83410759204', 'customer', '1978-04-16', '01327970', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Rafaela', 'Feminino', 'rafaelacustomer50961@gmail.com', SHA2('ExSenha1', 256), '81523794682', 'customer', '2008-03-02', '01536040', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Nicole', 'Feminino', 'nicolecustomer62720@gmail.com', SHA2('ExSenha1', 256), '15983240633', 'customer', '2021-07-25', '01548030', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Stella', 'Feminino', 'stellacustomer40195@gmail.com', SHA2('ExSenha1', 256), '13792508460', 'customer', '2011-04-03', '02060010', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Alícia', 'Feminino', 'alíciacustomer76336@gmail.com', SHA2('ExSenha1', 256), '75148236035', 'customer', '1994-08-26', '02124010', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png'),
('Isaac', 'Masculino', 'isaaccustomer76199@gmail.com', SHA2('ExSenha1', 256), '65397401820', 'customer', '2019-11-19', '02124030', 'n', 'https://s3.amazonaws.com/homebox.com/assets/img/profileIcon.png');

INSERT INTO service (
        worker_id_user,
        category_id_category,
        name,
        description,
        reference_price
    )
VALUES (1, 1, "Troca de cano", "Troca de cano", 100.00),
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

INSERT INTO service(worker_id_user,category_id_category,name,description,reference_price) VALUES
('8', '1', 'Troca de cano', 'Troca de cano da pia da cozinha.', '520.00'),
('8', '4', 'Pintura de parede', 'Pintura de 7 paredes.', '391.00'),
('9', '5', 'Limpeza',  'Limpeza pos obra.', '209.00'),
('11', '3', 'Montagem de movel', 'Montagem de um armario e uma cama.', '562.00'),
('11', '1', 'Troca de cano', 'Troca de cano da pia da cozinha e de dois banheiros.', '499.00'),
('12', '5', 'Limpeza', 'Limpeza de rotina.', '123.00'),
('12', '3', 'Montagem de movel', 'Montagem de uma mesa.', '286.00'),
('13', '4', 'Pintura de parede', 'Pintura de 4 paredes.', '154.00'),
('13', '5', 'Limpeza', 'Limpeza de rotina.', '147.00'),
('14', '2', 'Troca de chuveiro', 'Troca de dois chuveiros.', '172.00'),
('14', '2', 'Instalacao de luminaria', 'Instalacao de luminaria em 2 comodos.', '149.00'),
('9', '2', 'Instalacao eletrica', 'Checkup de todas as instalacoes eletricas do apartamento.', '666.00'),
('1', '1', 'Troca de cano', 'Troca de cano da pia da cozinha.', '100.00'),
('2', '4', 'Pintura de parede', 'Pintura de 7 paredes.', '391.00'),
('3', '5', 'Limpeza',  'Limpeza pos obra.', '209.00'),
('4', '3', 'Montagem de movel', 'Montagem de um armario e uma cama.', '562.00'),
('8', '1', 'Troca de cano', 'Troca de cano da pia da cozinha e de dois banheiros.', '499.00'),
('10', '5', 'Limpeza', 'Limpeza de rotina.', '123.00'),
('14', '3', 'Montagem de movel', 'Montagem de uma mesa.', '286.00'),
('15', '4', 'Pintura de parede', 'Pintura de 4 paredes.', '154.00'),
('16', '5', 'Limpeza', 'Limpeza de rotina.', '147.00'),
('17', '2', 'Troca de chuveiro', 'Troca de dois chuveiros.', '172.00'),
('20', '2', 'Instalacao de luminaria', 'Instalacao de luminaria em 2 comodos.', '149.00'),
('22', '2', 'Instalacao eletrica', 'Checkup de todas as instalacoes eletricas do apartamento.', '666.00'),
('24', '1', 'Troca de cano', 'Troca de cano da pia da cozinha.', '100.00'),
('26', '4', 'Pintura de parede', 'Pintura de 7 paredes.', '391.00'),
('27', '5', 'Limpeza',  'Limpeza pos obra.', '209.00'),
('29', '3', 'Montagem de movel', 'Montagem de um armario e uma cama.', '562.00'),
('30', '1', 'Troca de cano', 'Troca de cano da pia da cozinha e de dois banheiros.', '499.00'),
('31', '5', 'Limpeza', 'Limpeza de rotina.', '123.00'),
('37', '3', 'Montagem de movel', 'Montagem de uma mesa.', '286.00'),
('39', '4', 'Pintura de parede', 'Pintura de 4 paredes.', '154.00'),
('43', '5', 'Limpeza', 'Limpeza de rotina.', '147.00'),
('46', '2', 'Troca de chuveiro', 'Troca de dois chuveiros.', '172.00'),
('48', '2', 'Instalacao de luminaria', 'Instalacao de luminaria em 2 comodos.', '149.00'),
('49', '2', 'Instalacao eletrica', 'Checkup de todas as instalacoes eletricas do apartamento.', '666.00'),
('50', '2', 'Instalacao eletrica', 'Checkup de todas as instalacoes eletricas do apartamento.', '666.00'),
('51', '1', 'Troca de cano', 'Troca de cano da pia da cozinha.', '100.00'),
('54', '4', 'Pintura de parede', 'Pintura de 7 paredes.', '391.00'),
('55', '5', 'Limpeza',  'Limpeza pos obra.', '209.00'),
('56', '3', 'Montagem de movel', 'Montagem de um armario e uma cama.', '562.00'),
('57', '1', 'Troca de cano', 'Troca de cano da pia da cozinha e de dois banheiros.', '499.00'),
('59', '5', 'Limpeza', 'Limpeza de rotina.', '123.00'),
('62', '3', 'Montagem de movel', 'Montagem de uma mesa.', '286.00'),
('67', '4', 'Pintura de parede', 'Pintura de 4 paredes.', '154.00'),
('68', '5', 'Limpeza', 'Limpeza de rotina.', '147.00'),
('71', '2', 'Troca de chuveiro', 'Troca de dois chuveiros.', '172.00'),
('72', '2', 'Instalacao de luminaria', 'Instalacao de luminaria em 2 comodos.', '149.00'),
('73', '2', 'Instalacao eletrica', 'Checkup de todas as instalacoes eletricas do apartamento.', '666.00'),
('75', '4', 'Pintura de parede', 'Pintura de 7 paredes.', '391.00'),
('78', '5', 'Limpeza',  'Limpeza pos obra.', '209.00'),
('79', '3', 'Montagem de movel', 'Montagem de um armario e uma cama.', '562.00'),
('80', '1', 'Troca de cano', 'Troca de cano da pia da cozinha e de dois banheiros.', '499.00'),
('81', '5', 'Limpeza', 'Limpeza de rotina.', '123.00'),
('83', '3', 'Montagem de movel', 'Montagem de uma mesa.', '286.00'),
('84', '4', 'Pintura de parede', 'Pintura de 4 paredes.', '154.00'),
('85', '5', 'Limpeza', 'Limpeza de rotina.', '147.00'),
('86', '2', 'Troca de chuveiro', 'Troca de dois chuveiros.', '172.00'),
('87', '2', 'Instalacao de luminaria', 'Instalacao de luminaria em 2 comodos.', '149.00'),
('88', '2', 'Instalacao eletrica', 'Checkup de todas as instalacoes eletricas do apartamento.', '666.00'),
('90', '4', 'Pintura de parede', 'Pintura de 7 paredes.', '391.00'),
('95', '5', 'Limpeza',  'Limpeza pos obra.', '209.00'),
('96', '3', 'Montagem de movel', 'Montagem de um armario e uma cama.', '562.00'),
('97', '1', 'Troca de cano', 'Troca de cano da pia da cozinha e de dois banheiros.', '499.00'),
('98', '5', 'Limpeza', 'Limpeza de rotina.', '123.00'),
('99', '3', 'Montagem de movel', 'Montagem de uma mesa.', '286.00'),
('101', '4', 'Pintura de parede', 'Pintura de 4 paredes.', '154.00'),
('102', '5', 'Limpeza', 'Limpeza de rotina.', '147.00'),
('103', '2', 'Troca de chuveiro', 'Troca de dois chuveiros.', '172.00'),
('106', '2', 'Troca de chuveiro', 'Troca de dois chuveiros.', '172.00'),
('108', '2', 'Instalacao de luminaria', 'Instalacao de luminaria em 2 comodos.', '149.00'),
('108', '2', 'Instalacao eletrica', 'Checkup de todas as instalacoes eletricas do apartamento.', '666.00'),
-- alterar daqui pra baixo;
('8', '1', 'Troca de cano', 'Troca de cano da pia da cozinha.', '520.00'),
('8', '4', 'Pintura de parede', 'Pintura de 7 paredes.', '391.00'),
('9', '5', 'Limpeza',  'Limpeza pos obra.', '209.00'),
('11', '3', 'Montagem de movel', 'Montagem de um armario e uma cama.', '562.00'),
('11', '1', 'Troca de cano', 'Troca de cano da pia da cozinha e de dois banheiros.', '499.00'),
('12', '5', 'Limpeza', 'Limpeza de rotina.', '123.00'),
('12', '3', 'Montagem de movel', 'Montagem de uma mesa.', '286.00'),
('13', '4', 'Pintura de parede', 'Pintura de 4 paredes.', '154.00'),
('13', '5', 'Limpeza', 'Limpeza de rotina.', '147.00'),
('14', '2', 'Troca de chuveiro', 'Troca de dois chuveiros.', '172.00'),
('14', '2', 'Instalacao de luminaria', 'Instalacao de luminaria em 2 comodos.', '149.00'),
('9', '2', 'Instalacao eletrica', 'Checkup de todas as instalacoes eletricas do apartamento.', '666.00'),
('1', '1', 'Troca de cano', 'Troca de cano da pia da cozinha.', '100.00'),
('2', '4', 'Pintura de parede', 'Pintura de 7 paredes.', '391.00'),
('3', '5', 'Limpeza',  'Limpeza pos obra.', '209.00'),
('4', '3', 'Montagem de movel', 'Montagem de um armario e uma cama.', '562.00'),
('8', '1', 'Troca de cano', 'Troca de cano da pia da cozinha e de dois banheiros.', '499.00'),
('10', '5', 'Limpeza', 'Limpeza de rotina.', '123.00'),
('14', '3', 'Montagem de movel', 'Montagem de uma mesa.', '286.00'),
('15', '4', 'Pintura de parede', 'Pintura de 4 paredes.', '154.00'),
('16', '5', 'Limpeza', 'Limpeza de rotina.', '147.00'),
('17', '2', 'Troca de chuveiro', 'Troca de dois chuveiros.', '172.00'),
('20', '2', 'Instalacao de luminaria', 'Instalacao de luminaria em 2 comodos.', '149.00'),
('22', '2', 'Instalacao eletrica', 'Checkup de todas as instalacoes eletricas do apartamento.', '666.00'),
('24', '1', 'Troca de cano', 'Troca de cano da pia da cozinha.', '100.00'),
('26', '4', 'Pintura de parede', 'Pintura de 7 paredes.', '391.00'),
('27', '5', 'Limpeza',  'Limpeza pos obra.', '209.00'),
('29', '3', 'Montagem de movel', 'Montagem de um armario e uma cama.', '562.00'),
('30', '1', 'Troca de cano', 'Troca de cano da pia da cozinha e de dois banheiros.', '499.00'),
('31', '5', 'Limpeza', 'Limpeza de rotina.', '123.00'),
('37', '3', 'Montagem de movel', 'Montagem de uma mesa.', '286.00'),
('39', '4', 'Pintura de parede', 'Pintura de 4 paredes.', '154.00'),
('43', '5', 'Limpeza', 'Limpeza de rotina.', '147.00'),
('46', '2', 'Troca de chuveiro', 'Troca de dois chuveiros.', '172.00'),
('48', '2', 'Instalacao de luminaria', 'Instalacao de luminaria em 2 comodos.', '149.00'),
('49', '2', 'Instalacao eletrica', 'Checkup de todas as instalacoes eletricas do apartamento.', '666.00'),
('50', '2', 'Instalacao eletrica', 'Checkup de todas as instalacoes eletricas do apartamento.', '666.00'),
('51', '1', 'Troca de cano', 'Troca de cano da pia da cozinha.', '100.00'),
('54', '4', 'Pintura de parede', 'Pintura de 7 paredes.', '391.00'),
('55', '5', 'Limpeza',  'Limpeza pos obra.', '209.00'),
('56', '3', 'Montagem de movel', 'Montagem de um armario e uma cama.', '562.00'),
('57', '1', 'Troca de cano', 'Troca de cano da pia da cozinha e de dois banheiros.', '499.00'),
('59', '5', 'Limpeza', 'Limpeza de rotina.', '123.00'),
('62', '3', 'Montagem de movel', 'Montagem de uma mesa.', '286.00'),
('67', '4', 'Pintura de parede', 'Pintura de 4 paredes.', '154.00'),
('68', '5', 'Limpeza', 'Limpeza de rotina.', '147.00'),
('71', '2', 'Troca de chuveiro', 'Troca de dois chuveiros.', '172.00'),
('72', '2', 'Instalacao de luminaria', 'Instalacao de luminaria em 2 comodos.', '149.00'),
('73', '2', 'Instalacao eletrica', 'Checkup de todas as instalacoes eletricas do apartamento.', '666.00'),
('75', '4', 'Pintura de parede', 'Pintura de 7 paredes.', '391.00'),
('78', '5', 'Limpeza',  'Limpeza pos obra.', '209.00'),
('79', '3', 'Montagem de movel', 'Montagem de um armario e uma cama.', '562.00'),
('80', '1', 'Troca de cano', 'Troca de cano da pia da cozinha e de dois banheiros.', '499.00'),
('81', '5', 'Limpeza', 'Limpeza de rotina.', '123.00'),
('83', '3', 'Montagem de movel', 'Montagem de uma mesa.', '286.00'),
('84', '4', 'Pintura de parede', 'Pintura de 4 paredes.', '154.00'),
('85', '5', 'Limpeza', 'Limpeza de rotina.', '147.00'),
('86', '2', 'Troca de chuveiro', 'Troca de dois chuveiros.', '172.00'),
('87', '2', 'Instalacao de luminaria', 'Instalacao de luminaria em 2 comodos.', '149.00'),
('88', '2', 'Instalacao eletrica', 'Checkup de todas as instalacoes eletricas do apartamento.', '666.00'),
('90', '4', 'Pintura de parede', 'Pintura de 7 paredes.', '391.00'),
('95', '5', 'Limpeza',  'Limpeza pos obra.', '209.00'),
('96', '3', 'Montagem de movel', 'Montagem de um armario e uma cama.', '562.00'),
('97', '1', 'Troca de cano', 'Troca de cano da pia da cozinha e de dois banheiros.', '499.00'),
('98', '5', 'Limpeza', 'Limpeza de rotina.', '123.00'),
('99', '3', 'Montagem de movel', 'Montagem de uma mesa.', '286.00'),
('101', '4', 'Pintura de parede', 'Pintura de 4 paredes.', '154.00'),
('102', '5', 'Limpeza', 'Limpeza de rotina.', '147.00'),
('103', '2', 'Troca de chuveiro', 'Troca de dois chuveiros.', '172.00'),
('106', '2', 'Troca de chuveiro', 'Troca de dois chuveiros.', '172.00'),
('108', '2', 'Instalacao de luminaria', 'Instalacao de luminaria em 2 comodos.', '149.00'),
('108', '2', 'Instalacao eletrica', 'Checkup de todas as instalacoes eletricas do apartamento.', '666.00'),
('8', '1', 'Troca de cano', 'Troca de cano da pia da cozinha.', '520.00'),
('8', '4', 'Pintura de parede', 'Pintura de 7 paredes.', '391.00'),
('9', '5', 'Limpeza',  'Limpeza pos obra.', '209.00'),
('11', '3', 'Montagem de movel', 'Montagem de um armario e uma cama.', '562.00'),
('11', '1', 'Troca de cano', 'Troca de cano da pia da cozinha e de dois banheiros.', '499.00'),
('12', '5', 'Limpeza', 'Limpeza de rotina.', '123.00'),
('12', '3', 'Montagem de movel', 'Montagem de uma mesa.', '286.00'),
('13', '4', 'Pintura de parede', 'Pintura de 4 paredes.', '154.00'),
('13', '5', 'Limpeza', 'Limpeza de rotina.', '147.00'),
('14', '2', 'Troca de chuveiro', 'Troca de dois chuveiros.', '172.00'),
('14', '2', 'Instalacao de luminaria', 'Instalacao de luminaria em 2 comodos.', '149.00'),
('9', '2', 'Instalacao eletrica', 'Checkup de todas as instalacoes eletricas do apartamento.', '666.00'),
('1', '1', 'Troca de cano', 'Troca de cano da pia da cozinha e do banheiro.', '180.00'),
('2', '4', 'Pintura de parede', 'Pintura de 7 paredes.', '391.00'),
('3', '5', 'Limpeza',  'Limpeza pos obra.', '209.00'),
('4', '3', 'Montagem de movel', 'Montagem de um armario e uma cama.', '562.00'),
('8', '1', 'Troca de cano', 'Troca de cano da pia da cozinha e de dois banheiros.', '499.00'),
('10', '5', 'Limpeza', 'Limpeza de rotina.', '123.00'),
('14', '3', 'Montagem de movel', 'Montagem de uma mesa.', '286.00'),
('15', '4', 'Pintura de parede', 'Pintura de 4 paredes.', '154.00'),
('16', '5', 'Limpeza', 'Limpeza de rotina.', '147.00'),
('17', '2', 'Troca de chuveiro', 'Troca de dois chuveiros.', '172.00'),
('20', '2', 'Instalacao de luminaria', 'Instalacao de luminaria em 2 comodos.', '149.00'),
('22', '2', 'Instalacao eletrica', 'Checkup de todas as instalacoes eletricas do apartamento.', '666.00'),
('24', '1', 'Troca de cano', 'Troca de cano da pia da cozinha.', '100.00'),
('26', '4', 'Pintura de parede', 'Pintura de 7 paredes.', '391.00'),
('27', '5', 'Limpeza',  'Limpeza pos obra.', '209.00'),
('29', '3', 'Montagem de movel', 'Montagem de um armario e uma cama.', '562.00'),
('30', '1', 'Troca de cano', 'Troca de cano da pia da cozinha e de dois banheiros.', '499.00'),
('31', '5', 'Limpeza', 'Limpeza de rotina.', '123.00'),
('37', '3', 'Montagem de movel', 'Montagem de uma mesa.', '286.00'),
('39', '4', 'Pintura de parede', 'Pintura de 4 paredes.', '154.00'),
('43', '5', 'Limpeza', 'Limpeza de rotina.', '147.00'),
('46', '2', 'Troca de chuveiro', 'Troca de dois chuveiros.', '172.00'),
('48', '2', 'Instalacao de luminaria', 'Instalacao de luminaria em 2 comodos.', '149.00'),
('49', '2', 'Instalacao eletrica', 'Checkup de todas as instalacoes eletricas do apartamento.', '666.00'),
('50', '2', 'Instalacao eletrica', 'Checkup de todas as instalacoes eletricas do apartamento.', '666.00'),
('51', '1', 'Troca de cano', 'Troca de cano da pia da cozinha.', '100.00'),
('54', '4', 'Pintura de parede', 'Pintura de 7 paredes.', '391.00'),
('55', '5', 'Limpeza',  'Limpeza pos obra.', '209.00'),
('56', '3', 'Montagem de movel', 'Montagem de um armario e uma cama.', '562.00'),
('57', '1', 'Troca de cano', 'Troca de cano da pia da cozinha e de dois banheiros.', '499.00'),
('59', '5', 'Limpeza', 'Limpeza de rotina.', '123.00'),
('62', '3', 'Montagem de movel', 'Montagem de uma mesa.', '286.00'),
('67', '4', 'Pintura de parede', 'Pintura de 4 paredes.', '154.00'),
('68', '5', 'Limpeza', 'Limpeza de rotina.', '147.00'),
('71', '2', 'Troca de chuveiro', 'Troca de dois chuveiros.', '172.00'),
('72', '2', 'Instalacao de luminaria', 'Instalacao de luminaria em 2 comodos.', '149.00'),
('73', '2', 'Instalacao eletrica', 'Checkup de todas as instalacoes eletricas do apartamento.', '666.00'),
('75', '4', 'Pintura de parede', 'Pintura de 7 paredes.', '391.00'),
('78', '5', 'Limpeza',  'Limpeza pos obra.', '209.00'),
('79', '3', 'Montagem de movel', 'Montagem de um armario e uma cama.', '562.00'),
('80', '1', 'Troca de cano', 'Troca de cano da pia da cozinha e de dois banheiros.', '499.00'),
('81', '5', 'Limpeza', 'Limpeza de rotina.', '123.00'),
('83', '3', 'Montagem de movel', 'Montagem de uma mesa.', '286.00'),
('84', '4', 'Pintura de parede', 'Pintura de 4 paredes.', '154.00'),
('85', '5', 'Limpeza', 'Limpeza de rotina.', '147.00'),
('86', '2', 'Troca de chuveiro', 'Troca de dois chuveiros.', '172.00'),
('87', '2', 'Instalacao de luminaria', 'Instalacao de luminaria em 2 comodos.', '149.00'),
('88', '2', 'Instalacao eletrica', 'Checkup de todas as instalacoes eletricas do apartamento.', '666.00'),
('90', '4', 'Pintura de parede', 'Pintura de 7 paredes.', '391.00'),
('95', '5', 'Limpeza',  'Limpeza pos obra.', '209.00'),
('96', '3', 'Montagem de movel', 'Montagem de um armario e uma cama.', '562.00'),
('97', '1', 'Troca de cano', 'Troca de cano da pia da cozinha e de dois banheiros.', '499.00'),
('98', '5', 'Limpeza', 'Limpeza de rotina.', '123.00'),
('99', '3', 'Montagem de movel', 'Montagem de uma mesa.', '286.00'),
('101', '4', 'Pintura de parede', 'Pintura de 4 paredes.', '154.00'),
('102', '5', 'Limpeza', 'Limpeza de rotina.', '147.00'),
('103', '2', 'Troca de chuveiro', 'Troca de dois chuveiros.', '172.00'),
('106', '2', 'Troca de chuveiro', 'Troca de dois chuveiros.', '172.00'),
('108', '2', 'Instalacao de luminaria', 'Instalacao de luminaria em 2 comodos.', '149.00'),
('108', '2', 'Instalacao eletrica', 'Checkup de todas as instalacoes eletricas do apartamento.', '666.00');


INSERT INTO scheduling(customer_id_user, service_id_service) VALUES
('5', '1'),('5', '2'),('7', '3'),('9', '4'),('11', '5'),('12', '6'),('13', '7'),('18', '8'),('19', '9'),('21', '10'),
('23', '11'),('25', '12'),('28', '13'),('32', '14'),('33', '15'),('34', '16'),('35', '17'),('36', '18'),('38', '19'),('40', '20'),
('41', '21'),('42', '22'),('44', '23'),('45', '24'),('47', '25'),('52', '26'),('53', '27'),('58', '28'),('60', '29'),('61', '30'),
('63', '31'),('64', '32'),('65', '33'),('66', '34'),('69', '35'),('70', '36'),('74', '37'),('76', '38'),('77', '39'),('82', '40'),
('89', '41'),('91', '42'),('92', '43'),('93', '44'),('94', '45'),('100', '46'),('104', '47'),('105', '48'),('107', '49'), ('109', '50'),
(110, 51),
(111, 52),
(112, 53),
(113, 54),
(114, 55),
(115, 56),
(116, 57),
(117, 58),
(118, 59),
(119, 60),
(120, 61),
(121, 62),
(122, 63),
(123, 64),
(124, 65),
(125, 66),
(126, 67),
(127, 68),
(128, 69),
(129, 70),
(130, 71),
(131, 72),
(132, 73),
(133, 74),
(134, 75),
(135, 76),
(136, 77),
(137, 78),
(138, 79),
(139, 80),
(140, 81),
(141, 82),
(142, 83),
(143, 84),
(144, 85),
(145, 86),
(146, 87),
(147, 88),
(148, 89),
(149, 90),
(150, 91),
(151, 92),
(152, 93),
(153, 94),
(154, 95),
(155, 96),
(156, 97),
(157, 98),
(158, 99),
(159, 100),
(160, 101),
(161, 102),
(162, 103),
(163, 104),
(164, 105),
(165, 106),
(166, 107),
(167, 108),
(168, 109),
(169, 110),
(170, 111),
(171, 112),
(172, 113),
(173, 114),
(174, 115),
(175, 116),
(176, 117),
(177, 118),
(178, 119),
(179, 120),
(180, 121),
(181, 122),
(182, 123),
(183, 124),
(184, 125),
(185, 126),
(186, 127),
(187, 128),
(188, 129),
(189, 130),
(190, 131),
(191, 132),
(192, 133),
(193, 134),
(194, 135),
(195, 136),
(196, 137),
(197, 138),
(198, 139),
(199, 140),
(200, 141),
(201, 142),
(202, 143),
(203, 144),
(204, 145),
(205, 146),
(206, 147),
(207, 148),
(208, 149),
(209, 150),
(210, 151),
(211, 152),
(212, 153),
(213, 154),
(214, 155),
(215, 156),
(216, 157),
(217, 158),
(218, 159),
(219, 160),
(220, 161),
(221, 162),
(222, 163),
(223, 164),
(224, 165),
(225, 166),
(226, 167),
(227, 168),
(228, 169),
(229, 170),
(230, 171),
(231, 172),
(232, 173),
(233, 174),
(234, 175),
(235, 176),
(236, 177),
(237, 178),
(238, 179),
(239, 180),
(240, 181),
(241, 182),
(242, 183),
(243, 184),
(244, 185),
(245, 186),
(246, 187),
(247, 188),
(248, 189),
(249, 190),
(250, 191),
(251, 192),
(252, 193),
(253, 194),
(254, 195),
(255, 196),
(256, 197),
(257, 198),
(258, 199),
(259, 200);

INSERT INTO scheduling_status(
        service_status,
        status_date,
        scheduling_id_scheduling
    )
VALUES ('scheduled', DATE(NOW()), 1),
    ('scheduled', DATE(NOW()), 2),
    ('scheduled', DATE(NOW())-1, 3),
    ('scheduled', DATE(NOW())-1, 4),
    ('scheduled', DATE(NOW())-2, 5),
    ('scheduled', DATE(NOW())-2, 6),
    ('scheduled', DATE(NOW())-3, 7),
    ('scheduled', DATE(NOW())-3, 8),
    ('scheduled', DATE(NOW())-4, 9),
    ('scheduled', DATE(NOW())-4, 10),
    ('done', '2022-05-10 14:14:47', 11),
    ('done', '2022-05-13 13:17:47', 12),
    ('done', '2022-05-11 10:17:47', 13),
    ('done', '2022-06-11 16:13:47', 14),
    ('done', '2022-06-18 16:17:47', 15),
    ('done', '2022-06-19 11:17:47', 16),
    ('done', '2022-07-12 18:12:47', 17),
    ('done', '2022-07-18 19:17:47', 18),
    ('done', '2022-07-12 12:17:47', 19),
    ('done', '2022-08-13 12:11:47', 20),
    ('scheduled', DATE(NOW()), 21),
    ('scheduled', DATE(NOW()), 22),
    ('scheduled', DATE(NOW()), 23),
    ('scheduled', DATE(NOW())-2, 24),
    ('scheduled',DATE(NOW())-2, 25),
    ('scheduled', DATE(NOW())-2, 26),
    ('worker-cancelled', '2022-11-12 15:17:47', 27),
    ('worker-cancelled', '2022-11-11 17:17:47', 28),
    ('worker-cancelled', '2022-11-13 17:17:47', 29),
    ('worker-cancelled', '2022-12-11 16:17:47', 30),
    ('customer-cancelled', '2022-02-11 11:17:47', 31),
    ('customer-cancelled', '2022-02-10 11:17:47', 32),
    ('customer-cancelled', '2022-02-11 18:17:47', 33),
    ('customer-cancelled', '2022-03-19 14:17:47', 34),
    ('customer-cancelled', '2022-03-11 10:17:47', 35),
    ('customer-cancelled', '2022-03-14 16:17:47', 36),
    ('customer-cancelled', '2022-04-12 18:17:47', 37),
    ('customer-cancelled', '2022-04-13 23:17:47', 38),
    ('customer-cancelled', '2022-04-13 11:17:47', 39),
    ('customer-cancelled', '2022-05-13 19:17:47', 40),
    ('not-executed', '2022-06-13 11:17:47', 41),
    ('not-executed', '2022-06-13 13:17:47', 42),
    ('not-executed', '2022-10-10 16:17:47', 43),
    ('not-executed', '2022-07-19 14:17:47', 44),
    ('not-executed', '2022-07-13 12:17:47', 45),
    ('not-executed', '2022-10-11 12:17:47', 46),
    ('not-executed', '2022-08-11 17:17:47', 47),
    ('not-executed', '2022-08-13 11:17:47', 48),
    ('not-executed', '2022-10-12 15:17:47', 49),
    ('not-executed', '2022-09-12 20:17:47', 50),
    ('rated', '2022-10-12 17:17:47', 51),
    ('rated', '2022-10-15 17:17:47', 52),
    ('rated', '2022-10-11 17:17:47', 53),
    ('rated', '2022-10-13 17:17:47', 54),
    ('rated', '2022-10-16 17:17:47', 55),
    ('rated', '2022-10-12 17:17:47', 56),
    ('rated', '2022-10-14 17:17:47', 57),
    ('rated', '2022-10-10 17:17:47', 58),
    ('rated', '2022-10-13 17:17:47', 59),
    ('rated', '2022-10-15 17:17:47', 60),
    ('scheduled', DATE(NOW())-5, 61),
    ('scheduled', DATE(NOW())-5, 62),
    ('scheduled', DATE(NOW())-6, 63),
    ('scheduled', DATE(NOW())-6, 64),
    ('scheduled', DATE(NOW())-6, 65),
    ('scheduled', DATE(NOW())-1, 66),
    ('scheduled', DATE(NOW()), 67),
    ('scheduled', DATE(NOW())-2, 68),
    ('scheduled', DATE(NOW())-4, 69),
    ('scheduled', DATE(NOW())-3, 70),
    ('done', DATE(NOW()), 71),
    ('done', DATE(NOW()), 72),
    ('done', DATE(NOW()), 73),
    ('done', DATE(NOW()), 74),
    ('done', DATE(NOW())-1, 75),
    ('done', DATE(NOW())-1, 76),
    ('done', DATE(NOW())-1, 77),
    ('done', DATE(NOW())-2, 78),
    ('done', DATE(NOW())-2, 79),
    ('done', DATE(NOW())-2, 80),
    ('done', DATE(NOW())-3, 81),
    ('done', DATE(NOW())-3, 82),
    ('done', DATE(NOW())-3, 83),
    ('done', DATE(NOW())-4, 84),
    ('done', DATE(NOW())-4, 85),
    ('done', DATE(NOW())-4, 86),
    ('done', DATE(NOW())-5, 87),
    ('done', DATE(NOW())-5, 88),
    ('done', DATE(NOW())-5, 89),
    ('done', DATE(NOW())-5, 90),
    ('done', DATE(NOW())-6, 91),
    ('done', DATE(NOW())-6, 101),
    ('done', DATE(NOW())-6, 102),
    ('done', DATE(NOW())-3, 103),
    ('done', DATE(NOW())-3, 104),
    ('done', '2022-10-26 17:17:47', 105),
    ('done', '2022-10-21 17:17:47', 106),
    ('done', '2022-10-22 17:17:47', 107),
    ('done', '2022-10-20 17:17:47', 108),
    ('done', '2022-10-22 17:17:47', 109),
    ('done', '2022-10-23 17:17:47', 110),
    ('done', '2022-10-24 17:17:47', 111),
    ('done', '2022-10-24 17:17:47', 112),
    ('done', '2022-10-21 17:17:47', 113),
    ('done', '2022-10-21 17:17:47', 114),
    ('done', '2022-10-26 17:17:47', 115),
    ('done', '2022-10-21 17:17:47', 116),
    ('done', '2022-10-22 17:17:47', 117),
    ('done', '2022-10-20 17:17:47', 118),
    ('done', '2022-10-22 17:17:47', 119),
    ('done', '2022-10-20 17:17:47', 121),
    ('done', '2022-10-25 17:17:47', 122),
    ('done', '2022-10-21 17:17:47', 123),
    ('done', '2022-10-21 17:17:47', 124),
    ('done', '2022-10-26 17:17:47', 125),
    ('done', '2022-10-21 17:17:47', 126),
    ('done', '2022-10-22 17:17:47', 127),
    ('done', '2022-10-20 17:17:47', 128),
    ('done', '2022-10-22 17:17:47', 129),
    ('done', '2022-10-23 17:17:47', 120),
    ('done', '2022-10-24 17:17:47', 121),
    ('done', '2022-10-24 17:17:47', 122),
    ('done', '2022-10-21 17:17:47', 123),
    ('done', '2022-10-21 17:17:47', 124),
    ('done', '2022-10-26 17:17:47', 125),
    ('done', '2022-10-21 17:17:47', 126),
    ('done', '2022-10-22 17:17:47', 127),
    ('done', '2022-10-20 17:17:47', 128),
    ('done', '2022-10-22 17:17:47', 129),
    ('done', '2022-10-20 17:17:47', 131),
    ('done', '2022-10-25 17:17:47', 132),
    ('done', '2022-10-21 17:17:47', 133),
    ('done', '2022-10-21 17:17:47', 134),
    ('done', '2022-10-26 17:17:47', 135),
    ('done', '2022-10-21 17:17:47', 136),
    ('done', '2022-10-22 17:17:47', 137),
    ('done', '2022-10-20 17:17:47', 138),
    ('done', '2022-10-22 17:17:47', 139),
    ('done', '2022-10-23 17:17:47', 130),
    ('done', '2022-10-24 17:17:47', 131),
    ('done', '2022-10-24 17:17:47', 132),
    ('done', '2022-10-21 17:17:47', 133),
    ('done', '2022-10-21 17:17:47', 134),
    ('done', '2022-10-26 17:17:47', 135),
    ('done', '2022-10-21 17:17:47', 136),
    ('done', '2022-10-22 17:17:47', 137),
    ('done', '2022-10-20 17:17:47', 138),
    ('done', '2022-10-22 17:17:47', 139),
	('done', '2022-10-22 17:17:47', 140),
	('done', '2022-05-10 14:14:47', 141),
    ('done', '2022-05-13 13:17:47', 142),
    ('done', '2022-05-11 10:17:47', 144),
    ('done', '2022-06-11 16:13:47', 145),
    ('done', '2022-06-18 16:17:47', 146),
    ('done', '2022-06-19 11:17:47', 147),
    ('done', '2022-07-12 18:12:47', 148),
    ('done', '2022-07-18 19:17:47', 149),
    ('done', '2022-07-12 12:17:47', 150),
    ('done', '2022-08-13 12:11:47', 151);
--
INSERT INTO accomplished_service(
        scheduling_id_scheduling,
        price,
        description,
        service_date
    )
VALUES (1, 39, "Rua Senador Feijo", DATE(NOW())),
	   (2, 81, "Rua Senador Feijo", DATE(NOW())),
       (3, 6, "Rua Pedro Alciati", DATE(NOW())),
       (4, 77, "Via de Pedestre Nagoia", DATE(NOW())),
       (5, 40, "Rua Alagoas", DATE(NOW())),
       (6, 76, "Avenida Doutor Arnaldo", DATE(NOW())),
       (7, 6, "Rua Dona Adma Jafet", DATE(NOW())),
       (8, 94, "Alameda Santos", DATE(NOW())),
       (9, 3, "Rua Ezequiel Freire", DATE(NOW())),
       (10, 72, "Rua Marina Cintra", DATE(NOW())),
       (11, 14, "Rua Doutor Zuquim", DATE(NOW())),
       (12, 99, "Rua Jovita", DATE(NOW()) - 1),
       (13, 15, "Rua Duarte de Azevedo", DATE(NOW()) - 1),
       (14, 77, "Rua Doutor Olavo Egidio", DATE(NOW()) - 1),
       (15, 13, "Avenida Otto Baumgart", DATE(NOW()) - 1),
       (16, 79, "Rua Zulmira", DATE(NOW()) - 1),
       (17, 35, "Rua Itauna", DATE(NOW()) - 1),
       (18, 66, "Rua Nascimento Antunes de Oliveira", DATE(NOW()) - 1),
       (19, 29, "Avenida Bandeirantes do Sul", DATE(NOW()) - 1),
       (20, 98, "Rua Edgard Seeling Heinz Peine", DATE(NOW()) - 1),
       (21, 35, "Rua Carmina Pasqui", DATE(NOW()) - 1),
       (22, 73, "Rua Aiapua", DATE(NOW()) - 1),
       (23, 5, "Rua Tanque Velho", DATE(NOW()) - 1),
       (24, 73, "Avenida Francisco Rodrigues", DATE(NOW()) - 1),
       (25, 14, "Rua Freire Bastos", DATE(NOW()) - 1),
       (26, 82, "Avenida Guapira", DATE(NOW()) - 2),
	   (27, 32, "Avenida Jacana", DATE(NOW()) - 2),
       (28, 61, "Rua Ana do Sacramento Andrade", DATE(NOW()) - 2),
       (29, 31, "Rua Freire Bastos", DATE(NOW()) - 2),
       (30, 87, "Avenida Paula Ferreira", DATE(NOW()) - 2),
       (31, 4, "Rua Antonieta Leitao", DATE(NOW()) - 2),
       (32, 73, "Rua Rio Verde", DATE(NOW()) - 2),
       (33, 4, "Rua Dona Primitiva Vianco", DATE(NOW()) - 3),
       (34, 92, "Avenida Maria Campos", DATE(NOW()) - 3),
       (35, 14, "Rua Senador Feijo", DATE(NOW()) - 3),
       (36, 81, "Rua Senador Feijo", DATE(NOW()) - 3),
       (37, 17, "Rua Pedro Alciati", DATE(NOW()) - 3),
       (38, 73, "Rua Alagoas", DATE(NOW()) - 3),
       (39, 26, "Avenida Maria Campos", DATE(NOW()) - 3),
       (40, 75, "Rua Dona Primitiva Vianco", DATE(NOW()) - 3),
       (41, 35, "Rua Rio Verde", DATE(NOW()) - 3),
       (42, 92, "Avenida Paula Ferreira", DATE(NOW()) - 4),
       (43, 36, "Rua Freire Bastos", DATE(NOW()) - 4),
       (44, 61, "Avenida Francisco Rodrigues", DATE(NOW()) - 4),
       (45, 28, "Rua Aiapua", DATE(NOW()) - 4),
       (46, 65, "Rua Edgard Seeling Heinz Peine", DATE(NOW()) - 4),
       (47, 55, "Rua Itauna", DATE(NOW()) - 4),
       (48, 47, "Rua Zulmira", DATE(NOW()) - 4),
       (49, 42, "Rua Marina Cintra", DATE(NOW()) - 4),
       (50, 42, "Rua Dona Adma Jafet", DATE(NOW()) - 4),
       (51, 52, "Rua Alagoas", DATE(NOW()) - 5),
       (52, 60, "Rua Ezequiel Freire", DATE(NOW()) - 5),
       (53, 54, "Rua Zulmira", DATE(NOW()) - 5),
       (54, 60, "Rua Tanque Velho", DATE(NOW()) - 5),
       (55, 45, "Rua Doutor Olavo Egidio", DATE(NOW()) - 5),
       (56, 41, "Avenida Cardeal Motta", DATE(NOW()) - 5),
       (57, 50, "Rua Doutor Zuquim", DATE(NOW()) - 5),
       (58, 46, "Rua Ana do Sacramento Andrade", DATE(NOW()) - 5),
       (59, 51, "Rua Rio Verde", DATE(NOW()) - 5),
       (60, 46, "Avenida Maria Campos", DATE(NOW()) - 5),
       (61, 56, "Avenida Bandeirantes do Sul", DATE(NOW()) - 5),
       (62, 55, "Avenida Paulista", DATE(NOW()) - 5),
       (63, 52, "Rua Arthur de Azevedo", DATE(NOW()) - 5),
       (64, 59, "Rua Haddock Lobo" ,DATE(NOW()) - 5),
       (65, 51, "Rua Bandeirantes", DATE(NOW()) - 5),
       (66, 59, "Avenida Brasil", DATE(NOW()) - 5),
       (67, 50, "Avenida Cardeal Motta", DATE(NOW()) - 5),
       (68, 48, "Rua Ana do Sacramento Andrade", DATE(NOW()) - 5),
       (69, 59, "Rua Doutor Olavo Egidio", DATE(NOW()) - 5),
       (70, 47, "Rua Arthur de Azevedo", DATE(NOW()) - 6),
       (71, 55, "Rua Dona Adma Jafet", DATE(NOW()) - 6),
       (72, 42, "Rua Senador Feijo", DATE(NOW()) - 6),
       (73, 49, "Avenida Maria Campos", DATE(NOW()) - 6),
       (74, 56, "Avenida Bandeirantes do Sul", DATE(NOW()) - 6),
       (75, 47, "Rua Tanque Velho", DATE(NOW()) - 6),
       (76, 54, "Rua Freire Bastos", DATE(NOW()) - 6),
       (77, 53, "Rua Estados Unidos", DATE(NOW()) - 6),
       (78, 48, "Rua Arthur de Azevedo", DATE(NOW()) - 6),
       (79, 52, "Rua Senador Feijo", DATE(NOW()) - 6),
       (80, 42, "Rua Haddock Lobo", DATE(NOW()) - 6),
       (81, 51, "Avenida Paulista", DATE(NOW()) - 6),
	   (82, 55, "Rua Haddock Lobo", DATE(NOW()) - 6),
       (83, 41, "Avenida Paulista", DATE(NOW()) - 6),
	   (84, 44, "Rua Senador Feijo", DATE(NOW()) - 6),
       (85, 57, "Rua Haddock Lobo", DATE(NOW()) - 6),
       (86, 46, "Avenida Paulista", DATE(NOW()) - 6),
	   (87, 58, "Rua Haddock Lobo", DATE(NOW()) - 6),
       (88, 55, "Avenida Paulista", DATE(NOW()) - 6),
	   (89, 60, "Rua Rio Verde", DATE(NOW()) - 5),
       (90, 46, "Avenida Maria Campos", DATE(NOW()) - 5),
	   (91, 55, "Avenida Maria Campos", DATE(NOW()) - 5),
	   (92, 41, "Rua Aiapua", DATE(NOW()) - 1),
	   (93, 49, "Rua Tanque Velho", DATE(NOW()) - 1),
       (94, 40, "Rua Duarte de Azevedo", DATE(NOW()) - 1),
       (95, 42, "Rua Doutor Olavo Egidio", DATE(NOW()) - 1),
       (96, 52, "Avenida Otto Baumgart", DATE(NOW()) - 1),
       (97, 47, "Rua Zulmira", DATE(NOW()) - 1),
	   (98, 50, "Rua Zulmira", DATE(NOW()) - 1),
	   (99, 42, "Rua Zulmira", DATE(NOW()) - 1),
	   (100, 49, "Rua Zulmira", DATE(NOW()) - 1),
       (101, 41, "Rua Alagoas", DATE(NOW()) - 3),
	   (102, 48, "Rua Ana do Sacramento Andrade", DATE(NOW()) - 2),
       (103, 59, "Rua Freire Bastos", DATE(NOW()) - 2),
       (104, 55, "Avenida Paula Ferreira", DATE(NOW()) - 2),
       (105, 56, "Rua Antonieta Leitao", DATE(NOW()) - 2),
       (106, 42, "Rua Rio Verde", DATE(NOW()) - 2),
       (107, 50, "Rua Antonieta Leitao", DATE(NOW()) - 2),
	   (108, 46, "Rua Pedro Alciati", DATE(NOW())),
       (109, 43, "Via de Pedestre Nagoia", DATE(NOW())),
       (110, 48, "Rua Alagoas", DATE(NOW())),
       (111, 52, "Avenida Doutor Arnaldo", DATE(NOW())),
       (112, 54, "Rua Dona Adma Jafet", DATE(NOW())),
       (113, 42, "Alameda Santos", DATE(NOW())),
       (114, 46, "Rua Ezequiel Freire",DATE(NOW()) - 2), -- checar a partir daqui
       (115, 48, "Rua Marina Cintra", DATE(NOW()) - 2),
       (116, 50, "Rua Doutor Zuquim", DATE(NOW()) - 2),
	   (117, 43, "Avenida Paula Ferreira", DATE(NOW()) - 4),
       (118, 59, "Rua Freire Bastos", DATE(NOW()) - 4),
       (119, 43, "Avenida Francisco Rodrigues", DATE(NOW()) - 4),
       (120, 57, "Rua Aiapua", DATE(NOW()) - 4),
       (121, 56, "Rua Edgard Seeling Heinz Peine", DATE(NOW()) - 4),
       (122, 40, "Rua Itauna", DATE(NOW()) - 4),
       (123, 58, "Rua Zulmira", DATE(NOW()) - 4),
       (124, 91, "Rua Marina Cintra", DATE(NOW()) - 4),
	   (125, 29, "Rua Senador Feijo", DATE(NOW()) - 6),
       (126, 77, "Avenida Maria Campos", DATE(NOW()) - 6),
       (127, 35, "Avenida Bandeirantes do Sul", DATE(NOW()) - 6),
       (128, 95, "Rua Tanque Velho", DATE(NOW()) - 6),
       (129, 11, "Rua Freire Bastos", DATE(NOW()) - 6),
       (130, 75, "Rua Estados Unidos", DATE(NOW()) - 6),
       (131, 9, "Rua Arthur de Azevedo", DATE(NOW()) - 6),
       (132, 75, "Rua Senador Feijo", DATE(NOW()) - 6),
       (133, 34, "Rua Haddock Lobo", DATE(NOW()) - 6),
       (134, 71, "Avenida Paulista", DATE(NOW()) - 6),
	   (135, 5, "Rua Haddock Lobo", DATE(NOW()) - 6),
       (136, 88, "Avenida Paulista", DATE(NOW()) - 6),
	   (137, 7, "Rua Senador Feijo", DATE(NOW()) - 6),
       (138, 73, "Rua Haddock Lobo", DATE(NOW()) - 6),
       (139, 10, "Avenida Paulista", DATE(NOW()) - 6),
	   (140, 72, "Rua Haddock Lobo", DATE(NOW()) - 6),
       (141, 5, "Avenida Paulista", DATE(NOW()) - 6),
	   (142, 93, "Rua Dona Primitiva Vianco", DATE(NOW()) - 3),
       (143, 40, "Avenida Maria Campos", DATE(NOW()) - 3),
       (144, 87, "Rua Senador Feijo", DATE(NOW()) - 3),
       (145, 12, "Rua Senador Feijo", DATE(NOW()) - 3),
       (146, 97, "Rua Pedro Alciati", DATE(NOW()) - 3),
       (147, 36, "Rua Alagoas", DATE(NOW()) - 3),
       (148, 74, "Avenida Maria Campos", DATE(NOW()) - 3),
	   (149, 22, "Avenida Maria Campos", DATE(NOW()) - 3),

	   (150, 90, "Rua Dona Primitiva Vianco", DATE(NOW()) - 3),
       (151, 17, "Rua Rio Verde", DATE(NOW()) - 3),
       (152, 88, "Avenida Paula Ferreira", DATE(NOW()) - 4),
       (153, 20, "Rua Freire Bastos", DATE(NOW()) - 4),
       (154, 76, "Avenida Francisco Rodrigues", DATE(NOW()) - 4),
       (155, 16, "Rua Aiapua", DATE(NOW()) - 4),
       (156, 89, "Rua Edgard Seeling Heinz Peine", DATE(NOW()) - 4),
	   (157, 1, "Rua Aiapua", DATE(NOW()) - 4),
       (158, 78, "Rua Edgard Seeling Heinz Peine", DATE(NOW()) - 4),

       (159, 1, "Rua Itauna", DATE(NOW()) - 4),
       (160, 73, "Rua Zulmira", DATE(NOW()) - 4),
       (161, 35, "Rua Marina Cintra", DATE(NOW()) - 4),
       (162, 83, "Rua Dona Adma Jafet", DATE(NOW()) - 4),
       (163, 5, "Rua Dona Adma Jafet", DATE(NOW()) - 4),
	   (164, 93, "Rua Jovita", DATE(NOW()) - 1),
       (165, 26, "Rua Duarte de Azevedo", DATE(NOW()) - 1),
       (166, 75, "Rua Doutor Olavo Egidio", DATE(NOW()) - 1),
       (167, 20, "Avenida Otto Baumgart", DATE(NOW()) - 1),
       (168, 95, "Rua Zulmira", DATE(NOW()) - 1),
       (169, 27, "Rua Itauna", DATE(NOW()) - 1),
       (170, 63, "Rua Nascimento Antunes de Oliveira", DATE(NOW()) - 1),
       (171, 13, "Avenida Bandeirantes do Sul", DATE(NOW()) - 1),
       (172, 75, "Rua Edgard Seeling Heinz Peine", DATE(NOW()) - 1),
       (173, 10, "Rua Carmina Pasqui", DATE(NOW()) - 1),
       (174, 92, "Rua Aiapua", DATE(NOW()) - 1),
       (175, 13, "Rua Tanque Velho", DATE(NOW()) - 1),
       (176, 86, "Avenida Francisco Rodrigues", DATE(NOW()) - 1),
       (177, 15, "Rua Freire Bastos", DATE(NOW()) - 1),
       (178, 69, "Avenida Guapira", DATE(NOW()) - 2),
	   (179, 14, "Avenida Jacana", DATE(NOW()) - 2),
       (180, 90, "Rua Ana do Sacramento Andrade", DATE(NOW()) - 2),
       (181, 32, "Rua Freire Bastos", DATE(NOW()) - 2),
       (182, 86, "Avenida Paula Ferreira", DATE(NOW()) - 2),
       (183, 15, "Rua Antonieta Leitao", DATE(NOW()) - 2),
       (184, 88, "Rua Rio Verde", DATE(NOW()) - 2),
	   (185, 39, "Rua Jovita", DATE(NOW()) - 1),
       (186, 97, "Rua Duarte de Azevedo", DATE(NOW()) - 1),
       (187, 24, "Rua Doutor Olavo Egidio", DATE(NOW()) - 1),
       (188, 68, "Avenida Otto Baumgart", DATE(NOW()) - 1),
       (189, 17, "Rua Zulmira", DATE(NOW()) - 1),
       (190, 85, "Rua Itauna", DATE(NOW()) - 1),
       (191, 23, "Rua Nascimento Antunes de Oliveira", DATE(NOW()) - 1),
       (192, 69, "Avenida Bandeirantes do Sul", DATE(NOW()) - 1),
       (193, 8, "Rua Edgard Seeling Heinz Peine", DATE(NOW()) - 1),
       (194, 91, "Rua Carmina Pasqui", DATE(NOW()) - 1),
       (195, 16, "Rua Aiapua", DATE(NOW()) - 1),
       (196, 79, "Rua Tanque Velho", DATE(NOW()) - 1),
	   (197, 28, "Rua Duarte de Azevedo", DATE(NOW()) - 1),
       (198, 74, "Rua Doutor Olavo Egidio", DATE(NOW()) - 1),
       (199, 18, "Avenida Otto Baumgart", DATE(NOW()) - 1),
       (200, 83, "Rua Zulmira", DATE(NOW()) - 1);
INSERT INTO rating(
        fk_accomplished_service,
        rating
    )
VALUES (1, 4),
    (2, 5),
    (3, 2),
    (4, 5),
    (5, 3),
    (6, 1),
    (7, 4),
    (8, 2),
    (9, 5),
    (10, 4),
    (11, 4),
    (12, 3),
    (13, 4),
    (14, 4),
    (15, 5),
    (16, 5),
    (17, 1),
    (18, 3),
    (19, 3),
    (20, 3),
    (21, 4),
    (22, 3),
    (23, 5),
    (24, 4),
    (25, 3),
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
    (38, 3),
    (39, 2),
    (40, 2),
    (41, 5),
    (42, 2),
    (43, 5),
    (44, 3),
    (45, 4),
    (46, 4),
    (47, 4),
    (48, 3),
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
    (62, 5),
    (63, 4),
    (64, 2),
    (65, 4),
    (66, 4),
    (67, 3),
    (68, 5),
    (69, 4),
    (70, 2),
    (71, 5),
    (72, 3),
    (73, 2),
    (74, 5),
    (75, 5),
    (76, 4),
    (77, 4),
    (78, 3),
    (79, 3),
    (80, 4),
    (81, 4),
	(82, 5),
    (83, 4),
    (84, 3),
    (85, 5),
    (86, 5),
    (87, 4),
    (88, 4),
    (89, 3);
--     (90, 3),
--     (91, 4),
--     (92, 4);

insert into msg
values(
        null,
        "Ola eu sou o Jose, como posso te ajudar?",
        'y',
        1
    ),
    (
        null,
        "Ola eu sou o Robson, como posso te ajudar?",
        'y',
        2
    ),
    (
        null,
        "Ola eu sou a Larissa, como posso te ajudar?",
        'y',
        3
    ),
    (
        null,
        "Ola eu sou a Pedro, como posso te ajudar?",
        'y',
        4
    );
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into chat
values(null, '2022-10-06 00:00:00');
insert into user_has_chat
values(null, 1, 7),
    (null, 1, 8);
insert into tag
values (null, "limpeza"),
    (null, "limpeza"),
    (null, "limpeza"),
    (null, "limpeza"),
    (null, "limpeza");
insert into interest_access
values (null, 1, 1, NOW()),
    (null, 1, 1, NOW()),
    (null, 1, 1, NOW()),
    (null, 1, 1, NOW()),
    (null, 1, 1, NOW());