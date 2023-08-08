--- insert brands data in the database
Insert Into brands (id, name, about, image_uri, active)
VALUES (1,
        'Nike',
        'Nike, Inc. is an American multinational corporation that is engaged in the design, development, manufacturing, and worldwide marketing and sales of footwear, apparel, equipment, accessories, and services.',
        'https://sneakermyth.com/wp-content/uploads/2018/11/nike-logo-square-1.png', true);

Insert Into brands (id, name, about, image_uri, active)
VALUES (2, 'Adidas',
        'Adidas AG is a German multinational corporation, founded and headquartered in Herzogenaurach, Germany, that designs and manufactures shoes, clothing and accessories.',
        'https://thumbs.dreamstime.com/b/adidas-logo-editorial-illustrative-white-background-adidas-logo-editorial-illustrative-white-background-eps-download-vector-208329055.jpg', true);

Insert Into brands (id, name, about, image_uri, active)
VALUES (3, 'Puma',
        'Puma SE, branded as Puma, is a German multinational corporation that designs and manufactures athletic and casual footwear, apparel and accessories, which is headquartered in Herzogenaurach, Bavaria, Germany.',
        'https://www.pngitem.com/pimgs/m/82-827316_logo-puma-brand-nike-symbol-transparent-background-puma.png', true);

Insert Into brands (id, name, about, image_uri, active)
VALUES (4, 'Reebok',
        'Reebok International Limited is an Anglo-American footwear and clothing company that has been a subsidiary of German sporting goods giant Adidas since August 2005.',
        'https://www.pngitem.com/pimgs/m/133-1332982_reebok-vector-cricket-reebok-logo-png-transparent-png.png', true);
--- Insert Categories data in the database

Insert Into categories (id, name, description, image_uri, active)
VALUES (1, 'Shoes',
        'Shoes are a type of footwear. Shoes come in pairs, with one shoe for each foot.',
        'https://static.vecteezy.com/system/resources/previews/008/956/590/original/creative-abstract-black-silhouette-running-shoe-design-logo-design-template-free-vector.jpg',
        true);

Insert Into categories (id, name, description, image_uri, active)
VALUES (2, 'Clothes',
        'Clothing is items worn on the body. Clothing is typically made of fabrics or textiles but over time has included garments made from animal skin or other thin sheets of materials put together.',
        'https://img.freepik.com/free-vector/hand-drawn-clothing-store-logo-template_23-2149511674.jpg',
        true);

Insert Into categories (id, name, description, image_uri, active)
VALUES (3,
        'Accessories',
        'Accessories are items that are used to contribute, in a secondary manner, to the wearers outfit.',
        'https://cdn5.vectorstock.com/i/1000x1000/52/64/clothes-and-accessories-logo-linear-vector-30095264.jpg',
        true);

--- Insert Products data in the database

Insert Into products (id, product_code, rating, name,created_at, description, price, stock, image_uri, active, category_id, brand_id)
VALUES (1, 'P-NK-1', 5,'Nike Air Max 90', now(),
        'The Nike Air Max 90 is a retro running shoe with Max Air® in the heel for comfort and cushioning. ',
        119.99, 100,
        'https://static.nike.com/a/images/t_PDP_1280_v1/f_auto,q_auto:eco/f9e940d3-2192-434e-a017-072303ce2f14/air-max-90-mens-shoes-6mml4F.png',
        true, 1, 1);

Insert Into products (id, product_code, rating, name,created_at, description, price, stock, image_uri, active, category_id, brand_id)
VALUES (2, 'P-NK-2',5, 'Nike Air Max 95', now(),
        'The Nike Air Max 95 boasts classic style and a comfortable step. Synthetic leather, mesh, suede, and leather upper provides a comfortable fit.',
        169.99, 100,
        'https://static.nike.com/a/images/t_PDP_1280_v1/f_auto,q_auto:eco/e20484c2-2b66-4124-b692-ad132a4ef9a5/air-max-95-essential-mens-shoes-4Nzc1w.png',
        true, 1, 1);

