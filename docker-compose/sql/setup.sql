CREATE DATABASE IF NOT EXISTS `foerderverein`;
USE `foerderverein`;

CREATE TABLE IF NOT EXISTS `foerderverein`.`users`
(
    `id`       BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `username` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    UNIQUE KEY `uc_users_username` (`username`)
) DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `foerderverein`.`roles`
(
    `id`   BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `role` VARCHAR(255) NOT NULL,
    UNIQUE KEY `uc_roles_role` (`role`)
) DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `foerderverein`.`users_roles`
(
    `user_id` BIGINT NOT NULL,
    `role_id` BIGINT NOT NULL,
    CONSTRAINT `fk_users_roles_user_id` FOREIGN KEY (`user_id`) REFERENCES `foerderverein`.`users` (`id`),
    CONSTRAINT `fk_users_roles_role_id` FOREIGN KEY (`role_id`) REFERENCES `foerderverein`.`roles` (`id`),
    CONSTRAINT `uc_users_roles_use_role_id` UNIQUE (`user_id`, `role_id`)
) DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `foerderverein`.`board_members`
(
    `id`             BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `full_name`      VARCHAR(255) NOT NULL,
    `image_filename` VARCHAR(255) NOT NULL,
    `introduction`   TEXT         NOT NULL,
    `rank`           TINYINT      NOT NULL
) DEFAULT CHARSET = utf8;

INSERT INTO `foerderverein`.`board_members` (`full_name`, `image_filename`, `introduction`, `rank`)
VALUES ('Nastassja Altholz', 'nastassjaA360dpi.jpg',
        '<p>Als neue Vorsitzende freue ich mich sehr darauf gemeinsam mit dem Förderverein spannende Projekte zu planen, an denen alle Kinder Freude und Spaß haben.</p><p>Als Mama von Jan aus der Vogelgruppe und Nora aus der Kükengruppe kenne ich die strahlende Kinderaugen, die solche Projekte hervorbringen. Für Ideen und Vorschläge sind wir jederzeit offen - sprecht uns an oder schickt uns eine Mail!</p><p>Der Föderverein macht''s möglich - mach mit! :)',
        1),
       ('David Weber', 'daveW360dpi.jpg', 'Meine Tochter Annabelle geht in die Bärenhöhle.', 2),
       ('Klaus Huthmacher', 'klausH360dpi.jpg', 'Mein Sohn Lennard geht in die Kükengruppe.', 3);

CREATE TABLE IF NOT EXISTS `foerderverein`.`event_registrations`
(
    `id`                     BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `full_name`              VARCHAR(255) NOT NULL,
    `e_mail`                 VARCHAR(255) NOT NULL UNIQUE,
    `comment`                TEXT,
    `registration_timestamp` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
) DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `foerderverein`.`storing_position`
(
    `id`                    INT    NOT NULL PRIMARY KEY,
    `number`                INT    NOT NULL,
    `event_registration_id` BIGINT NOT NULL,
    CONSTRAINT `fk_storing_position_event_registration_id` FOREIGN KEY (`event_registration_id`) REFERENCES `foerderverein`.`event_registrations` (`id`)
) DEFAULT CHARSET = utf8;