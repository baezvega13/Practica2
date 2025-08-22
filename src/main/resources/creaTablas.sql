DROP DATABASE IF EXISTS panaderia;
CREATE DATABASE panaderia CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE panaderia;

-- Crea usuario nuevo con contraseña
CREATE USER IF NOT EXISTS 'usuario_panaderia'@'localhost' IDENTIFIED BY 'Adictiva';

-- Da permisos al usuario sobre la base
GRANT ALL PRIVILEGES ON panaderia.* TO 'usuario_panaderia'@'localhost';
FLUSH PRIVILEGES;

-- -----------------------------------------------------------------------------------------------------
-- TABLAS DEL MODELO DE NEGOCIO
-- -----------------------------------------------------------------------------------------------------

-- Tabla de productos compatible con la clase Producto
CREATE TABLE producto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- Corresponde a id (Long)
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

-- -----------------------------------------------------------------------------------------------------
-- DATOS DE EJEMPLO
-- -----------------------------------------------------------------------------------------------------

-- Productos de panadería (corresponde a panaderia.html)
INSERT INTO producto (nombre, categoria, descripcion, detalle, precio, ruta_imagen) VALUES
    ('Panes rústicos', 'Panadería', 'Una selección de panes rústicos', 'Panes artesanales hechos con masa madre y horneados en piedra, perfectos para cualquier comida.', 1500.00, 'pan 1.jpg'),
    ('Baguette', 'Panadería', 'Crujiente y tradicional baguette', 'Pan francés clásico, crujiente por fuera y suave por dentro, ideal para sándwiches o para acompañar sopas y quesos.', 1200.00, 'pan 2.jpg'),
    ('Pan de barra', 'Panadería', 'Pan de barra suave y esponjoso', 'Pan de barra grande, ideal para toda la familia, con una corteza dorada y un interior suave.', 2100.00, 'pan 3.jpg'),
    ('Pan artesanal', 'Panadería', 'Hecho a mano con los mejores ingredientes', 'Pan de campo con una corteza gruesa y una miga densa, lleno de sabor.', 2500.00, 'pan 4.jpg'),
    ('Rollos de pan', 'Panadería', 'Rollos individuales de pan fresco', 'Pequeños y suaves rollos, perfectos para cenas, bocadillos o para acompañar guisos.', 1800.00, 'pan 5.jpg'),
    ('Pan de Viena', 'Panadería', 'Pan de Viena, ligero y tierno', 'Con una textura delicada y un sabor ligeramente dulce, ideal para tostadas o sandwiches.', 1600.00, 'pan 6.jpg');

-- Inserta los productos de repostería (corresponde a reposteria.html)
INSERT INTO producto (nombre, categoria, descripcion, detalle, precio, ruta_imagen) VALUES
    ('Pastel de chocolate', 'Repostería', 'Delicioso pastel de chocolate', 'Pastel de chocolate rico y húmedo con una suave cobertura de ganache de chocolate, perfecto para los amantes del cacao.', 3500.00, 'pastel 1.jpg'),
    ('Pastel de vainilla', 'Repostería', 'Clásico pastel de vainilla', 'Pastel de vainilla esponjoso con crema de mantequilla, un postre clásico que siempre agrada.', 3200.00, 'pastel 2.jpg'),
    ('Pastel de fresa', 'Repostería', 'Pastel con fresas frescas', 'Pastel ligero con capas de crema batida y fresas frescas, una opción refrescante y deliciosa.', 3100.00, 'pastel 3.jpg'),
    ('Cheesecake', 'Repostería', 'Cheesecake cremoso', 'Pastel de queso cremoso sobre una base de galleta, con un toque de acidez que lo hace irresistible.', 4000.00, 'pastel 4.jpg'),
    ('Tiramisú', 'Repostería', 'Tiramisú tradicional', 'Postre italiano clásico con capas de bizcochos de soletilla empapados en café y crema de mascarpone.', 2800.00, 'pastel 5.jpg'),
    ('Mousse de limón', 'Repostería', 'Mousse de limón, ligero y refrescante', 'Postre suave y aireado con un intenso sabor a limón, ideal para terminar una comida ligera.', 2500.00, 'pastel 6.jpg');

-- Orden de ejemplo
INSERT INTO orden (codigo_retiro, total) VALUES
    ('PAND123456', 5000.00);

-- Detalles para la orden de ejemplo
INSERT INTO detalle_orden (orden_id, producto_id, cantidad, precio_unitario, subtotal) VALUES
    (1, 1, 1, 1500.00, 1500.00), -- 1 Panes rústicos
    (1, 7, 1, 3500.00, 3500.00); -- 1 Pastel de chocolate
