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
VALUES (1, 'dy', md5("12345678"), '2023-10-22 16:41:44', '2023-10-22 16:41:44');
INSERT INTO user (id, account, password, create_time, update_time)
VALUES (2, 'wyc', md5("12345678"), '2023-10-23 16:41:44', '2023-10-23 16:41:44');
# INSERT INTO user (id, account, password, create_time, update_time)
# VALUES (3, 'wjh', md5("12345678"), '2023-10-23 16:41:44', '2023-10-23 16:41:44');
# INSERT INTO user (id, account, password, create_time, update_time)
# VALUES (4, 'lgc', md5("12345678"), '2023-10-23 16:41:44', '2023-10-23 16:41:44');
# INSERT INTO user (id, account, password, create_time, update_time)
# VALUES (5, 'ljy', md5("12345678"), '2023-10-23 16:41:44', '2023-10-23 16:41:44');
# INSERT INTO user (id, account, password, create_time, update_time)
# VALUES (6, 'hyg', md5("12345678"), '2023-10-23 16:41:44', '2023-10-23 16:41:44');
UNLOCK TABLES;


-- 项目表
DROP TABLE IF EXISTS project;
CREATE TABLE project(
    id            bigint                         auto_increment comment 'id' primary key,
    project_name  VARCHAR(256)                   not null comment '项目名称'
) comment '项目' collate = utf8mb4_unicode_ci;

LOCK TABLES project WRITE;
INSERT INTO project (id, project_name) VALUES (1, 'Search Engine');
INSERT INTO project (id, project_name) VALUES (2, 'Shopping Mall');
INSERT INTO project (id, project_name) VALUES (3, 'Deliver System');
INSERT INTO project (id, project_name) VALUES (4, 'VSCode Plugin');
INSERT INTO project (id, project_name) VALUES (5, 'Chat Room');
UNLOCK TABLES;

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

LOCK TABLES defect_info WRITE;
INSERT INTO defect_info
VALUES (1, 'SQL Injection Vulnerability', 'Open', 'The application fails to properly sanitize user input, allowing malicious SQL statements to be executed, leading to unauthorized access or data manipulation', 'TODO', 'Security Defect',
        'Low', '1', '1', 'fix it tomorrow => almost done', '2023-10-23 16:41:44','2023-10-23 16:41:44');
INSERT INTO defect_info
VALUES (2, 'Cross-Site Scripting (XSS) Vulnerability', 'Open', 'The application does not properly validate and sanitize user-supplied input, allowing the execution of malicious scripts in a victim''s browser, leading to session hijacking or information theft', 'TODO', 'Security Defect',
        'High', '1', '1', 'fix it today', '2023-10-23 18:41:44','2023-10-23 18:41:44');
INSERT INTO defect_info
VALUES (3, 'Incorrect Approval Routing', 'Fixed', 'The workflow system is configured with incorrect approval routing, causing approval requests to be sent to the wrong individuals or departments, leading to delays or improper decision-making.', 'FINISHED', 'Workflow Defect',
        'Critical', '1', '2', 'done', '2023-10-23 18:54:44','2023-10-23 18:54:44');
INSERT INTO defect_info
VALUES (4, 'Missing or Incomplete Workflow Steps', 'ReOpened', 'The defined workflow process does not include all the necessary steps or actions required for proper task completion, leading to incomplete or inconsistent workflows.', 'TODO', 'Workflow Defect',
        'Critical', '1', '4', 'done => still need to be fixed', '2023-10-23 18:54:50','2023-10-23 18:54:50');
INSERT INTO defect_info
VALUES (5, 'Data Inconsistencies between Integrated Systems', 'Deferred', 'The integrated systems fail to synchronize and maintain consistent data, resulting in discrepancies and conflicts when accessing or updating information across different systems.', 'FINISHED', 'System-level Integration Defect',
        'High', '1', '5', 'done', '2023-10-23 18:54:51','2023-10-23 18:54:51');
