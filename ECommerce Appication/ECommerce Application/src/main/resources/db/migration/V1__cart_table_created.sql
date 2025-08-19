create table carts
(
    id   binary(16) default (uuid_to_bin(uuid())) not null
        primary key,
    date date       default (curdate())           not null
);
