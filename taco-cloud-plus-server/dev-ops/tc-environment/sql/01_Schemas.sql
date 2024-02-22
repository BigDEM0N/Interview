
USE `tacocloud`;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`(
    `id`            BIGINT NOT NULL,
    `username`      VARCHAR(64) NOT NULL,
    `password`      VARCHAR(64) NOT NULL,
    PRIMARY KEY (`id`)
);