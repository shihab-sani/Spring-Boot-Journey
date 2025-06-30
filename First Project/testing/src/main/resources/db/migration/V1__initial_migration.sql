create table user
(
    id       bigint auto_increment
        primary key,
    name     varchar(255) not null,
    email    varchar(255) not null,
    passward varchar(255) not null
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