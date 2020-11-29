DROP DATABASE IF EXISTS movieDataBase;
CREATE DATABASE movieDataBase; 
USE movieDataBase;

DROP TABLE IF EXISTS MOVIE;
CREATE TABLE MOVIE (
  MovieName		varchar(25) not null,
  MoviePrice    double(10) not null,
  EarlyAccess	varchar(10) not null 

  primary key (ToolID),
  foreign key (SupplierID) references SUPPLIER(SupplierID),

);

CREATE TABLE Theatre (
  TheatreName	varchar(25) not null
);

CREATE TABLE ShowTime (
  time	varchar(25) not null,
  EarlyAccessMovie boolean,
  EarlyAccessSeatsAvailable boolean,
  isFull boolean
);

CREATE TABLE Seat (
  seatStatus boolean,
  seatRow int(2),
  seatCol int(2)
);

CREATE TABLE Seat (
  seatStatus boolean,
  seatRow int(2),
  seatCol int(2)
);


CREATE TABLE User (

);

