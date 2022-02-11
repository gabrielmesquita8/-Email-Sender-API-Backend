  DROP TABLE IF EXISTS users_configs;

  CREATE TABLE users_configs(
   id int PRIMARY KEY,
   firstname varchar(30) NOT NULL,
   lastname varchar(30),
   username varchar(50) NOT NULL,
   password varchar(30)
  );