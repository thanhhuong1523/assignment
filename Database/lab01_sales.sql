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

alter table orders 
add column total decimal(10,2),
add column order_status ENUM('Pending', 'Processing', 'Completed', 'Cancelled') NOT NULL DEFAULT 'Pending',
add column payment_method ENUM('Cash', 'Credit Card', 'Bank Transfer', 'PayPal') NOT NULL DEFAULT 'Cash';

drop procedure if exists sp_add_employee;
delimiter //
create procedure sp_add_employee(in v_last_name varchar(20), in v_first_name varchar(20), in v_birthday date, in v_supervisor int)
begin
	insert into employees(last_name, first_name, birth_day, supervisor_id)
    values(v_last_name, v_first_name, v_birthday, v_supervisor);
end; //
delimiter ;
    
call sp_add_employee('Nguyen Van', 'Bao', '2005-12-20', 2);

select * from employees
