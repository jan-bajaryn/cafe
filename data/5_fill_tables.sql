INSERT INTO product_group(id, name, description, photo_name, type)
VALUES (1, 'Четыре сыра', 'Сливочный соус, смесь сыров пармезан и чеддер, сыр блю чиз, моцарелла', 'chetyre_syra.jpg',
        0);

INSERT INTO product_group(id, name, description, photo_name, type)
VALUES (2, 'Дон Бекон', 'Томатный соус, цыпленок филе, пикантная пепперони, красный лук, моцарелла, бекон',
        'don_bekon.jpg',
        0);

INSERT INTO product_group(id, name, description, photo_name, type)
VALUES (3, 'Испанские колбаски чоризо', 'Томаты свежие, острая чоризо, моцарелла, соус чипотле', 'kolbaski.jpeg',
        0);

INSERT INTO product_group(id, name, description, photo_name, type)
VALUES (4, 'Четыре сыра', 'Сливочный соус, смесь сыров пармезан и чеддер, сыр блю чиз, моцарелла', 'krevetki.jpg',
        0);

INSERT INTO product_group(id, name, description, photo_name, type)
VALUES (5, 'Креветки по-азиатски', 'Черный кунжут, шампиньоны, креветки, моцарелла, кисло-сладкий соус',
        'chetyre_syra.jpg',
        0);

INSERT INTO product_group(id, name, description, photo_name, type)
VALUES (6, 'Пепперони Фреш с перцем', 'Сладкий перец, пепперони, моцарелла, томатный соус', 'pepperony.jpeg',
        0);

INSERT INTO product(id, price, weight, product_group_id)
VALUES (1, 1000, 244, 1);

INSERT INTO product(id, price, weight, product_group_id)
VALUES (6, 1000, 244, 2);

INSERT INTO product(id, price, weight, product_group_id)
VALUES (2, 3423, 244, 3);

INSERT INTO product(id, price, weight, product_group_id)
VALUES (3, 1120, 244, 4);

INSERT INTO product(id, price, weight, product_group_id)
VALUES (4, 1820, 244, 5);

INSERT INTO product(id, price, weight, product_group_id)
VALUES (5, 1110, 244, 6);

