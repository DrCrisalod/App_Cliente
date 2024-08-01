CREATE TABLE IF NOT EXISTS cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

INSERT INTO cliente (nombre, apellido, email) VALUES ('Juan', 'Pérez', 'juan.perez@example.com');
INSERT INTO cliente (nombre, apellido, email) VALUES ('Ana', 'García', 'ana.garcia@example.com');