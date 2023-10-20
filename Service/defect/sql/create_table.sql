# 数据库初始化

-- 创建库
create database if not exists DEFECT;

-- 切换库
use DEFECT;

-- 用户表
create table if not exists user
(
    id           bigint auto_increment comment 'id' primary key,
    account  varchar(256)                           not null comment '账号',
    password varchar(512)                           not null comment '密码',
    role     varchar(64) default 'user'            not null comment '用户角色：user/admin',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
) comment '用户' collate = utf8mb4_unicode_ci;

-- 项目表
create table if not exists project
(
    id           bigint auto_increment comment 'id' primary key,
    projectName  varchar(256)                           not null comment '项目名称'
) comment '项目' collate = utf8mb4_unicode_ci;

-- 缺陷表
create table if not exists defect_info
(
    id                 bigint auto_increment comment 'id' primary key,
    defectName         varchar(512)                    not null comment '缺陷名称',
    defectStatus       varchar(64)                     not null comment '缺陷状态',
    defectDetail       varchar(512)                    null comment '缺陷详情',
    defectType         varchar(64)                     not null comment '缺陷类型：功能未实现 通用异常未处理 界面优化 安全相关 性能问题 兼容性',
    defectLevel        varchar(64)                     not null comment '缺陷等级：Low Medium High Critical',
    userId             bigint                          not null comment '缺陷所有者id',
    projectId          bigint                          not null comment '缺陷对应项目id',
    defectComment      longtext                        null comment '缺陷注释',
    createTime datetime default CURRENT_TIMESTAMP not null comment '缺陷创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '缺陷更新时间',
    FOREIGN KEY (userId) REFERENCES user(id),     -- userId 列作为外键，引用 user 表的 id 列
    FOREIGN KEY (projectId) REFERENCES project(id) -- projectId 列作为外键，引用 project 表的 id 列
) comment '缺陷' collate = utf8mb4_unicode_ci;
