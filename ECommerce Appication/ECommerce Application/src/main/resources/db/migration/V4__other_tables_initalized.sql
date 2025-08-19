create table user
(
    id       bigint auto_increment
        primary key,
    name     varchar(255) not null,
    email    varchar(255) not null,
    password varchar(255) not null
);

create table addresses
(
    id      bigint auto_increment
        primary key,
    street  varchar(255) not null,
    city    varchar(255) not null,
    zip     int          not null,
    user_id bigint       not null,
    constraint addresses_user_id_fk
        foreign key (user_id) references user (id)
);

create table profile
(
    id            bigint auto_increment
        primary key,
    gender        varchar(255)   not null,
    bio           varchar(255)   not null,
    phone_number  bigint         null,
    date_of_birth date   null,
    loyalty_point int default 10 null,
    constraint profile_user_id_fk
        foreign key (id) references user (id)
);

CREATE TABLE wishlist
(
    user_id     BIGINT NOT NULL,
    products_id BIGINT NOT NULL,
    CONSTRAINT pk_wishlist PRIMARY KEY (user_id, products_id)
);

ALTER TABLE wishlist
    ADD CONSTRAINT fk_wishlist_on_products FOREIGN KEY (products_id) REFERENCES products (id) on delete cascade;

ALTER TABLE wishlist
    ADD CONSTRAINT fk_wishlist_on_user FOREIGN KEY (user_id) REFERENCES user (id) on delete cascade;