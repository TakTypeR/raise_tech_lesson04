CREATE TABLE IF NOT EXISTS pcs (
    id           INT             NOT NULL    AUTO_INCREMENT  PRIMARY KEY,
    host_name    VARCHAR(45)     NOT NULL,
    owner        VARCHAR(45),
    platform_id  INT             NOT NULL
);

CREATE TABLE IF NOT EXISTS platforms (
    id      INT            NOT NULL    AUTO_INCREMENT  PRIMARY KEY,
    name    VARCHAR(45)    NOT NULL
);

CREATE TABLE IF NOT EXISTS project_pc_relation (
    id          INT        NOT NULL    AUTO_INCREMENT  PRIMARY KEY,
    project_id  INT        NOT NULL,
    pc_id       INT
);

CREATE TABLE IF NOT EXISTS projects (
    id          INT            NOT NULL     AUTO_INCREMENT  PRIMARY KEY,
    name        VARCHAR(45)    NOT NULL                     PRIMARY KEY
);