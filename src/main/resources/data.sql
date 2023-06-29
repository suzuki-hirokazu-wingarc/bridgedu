INSERT INTO USERS (NAME, display_name, PASSWORD, ENABLED, icon_id) VALUES
('admin', '管理者', '$2a$10$vC.r53zKYPwEXplBYH3mxuZP52r2u3udRcEg9yTUmwYE5yjmoUXyG', true, 1);

INSERT INTO AUTHORITIES (USERNAME, ROLE) VALUES ('admin', 'ROLE_ADMIN');

INSERT INTO TAGS(name, color) values
('デフォルト', 'transparent'),
('国語', '#f5b7b1'),
('数学', '#85c1e9'),
('理科', '#82e0aa'),
('社会', '#f5cba7'),
('英語', '#e6aae4'),
('音楽', '#9955bb'),
('体育', '#add8e6');

-- 以下 ユーザ
INSERT INTO USERS (NAME, display_name, PASSWORD, ENABLED, icon_id) VALUES
('r230001', '奥井 大貴', '$2a$10$vC.r53zKYPwEXplBYH3mxuZP52r2u3udRcEg9yTUmwYE5yjmoUXyG', true, 1),
('r230002', '鈴木 宏和', '$2a$10$vC.r53zKYPwEXplBYH3mxuZP52r2u3udRcEg9yTUmwYE5yjmoUXyG', true, 1),
('r230003', '土井 朋哉', '$2a$10$vC.r53zKYPwEXplBYH3mxuZP52r2u3udRcEg9yTUmwYE5yjmoUXyG', true, 1),
('r230004', '山田 一郎', '$2a$10$vC.r53zKYPwEXplBYH3mxuZP52r2u3udRcEg9yTUmwYE5yjmoUXyG', true, 1),
('r230005', '山田 二郎', '$2a$10$vC.r53zKYPwEXplBYH3mxuZP52r2u3udRcEg9yTUmwYE5yjmoUXyG', true, 1),
('r230006', '山田 三郎', '$2a$10$vC.r53zKYPwEXplBYH3mxuZP52r2u3udRcEg9yTUmwYE5yjmoUXyG', true, 1),
('r230007', '山田 四郎', '$2a$10$vC.r53zKYPwEXplBYH3mxuZP52r2u3udRcEg9yTUmwYE5yjmoUXyG', true, 1),
('r230008', '山田 五郎', '$2a$10$vC.r53zKYPwEXplBYH3mxuZP52r2u3udRcEg9yTUmwYE5yjmoUXyG', true, 1),
('r230009', '山田 六郎', '$2a$10$vC.r53zKYPwEXplBYH3mxuZP52r2u3udRcEg9yTUmwYE5yjmoUXyG', true, 1),
('teacher', '教師 鈴木', '$2a$10$vC.r53zKYPwEXplBYH3mxuZP52r2u3udRcEg9yTUmwYE5yjmoUXyG', true, 1);

INSERT INTO AUTHORITIES (USERNAME, ROLE) VALUES
('r230001', 'ROLE_STUDENT'),
('r230002', 'ROLE_STUDENT'),
('r230003', 'ROLE_STUDENT'),
('r230004', 'ROLE_STUDENT'),
('r230005', 'ROLE_STUDENT'),
('r230006', 'ROLE_STUDENT'),
('r230007', 'ROLE_STUDENT'),
('r230008', 'ROLE_STUDENT'),
('r230009', 'ROLE_STUDENT'),
('teacher', 'ROLE_TEACHER');

insert into milestones (username, title, memo, importance, achievement, goal, created, modified, enabled) values
('r230001', '期末テスト', '上位5位以内とる!', 3, 100, '2023-09-15', '2023-10-01', '2023-10-01', true),
('r230001', '全国じゃんけん大会', '優勝する', 5, 100, '2023-10-01', '2023-10-01', '2023-10-01', true),
('r230001', 'テストに向けて勉強する', ' ', 5, 20, '2023-11-15', '2023-10-01', '2023-10-01', true),
('r230001', '共通テスト模擬試験', 'ギブリー大 A判定', 4, 0, '2023-11-16', '2023-10-01', '2023-10-01', true),
('r230001', 'ギブリー大学 模擬試験', 'ギブリー大 A判定', 4, 0, '2023-12-15', '2023-10-01', '2023-10-01', true),
('r230001', '共通テスト 本番', 'ギブリー大 A判定', 5, 0, '2024-01-15', '2023-10-01', '2023-10-01', true),
('r230001', 'ギブリー大学 試験本番', '合格するぞ', 5, 0, '2024-02-01', '2023-10-01', '2023-10-01', true),
('r230001', '検閲済み', '検閲済み', 5, 0, '2024-3-3', '2023-4-4', '2023-4-4', false);

INSERT INTO TASKS(milestone_id, name, importance, achievement, enabled, tag_id) values
(1, '国語 10位以内', 3, 90, true, 2),
(1, '数学 3位以内', 5, 100, true, 3),
(1, '理科 10位以内', 4, 50, true, 4),
(1, '社会 20位以内', 2, 80, true, 5),
(1, '英語 1位とる', 5, 100, true, 6),
(2, '地区予選で1位をとる', 5, 100, true, 1),
(2, '県大会で1位をとる', 5, 100, true, 1),
(2, '全国大会で1位をとる', 5, 100, true, 1),
(3, '国語 問題集終わらせる', 5, 90, true, 2),
(3, '数学 問題集終わらせる', 5, 0, true, 3),
(3, '理科 問題集終わらせる', 5, 0, true, 4),
(3, '社会 問題集終わらせる', 5, 0, true, 5),
(3, '英語 問題集終わらせる', 5, 0, true, 6),
(3, '体育 問題集終わらせる', 5, 100, true, 7);

INSERT INTO COMMENTS(milestone_id, username, body, created, enabled) values
(1, 'teacher', 'いいね！', '2023-09-01', true),
(1, 'teacher', '今日の国語の試験、1問目の回答は2です。こっそり教えます。', '2023-09-15', true),
(1, 'r230001', '目標達成!', '2023-09-17', true);
