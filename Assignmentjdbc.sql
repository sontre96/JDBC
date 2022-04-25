create database mycompany

use mycompany

create table employee(
empid int primary key,
empno varchar(50),
name varchar(50),
age int,
address varchar(255))
drop table employee
select * from employee