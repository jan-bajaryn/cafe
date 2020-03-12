use pizzeria_spring;

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

INSERT INTO product(id, price, weight, product_group_id)
VALUES (18, 1212, 500, 1);
INSERT INTO product(id, price, weight, product_group_id)
VALUES (7, 1212, 500, 2);
INSERT INTO product(id, price, weight, product_group_id)
VALUES (8, 3333, 500, 3);
INSERT INTO product(id, price, weight, product_group_id)
VALUES (9, 3355, 500, 4);
INSERT INTO product(id, price, weight, product_group_id)
VALUES (10, 2211, 500, 5);
INSERT INTO product(id, price, weight, product_group_id)
VALUES (11, 2222, 500, 6);

INSERT INTO product(id, price, weight, product_group_id)
VALUES (12, 2020, 700, 1);
INSERT INTO product(id, price, weight, product_group_id)
VALUES (13, 2020, 700, 2);
INSERT INTO product(id, price, weight, product_group_id)
VALUES (14, 4444, 700, 3);
INSERT INTO product(id, price, weight, product_group_id)
VALUES (15, 4444, 700, 4);
INSERT INTO product(id, price, weight, product_group_id)
VALUES (16, 3030, 700, 5);
INSERT INTO product(id, price, weight, product_group_id)
VALUES (17, 3030, 700, 6);

# INSERT INTO delivery_inf(id, client_name, delivery_time, email, floor, house, phone, porch, room, street)
# VALUES (1, 'Andrey', now(), 'aaa@gmail.com', 2, 4, '+375 29 444 44 44', 1, 24, 'Lenina');

# INSERT INTO `order` (id, creation, payment_type, price, status, delivery_inf_id)
# VALUES (1, now(), 1, 2000, 1, 1);

# INSERT INTO order_products (order_id, products_id)
# VALUES (1, 1);
# INSERT INTO order_products (order_id, products_id)
# VALUES (1, 3);
# INSERT INTO order_products (order_id, products_id)
# VALUES (1, 2);






# INSERT INTO `delivery_inf` (`id`, `client_name`, `delivery_time`, `email`, `floor`, `house`, `phone`, `porch`, `room`,
#                             `street`, `comments`)
# VALUES (1, 'Andreyaaa', '2020-03-11 19:07:32.000000', 'aaa@gmail.com', '2', '4', '+375 29 111 11 11', '1', '24',
#         'Leninaff', ''),
#        (2, 'Анастасия', NULL, 'anastasiaSemenova@mail.ru', '3', '43', '+375 444 44 11', '5', '33', 'Семенова',
#         ''),
#        (4, 'Андрей', NULL, 'jjj@gmail.com', '3', '3', '+375 29 456 45 45', '1', '23', 'Курочкина', '');
#
# INSERT INTO `order` (`id`, `creation`, `payment_type`, `price`, `status`, `delivery_inf_id`)
# VALUES (1, '2020-03-11 00:00:00', 0, 2002, 4, 1),
#        (3, NULL, 0, 0, 0, 2),
#        (5, NULL, 0, 10005, 0, 4);


# INSERT INTO `order_products` (`order_id`, `products_id`)
# VALUES (1, 1),
#        (1, 3),
#        (1, 2),
#        (5, 8),
#        (5, 8),
#        (5, 8);