INSERT INTO defect_info
VALUES (6, 'Incorrect Calculation of Total', 'NotABug', 'The system performs incorrect calculations when determining the total value, resulting in inaccurate totals for quantities, prices, or financial calculations', 'FINISHED', 'Functional Defect',
        'Medium', '1', '2', 'I am right', '2023-10-23 18:54:52','2023-10-23 18:54:52');
INSERT INTO defect_info
VALUES (7, 'Search Function Returns Incomplete Results', 'Open', 'The search function fails to retrieve and display all relevant results, omitting certain records or providing incomplete search results, leading to user frustration and information gaps.', 'TODO', 'Functional Defect',
        'High', '1', '1', '', '2023-10-23 18:57:52','2023-10-23 18:57:52');
INSERT INTO defect_info
VALUES (8, 'Incorrect Handling of User Input', 'Open', 'The system does not properly validate or handle user input, allowing invalid or unexpected inputs to cause errors, crashes, or incorrect behavior in the application.', 'TODO', 'Functional Defect',
        'High', '1', '3', '', '2023-10-23 19:57:52','2023-10-23 19:57:52');
INSERT INTO defect_info
VALUES (9, 'Inconsistent Behavior Across Platforms', 'Open', 'The application exhibits inconsistent behavior or functionality when accessed from different platforms or devices, leading to user confusion and a lack of uniform user experience.', 'TODO', 'Functional Defect',
        'High', '1', '4', '', '2023-10-23 19:59:52','2023-10-23 19:59:52');
INSERT INTO defect_info
VALUES (10, 'Missing or Incomplete Error Handling', 'Open', 'The system lacks proper error handling mechanisms, resulting in unhandled exceptions, error messages that do not provide meaningful guidance, or system crashes when errors occur.', 'TODO', 'Functional Defect',
        'Critical', '1', '5', 'no comment', '2023-10-23 19:10:52','2023-10-23 19:10:52');
INSERT INTO defect_info
VALUES (11, 'Incorrect Notification Triggers', 'Open', 'The workflow system is triggering notifications at incorrect or inappropriate stages of the workflow, resulting in unnecessary or missed notifications, which can impact communication and decision-making.', 'TODO', 'Workflow Defect',
        'High', '1', '1', 'no comment', '2023-10-23 19:11:52','2023-10-23 19:11:52');
INSERT INTO defect_info
VALUES (12, 'Insecure File Upload', 'Open', 'The application does not properly validate or restrict the file types and content during the file upload process, enabling attackers to upload malicious files that can compromise the system.', 'TODO', 'Security Defect',
        'High', '1', '2', 'no comment', '2023-10-23 19:12:52','2023-10-23 19:12:52');
INSERT INTO defect_info
VALUES (13, 'Null Pointer Exception', 'Open', 'The code attempts to access or manipulate a null object reference, resulting in a runtime exception and potential program termination', 'TODO', 'Unit Level Defect',
        'High', '1', '1', 'no comment', '2023-10-23 19:11:52','2023-10-23 19:11:52');
INSERT INTO defect_info
VALUES (14, 'Off-by-One Error', 'Open', 'The code incorrectly increments or decrements a loop or index variable, resulting in incorrect array or collection access, and leading to unexpected behavior or incorrect results.', 'TODO', 'Unit Level Defect',
        'High', '1', '1', 'no comment', '2023-10-23 19:11:52','2023-10-23 19:11:52');
INSERT INTO defect_info
VALUES (15, 'Memory Leak', 'Open', 'The code fails to release dynamically allocated memory after it is no longer needed, leading to a gradual depletion of available memory resources and potential system instability or crashes.', 'TODO', 'Unit Level Defect',
        'High', '1', '1', 'no comment', '2023-10-23 19:11:52','2023-10-23 19:11:52');

UNLOCK TABLES;
