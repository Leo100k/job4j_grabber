create table post (
                       id serial primary key,
                       name text,
                       text text,
                       link text,
                       created timestamp,
                       CONSTRAINT link_unique UNIQUE (link)
);