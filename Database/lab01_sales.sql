create database Sales;
use Sales;

-- Lab 01 - Create table --
create table employees (
	employee_id INT auto_increment primary key,
    last_name varchar(20) not null,
    first_name varchar(10) not null,
    birth_day date,
    supervisor_id int
);

create table customers (
	customer_id int auto_increment primary key,
    customer_name varchar(255),
    contact_name varchar(255),
    address varchar(255),
    city varchar(255),
    postal_code varchar(10),
    country varchar(50)
);

create table orders (
	order_id int auto_increment primary key,
	customer_id int,
    employee_id int,
    order_date datetime default current_timestamp,
    foreign key(customer_id) references customers(customer_id),
    foreign key(employee_id) references employees(employee_id)
);
