create table Organization (
  id bigint(20) not null PRIMARY KEY auto_increment unique,
  inn varchar(13) not null unique,
  ogrn varchar(16) not null unique,
  name varchar(255) not null,
  address varchar(255) not null
);