DROP DATABASE IF EXISTS order_service;
DROP USER IF EXISTS `service_admin`@`%`;
DROP USER IF EXISTS `service_user`@`%`;
CREATE DATABASE IF NOT EXISTS order_service CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `service_admin`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
    CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `order_service`.* TO `service_admin`@`%`;
CREATE USER IF NOT EXISTS `service_user`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, SHOW VIEW ON `order_service`.* TO `service_user`@`%`;
FLUSH PRIVILEGES;