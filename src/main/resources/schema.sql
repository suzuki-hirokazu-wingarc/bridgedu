-- drop table if exists users;

-- create table users (
--     -- id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
--     name VARCHAR(255) NOT NULL PRIMARY KEY,
--     display_name  VARCHAR(255) NOT NULL,
--     password VARCHAR(255) NOT NULL,
--     enabled BOOLEAN NOT NULL
-- );

drop table if exists authorities;

create table authorities (
    username VARCHAR(255) NOT NULL,
    authority VARCHAR(255) NOT NULL
);

drop table if exists milestones;

create table milestones (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    memo VARCHAR(255) NOT NULL,
    importance TINYINT NOT NULL,
    achievement TINYINT NOT NULL,
    goal DATE NOT NULL,
    created DATE NOT NULL,
    modified DATE NOT NULL
);

drop table if exists tasks;

create table tasks (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    milestone_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    importance TINYINT NOT NULL,
    achievement TINYINT NOT NULL,
    tag_id BIGINT NOT NULL
);

drop table if exists tags;

create table tags (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    color VARCHAR(255) NOT NULL
);

drop table if exists comments;

create table comments (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    milestone_id BIGINT NOT NULL,
    username VARCHAR(255) NOT NULL,
    body VARCHAR(255) NOT NULL,
    created DATE NOT NULL,
    enabled BOOLEAN NOT NULL
);
