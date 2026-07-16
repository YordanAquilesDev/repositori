USE granja;

-- ===================== ROLES =====================
INSERT INTO Rol (nombre) VALUES
('Administrador'),
('Cliente');

-- ===================== USUARIOS =====================
-- Todos con password: 123456
INSERT INTO Usuario (idRol, nombre, correo, password, estado) VALUES
(1, 'Admin',          'admin@gmail.com',    '123456', TRUE),
(1, 'Juan Perez',     'juan@gmail.com',     '123456', TRUE),
(2, 'Maria Garcia',   'maria@gmail.com',    '123456', TRUE),
(1, 'Carlos Lopez',   'carlos@gmail.com',   '123456', TRUE),
(2, 'Ana Torres',     'ana@gmail.com',      '123456', TRUE),
(2, 'Pedro Ramirez',  'pedro@gmail.com',    '123456', FALSE),
(2, 'Lucia Fernandez', 'lucia@gmail.com',   '123456', TRUE),
(2, 'Miguel Sanchez', 'miguel@gmail.com',   '123456', TRUE);

-- ===================== CLIENTES =====================
INSERT INTO Cliente (idUsuario, dni, telefono, direccion) VALUES
(3, '12345678', '999111222', 'Av. Los Olivos 123, Lima'),
(5, '87654321', '988333444', 'Jr. Tacna 456, Cusco'),
(6, '11223344', '977555666', 'Calle San Martin 789, Arequipa'),
(7, '55667788', '966777888', 'Av. Brasil 321, Trujillo'),
(8, '99887766', '955111222', 'Calle Junin 654, Huancayo');

-- ===================== ESPECIES =====================
INSERT INTO Especie (nombre) VALUES
('Gallina'),
('Cerdo'),
('Vaca'),
('Conejo'),
('Pato'),
('Caballo'),
('Asno'),
('Oveja');

-- ===================== RAZAS =====================
-- Gallinas (idEspecie = 1)
INSERT INTO Raza (idEspecie, nombre) VALUES
(1, 'Rhode Island Red'),
(1, 'Leghorn'),
(1, 'Plymouth Rock'),
(1, 'Sussex');

-- Cerdos (idEspecie = 2)
INSERT INTO Raza (idEspecie, nombre) VALUES
(2, 'Large White'),
(2, 'Duroc'),
(2, 'Landrace'),
(2, 'Hampshire');

-- Vacas (idEspecie = 3)
INSERT INTO Raza (idEspecie, nombre) VALUES
(3, 'Holstein'),
(3, 'Jersey'),
(3, 'Angus'),
(3, 'Brahman');

-- Conejos (idEspecie = 4)
INSERT INTO Raza (idEspecie, nombre) VALUES
(4, 'Nueva Zelandia'),
(4, 'Californiano'),
(4, 'Angora');

-- Patos (idEspecie = 5)
INSERT INTO Raza (idEspecie, nombre) VALUES
(5, 'Pekin'),
(5, 'Muscovy');

-- Caballos (idEspecie = 6)
INSERT INTO Raza (idEspecie, nombre) VALUES
(6, 'Criollo'),
(6, 'Cuarto de Milla');

-- Asnos (idEspecie = 7)
INSERT INTO Raza (idEspecie, nombre) VALUES
(7, 'Espanol'),
(7, 'San Hubertino');

-- Ovejas (idEspecie = 8)
INSERT INTO Raza (idEspecie, nombre) VALUES
(8, 'Merino'),
(8, 'Suffolk');

-- ===================== ANIMALES =====================

-- Gallinas (idRaza 1-4, ids 1-6)
INSERT INTO Animal (idRaza, nombre, sexo, edad, precio, stock, estado) VALUES
(1, 'Gallina Rhode Island Hembra',  'Hembra', 1,  25.00,  15, 'Disponible'),
(2, 'Gallina Leghorn Macho',        'Macho',  2,  30.00,  10, 'Disponible'),
(3, 'Gallina Plymouth Rock Hembra', 'Hembra', 1,  22.00,  12, 'Disponible'),
(4, 'Gallina Sussex Macho',         'Macho',  3,  28.00,   8, 'Disponible'),
(1, 'Gallina Rhode Island Macho',   'Macho',  2,  24.00,   0, 'Vendido'),
(3, 'Gallina Plymouth Hembra Joven','Hembra', 0,  20.00,  20, 'Disponible');

