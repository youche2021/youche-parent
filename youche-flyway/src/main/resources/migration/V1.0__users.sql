DROP TABLE IF EXISTS t_base_user;
-- auto-generated definition
CREATE TABLE t_base_user
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    user_name   VARCHAR(50) NULL COMMENT '姓名',
    create_time DATETIME    NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

