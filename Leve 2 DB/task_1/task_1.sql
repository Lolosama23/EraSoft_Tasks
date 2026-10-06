CREATE TABLE Manager
(
id NUMBER (11) NOT NULL ,
name varchar (20),
age NUMBER (2),
birth_date DATE,
 address varchar (20)
);
ALTER  TABLE MANAGER DROP COLUMN ADDRESS ;
ALTER  TABLE MANAGER ADD ( city_address varchar (20),street varchar (20));
ALTER  TABLE MANAGER RENAME COLUMN   name TO  full_name  ;
ALTER  TABLE MANAGER READ ONLY ;
CREATE  TABLE Owner AS SELECT id,full_name,birth_date FROM Manager ;
ALTER TABLE MANAGER RENAME TO master;
DROP TABLE Manager;
DROP TABLE Owner;