-- Cerdos (idRaza 5-8, ids 7-12)
INSERT INTO Animal (idRaza, nombre, sexo, edad, precio, stock, estado) VALUES
(5, 'Cerdo Large White Macho',      'Macho',  8,  350.00,  5, 'Disponible'),
(6, 'Cerdo Duroc Hembra',           'Hembra', 6,  400.00,  3, 'Disponible'),
(7, 'Cerdo Landrace Macho',         'Macho',  7,  380.00,  4, 'Disponible'),
(8, 'Cerdo Hampshire Hembra',       'Hembra', 5,  420.00,  2, 'Disponible'),
(5, 'Cerdo Large White Hembra',     'Hembra', 9,  370.00,  0, 'Vendido'),
(6, 'Cerdo Duroc Macho Joven',      'Macho',  4,  320.00,  6, 'Disponible');

-- Vacas (idRaza 9-12, ids 13-18)
INSERT INTO Animal (idRaza, nombre, sexo, edad, precio, stock, estado) VALUES
(9,  'Vaca Holstein Hembra',        'Hembra', 36, 1500.00, 2, 'Disponible'),
(10, 'Vaca Jersey Hembra',          'Hembra', 24, 1800.00, 3, 'Disponible'),
(11, 'Vaca Angus Macho',            'Macho',  30, 2000.00, 2, 'Disponible'),
(12, 'Vaca Brahman Hembra',         'Hembra', 28, 1600.00, 2, 'Disponible'),
(9,  'Vaca Holstein Macho',         'Macho',  42, 1200.00, 1, 'Vendido'),
(11, 'Vaca Angus Hembra',           'Hembra', 20, 2200.00, 1, 'Disponible');

-- Conejos (idRaza 13-15, ids 19-23)
INSERT INTO Animal (idRaza, nombre, sexo, edad, precio, stock, estado) VALUES
(13, 'Conejo Nueva Zelandia Hembra','Hembra', 4,  50.00,  20, 'Disponible'),
(14, 'Conejo Californiano Macho',   'Macho',  3,  45.00,  15, 'Disponible'),
(15, 'Conejo Angora Hembra',        'Hembra', 5,  60.00,  10, 'Disponible'),
(13, 'Conejo Nueva Zelandia Macho', 'Macho',  2,  48.00,  18, 'Disponible'),
(14, 'Conejo Californiano Hembra',  'Hembra', 6,  55.00,   0, 'Vendido');

-- Patos (idRaza 16-17, ids 24-27)
INSERT INTO Animal (idRaza, nombre, sexo, edad, precio, stock, estado) VALUES
(16, 'Pato Pekin Macho',            'Macho',  5,  60.00,   8, 'Disponible'),
(17, 'Pato Muscovy Hembra',         'Hembra', 4,  55.00,   6, 'Disponible'),
(16, 'Pato Pekin Hembra',           'Hembra', 3,  58.00,  10, 'Disponible'),
(17, 'Pato Muscovy Macho',          'Macho',  6,  52.00,   0, 'Vendido');

-- Caballos (idRaza 18-19, ids 28-32)
INSERT INTO Animal (idRaza, nombre, sexo, edad, precio, stock, estado) VALUES
(18, 'Caballo Criollo Macho',       'Macho',  60, 3500.00, 2, 'Disponible'),
(19, 'Caballo Cuarto de Milla Hembra','Hembra',48, 4200.00, 1, 'Disponible'),
(18, 'Caballo Criollo Hembra',      'Hembra', 72, 3800.00, 1, 'Disponible'),
(19, 'Caballo Cuarto de Milla Macho','Macho', 36, 4000.00, 2, 'Disponible'),
(18, 'Caballo Criollo Joven Macho', 'Macho',  24, 3200.00, 1, 'Vendido');

-- Asnos (idRaza 20-21, ids 33-36)
INSERT INTO Animal (idRaza, nombre, sexo, edad, precio, stock, estado) VALUES
(20, 'Asno Espanol Macho',          'Macho',  48, 1800.00, 2, 'Disponible'),
(21, 'Asno San Hubertino Hembra',   'Hembra', 36, 2000.00, 2, 'Disponible'),
(20, 'Asno Espanol Hembra',         'Hembra', 60, 1600.00, 1, 'Disponible'),
(21, 'Asno San Hubertino Macho',    'Macho',  24, 1900.00, 1, 'Vendido');

