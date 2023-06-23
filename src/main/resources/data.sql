insert into milestones (userid, title, memo, achievement, goal, created, modified) values
(1, '5月テスト', '8割', 50, '2023-5-20', '2023-4-4', '2023-4-4'),
(1, '模試', 'A判定', 0, '2023-9-20', '2023-4-4', '2023-4-4'),
(1, '受験', '合格', 0, '2024-3-3', '2023-4-4', '2023-4-4');

INSERT INTO USERS (USERNAME, PASSWORD, ENABLED) VALUES
('admin', '{bcrypt}$2a$10$vC.r53zKYPwEXplBYH3mxuZP52r2u3udRcEg9yTUmwYE5yjmoUXyG', true);
INSERT INTO AUTHORITIES (USERNAME, AUTHORITY) VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO AUTHORITIES (USERNAME, AUTHORITY) VALUES ('admin', 'ROLE_USER');