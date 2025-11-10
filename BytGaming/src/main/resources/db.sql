USE bygaming;

INSERT INTO category (id, description, nombre) VALUES
(1, 'Consolas de videojuegos como PlayStation, Xbox, y Nintendo Switch', 'Consolas'),
(2, 'Accesorios gamer como teclados, mouses, headsets y mandos', 'Accesorios'),
(3, 'Juegos físicos y digitales para diversas plataformas', 'Juegos'),
(4, 'Computadoras diseñadas para juegos de alto rendimiento', 'PC Gamer'),
(5, 'Sillas ergonómicas para gamers', 'Sillas Gamer'),
(6, 'Monitores con alta tasa de refresco para gaming', 'Monitores');

INSERT INTO image (id, image_id, image_url, name) VALUES
(1, 'ytmdzdiidfhwluljuxs4', 'http://res.cloudinary.com/dgtgmsqo7/image/upload/v1746230424/ytmdzdiidfhwluljuxs4.webp', 'nintendo.jpg'),
(2, 'zzzec1z2irasi0askdic', 'http://res.cloudinary.com/dgtgmsqo7/image/upload/v1746230525/zzzec1z2irasi0askdic.webp', 'full_image-2031346623958.jpg'),
(3, 'lhf2xpppi2rnkfdkpubj', 'http://res.cloudinary.com/dgtgmsqo7/image/upload/v1746230678/lhf2xpppi2rnkfdkpubj.webp', 'full_image-1.webp'),
(4, 'ruznrmqfv3nbe9nufeuv', 'http://res.cloudinary.com/dgtgmsqo7/image/upload/v1746230755/ruznrmqfv3nbe9nufeuv.webp', 'full_image-2031349807874.webp'),
(5, 'voeebccirqzsmbiequdg', 'http://res.cloudinary.com/dgtgmsqo7/image/upload/v1746230886/voeebccirqzsmbiequdg.webp', 'full_image-1 (1).webp'),
(6, 'y7svtplprlm6w9ddvlsi', 'http://res.cloudinary.com/dgtgmsqo7/image/upload/v1746230933/y7svtplprlm6w9ddvlsi.webp', 'full_image-1.jpg'),
(7, 'lquli0rr0p6qi4jfovuw', 'http://res.cloudinary.com/dgtgmsqo7/image/upload/v1746231046/lquli0rr0p6qi4jfovuw.webp', 'full_image-2004354889395.jpg'),
(8, 'jumztvufqleiwfdgwany', 'http://res.cloudinary.com/dgtgmsqo7/image/upload/v1746231087/jumztvufqleiwfdgwany.webp', 'full_image-2004335036978.jpg'),
(9, 'myat0yzaknfx6zquxgqm', 'http://res.cloudinary.com/dgtgmsqo7/image/upload/v1746231166/myat0yzaknfx6zquxgqm.webp', 'full_image-1.jpg'),
(10, 'l8lqhqhyrmlaqlhr2l0x', 'http://res.cloudinary.com/dgtgmsqo7/image/upload/v1746231216/l8lqhqhyrmlaqlhr2l0x.webp', 'full_image-1.jpg');

