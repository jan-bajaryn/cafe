use pizzeria_spring;
create table delivery_inf
(
    id            bigint not null,
    client_name   varchar(255),
    comments      varchar(255),
    delivery_time datetime(6),
    email         varchar(255),
    floor         varchar(255),
    house         varchar(255),
    phone         varchar(255),
    porch         varchar(255),
    room          varchar(255),
    street        varchar(255),
#     `order_id`    bigint,
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

create table order_products
(
    order_id    bigint not null,
    products_id bigint not null
) engine = InnoDB;
# create table order_product
# (
#     id         bigint not null,
#     `order_id` bigint,
#     product_id bigint,
#     primary key (id)
# ) engine = InnoDB;

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
#     address  varchar(255),
    creation datetime(6),
    name     varchar(255),
    password varchar(255),
    phone    varchar(255),
    role     integer,
    surname  varchar(255),
    username varchar(255),
    primary key (id)
) engine = InnoDB;