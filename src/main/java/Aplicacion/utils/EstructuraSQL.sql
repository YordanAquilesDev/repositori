/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  neker
 * Created: 30 jun. 2026
 */

CREATE DATABASE granja;

USE granja;

CREATE TABLE Rol(
    idRol INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL
);

CREATE TABLE Usuario(
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,
    idRol INT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    estado BOOLEAN DEFAULT TRUE,
    FOREIGN KEY(idRol) REFERENCES Rol(idRol)
);

CREATE TABLE Cliente(
    idCliente INT AUTO_INCREMENT PRIMARY KEY,
    idUsuario INT NOT NULL,
    dni CHAR(8),
    telefono VARCHAR(15),
    direccion VARCHAR(150),
    FOREIGN KEY(idUsuario) REFERENCES Usuario(idUsuario)
);

CREATE TABLE Especie(
    idEspecie INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE Raza(
    idRaza INT AUTO_INCREMENT PRIMARY KEY,
    idEspecie INT NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    FOREIGN KEY(idEspecie) REFERENCES Especie(idEspecie)
);

CREATE TABLE Animal(
    idAnimal INT AUTO_INCREMENT PRIMARY KEY,
    idRaza INT NOT NULL,
    nombre VARCHAR(100),
    sexo ENUM('Macho','Hembra'),
    edad INT,
    precio DECIMAL(10,2),
    stock INT,
    estado ENUM('Disponible','Vendido') DEFAULT 'Disponible',
    FOREIGN KEY(idRaza) REFERENCES Raza(idRaza)
);

CREATE TABLE Venta(
    idVenta INT AUTO_INCREMENT PRIMARY KEY,
    idCliente INT NOT NULL,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10,2),
    FOREIGN KEY(idCliente) REFERENCES Cliente(idCliente)
);

CREATE TABLE DetalleVenta(
    idDetalle INT AUTO_INCREMENT PRIMARY KEY,
    idVenta INT NOT NULL,
    idAnimal INT NOT NULL,
    cantidad INT NOT NULL,
    precio DECIMAL(10,2),
    subtotal DECIMAL(10,2),
    FOREIGN KEY(idVenta) REFERENCES Venta(idVenta),
    FOREIGN KEY(idAnimal) REFERENCES Animal(idAnimal)
);