INSERT INTO tlb_product (is_active, price, units, category_id, id, image_id, description, product_name) VALUES
(TRUE,   154.00,    25, 1, 1, 1, 'Consola Nintendo Switch versión OLED con Joy-Con rojo y azul neón, ideal para juegos en casa y en movimiento', 'Nintendo Switch OLED Neon'),
(TRUE,  2749.00,    15, 1, 2, 2, 'PlayStation 5 Slim edición estándar con dos juegos incluidos. Potente rendimiento, diseño compacto y experiencia inmersiva.', 'CONSOLA PS5 PLAYSTATION 5 SLIM STANDARD BUNDLE + 2 JUEGOS'),
(TRUE,    49.90,    25, 3, 3, 3, 'Videojuego para PlayStation 4 - Battleborn. Shooter en primera persona con modo historia y multijugador online.', 'JUEGO PLAYSTATION 4 BATTLEBORN'),
(TRUE,    49.99,     5, 3, 4, 4, 'Videojuego para PlayStation 5 - Star Wars Outlaws. Juego de acción y aventura en el universo de Star Wars.', 'JUEGO STAR WARS OUTLAWS PS5'),
(TRUE,   239.99,    30, 6, 5, 5, 'Monitor gaming IPS Full HD de 29 pulgadas, 1ms de respuesta, 100Hz de tasa de refresco y diseño ultrawide para una experiencia visual inmersiva.', 'MONITOR IPS FULL HD TEROS 29 TE-2910G GAMING 29 1MS 100HZ ULTRAWIDE PLANO W'),
(TRUE,   329.99,    20, 6, 6, 6, 'Monitor gaming QHD de 32 pulgadas, resolución 2560x1440, 165Hz de tasa de refresco, 1ms de respuesta, FreeSync y curvado para una experiencia de juego fluida y visualmente impresionante.', 'MONITOR QHD TEROS 32 TE-3210G CURVO 2K 2560X1440 FREESYNC 165HZ 1MS 250NITS 125.000694444444 VA'),
(TRUE,  2099.99,    15, 4, 7, 7, 'Laptop gamer ASUS TUF F15 con procesador Intel Core i5-12500H, 16GB de RAM, 512GB de SSD, tarjeta gráfica RTX 3050, pantalla de 15.6 pulgadas Full HD, diseñada para ofrecer un rendimiento excelente en videojuegos.', 'LAPTOP GAMER ASUS TUF F15 FX507ZC4-HN087W INTEL CORE I5-12500H 16GB 512GB SSD RTX3050 15.6 FHD'),
(TRUE,  1999.99,    10, 4, 8, 8, 'Laptop gamer Lenovo Legion Pro 5i con procesador Intel Core i9-14900HX, 16GB de RAM, 1TB de SSD, tarjeta gráfica NVIDIA GeForce RTX 4060 con 8GB, pantalla de 16 pulgadas. Ideal para gaming de alto rendimiento.', 'LAPTOP GAMER LENOVO LEGION PRO 5I INTEL CORE I9-14900HX 16GB RAM 1TB SSD 16 NVIDIA GEFORCE RTX4060 8GB'),
(TRUE,   399.99,    15, 5, 9, 9, 'Silla gamer ergonómica Dreitzt Shine RGB Series, con luces RGB, posapiés, y función reclinable para una experiencia de juego cómoda y personalizada.', 'SILLA DREIZT BLANCO GAMER ERGONÓMICA SHINE RGB SERIES LUCES CON POSAPIES RECLINABLE'),
(TRUE,   399.99,    10, 5,10,10, 'Silla gamer ergonómica Dreitzt Shine RGB Series en color negro, con luces RGB, posapiés, y función reclinable para una experiencia de juego cómoda y personalizada.', 'SILLA DREIZT NEGRO GAMER ERGONÓMICA SHINE RGB SERIES LUCES CON POSAPIES RECLINABLE');

INSERT INTO staff (id, email,               name,         post) VALUES
(1, 'laura.gomez@example.com', 'Laura Gómez',  'ALMACENERO'),
 (2, 'carlos.ruiz@example.com','Carlos Ruiz',  'ALMACENERO');

INSERT INTO suppliers (is_active, id, email, name,phone,ruc) VALUES
(TRUE, 1, 'contacto@techgaming.pe', 'TechGaming Perú', '987654321', '20458712345'),
(TRUE, 2, 'ventas@zonagamer.com',   'Zona Gamer SAC', '912345678', '20587654321'),
(TRUE, 3, 'info@prohardware.com',  'ProHardware Distribuciones','923456789', '20654321876'),
(TRUE, 4, 'soporte@gamingworld.pe','Gaming World S.R.L.',      '934567890', '20765438901'),
(TRUE, 5, 'contact@hyperpc.la',    'HyperPC Latinoamérica',    '945678901', '20876543219');

INSERT INTO kardex (id, balance,      cost,     date, movement_type, quantity, product_id, supplier_id, staff_id) VALUES
 (1, 1073741824, 1000.00, '2025-05-03 03:57:59.694000','ENTRADA', 4, 1,1,1);

INSERT INTO user_address (id, created_at,dirrection, email, first_name, last_name, phone, user_id) VALUES
(1, '2025-05-03 14:45:00.000000', 'Jr. Los Libertadores 456, Lima, Perú','carlos.garcia@example.com','Carlos',    'García',  '+51 912345678', 2),
(2, '2025-05-03 14:45:00.000000', 'Jr. Los Libertadores 456, Lima, Perú','carlos.garcia@example.com','Carlos',    'García',  '+51 912345678', 1);

INSERT INTO carts (id, user_id) VALUES
(7, 1);

INSERT INTO cart_items (quantity, cart_id, id, product_id) VALUES
(4, 7, 4, 1),
(4, 7, 5, 3);
