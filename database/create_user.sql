-- DROP USER IF EXISTS 'homebox' @'localhost';

DROP USER IF EXISTS 'homebox' @'%';

CREATE USER IF NOT EXISTS 'homebox' @'%' IDENTIFIED BY 'Homebox265@';

GRANT ALL PRIVILEGES ON `homebox`.* TO `homebox` @`%`;

FLUSH PRIVILEGES;