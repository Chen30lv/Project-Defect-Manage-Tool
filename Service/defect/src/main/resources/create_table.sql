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

-- LOCK TABLES user WRITE;
# INSERT INTO user (id, account, password, create_time, update_time)
# VALUES (1, 'DING', md5("123456"), '2023-10-22 16:41:44', '2023-10-22 16:41:44');
--
-- INSERT INTO user (id, account, password, create_time, update_time)
-- VALUES (2, 'WANG', 123456, '2023-10-23 16:41:44', '2023-10-23 16:41:44');
-- UNLOCK TABLES;


-- 项目表
DROP TABLE IF EXISTS project;
CREATE TABLE project(
    id            bigint                         auto_increment comment 'id' primary key,
    project_name  VARCHAR(256)                   not null comment '项目名称'
) comment '项目' collate = utf8mb4_unicode_ci;

-- LOCK TABLES project WRITE;
-- INSERT INTO project (id, project_name) VALUES (1, 'SOFTWARE ENGINEERING');
-- INSERT INTO project (id, project_name) VALUES (2, 'DATA ENGINEERING');
-- UNLOCK TABLES;

-- 缺陷表
DROP TABLE IF EXISTS defect_info;
CREATE TABLE defect_info(
    id                 bigint                    auto_increment comment 'id' primary key,
    defect_name        VARCHAR(512)              NOT NULL comment '缺陷名称',
    defect_status      VARCHAR(64)               NOT NULL comment '缺陷状态',
    defect_detail      VARCHAR(512)              NOT NULL comment '缺陷详情',
    is_to_do           VARCHAR(64)               NOT NULL comment '是否完成',
    defect_type        VARCHAR(64)               NOT NULL comment '缺陷类型',
    defect_level       VARCHAR(64)               NOT NULL comment '缺陷等级：Low Medium High Critical',
    user_id            bigint                    NOT NULL comment '缺陷所有者id',
    project_id         bigint                    NOT NULL comment '缺陷对应项目id',
    defect_comment     longtext                  NOT NULL comment '缺陷注释',
    create_time        datetime                  default CURRENT_TIMESTAMP NOT NULL comment '缺陷创建时间',
    update_time        datetime                  default CURRENT_TIMESTAMP NOT NULL on update CURRENT_TIMESTAMP comment '缺陷更新时间'
) comment '缺陷信息' collate = utf8mb4_unicode_ci;

-- LOCK TABLES defect_info WRITE;
-- INSERT INTO defect_info
-- VALUES (1, 'not safe', 'Open', 'there are too many software vulnerabilities', 'TODO', 'Security Defect',
--         'Low', '1', '1', 'no comment', '2023-10-23 16:41:44','2023-10-23 16:41:44');
-- INSERT INTO defect_info
-- VALUES (2, 'not beautiful', 'Open', 'The software interface is really ugly', 'TODO', 'Unit Level Defect',
--         'High', '2', '2', 'no comment', '2023-10-23 18:41:44','2023-10-23 18:41:44');
-- INSERT INTO defect_info
-- VALUES (3, 'diu lei', 'Fixed', 'lao mu', 'FINISH', 'Workflow Defect',
--         'Critical', '2', '1', 'no comment', '2023-10-23 18:54:44','2023-10-23 18:54:44');
-- INSERT INTO defect_info
-- VALUES (4, 'TEST 4', 'ReOpened', 'TEST 4', 'TODO', 'Workflow Defect',
--         'Critical', '1', '2', 'no comment', '2023-10-23 18:54:50','2023-10-23 18:54:50');
-- INSERT INTO defect_info
-- VALUES (5, 'TEST 5', 'Deffered', 'TEST 5', 'FINISH', 'System-level Integration Defect',
--         'High', '2', '2', 'no comment', '2023-10-23 18:54:51','2023-10-23 18:54:51');
-- INSERT INTO defect_info
-- VALUES (6, 'TEST 6', 'NotABug', 'TEST 6', 'FINISH', 'Functional Defect',
--         'Medium', '2', '1', 'no comment', '2023-10-23 18:54:52','2023-10-23 18:54:52');
-- INSERT INTO defect_info
-- VALUES (7, 'TEST 7', 'Open', 'TEST 7', 'TODO', 'Functional Defect',
--         'High', '2', '1', 'no comment', '2023-10-23 18:57:52','2023-10-23 18:57:52');
-- INSERT INTO defect_info
-- VALUES (8, 'TEST 8', 'Open', 'TEST 8', 'TODO', 'Functional Defect',
--         'High', '2', '1', 'no comment', '2023-10-23 19:57:52','2023-10-23 19:57:52');
-- INSERT INTO defect_info
-- VALUES (9, 'TEST 9', 'Open', 'TEST 9', 'TODO', 'Functional Defect',
--         'High', '2', '1', 'no comment', '2023-10-23 19:59:52','2023-10-23 19:59:52');
-- INSERT INTO defect_info
-- VALUES (10, 'TEST 10', 'Open', 'TEST 10', 'TODO', 'Functional Defect',
--         'Critical', '2', '1', 'no comment', '2023-10-23 19:10:52','2023-10-23 19:10:52');
-- INSERT INTO defect_info
-- VALUES (11, 'TEST 11', 'Open', 'TEST 11', 'TODO', 'Functional Defect',
--         'High', '1', '1', 'no comment', '2023-10-23 19:11:52','2023-10-23 19:11:52');
-- INSERT INTO defect_info
-- VALUES (12, 'TEST 12', 'Open', 'TEST 12', 'TODO', 'Workflow Defect',
--         'High', '1', '2', 'no comment', '2023-10-23 19:12:52','2023-10-23 19:12:52');
-- UNLOCK TABLES;
