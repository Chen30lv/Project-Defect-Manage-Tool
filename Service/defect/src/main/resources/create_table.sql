# 数据库初始化

-- 创建库
create database if not exists DEFECT;

-- 切换库
use DEFECT;

-- 用户表
DROP TABLE IF EXISTS user;
CREATE TABLE user (
    id           bigint                          auto_increment comment 'id' primary key,
    account      VARCHAR(256)                    NOT NULL comment '账号',
    password     VARCHAR(512)                    NOT NULL comment '密码',
    create_time  datetime                        default CURRENT_TIMESTAMP NOT NULL comment '创建时间',
    update_time  datetime                        default CURRENT_TIMESTAMP NOT NULL on update CURRENT_TIMESTAMP comment '更新时间'
) comment '用户' collate = utf8mb4_unicode_ci;

LOCK TABLES user WRITE;
INSERT INTO user (id, account, password, create_time, update_time)
VALUES (1, 'DING', 123456, '2023-10-22 16:41:44', '2023-10-22 16:41:44');

INSERT INTO user (id, account, password, create_time, update_time)
VALUES (2, 'WANG', 123456, '2023-10-23 16:41:44', '2023-10-23 16:41:44');
UNLOCK TABLES;


-- 项目表
DROP TABLE IF EXISTS project;
CREATE TABLE project(
    id            bigint                         auto_increment comment 'id' primary key,
    project_name  VARCHAR(256)                   not null comment '项目名称'
) comment '项目' collate = utf8mb4_unicode_ci;

LOCK TABLES project WRITE;
INSERT INTO project (id, project_name) VALUES (1, 'SOFTWARE ENGINEERING');
INSERT INTO project (id, project_name) VALUES (2, 'DATA ENGINEERING');
UNLOCK TABLES;

-- 缺陷表
DROP TABLE IF EXISTS defect_info;
CREATE TABLE defect_info(
    id                 bigint                    auto_increment comment 'id' primary key,
    defect_name        VARCHAR(512)              NOT NULL comment '缺陷名称',
    defect_status      VARCHAR(64)               NOT NULL comment '缺陷状态',
    defect_detail      VARCHAR(512)              NOT NULL comment '缺陷详情',
    is_to_do           VARCHAR(64)               NOT NULL comment '是否完成',
    defect_type        VARCHAR(64)               NOT NULL comment '缺陷类型：1-功能未实现 2-通用异常未处理 3-界面优化 4-安全相关 5-性能问题 6-兼容性',
    defect_level       VARCHAR(64)               NOT NULL comment '缺陷等级：Low Medium High Critical',
    user_id            bigint                    NOT NULL comment '缺陷所有者id',
    project_id         bigint                    NOT NULL comment '缺陷对应项目id',
    defect_comment     longtext                  NOT NULL comment '缺陷注释',
    create_time        datetime                  default CURRENT_TIMESTAMP NOT NULL comment '缺陷创建时间',
    update_time        datetime                  default CURRENT_TIMESTAMP NOT NULL on update CURRENT_TIMESTAMP comment '缺陷更新时间'
) comment '缺陷信息' collate = utf8mb4_unicode_ci;

LOCK TABLES defect_info WRITE;
INSERT INTO defect_info
VALUES (1, 'not safe', 'open', 'there are too many software vulnerabilities', 'todo', '1',
        'Low', '1', '1', 'no comment', '2023-10-23 16:41:44','2023-10-23 16:41:44');
INSERT INTO defect_info
VALUES (2, 'not beautiful', 'open', 'The software interface is really ugly', 'todo', '3',
        'High', '2', '2', 'no comment', '2023-10-23 18:41:44','2023-10-23 18:41:44');
INSERT INTO defect_info
VALUES (3, 'diu lei', 'solved', 'lao mu', 'finish', '5',
        'Critical', '2', '1', 'no comment', '2023-10-23 18:54:44','2023-10-23 18:54:44');
UNLOCK TABLES;
