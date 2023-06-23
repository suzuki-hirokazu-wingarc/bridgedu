drop table if exists milestones;

create table milestones (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userid BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    memo VARCHAR(255) NOT NULL,
    importance TINYINT NOT NULL,
    achievement TINYINT NOT NULL,
    goal DATE NOT NULL,
    created DATE NOT NULL,
    modified DATE NOT NULL
);
