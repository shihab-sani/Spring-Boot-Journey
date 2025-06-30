create table profile
(
    id            bigint,
    bio           varchar(255)   not null,
    phone_number  bigint         null,
    date_of_birth varchar(255)   null,
    loyalty_point int default 10 null,
    constraint profile_user_id_fk
        foreign key (id) references user (id)
);

alter table profile
    modify id bigint auto_increment;

