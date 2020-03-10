create table `product_orders`
(
    product_id  bigint not null,
    `orders_id` bigint not null
) engine = InnoDB;
create table delivery_inf
(
    id            bigint not null,
    client_name   varchar(255),
    delivery_time datetime(6),
    email         varchar(255),
    floor         varchar(255),
    house         varchar(255),
    phone         varchar(255),
    porch         varchar(255),
    room          varchar(255),
    street        varchar(255),
    primary key (id)
) engine = InnoDB;
create table hibernate_sequence
(
    next_val bigint
) engine = InnoDB;
insert into hibernate_sequence
values (1);
insert into hibernate_sequence
values (1);
insert into hibernate_sequence
values (1);
insert into hibernate_sequence
values (1);
insert into hibernate_sequence
values (1);
insert into hibernate_sequence
values (1);
create table `order`
(
    id              bigint not null,
    creation        date,
    payment_type    integer,
    price           integer,
    status          integer,
    delivery_inf_id bigint,
    primary key (id)
) engine = InnoDB;
create table `order_products`
(
    `order_id`  bigint not null,
    products_id bigint not null
) engine = InnoDB;
create table order_product
(
    id         bigint not null,
    `order_id` bigint,
    product_id bigint,
    primary key (id)
) engine = InnoDB;
create table product
(
    id               bigint not null,
    price            integer,
    weight           integer,
    product_group_id bigint,
    primary key (id)
) engine = InnoDB;
create table product_group
(
    id          bigint not null,
    description varchar(255),
    name        varchar(255),
    photo_name  varchar(255),
    type        integer,
    primary key (id)
) engine = InnoDB;
create table product_group_products
(
    product_group_id bigint not null,
    products_id      bigint not null,
    primary key (product_group_id, products_id)
) engine = InnoDB;
create table user
(
    id       bigint not null,
    address  varchar(255),
    creation date,
    name     varchar(255),
    password varchar(255),
    phone    varchar(255),
    role     integer,
    surname  varchar(255),
    username varchar(255),
    primary key (id)
) engine = InnoDB;
alter table product
    drop index UKrq12cvslj69k7uccupx2bl9wm;
alter table product
    add constraint UKrq12cvslj69k7uccupx2bl9wm unique (product_group_id, weight);
alter table product_group_products
    drop index UK_1fuixgtvfecpilpb2flxemsrp;
alter table product_group_products
    add constraint UK_1fuixgtvfecpilpb2flxemsrp unique (products_id);
alter table `product_orders`
    add constraint FK6c5t5anxgvov36dyto4w07fc9 foreign key (`orders_id`) references `order` (id);
alter table `product_orders`
    add constraint FKasacg2pohulc55ib5ok1mjil6 foreign key (product_id) references product (id);
alter table `order`
    add constraint FKg6o2fxcfbc1d3txvja7agwf2i foreign key (delivery_inf_id) references delivery_inf (id);
alter table `order_products`
    add constraint FK9wnm7ngwoe4uk544gg9gt9o5c foreign key (products_id) references product (id);
alter table `order_products`
    add constraint FK633bum8bdtjbbsld4ef061y64 foreign key (`order_id`) references `order` (id);
alter table order_product
    add constraint FK9vc18aep0mt7lfhp1qnftfmqb foreign key (`order_id`) references `order` (id);
alter table order_product
    add constraint FKhnfgqyjx3i80qoymrssls3kno foreign key (product_id) references product (id);
alter table product
    add constraint FKd1puiblqvkggoc63q7c3ux5x6 foreign key (product_group_id) references product_group (id);
alter table product_group_products
    add constraint FKbj3geeqynd1hio5k4t8e9kbtb foreign key (products_id) references product (id);
alter table product_group_products
    add constraint FKigwej49rdus46psp1v7phtl3s foreign key (product_group_id) references product_group (id);