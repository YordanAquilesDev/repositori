USE granja;

-- ===================== ROLES =====================
INSERT INTO Rol (nombre) VALUES
('Administrador'),
('Cliente');

-- ===================== USUARIOS =====================
-- Todos con password: 123456
INSERT INTO Usuario (idRol, nombre, correo, password, estado) VALUES
(1, 'Admin',     'admin',    'admin', TRUE),
(1, 'Juan Perez',     'juan@gmail.com',    '123456', TRUE),
(2, 'Maria Garcia',   'maria@gmail.com',   '123456', TRUE),
(1, 'Carlos Lopez',   'carlos@gmail.com',  '123456', TRUE),
(2, 'Ana Torres',     'ana@gmail.com',     '123456', TRUE),
(2, 'Pedro Ramirez',  'pedro@gmail.com',   '123456', FALSE);

-- ===================== CLIENTES =====================
INSERT INTO Cliente (idUsuario, dni, telefono, direccion) VALUES
(2, '12345678', '999111222', 'Av. Los Olivos 123, Lima'),
(3, '87654321', '988333444', 'Jr. Tacna 456, Cusco'),
(4, '11223344', '977555666', 'Calle San Martin 789, Arequipa'),
(5, '55667788', '966777888', 'Av. Brasil 321, Trujillo');

-- ===================== ESPECIES =====================
INSERT INTO Especie (nombre) VALUES
('Gallina'),
('Cerdo'),
('Vaca'),
('Conejo'),
('Pato');

-- ===================== RAZAS =====================
-- Gallinas (idEspecie = 1)
INSERT INTO Raza (idEspecie, nombre) VALUES
(1, 'Rhode Island Red'),
(1, 'Leghorn'),
(1, 'Plymouth Rock');

-- Cerdos (idEspecie = 2)
INSERT INTO Raza (idEspecie, nombre) VALUES
(2, 'Large White'),
(2, 'Duroc'),
(2, 'Landrace');

-- Vacas (idEspecie = 3)
INSERT INTO Raza (idEspecie, nombre) VALUES
(3, 'Holstein'),
(3, 'Jersey'),
(3, 'Angus');

-- Conejos (idEspecie = 4)
INSERT INTO Raza (idEspecie, nombre) VALUES
(4, 'Nueva Zelandia'),
(4, 'Californiano');

-- Patos (idEspecie = 5)
INSERT INTO Raza (idEspecie, nombre) VALUES
(5, 'Pekin'),
(5, 'Muscovy');

-- ===================== ANIMALES =====================
-- Gallinas
INSERT INTO Animal (idRaza, nombre, sexo, edad, precio, stock, estado) VALUES
(1, 'Rosita',    'Hembra', 1, 25.00,  10, 'Disponible'),
(2, 'Plumita',   'Hembra', 2, 30.00,   8, 'Disponible'),
(3, 'Copeton',   'Macho',  1, 20.00,  12, 'Disponible'),
(1, 'Colorada',  'Hembra', 3, 28.00,   5, 'Vendido');

-- Cerdos
INSERT INTO Animal (idRaza, nombre, sexo, edad, precio, stock, estado) VALUES
(4, 'Chanchito', 'Macho',  6, 350.00,  4, 'Disponible'),
(5, 'Doradito',  'Macho',  8, 400.00,  3, 'Disponible'),
(6, 'Linda',     'Hembra', 5, 380.00,  2, 'Disponible');

-- Vacas
INSERT INTO Animal (idRaza, nombre, sexo, edad, precio, stock, estado) VALUES
(7, 'Blanca',    'Hembra', 24, 1200.00, 2, 'Disponible'),
(8, 'Lechera',   'Hembra', 36, 1500.00, 1, 'Disponible'),
(9, 'Negra',     'Hembra', 18, 1800.00, 2, 'Disponible');

-- Conejos
INSERT INTO Animal (idRaza, nombre, sexo, edad, precio, stock, estado) VALUES
(10, 'Nieve',   'Hembra', 4, 50.00,  15, 'Disponible'),
(11, 'Rayo',    'Macho',  3, 45.00,  10, 'Disponible'),
(10, 'Bolita',  'Hembra', 6, 55.00,   0, 'Vendido');

-- Patos
INSERT INTO Animal (idRaza, nombre, sexo, edad, precio, stock, estado) VALUES
(12, 'Donald',  'Macho',  5, 60.00,   6, 'Disponible'),
(13, 'Daisy',   'Hembra', 4, 55.00,   4, 'Disponible');

-- ===================== VENTAS =====================
INSERT INTO Venta (idCliente, fecha, total) VALUES
(1, '2026-06-20 10:30:00', 75.00),
(2, '2026-06-25 14:15:00', 700.00),
(3, '2026-07-01 09:00:00', 120.00);

-- ===================== DETALLES DE VENTA =====================
-- Venta 1: Maria compra 3 Gallinas Rhode Island (idAnimal=1, precio=25)
INSERT INTO DetalleVenta (idVenta, idAnimal, cantidad, precio, subtotal) VALUES
(1, 1, 3, 25.00, 75.00);

-- Venta 2: Carlos compra 2 Cerdos Large White (idAnimal=5, precio=350)
INSERT INTO DetalleVenta (idVenta, idAnimal, cantidad, precio, subtotal) VALUES
(2, 5, 2, 350.00, 700.00);

-- Venta 3: Ana compra 2 Gallinas Leghorn (idAnimal=2, precio=30)
INSERT INTO DetalleVenta (idVenta, idAnimal, cantidad, precio, subtotal) VALUES
(3, 2, 4, 30.00, 120.00);
