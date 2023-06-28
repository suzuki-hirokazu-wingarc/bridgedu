insert into milestones (username, title, memo, importance, achievement, goal, created, modified) values
('student1', '5月テスト', '8割', 3, 5, '2023-5-20', '2023-4-4', '2023-4-4'),
('student1', '模試', 'A判定', 4, 5, '2023-9-20', '2023-4-4', '2023-4-4'),
('student1', '受験', '合格', 5, 0, '2024-3-3', '2023-4-4', '2023-4-4');

INSERT INTO USERS (NAME, display_name, PASSWORD, ENABLED) VALUES
('admin', '管理者', '$2a$10$vC.r53zKYPwEXplBYH3mxuZP52r2u3udRcEg9yTUmwYE5yjmoUXyG', true);
INSERT INTO AUTHORITIES (USERNAME, AUTHORITY) VALUES ('admin', 'ROLE_ADMIN');
-- INSERT INTO AUTHORITIES (USERNAME, AUTHORITY) VALUES ('admin', 'ROLE_TEACHER');
-- INSERT INTO AUTHORITIES (USERNAME, AUTHORITY) VALUES ('admin', 'ROLE_STUDENT');

INSERT INTO USERS (NAME, display_name, PASSWORD, ENABLED) VALUES
('teacher1', '教師1', '$2a$10$vC.r53zKYPwEXplBYH3mxuZP52r2u3udRcEg9yTUmwYE5yjmoUXyG', true);
INSERT INTO AUTHORITIES (USERNAME, AUTHORITY) VALUES ('teacher1', 'ROLE_TEACHER');
-- INSERT INTO AUTHORITIES (USERNAME, AUTHORITY) VALUES ('teacher1', 'ROLE_STUDENT');

INSERT INTO USERS (NAME, display_name, PASSWORD, ENABLED) VALUES
('teacher2', '教師2', '$2a$10$vC.r53zKYPwEXplBYH3mxuZP52r2u3udRcEg9yTUmwYE5yjmoUXyG', true);
INSERT INTO AUTHORITIES (USERNAME, AUTHORITY) VALUES ('teacher2', 'ROLE_TEACHER');

INSERT INTO USERS (NAME, display_name, PASSWORD, ENABLED) VALUES
('student1', '学生1', '$2a$10$vC.r53zKYPwEXplBYH3mxuZP52r2u3udRcEg9yTUmwYE5yjmoUXyG', true);
INSERT INTO AUTHORITIES (USERNAME, AUTHORITY) VALUES ('student1', 'ROLE_STUDENT');

INSERT INTO USERS (NAME, display_name, PASSWORD, ENABLED) VALUES
('student2', '学生2', '$2a$10$vC.r53zKYPwEXplBYH3mxuZP52r2u3udRcEg9yTUmwYE5yjmoUXyG', true);
INSERT INTO AUTHORITIES (USERNAME, AUTHORITY) VALUES ('student2', 'ROLE_STUDENT');


INSERT INTO TAGS(name, color) values
('default', 'transparent'),
('国語', '#f5b7b1'),
('数学', '#85c1e9'),
('理科', '#82e0aa'),
('社会', '#f5cba7'),
('英語', '#e6aae4');

INSERT INTO TASKS(milestone_id, name, importance, achievement, tag_id) values
(1, 'def', 1, 0, 1),
(1, 'jap', 1, 0, 2),
(1, 'mat', 1, 0, 3),
(1, 'sci', 1, 0, 4),
(1, 'soc', 1, 0, 5),
(1, 'eng', 1, 0, 6);

INSERT INTO COMMENTS(milestone_id, username, body, created, enabled) values
(1, 'admin', 'いいね！', '2000-1-1', true),
(1, 'admin', 'よくないね！', '2000-1-1', true),
(1, 'admin', 'owata', '2000-1-1', false);
