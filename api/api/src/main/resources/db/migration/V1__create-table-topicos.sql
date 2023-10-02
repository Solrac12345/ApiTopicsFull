CREATE TABLE topicos(
id bigint not null auto_increment,
titulo varchar(100) NOT NULL UNIQUE,
mensaje varchar(100) NOT NULL UNIQUE,
fecha_de_creacion DATE NOT NULL,
status_del_topico varchar(50) NOT NULL,
autor varchar(50) NOT NULL,
curso varchar(50) NOT NULL,
status tinyint,
primary key (id));