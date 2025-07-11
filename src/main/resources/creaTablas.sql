-- Borra y crea la base de datos
DROP DATABASE IF EXISTS panaderia;
CREATE DATABASE panaderia CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE panaderia;

-- Crea usuario nuevo con contraseña
CREATE USER IF NOT EXISTS 'usuario_panaderia'@'localhost' IDENTIFIED BY 'Adictiva';

-- Da permisos al usuario sobre la base
GRANT ALL PRIVILEGES ON panaderia.* TO 'usuario_panaderia'@'localhost';
FLUSH PRIVILEGES;

-- Tabla de productos compatible con la clase Producto
CREATE TABLE producto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- corresponde a idProducto (Long)
    nombre VARCHAR(100) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    descripcion TEXT,
    detalle TEXT,
    precio DECIMAL(10,2) NOT NULL,
    ruta_imagen VARCHAR(255),
    activo BOOLEAN DEFAULT TRUE
);

-- Tabla de órdenes
CREATE TABLE orden (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo_retiro VARCHAR(10) NOT NULL UNIQUE,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10,2) NOT NULL
);

-- Tabla de detalles de orden
CREATE TABLE detalle_orden (
    orden_id INT NOT NULL,
    producto_id BIGINT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (orden_id) REFERENCES orden(id) ON DELETE CASCADE,
    FOREIGN KEY (producto_id) REFERENCES producto(id)
);

-- Datos de ejemplo
INSERT INTO producto (nombre, categoria, descripcion, detalle, precio, ruta_imagen, activo) VALUES
('Pan dulce', 'Dulce', 'Hecho con miel natural', 'Ideal para acompañar el café', 1500.00, 'pan_dulce.jpg', TRUE),
('Pastel de chocolate', 'Dulce', 'Con cobertura de ganache', 'Rinde 8 porciones', 3500.00, 'pastel_chocolate.jpg', TRUE),
('Pan integral', 'Salado', 'Con semillas y cereales', 'Aporta fibra y energía', 1800.00, 'pan_integral.jpg', TRUE),
('Empanada de carne', 'Salado', 'Rellena con carne mechada', 'Con masa crujiente', 1200.00, 'empanada_carne.jpg', TRUE);

-- Inserta orden
INSERT INTO orden (codigo_retiro, total)
VALUES ('ABC1234567', 8200.00);

-- Inserta detalles de orden
INSERT INTO detalle_orden (orden_id, producto_id, cantidad, precio_unitario, subtotal)
VALUES 
(1, 1, 2, 1500.00, 3000.00),  -- 2 Panes dulces
(1, 2, 1, 3500.00, 3500.00),  -- 1 Pastel chocolate
(1, 4, 2, 1200.00, 2400.00);  -- 2 Empanadas carne
