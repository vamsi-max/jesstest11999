create schema if not exists dbs;  

CREATE TABLE person
(
    uuid           UUID         NOT NULL PRIMARY KEY,
    name           VARCHAR(255) NOT NULL
);
