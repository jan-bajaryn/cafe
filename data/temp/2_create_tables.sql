use pizzeria_spring;

create table delivery_inf
(
    id            bigint not null,
#     address       varchar(255),
    street        varchar(255),
    house         varchar(255),
    room          varchar(255),
    porch         varchar(255),
    floor         varchar(255),
    client_name   varchar(255),
    delivery_time datetime(6),
    email         varchar(255),
    phone         varchar(255),
    primary key (id)
) engine = InnoDB;

create table hibernate_sequence
(
    next_val bigint
) engine = InnoDB;

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
    add constraint uk_product_pgi_w unique (product_group_id, weight);

alter table product_group_products
    add constraint uk_product_group_products_pi unique (products_id);

alter table `order`
    add constraint fk_order_dii foreign key (delivery_inf_id) references delivery_inf (id);
alter table order_product
    add constraint fk_order_product_oi foreign key (`order_id`) references `order` (id);
alter table order_product
    add constraint fk_order_product_pi foreign key (product_id) references product (id);
alter table product
    add constraint fk_product_pgi foreign key (product_group_id) references product_group (id);
alter table product_group_products
    add constraint fk_product_group_products_pi foreign key (products_id) references product (id);
alter table product_group_products
    add constraint fk_product_group_products_pgi foreign key (product_group_id) references product_group (id);