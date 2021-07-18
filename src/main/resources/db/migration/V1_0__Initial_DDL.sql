CREATE TABLE employee (
   id bigint(20) NOT NULL AUTO_INCREMENT,
   identity bigint(20) NOT NULL,
   name varchar(50) NOT NULL,
   surname varchar(50) NOT NULL,
   started timestamp DEFAULT current_timestamp,
   PRIMARY KEY (id)
);

CREATE TABLE annual_leave (
   id bigint(20) NOT NULL AUTO_INCREMENT,
   identity bigint(20) NOT NULL,
   annual_leave_used bigint(20) DEFAULT NULL,
   total_leave_used bigint(20) DEFAULT NULL,
   PRIMARY KEY (id)
);

CREATE TABLE approval_table (
   id bigint(20) NOT NULL AUTO_INCREMENT,
   topic varchar(50) NOT NULL,
   dto_binder varchar(250),
   status varchar(50) NOT NULL,
   note varchar(50),
   PRIMARY KEY (id)
);