# Luka-R18
SpringBoot试验性项目

## MySQL表结构
~~~MySQL
create table anime_file
(
    id             int auto_increment,
    uuid           varchar(128)  default ''                not null,
    file_name      varchar(1024) default ''                not null,
    parent_folder  varchar(128)  default ''                not null,
    type           varchar(32)   default ''                not null,
    md5            varchar(64)   default ''                not null,
    file_size      varchar(1024) default ''                not null,
    info           varchar(2048) default ''                not null,
    absolute_path  varchar(4096) default ''                not null,
    create_time    datetime      default CURRENT_TIMESTAMP not null,
    last_edit_time datetime      default CURRENT_TIMESTAMP not null,
    constraint anime_collection_uuid_uindex
        unique (uuid),
    constraint anime_file_id_uindex
        unique (id)
);

alter table anime_file
    add primary key (uuid);

create table anime_info_link
(
    id            int auto_increment,
    uuid          varchar(128)            not null,
    video_uuid    varchar(128) default '' not null,
    cover_uuid    varchar(128) default '' not null,
    subtitle_uuid varchar(128) default '' not null,
    info          varchar(128) default '' not null,
    constraint anime_info_link_id_uindex
        unique (id),
    constraint uuid
        unique (uuid),
    constraint anime_info_link_ibfk_1
        foreign key (video_uuid) references anime_file (uuid)
);

alter table anime_info_link
    add primary key (uuid);

create table anime_tag
(
    id          int auto_increment,
    uuid        varchar(128)                           not null,
    tag_name    varchar(128)                           not null,
    info        varchar(128) default ''                not null,
    create_time datetime     default CURRENT_TIMESTAMP not null,
    constraint anime_tag_id_uindex
        unique (id),
    constraint tag_name
        unique (tag_name),
    constraint uuid
        unique (uuid)
);

alter table anime_tag
    add primary key (uuid);

create table anime_view
(
    id          int auto_increment,
    uuid        varchar(128)                           not null,
    path_uuid   varchar(128) default ''                not null,
    info        varchar(128) default ''                not null,
    clicks      int          default 0                 not null,
    create_time datetime     default CURRENT_TIMESTAMP not null,
    constraint anime_view_id_uindex
        unique (id),
    constraint uuid
        unique (uuid),
    constraint anime_view_ibfk_1
        foreign key (path_uuid) references anime_file (uuid)
            on update cascade on delete cascade
);

alter table anime_view
    add primary key (uuid);

create table anime_tag_map
(
    id              int auto_increment,
    uuid            varchar(128)                           not null,
    anime_view_uuid varchar(128) default ''                not null,
    tag_uuid        varchar(128) default ''                not null,
    create_time     datetime     default CURRENT_TIMESTAMP not null,
    constraint anime_tag_map_id_uindex
        unique (id),
    constraint uuid
        unique (uuid),
    constraint anime_tag_map_ibfk_1
        foreign key (anime_view_uuid) references anime_view (uuid),
    constraint anime_tag_map_ibfk_2
        foreign key (tag_uuid) references anime_tag (uuid)
);

alter table anime_tag_map
    add primary key (uuid);

create table comic_view
(
    id          int auto_increment,
    uuid        varchar(128)                       not null,
    path_uuid   varchar(128)                       not null,
    info        varchar(128)                       not null,
    clicks      int      default 0                 not null,
    create_time datetime default CURRENT_TIMESTAMP not null,
    constraint id
        unique (id),
    constraint uuid
        unique (uuid),
    constraint comic_view_ibfk_1
        foreign key (path_uuid) references anime_file (uuid)
            on update cascade on delete cascade
);

create index path_uuid
    on comic_view (path_uuid);

alter table comic_view
    add primary key (uuid);

create table user_data
(
    id            int auto_increment,
    uuid          varchar(128)                           not null,
    username      varchar(16)                            not null,
    password      varchar(128) default ''                not null,
    salt          varchar(8)   default ''                not null,
    profile_photo varchar(128) default ''                not null,
    email         varchar(64)  default ''                not null,
    phone         varchar(16)  default ''                not null,
    sex_type      int          default 0                 not null,
    address       varchar(256) default ''                not null,
    info          varchar(128) default ''                not null,
    create_time   datetime     default CURRENT_TIMESTAMP not null,
    constraint user_data_id_uindex
        unique (id),
    constraint username
        unique (username),
    constraint uuid
        unique (uuid)
);

alter table user_data
    add primary key (uuid);

create table anime_collect
(
    id          int auto_increment,
    uuid        varchar(128)                           not null,
    user_uuid   varchar(128)                           not null,
    view_uuid   varchar(128) default ''                not null,
    create_time datetime     default CURRENT_TIMESTAMP not null,
    constraint anime_collect_id_uindex
        unique (id),
    constraint uuid
        unique (uuid),
    constraint anime_collect_ibfk_1
        foreign key (user_uuid) references user_data (uuid),
    constraint anime_collect_ibfk_2
        foreign key (view_uuid) references anime_view (uuid)
);

create index user_uuid
    on anime_collect (user_uuid);

create index view_uuid
    on anime_collect (view_uuid);

alter table anime_collect
    add primary key (uuid);


~~~
