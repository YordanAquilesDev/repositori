-- =========================================
--  CATÁLOGOS PRINCIPALES
-- =========================================

CREATE TABLE animal (
                        id_animal INT AUTO_INCREMENT PRIMARY KEY,
                        especie VARCHAR(60),
                        raza VARCHAR(60)
);

CREATE TABLE producto (
                          id_producto INT AUTO_INCREMENT PRIMARY KEY,
                          nombre VARCHAR(50),
                          tipo_producto VARCHAR(60),
                          unidad_medida VARCHAR(30),
                          precio_unidad DECIMAL(10,2),
                          stock_actual DECIMAL(10,2)
);

CREATE TABLE proveedor (
                           id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
                           nombre VARCHAR(60),
                           apellido VARCHAR(60),
                           dni VARCHAR(8),
                           telefono VARCHAR(9)
);

CREATE TABLE cliente (
                         id_cliente INT AUTO_INCREMENT PRIMARY KEY,
                         nombre VARCHAR(50),
                         apellido VARCHAR(60),
                         dni VARCHAR(8),
                         celular VARCHAR(9),
                         direccion TEXT
);

-- =========================================
--  ÁREA DE PRODUCCIÓN
-- =========================================

CREATE TABLE lote_animal (
                             id_lote INT AUTO_INCREMENT PRIMARY KEY,
                             id_animal INT,
                             fecha_inicio DATE,
                             cantidad_inicio INT,
                             cantidad_actual INT,
                             peso_promedio DECIMAL(10,2),
                             estado VARCHAR(50),
                             FOREIGN KEY (id_animal) REFERENCES animal(id_animal) ON DELETE RESTRICT
);

CREATE TABLE consumo_lote (
                              id_consumo INT AUTO_INCREMENT PRIMARY KEY,
                              id_lote INT,
                              id_producto INT,
                              cantidad DECIMAL(10,2),
                              fecha DATE,
                              FOREIGN KEY (id_lote) REFERENCES lote_animal(id_lote) ON DELETE CASCADE,
                              FOREIGN KEY (id_producto) REFERENCES producto(id_producto) ON DELETE RESTRICT
);

-- =========================================
--  ÁREA DE COMPRAS E INVENTARIO
-- =========================================

CREATE TABLE compra (
                        id_compra INT AUTO_INCREMENT PRIMARY KEY,
                        id_proveedor INT,
                        fecha DATE DEFAULT (CURRENT_DATE),
                        total DECIMAL(10,2),
                        FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor) ON DELETE RESTRICT
);

CREATE TABLE detalle_compra (
                                id_detalle INT AUTO_INCREMENT PRIMARY KEY,
                                id_compra INT,
                                id_producto INT,
                                cantidad DECIMAL(10,2),
                                subtotal DECIMAL(10,2),
                                FOREIGN KEY (id_compra) REFERENCES compra(id_compra) ON DELETE CASCADE,
                                FOREIGN KEY (id_producto) REFERENCES producto(id_producto) ON DELETE RESTRICT
);

CREATE TABLE movimiento_almacen (
                                    id_movimiento INT AUTO_INCREMENT PRIMARY KEY,
                                    id_producto INT,
                                    tipo_movimiento VARCHAR(50),
                                    cantidad DECIMAL(10,2),
                                    fecha DATE DEFAULT (CURRENT_DATE),
                                    contexto TEXT,
                                    FOREIGN KEY (id_producto) REFERENCES producto(id_producto) ON DELETE RESTRICT
);

-- =========================================
--  ÁREA DE VENTAS Y PEDIDOS
-- =========================================

CREATE TABLE pedido (
                        id_pedido INT AUTO_INCREMENT PRIMARY KEY,
                        id_cliente INT,
                        fecha DATE DEFAULT (CURRENT_DATE),
                        estado VARCHAR(60),
                        total DECIMAL(10,2),
                        FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente) ON DELETE RESTRICT
);

CREATE TABLE detalle_pedido (
                                id_detalle INT AUTO_INCREMENT PRIMARY KEY,
                                id_pedido INT,
                                id_producto INT,
                                cantidad DECIMAL(10,2),
                                subtotal DECIMAL(10,2),
                                FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido) ON DELETE CASCADE,
                                FOREIGN KEY (id_producto) REFERENCES producto(id_producto) ON DELETE RESTRICT
);

CREATE TABLE venta (
                       id_venta INT AUTO_INCREMENT PRIMARY KEY,
                       id_cliente INT,
                       fecha DATE DEFAULT (CURRENT_DATE),
                       total DECIMAL(10,2),
                       FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente) ON DELETE RESTRICT
);

CREATE TABLE detalle_venta (
                               id_detalle INT AUTO_INCREMENT PRIMARY KEY,
                               id_venta INT,
                               id_producto INT,
                               cantidad DECIMAL(10,2),
                               subtotal DECIMAL(10,2),
                               FOREIGN KEY (id_venta) REFERENCES venta(id_venta) ON DELETE CASCADE,
                               FOREIGN KEY (id_producto) REFERENCES producto(id_producto) ON DELETE RESTRICT
);

CREATE TABLE empleados (
                               id_empleado INT AUTO_INCREMENT PRIMARY KEY,
                               nombres VARCHAR(100) NOT NULL,
                               apellidos VARCHAR(100) NOT NULL,
                               usuario VARCHAR(50) NOT NULL UNIQUE,
                               contrasena VARCHAR(100) NOT NULL,
                               rol ENUM('ADMINISTRADOR','USUARIO') NOT NULL,
                               estado ENUM('ACTIVO','INACTIVO') DEFAULT 'ACTIVO'
);
INSERT INTO empleados
(nombres, apellidos, usuario, contrasena, rol)
VALUES
('Juan', 'Perez', 'admin', '1234', 'ADMINISTRADOR');

INSERT INTO empleados
(nombres, apellidos, usuario, contrasena, rol)
VALUES
('Adrian', 'Espinoza', 'adrian', '1234', 'USUARIO');



Create Table empresa(
    id_empresa int auto_increment primary key,
    nombre varchar(80),
    razon_social varchar(90),
    ruc varchar(11),

)