Insert Into products (id, product_code, rating, name,created_at, description, price, stock, image_uri, active, category_id, brand_id)
VALUES (3, 'P-NK-3',4, 'Nike Air Max 97', now(),
        'The Nike Air Max 97 reimagines an iconic running shoe into everyday kicks for kids. ',
        149.99, 100,
        'https://static.nike.com/a/images/t_PDP_1280_v1/f_auto,q_auto:eco/c6266a49-a968-41c9-91e3-bd7aeada98c6/air-max-97-womens-shoes-Fr6rM4.png',
        true, 1, 1);

Insert Into products (id, product_code,  rating,name, created_at,description, price, stock, image_uri, active, category_id, brand_id)
VALUES (4, 'P-NK-4',3, 'Nike Air Max 270', now(),
        'The Nike Air Max 270 React combines some of Nike’s latest cushioning innovations to create an ultra comfortable and supportive sneaker. ',
        149.99, 100,
        'https://static.nike.com/a/images/t_PDP_1280_v1/f_auto,q_auto:eco/41e147d8-6410-4b75-b46c-567b0d5903ca/air-max-270-mens-shoes-KkLcGR.png',
        true, 1, 1);

Insert Into products (id, product_code, rating, name,created_at, description, price, stock, image_uri, active, category_id, brand_id)
VALUES (5, 'P-AD-1',2, 'Adidas Superstar', now(),
        'The adidas Superstar sneaker reigns supreme. The fan favorite launched in 1969 and quickly lived up to its name as NBA players laced into the now-famous shell-toe design.',
        89.99, 100,
        'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/7ed0855435194229a525aad6009a0497_9366/Superstar_Shoes_White_EG4958_01_standard.jpg',
        true, 1, 2);

Insert Into products (id, product_code, rating, name,created_at, description, price, stock, image_uri, active, category_id, brand_id)
VALUES (6, 'P-AD-2',5, 'Adidas Stan Smith', now(),
        'The Stan Smith has been a style staple since it first hit the scene in the early 1970s. This pair puts a fresh spin on the classic with a flexible adidas Primeknit upper.',
        89.99, 100,
        'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/e01dea68cf93434bae5aac0900af99e8_9366/Stan_Smith_Shoes_White_FX5500_01_standard.jpg',
        true, 1, 2);

Insert Into products (id, product_code, rating, name,created_at, description, price, stock, image_uri, active, category_id, brand_id)
VALUES (7, 'P-AD-3', 4,'Adidas NMD_R1', now(),
        'Inspired by the pioneering spirit of running designs from the mid-80s, the NMD series celebrates a time when wearable technology was exciting and new. ',
        139.99, 100,
        'https://assets.adidas.com/images/w_1880,f_auto,q_auto/db571e00566e45b8b3eeae9201544310_9366/GY4276_01_standard.jpg',
        true, 1, 2);

Insert Into products (id, product_code, rating, name,created_at, description, price, stock, image_uri, active, category_id, brand_id)
VALUES (8, 'P-AD-4',5, 'Adidas Nite Jogger', now(),
        'Inspired by the 1980 Nite Jogger, these shoes shine bright with retro style and reflective details. The mesh and nylon ripstop upper is detailed with suede overlays. An updated Boost midsole adds responsive cushioning for all-day comfort.',
        129.99, 100,
        'https://assets.adidas.com/images/w_600,f_auto,q_auto/92aa10ad991842cda680ab8200d6d30e_9366/Nite_Jogger_Shoes_Black_FV1277_01_standard.jpg',
        true, 1, 2);

Insert Into products (id, product_code, rating, name,created_at, description, price, stock, image_uri, active, category_id, brand_id)
VALUES (9, 'P-PU-1',6, 'Puma RS-X3', now(),
        'The RS-X3 takes things to a new level: cubed, enhanced, extra. With amplified detailing on the upper, stronger material mixes, and bold color combinations, this kick is ready to take your shoe game to a new level.',
        109.99, 100,
        'https://images.puma.com/image/upload/f_auto,q_auto,b_rgb:fafafa,w_600,h_600/global/371570/01/sv01/fnd/PNA/fmt/png/RS-X%C2%B3-Puzzle-Men''s-Sneakers',
        true, 1, 3);

