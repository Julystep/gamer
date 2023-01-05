/* 游戏表 */
create table if not exists game(
    id integer not null      constraint tb_game_pk         primary key autoincrement,
    game_name varchar not null, /* 游戏名称 */
    chinese_name varchar not null , /* 中文名称 */
    picture_path varchar not null, /* 图片 */
    disk_path varchar not null, /* 图片 */
    platform integer not null, /* 平台 */
    year integer not null, /* 年份 */
    played_time varchar not null, /* 游戏时长 */
    comments varchar, /* 游戏评论，暂时不用 */
    stars double not null, /* 个人评分 */
    create_time datetime /* 创建时间 */
);
drop index if exists tb_game_id_uindex;
create unique index tb_game_id_uindex on game (id);
create unique index tb_game_game_name_uindex on game (game_name);

/* 年份表 */
create table if not exists year(
    id integer not null      constraint tb_year_pk         primary key autoincrement,
    year Integer not null /* 游戏名称 */
)
