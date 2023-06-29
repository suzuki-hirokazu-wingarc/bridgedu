insert into milestones (username, title, memo, importance, achievement, goal, created, modified, enabled) values
('student1', '5月テスト', '8割', 3, 5, '2023-5-20', '2023-4-4', '2023-4-4', true),
('student1', '模試', 'A判定', 4, 5, '2023-9-20', '2023-4-4', '2023-4-4', true),
('student1', '受験', '合格', 5, 0, '2024-3-3', '2023-4-4', '2023-4-4', true),
('student1', '検閲済み', '検閲済み', 5, 0, '2024-3-3', '2023-4-4', '2023-4-4', false);

INSERT INTO USERS (NAME, display_name, PASSWORD, ENABLED, icon_id) VALUES
('admin', '管理者', '$2a$10$vC.r53zKYPwEXplBYH3mxuZP52r2u3udRcEg9yTUmwYE5yjmoUXyG', true, 1);
INSERT INTO AUTHORITIES (USERNAME, ROLE) VALUES ('admin', 'ROLE_ADMIN');
-- INSERT INTO AUTHORITIES (USERNAME, ROLE) VALUES ('admin', 'ROLE_TEACHER');
-- INSERT INTO AUTHORITIES (USERNAME, ROLE) VALUES ('admin', 'ROLE_STUDENT');

INSERT INTO USERS (NAME, display_name, PASSWORD, ENABLED, icon_id) VALUES
('teacher1', '教師1', '$2a$10$vC.r53zKYPwEXplBYH3mxuZP52r2u3udRcEg9yTUmwYE5yjmoUXyG', true, 1);
INSERT INTO AUTHORITIES (USERNAME, ROLE) VALUES ('teacher1', 'ROLE_TEACHER');
-- INSERT INTO AUTHORITIES (USERNAME, ROLE) VALUES ('teacher1', 'ROLE_STUDENT');

INSERT INTO USERS (NAME, display_name, PASSWORD, ENABLED, icon_id) VALUES
('teacher2', '教師2', '$2a$10$vC.r53zKYPwEXplBYH3mxuZP52r2u3udRcEg9yTUmwYE5yjmoUXyG', true, 1);
INSERT INTO AUTHORITIES (USERNAME, ROLE) VALUES ('teacher2', 'ROLE_TEACHER');

INSERT INTO USERS (NAME, display_name, PASSWORD, ENABLED, icon_id) VALUES
('student1', '学生1', '$2a$10$vC.r53zKYPwEXplBYH3mxuZP52r2u3udRcEg9yTUmwYE5yjmoUXyG', true, 1);
INSERT INTO AUTHORITIES (USERNAME, ROLE) VALUES ('student1', 'ROLE_STUDENT');

INSERT INTO USERS (NAME, display_name, PASSWORD, ENABLED, icon_id) VALUES
('student2', '学生2', '$2a$10$vC.r53zKYPwEXplBYH3mxuZP52r2u3udRcEg9yTUmwYE5yjmoUXyG', true, 1);
INSERT INTO AUTHORITIES (USERNAME, ROLE) VALUES ('student2', 'ROLE_STUDENT');


INSERT INTO TAGS(name, color) values
('default', 'transparent'),
('国語', '#f5b7b1'),
('数学', '#85c1e9'),
('理科', '#82e0aa'),
('社会', '#f5cba7'),
('英語', '#e6aae4');

INSERT INTO TASKS(milestone_id, name, importance, achievement, enabled, tag_id) values
(1, 'def', 1, 0, true, 1),
(1, 'jap', 1, 0, true, 2),
(1, 'mat', 1, 0, true, 3),
(1, 'sci', 1, 0, true, 4),
(1, 'soc', 1, 0, true, 5),
(1, 'eng', 1, 0, true, 6),
(1, 'non', 1, 0, false, 1);

INSERT INTO COMMENTS(milestone_id, username, body, created, enabled) values
(1, 'admin', 'いいね！', '2000-1-1', true),
(1, 'admin', 'よくないね！', '2000-1-1', true),
(1, 'admin', 'owata', '2000-1-1', false);