Insert Into products (id, product_code, rating, name, created_at,description, price, stock, image_uri, active, category_id, brand_id)
VALUES (10, 'P-PU-2',7, 'Puma RS-X Bold', now(),
        'The RS-X Bold is a new silhouette that celebrates extreme reinvention. The bulky design is inspired by archive PUMA Running System silhouettes with a progressive design that incorporates the latest trends in streetwear.',
        129.99, 100,
        'https://www.tradeinn.com/f/13722/137228660/puma-rs-x-bold-trainers.jpg',
        true, 1, 3);

Insert Into products (id, product_code, rating, name,created_at, description, price, stock, image_uri, active, category_id, brand_id )
VALUES (11, 'P-PU-3',6, 'Puma RS-X2 Puzzle', now(),
        'The RS-X2 Puzzle takes things to a new level: cubed, enhanced, extra. With amplified detailing on the upper, stronger material mixes, and bold color combinations, this kick is ready to take your shoe game to a new level.',
        109.99, 100,
        'https://images.puma.com/image/upload/f_auto,q_auto,b_rgb:fafafa,w_600,h_600/global/369579/02/sv01/fnd/PNA/fmt/png/RS-X-Reinvention-Sneakers',
        false, 1, 3);


Insert Into products (id, product_code, rating, name,created_at, description, price, stock, image_uri, active, category_id, brand_id)
VALUES (12, 'P-PU-4', 4,'Puma RS-X3 Puzzle', now(),
        'The RS-X3 Puzzle takes things to a new level: cubed, enhanced, extra. With amplified detailing on the upper, stronger material mixes, and bold color combinations, this kick is ready to take your shoe game to a new level.',
        109.99, 100,
        'https://www.tradeinn.com/f/13735/137359343/puma-rs-x3-puzzle-ps-trainers.jpg',
        false, 1, 3);


-- Insert clothes products data in the database
INSERT INTO products (id, product_code, rating, name,created_at, description, price, stock, image_uri, active, category_id, brand_id)
VALUES (13, 'P-NK-5',3, 'Nike Sportswear Club Fleece', now(),
        'The Nike Sportswear Club Fleece Pants are made with soft brushed-back fleece for all-day comfort.',
        54.99, 100,
        'https://static.nike.com/a/images/t_PDP_1280_v1/f_auto,q_auto:eco/b43612fb-0ca4-4d02-bc8b-5769484203cc/sportswear-repeat-fleece-sweatshirt-5lQP2H.png',
        true, 2, 1);

INSERT INTO products (id, product_code, rating, name,created_at, description, price, stock, image_uri, active, category_id, brand_id)
VALUES (14, 'P-AD-5', 5,'Adidas Sportswear Mo',  now(),'The Mo ', 99.9, 100, 'https://assets.adidas.com/images/w_940,f_auto,q_auto/74e561f630fc4ea39aa3af34016c10ae_9366/HR3002_21_model.jpg', true, 2, 2);


-- insert accessories product data in the database
INSERT INTO products (id, product_code,  rating,name, created_at,description, price, stock, image_uri, active, category_id, brand_id)
VALUES (15, 'P-NK-6',5, 'Nike Accessories Club Cap', now(),
        'The Nike Sportswear Club Fleece Pants are made with soft brushed-back fleece for all-day comfort.',
        54.99, 100,
        'https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/c06b0ac1-0cba-44c2-a1a2-5e78cd14e183/sportswear-heritage86-futura-washed-hat-VSW4x0.png',
        true, 3, 1);

INSERT INTO products (id, product_code, rating, name, created_at,description, price, stock, image_uri, active, category_id, brand_id)
VALUES (16, 'P-AD-6',4, 'Adidas Accessories Mo', now(),
        'The Mo ', 99.9, 100, 'https://assets.adidas.com/images/w_940,f_auto,q_auto/5428a1ccea3948b2977daefa00cf30e2_9366/HT2034_01_standard.jpg', true, 3, 2);
