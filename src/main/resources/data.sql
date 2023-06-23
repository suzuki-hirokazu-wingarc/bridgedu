insert into milestones (username, title, memo, importance, achievement, goal, created, modified) values
('admin', '5月テスト', '8割', 1, 50, '2023-5-20', '2023-4-4', '2023-4-4'),
('admin', '模試', 'A判定', 5, 0, '2023-9-20', '2023-4-4', '2023-4-4'),
('admin', '受験', '合格', 10, 0, '2024-3-3', '2023-4-4', '2023-4-4');

INSERT INTO USERS (NAME, display_name, PASSWORD, ENABLED) VALUES
('admin', '管理者', '$2a$10$vC.r53zKYPwEXplBYH3mxuZP52r2u3udRcEg9yTUmwYE5yjmoUXyG', true);
INSERT INTO AUTHORITIES (USERNAME, AUTHORITY) VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO AUTHORITIES (USERNAME, AUTHORITY) VALUES ('admin', 'ROLE_TEACHER');
INSERT INTO AUTHORITIES (USERNAME, AUTHORITY) VALUES ('admin', 'ROLE_STUDENT');