CREATE TABLE customer (
	id BIGINT primary key auto_increment,
	first_name varchar(100) not null,
	last_name varchar(100) not null,
	mobile_number varchar(15) not null
);
ALTER TABLE customer ADD CONSTRAINT customer_uk1 UNIQUE (mobile_number);
