/* 游戏表 */
create table if not exists game(
    id integer not null      constraint tb_deploy_pk         primary key autoincrement,
    game_name varchar not null, /* 游戏名称 */
    chinese_name varchar not null , /* 中文名称 */
    picture_path varchar not null, /* 图片 */
    disk_path varchar not null, /* 图片 */
    platform integer not null, /* 平台 */
    year integer not null, /* 年份 */
    played_time varchar not null, /* 游戏时长 */
    stars double not null, /* 个人评分 */
    create_time datetime /* 创建时间 */
);
drop index if exists tb_deploy_id_uindex;
create unique index tb_deploy_id_uindex on game (id);
create unique index tb_deploy_game_name_uindex on game (game_name);
