create table user_tags
(
    user_id bigint auto_increment,
    tag_id  bigint,
    constraint user_tags_pk
        primary key (user_id, tag_id),
    constraint user_tags_user_id_fk
        foreign key (user_id) references user (id),
    constraint user_tags_tag_id_fk
        foreign key (tag_id) references tags (id)
);

alter table user_tags
    modify tag_id bigint auto_increment;

create table tags
(
    id   bigint auto_increment
        primary key,
    name varchar(255) not null
);
