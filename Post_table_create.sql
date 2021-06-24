create table if not exists post (
id int primary key,
name varchar(255),
text text,
link varchar(255) NOT NULL
      CONSTRAINT name_unique UNIQUE,
created timestamp);