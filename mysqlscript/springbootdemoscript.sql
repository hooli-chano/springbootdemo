drop database if exists springbootdemo;
create database springbootdemo;
use springbootdemo;
create table car(
	id SERIAL,
    brand varchar(50),
    model varchar(50),
    make varchar(50),
    Year varchar(10)
);
    
insert into car (brand, model, make, Year) values ("Audi","A3","Audi","2023");

select * from car;