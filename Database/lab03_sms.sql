create database sms;
use sms;

create table Customer (
	customer_id int auto_increment primary key,
    customer_name varchar(255) not null
);

create table Employee (
	employee_id int auto_increment primary key,
    employee_name varchar(255) not null,
    salary decimal(10,2) not null,
    supervisor_id int,
    foreign key(supervisor_id) references Employee(employee_id)
);

create table Product (
	product_id int auto_increment primary key,
    product_name varchar(255) not null,
    list_price decimal(10,2) not null
);

create table Orders (
	order_id int auto_increment primary key,
    order_date datetime not null,
    customer_id int not null,
    employee_id int not null,
    total decimal(10,2),
    foreign key(customer_id) references Customer(customer_id),
    foreign key(employee_id) references Employee(employee_id)
);

create table LineItem(
	order_id int not null,
    product_id int not null,
    quantity int not null,
    price decimal(10,2),
    primary key(order_id, product_id),
    foreign key(order_id) references Orders(order_id),
    foreign key(product_id) references Product(product_id)
);

-- 1) List all customers consist of customer id, customer name in the database, returns a 
-- list with all customers in the order table. 
drop procedure if exists get_all_customers;
delimiter //
create procedure get_all_customers()
begin
	select c.customer_id, c.customer_name
    from Customer c
    join Orders o on c.customer_id = o.customer_id;
end //
delimiter ;
call get_all_customers();

-- 2) List all orders consist of order id, order date, customer id, employee id, total for a 
-- customer, returns a list with all the orders for a given customer id. 
drop procedure if exists get_all_orders_for_a_customer;
delimiter //
create procedure get_all_orders_for_a_customer(in v_customer_id int)
begin
	select order_id, order_date, customer_id, employee_id, total
    from Orders
    where customer_id = v_customer_id;
end //
delimiter ;
call get_all_orders_for_a_customer(2);

-- 3) List all line items for an order, returns a list with all line items for a given order id
drop procedure if exists get_all_items;
delimiter //
create procedure get_all_items (in v_order_id int)
begin
	select order_id, product_id, quantity, price
    from LineItem
    where order_id = v_order_id;
end //
delimiter ;
call get_all_items(2);

-- 4) Compute order total (quantity * price) from the line items for a given order id. You 
-- must use an User Define Function. 
drop function if exists f_total_orders;
delimiter //
create function f_total_orders (v_order_id INT)
returns decimal(10,2)
reads sql data
begin
	declare v_total decimal(10,2);
    select sum(quantity * price) into v_total
    from LineItem
    where order_id = v_order_id;
    return ifnull(v_total, 0);
end //
delimiter ;
select f_total_orders(2);

-- 5) Add a customer into the database, you must use a Stored Procedure. 
drop procedure if exists add_a_customer;
delimiter //
create procedure add_a_customer(in v_customer_name varchar(255))
begin
	insert into Customer(customer_name)
    values (v_customer_name);
end //
delimiter ;
call add_a_customer("Rocky");

-- 6) Delete a customer from the database, make sure to also delete Orders and LineItem 
-- for the deleted customer. You must use a Stored Procedure. 
drop procedure if exists delete_a_customer;
delimiter //
create procedure delete_a_customer(in v_customer_id int)
begin
	delete li
    from LineItem li
    join Orders o on li.order_id = o.order_id
    where o.customer_id = v_customer_id;
    
    delete from Orders
    where customer_id = v_customer_id;
    
    delete from Customer
    where customer_id = v_customer_id;
end //
delimiter ;

call delete_a_customer(1);

-- 7) Update a customer in the database, you must use a Stored Procedure. 
drop procedure if exists update_a_customer;
delimiter //
create procedure update_a_customer(in v_customer_id int, in v_customer_name varchar(255))
begin
	update Customer
    set customer_name = v_customer_name
    where customer_id = v_customer_id;
end //
delimiter ;
call update_a_customer(1, 'Hello');

-- 8) Create an order into the database.
drop procedure if exists insert_an_order;
delimiter //
create procedure insert_an_order(in v_order_date datetime, in v_customer_id int, in v_employee_id int)
begin
	insert into Orders(order_date, customer_id, employee_id, total)
    values (v_order_date, v_customer_id, v_employee_id, 0);
end //
delimiter ;
call insert_an_order('2026-02-03', 1, 1);

-- 9) Create a LineItem into the database.
drop procedure if exists insert_line_item;
delimiter //
create procedure insert_line_item(in v_order_id int, in v_product_id int, in v_quantity int, in v_price decimal(10,2))
begin
	insert into LineItem(order_id, product_id, quantity, price)
    values (v_order_id, v_product_id, v_quantity, v_price);
end //
delimiter ;
call insert_line_item(1, 1, 1, 10.3);

-- 10) Update an order total into the database.
drop procedure if exists update_total_order;
delimiter //
create procedure update_total_order(in v_order_id int)
begin
	update Orders
    set total = f_total_order(v_order_total)
    where order_id = v_order_id;
end //
delimiter ;
call update_total_order(1);
