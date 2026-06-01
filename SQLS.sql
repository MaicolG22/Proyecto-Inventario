CREATE DATABASE inventario;
use inventario;

CREATE TABLE usuarios (
  id INT AUTO_INCREMENT PRIMARY KEY,
  usuario VARCHAR(20) NOT NULL UNIQUE,
  passwd VARCHAR(20) NOT NULL,
  tipo ENUM('Administrador','Normal') NOT NULL
);

CREATE TABLE tipoproducto (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE producto (
  id INT AUTO_INCREMENT PRIMARY KEY,
  codigo VARCHAR(20) NOT NULL UNIQUE,
  nombre VARCHAR(30) NOT NULL,
  precio DECIMAL(10,2) NOT NULL,
  cantidad INT NOT NULL DEFAULT 0,
  tipo_fk INT NOT NULL,
  categoria VARCHAR(15),
  observacion VARCHAR(50),
  estado ENUM('ACTIVO','INACTIVO') NOT NULL DEFAULT 'ACTIVO',
  CONSTRAINT fk_producto_tipoproducto
    FOREIGN KEY (tipo_fk) REFERENCES tipoproducto(id)
);

CREATE TABLE movimientos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  producto_fk INT NOT NULL,
  cantidad INT NOT NULL,
  tipo ENUM('entrada','salida') NOT NULL,
  observacion VARCHAR(50),
  CONSTRAINT fk_movimientos_producto
    FOREIGN KEY (producto_fk) REFERENCES producto(id)
);

INSERT INTO usuarios (usuario,passwd,tipo) VALUES ('root', 123,'Administrador');