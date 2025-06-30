create table tags
(
    id   bigint auto_increment
        primary key,
    name varchar(255) not null
);

create table user_tags
(
    user_id bigint not null,
    tag_id  bigint not null,
    constraint user_tags_pk
        primary key (user_id, tag_id),
    constraint user_tags_user_id_fk
        foreign key (user_id) references user (id),
    constraint user_tags_tag_id_fk
        foreign key (tag_id) references tags (id)
);