-- Ovejas (idRaza 22-23, ids 37-41)
INSERT INTO Animal (idRaza, nombre, sexo, edad, precio, stock, estado) VALUES
(22, 'Oveja Merino Hembra',         'Hembra', 36, 450.00,  8, 'Disponible'),
(23, 'Oveja Suffolk Macho',         'Macho',  24, 500.00,  5, 'Disponible'),
(22, 'Oveja Merino Macho',          'Macho',  30, 420.00,  6, 'Disponible'),
(23, 'Oveja Suffolk Hembra',        'Hembra', 42, 480.00,  4, 'Disponible'),
(22, 'Oveja Merino Hembra Joven',   'Hembra', 12, 400.00, 10, 'Disponible');

-- ===================== VENTAS =====================
INSERT INTO Venta (idCliente, fecha, total) VALUES
(1, '2026-06-15 09:30:00', 75.00),
(2, '2026-06-20 14:15:00', 834.00),
(3, '2026-06-25 10:00:00', 113.00),
(1, '2026-07-01 11:45:00', 3500.00),
(4, '2026-07-05 08:20:00', 950.00),
(2, '2026-07-08 16:00:00', 500.00),
(5, '2026-07-10 13:30:00', 3800.00),
(3, '2026-07-12 10:10:00', 175.00);

-- ===================== DETALLES DE VENTA =====================
-- Venta 1: Maria compra 3 Gallinas Rhode Island Hembra (idAnimal=1, precio=25)
INSERT INTO DetalleVenta (idVenta, idAnimal, cantidad, precio, subtotal) VALUES
(1, 1, 3, 25.00, 75.00);

-- Venta 2: Carlos compra 2 Cerdo Large White Macho (idAnimal=7, precio=350) + 1 Gallina Sussex (idAnimal=4, precio=28)
INSERT INTO DetalleVenta (idVenta, idAnimal, cantidad, precio, subtotal) VALUES
(2, 7, 2, 350.00, 700.00),
(2, 4, 3, 28.00, 84.00),
(2, 1, 2, 25.00, 50.00);

-- Venta 3: Pedro compra 4 Gallinas Plymouth Rock (idAnimal=3, precio=22)
INSERT INTO DetalleVenta (idVenta, idAnimal, cantidad, precio, subtotal) VALUES
(3, 3, 4, 22.00, 88.00),
(3, 1, 1, 25.00, 25.00);

-- Venta 4: Maria compra 1 Caballo Criollo Macho (idAnimal=28, precio=3500)
INSERT INTO DetalleVenta (idVenta, idAnimal, cantidad, precio, subtotal) VALUES
(4, 28, 1, 3500.00, 3500.00);

-- Venta 5: Ana compra 2 Ovejas Merino (idAnimal=37, precio=450) + 1 Cerdo Duroc (idAnimal=8, precio=400)
INSERT INTO DetalleVenta (idVenta, idAnimal, cantidad, precio, subtotal) VALUES
(5, 37, 1, 450.00, 450.00),
(5, 23, 1, 500.00, 500.00);

-- Venta 6: Carlos compra 10 Conejos Nueva Zelandia (idAnimal=19, precio=50)
INSERT INTO DetalleVenta (idVenta, idAnimal, cantidad, precio, subtotal) VALUES
(6, 19, 10, 50.00, 500.00);

-- Venta 7: Lucia compra 1 Vaca Angus Macho (idAnimal=15, precio=2000) + 1 Asno Espanol Macho (idAnimal=33, precio=1800)
-- (no aplica porque Vaca Angus Macho es un solo item, ajustamos total)
INSERT INTO DetalleVenta (idVenta, idAnimal, cantidad, precio, subtotal) VALUES
(7, 15, 1, 2000.00, 2000.00),
(7, 33, 1, 1800.00, 1800.00);

-- Venta 8: Pedro compra 5 Gallinas Plymouth Hembra Joven (idAnimal=6, precio=20) + 3 Gallinas Rhode Island (idAnimal=1, precio=25)
INSERT INTO DetalleVenta (idVenta, idAnimal, cantidad, precio, subtotal) VALUES
(8, 6, 5, 20.00, 100.00),
(8, 1, 3, 25.00, 75.00);
