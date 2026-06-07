-- =========================================
--  CATÁLOGOS PRINCIPALES
-- =========================================
select *
from Clientes;

CREATE TABLE animal (
                        id_animal SERIAL PRIMARY KEY,
                        especie VARCHAR(60),
                        raza VARCHAR(60)
);
insert into Animal(especie,raza) values
    ('Pollo','Rihxwpo'

    );
select * from Animal;

CREATE TABLE producto (
                          id_producto SERIAL PRIMARY KEY,
                          nombre VARCHAR(50),
                          tipo_producto VARCHAR(60),
                          unidad_medida VARCHAR(30),
                          precio_unidad NUMERIC(10,2),
                          stock_actual NUMERIC(10,2)
);

CREATE TABLE proveedor (
                           id_proveedor SERIAL PRIMARY KEY,
                           nombre VARCHAR(60),
                           apellido VARCHAR(60),
                           dni VARCHAR(8),
                           telefono VARCHAR(9)
);

CREATE TABLE cliente (
                         id_cliente SERIAL PRIMARY KEY,
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
                             id_lote SERIAL PRIMARY KEY,
                             id_animal INT REFERENCES animal(id_animal) ON DELETE RESTRICT,
                             fecha_inicio DATE,
                             cantidad_inicio INT,
                             cantidad_actual INT,
                             peso_promedio NUMERIC(10,2),
                             estado VARCHAR(50)
);

CREATE TABLE consumo_lote (
                              id_consumo SERIAL PRIMARY KEY,
                              id_lote INT REFERENCES lote_animal(id_lote) ON DELETE CASCADE,
                              id_producto INT REFERENCES producto(id_producto) ON DELETE RESTRICT,
                              cantidad NUMERIC(10,2),
                              fecha DATE
);

-- =========================================
--  ÁREA DE COMPRAS E INVENTARIO
-- =========================================

CREATE TABLE compra (
                        id_compra SERIAL PRIMARY KEY,
                        id_proveedor INT REFERENCES proveedor(id_proveedor) ON DELETE RESTRICT,
                        fecha DATE DEFAULT CURRENT_DATE,
                        total NUMERIC(10,2)
);

CREATE TABLE detalle_compra (
                                id_detalle SERIAL PRIMARY KEY,
                                id_compra INT REFERENCES compra(id_compra) ON DELETE CASCADE,
                                id_producto INT REFERENCES producto(id_producto) ON DELETE RESTRICT,
                                cantidad NUMERIC(10,2),
                                subtotal NUMERIC(10,2)
);

CREATE TABLE movimiento_almacen (
                                    id_movimiento SERIAL PRIMARY KEY,
                                    id_producto INT REFERENCES producto(id_producto) ON DELETE RESTRICT,
                                    tipo_movimiento VARCHAR(50),
                                    cantidad NUMERIC(10,2),
                                    fecha DATE DEFAULT CURRENT_DATE,
                                    contexto TEXT
);

-- =========================================
--  ÁREA DE VENTAS Y PEDIDOS
-- =========================================

CREATE TABLE pedido (
                        id_pedido SERIAL PRIMARY KEY,
                        id_cliente INT REFERENCES cliente(id_cliente) ON DELETE RESTRICT,
                        fecha DATE DEFAULT CURRENT_DATE,
                        estado VARCHAR(60),
                        total NUMERIC(10,2)
);

CREATE TABLE detalle_pedido (
                                id_detalle SERIAL PRIMARY KEY,
                                id_pedido INT REFERENCES pedido(id_pedido) ON DELETE CASCADE,
                                id_producto INT REFERENCES producto(id_producto) ON DELETE RESTRICT,
                                cantidad NUMERIC(10,2),
                                subtotal NUMERIC(10,2)
);

CREATE TABLE venta (
                       id_venta SERIAL PRIMARY KEY,
                       id_cliente INT REFERENCES cliente(id_cliente) ON DELETE RESTRICT,
                       fecha DATE DEFAULT CURRENT_DATE,
                       total NUMERIC(10,2)
);

CREATE TABLE detalle_venta (
                               id_detalle SERIAL PRIMARY KEY,
                               id_venta INT REFERENCES venta(id_venta) ON DELETE CASCADE,
                               id_producto INT REFERENCES producto(id_producto) ON DELETE RESTRICT,
                               cantidad NUMERIC(10,2),
                               subtotal NUMERIC(10,2)
);