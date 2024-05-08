create database virtual_cinema;

use virtual_cinema;

create table file
(
    id             int auto_increment,
    uuid           varchar(128)  default ''                not null
        primary key,
    file_name      varchar(1024) default ''                not null,
    parent_folder  varchar(128)  default ''                not null,
    type           varchar(32)   default ''                not null,
    md5            varchar(64)   default ''                not null,
    file_size      varchar(1024) default ''                not null,
    info           varchar(2048) default ''                not null,
    absolute_path  varchar(4096) default ''                not null,
    create_time    datetime      default CURRENT_TIMESTAMP not null,
    last_edit_time datetime      default CURRENT_TIMESTAMP not null,
    constraint collection_uuid_uindex
        unique (uuid),
    constraint file_id_uindex
        unique (id)
)
    engine = InnoDB;

create table video_info
(
    id            int auto_increment,
    uuid          varchar(128)            not null
        primary key,
    video_uuid    varchar(128) default '' not null,
    cover_uuid    varchar(128) default '' not null,
    subtitle_uuid varchar(128) default '' not null,
    info          varchar(128) default '' not null,
    constraint info_link_id_uindex
        unique (id),
    constraint uuid
        unique (uuid),
    constraint info_link_ibfk_1
        foreign key (video_uuid) references file (uuid)
)
    engine = InnoDB;

create table tag
(
    id          int auto_increment,
    uuid        varchar(128)                           not null
        primary key,
    tag_name    varchar(128)                           not null,
    info        varchar(128) default ''                not null,
    create_time datetime     default CURRENT_TIMESTAMP not null,
    constraint tag_id_uindex
        unique (id),
    constraint tag_name
        unique (tag_name),
    constraint uuid
        unique (uuid)
)
    engine = InnoDB;

create table video_view
(
    id          int auto_increment,
    uuid        varchar(128)                           not null
        primary key,
    path_uuid   varchar(128) default ''                not null,
    info        varchar(128) default ''                not null,
    clicks      int          default 0                 not null,
    create_time datetime     default CURRENT_TIMESTAMP not null,
    constraint video_view_id_uindex
        unique (id),
    constraint uuid
        unique (uuid),
    constraint video_view_ibfk_1
        foreign key (path_uuid) references file (uuid)
            on update cascade on delete cascade
)
    engine = InnoDB;

create table tag_map
(
    id              int auto_increment,
    uuid            varchar(128)                           not null
        primary key,
    view_uuid varchar(128) default ''                not null,
    tag_uuid        varchar(128) default ''                not null,
    create_time     datetime     default CURRENT_TIMESTAMP not null,
    constraint tag_map_id_uindex
        unique (id),
    constraint uuid
        unique (uuid),
    constraint tag_map_ibfk_1
        foreign key (view_uuid) references video_view (uuid),
    constraint tag_map_ibfk_2
        foreign key (tag_uuid) references tag (uuid)
)
    engine = InnoDB;

create table comic_view
(
    id          int auto_increment,
    uuid        varchar(128)                       not null
        primary key,
    path_uuid   varchar(128)                       not null,
    info        varchar(128)                       not null,
    clicks      int      default 0                 not null,
    create_time datetime default CURRENT_TIMESTAMP not null,
    constraint id
        unique (id),
    constraint uuid
        unique (uuid),
    constraint comic_view_ibfk_1
        foreign key (path_uuid) references file (uuid)
            on update cascade on delete cascade
)
    engine = InnoDB;

create index path_uuid
    on comic_view (path_uuid);

create table model
(
    uuid        varchar(128)                          not null
        primary key,
    type        varchar(20) default ''                not null,
    file_name   varchar(128)                          not null,
    create_time datetime    default CURRENT_TIMESTAMP null
)
    engine = InnoDB;

create table user
(
    id            int auto_increment,
    uuid          varchar(128)                           not null
        primary key,
    username      varchar(16)                            not null,
    password      varchar(128) default ''                not null,
    salt          varchar(8)   default ''                not null,
    profile_photo varchar(128) default ''                not null,
    email         varchar(64)  default ''                not null,
    sex_type      int          default 0                 not null,
    info          varchar(128) default ''                not null,
    create_time   datetime     default CURRENT_TIMESTAMP not null,
    constraint user_id_uindex
        unique (id),
    constraint username
        unique (username),
    constraint uuid
        unique (uuid)
)
    engine = InnoDB;

create table collection
(
    id          int auto_increment,
    uuid        varchar(128)                           not null
        primary key,
    user_uuid   varchar(128)                           not null,
    view_uuid   varchar(128) default ''                not null,
    create_time datetime     default CURRENT_TIMESTAMP not null,
    constraint collect_id_uindex
        unique (id),
    constraint uuid
        unique (uuid),
    constraint collection_ibfk_1
        foreign key (user_uuid) references user (uuid),
    constraint collection_ibfk_2
        foreign key (view_uuid) references video_view (uuid)
)
    engine = InnoDB;

create index user_uuid
    on collection (user_uuid);

create index view_uuid
    on collection (view_uuid);

create table player_model
(
    uuid        varchar(128)                       not null
        primary key,
    user_uuid   varchar(128)                       not null,
    model_uuid  varchar(128)                       not null,
    create_time datetime default CURRENT_TIMESTAMP null,
    constraint player_model_model_uuid_fk
        foreign key (model_uuid) references model (uuid),
    constraint player_model_user_uuid_fk
        foreign key (user_uuid) references user (uuid)
)
    engine = InnoDB;