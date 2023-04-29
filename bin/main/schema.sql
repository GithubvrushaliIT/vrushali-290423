CREATE TABLE customer (
	id BIGINT primary key auto_increment,
	first_name varchar(100) not null,
	last_name varchar(100) not null
);
CREATE TABLE mobile_numbers (
	customer_id BIGINT,
	mobile_number varchar(15)  not null unique,
      foreign key(customer_id) references customer(id)
);



