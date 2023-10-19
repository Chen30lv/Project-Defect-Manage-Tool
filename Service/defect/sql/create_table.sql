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

-- 缺陷表
create table if not exists defectInfo
(
    id         bigint auto_increment comment 'id' primary key,
    defectName      varchar(512)                       not null comment '缺陷名称',
    defectStatus    varchar(64)                     not null comment '缺陷状态',
    defectDetail      varchar(512)                       null comment '缺陷详情',
    defectType      varchar(64)                       not null comment '缺陷类型：功能未实现-1 通用异常未处理-2 界面优化-3 安全相关-4 性能问题-5 兼容性-6',
    defectLevel      varchar(64)                      not null comment '缺陷等级：Low-1 Medium-2 High-3 Critical-4',
    defectOwner      varchar(512)                      not null comment '缺陷所有者',
    defectComment      longtext                        null comment '缺陷注释',
    createTime datetime default CURRENT_TIMESTAMP not null comment '缺陷创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '缺陷更新时间'
) comment '缺陷' collate = utf8mb4_unicode_ci;

