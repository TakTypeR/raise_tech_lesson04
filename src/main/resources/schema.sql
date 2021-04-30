CREATE TABLE IF NOT EXISTS platforms (
    id      INT             NOT NULL    AUTO_INCREMENT  PRIMARY KEY,
    name    VARCHAR(45)     NOT NULL
);

CREATE TABLE IF NOT EXISTS pcs (
  id        INT             NOT NULL    AUTO_INCREMENT  PRIMARY KEY,
  platform  VARCHAR(45)     NOT NULL,
  host_name VARCHAR(45)     NOT NULL,
  owner     VARCHAR(45)
);

CREATE TABLE IF NOT EXISTS pcs2 (
  id           INT             NOT NULL    AUTO_INCREMENT  PRIMARY KEY,
  platform_id  INT             NOT NULL,
  host_name    VARCHAR(45)     NOT NULL,
  owner        VARCHAR(45),
  platform     VARCHAR(45),    NOT NULL

);
