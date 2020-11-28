DROP DATABASE IF EXISTS ticketRegistrationDatabase;
CREATE DATABASE ticketRegistrationDatabase; 
USE ticketRegistrationDatabase;

DROP TABLE IF EXISTS CREDIT_INFORMATION;
CREATE TABLE CREDIT_INFORMATION (
  CreditCardNumber    	int not null,
  CreditCardType		varchar(25),
  primary key (CreditCardNumber)
  
);
INSERT INTO CREDIT_INFORMATION ( CreditCardNumber, CreditCardType) VALUES (9999,"D");
INSERT INTO CREDIT_INFORMATION ( CreditCardNumber, CreditCardType) VALUES (8888,"M");
INSERT INTO CREDIT_INFORMATION ( CreditCardNumber, CreditCardType) VALUES (7777,"V");


/*Supplier doesnt have phone number silly
*/
DROP TABLE IF EXISTS MOVIE;
CREATE TABLE MOVIE (
  MovieName    	  		varchar(25) not null,
  IsEarlyAccess 		boolean,
  primary key (MovieName)
  
);

INSERT INTO MOVIE (MovieName ,IsEarlyAccess) VALUES ("Die Hard",true);
INSERT INTO MOVIE (MovieName ,IsEarlyAccess) VALUES ("Rambo III",false);


DROP TABLE IF EXISTS THEATRE;
CREATE TABLE THEATRE (
  TheatreName			varchar(25) not null,
  Address      			varchar(25) not null,
  primary key (TheatreName)
);

INSERT INTO THEATRE (TheatreName ,Address) VALUES ("Country Hills Theatres", "Somewhere in Calgary");



DROP TABLE IF EXISTS HOSTED_BY;
CREATE TABLE HOSTED_BY (
  TheatreName			varchar(25) not null,
  MovieName      		varchar(25) not null,
  PRIMARY KEY (TheatreName, MovieName),
  FOREIGN KEY (TheatreName) REFERENCES THEATRE(TheatreName),
  FOREIGN KEY (MovieName) REFERENCES MOVIE(MovieName)
);

INSERT INTO HOSTED_BY (TheatreName ,MovieName) VALUES ("Country Hills Theatres", "Die Hard");
INSERT INTO HOSTED_BY (TheatreName ,MovieName) VALUES ("Country Hills Theatres", "Rambo III");


DROP TABLE IF EXISTS SHOWTIME;
CREATE TABLE SHOWTIME (
  ShowTimeID		int not null,
  StartTime			timestamp not null,
  EndTime			timestamp not null,
  PRIMARY KEY (ShowTimeID)
);

INSERT INTO SHOWTIME (ShowTimeID ,StartTime, EndTime ) VALUES (1, '2008-01-01 07:00:00', '2008-01-01 08:40:00');
INSERT INTO SHOWTIME (ShowTimeID ,StartTime, EndTime ) VALUES (2, '2008-01-01 09:00:00', '2008-01-01 10:40:00');


DROP TABLE IF EXISTS SHOWINGS;
CREATE TABLE SHOWINGS (
  ShowTimeID		int not null,
  TheatreName		varchar(25)  not null,
  MovieName			varchar(25) not null,
  PRIMARY KEY (TheatreName, ShowTimeID, MovieName),
  FOREIGN KEY (ShowTimeID) REFERENCES SHOWTIME(ShowTimeID),
  FOREIGN KEY (TheatreName) REFERENCES THEATRE(TheatreName),
  FOREIGN KEY (MovieName) REFERENCES Movie(MovieName)
);

INSERT INTO SHOWINGS (ShowTimeID ,TheatreName, MovieName ) VALUES (1, 'Country Hills Theatres', 'Die Hard');
INSERT INTO SHOWINGS (ShowTimeID ,TheatreName, MovieName ) VALUES (2, 'Country Hills Theatres', 'Rambo III');



DROP TABLE IF EXISTS SEATS;
CREATE TABLE SEATS (
  SeatNumber      		int not null,
  PRIMARY KEY (SeatNumber)
);

INSERT INTO SEATS (SeatNumber) VALUES (1);
INSERT INTO SEATS (SeatNumber) VALUES (2);
INSERT INTO SEATS (SeatNumber) VALUES (3);
INSERT INTO SEATS (SeatNumber) VALUES (4);
INSERT INTO SEATS (SeatNumber) VALUES (5);
INSERT INTO SEATS (SeatNumber) VALUES (6);
INSERT INTO SEATS (SeatNumber) VALUES (7);
INSERT INTO SEATS (SeatNumber) VALUES (8);
INSERT INTO SEATS (SeatNumber) VALUES (9);
INSERT INTO SEATS (SeatNumber) VALUES (10);

DROP TABLE IF EXISTS TICKET;
CREATE TABLE TICKET (
  TicketID					int not null,
  SeatNumber      			int not null,
  IsSeatReserved			boolean,
  ShowTimeID				int not null,
  TheatreName				varchar(25)  not null,
  MovieName					varchar(25) not null,
  Paid						boolean,
  Price						double,
  
  PRIMARY KEY (TicketID, SeatNumber, TheatreName, ShowTimeID, MovieName),
  FOREIGN KEY (ShowTimeID) REFERENCES SHOWTIME(ShowTimeID),
  FOREIGN KEY (TheatreName) REFERENCES THEATRE(TheatreName),
  FOREIGN KEY (MovieName) REFERENCES MOVIE(MovieName),
  FOREIGN KEY (SeatNumber) REFERENCES SEATS(SeatNumber)
);

INSERT INTO TICKET (TicketID, SeatNumber, IsSeatReserved, ShowTimeID, TheatreName, MovieName,Paid, Price   ) VALUES (1, 1, TRUE, 1, 'Country Hills Theatres' , 'Die Hard',  TRUE, 8.99) ;




DROP TABLE IF EXISTS REGISTERED_USERS;
CREATE TABLE REGISTERED_USERS (
  isMembershipPaid      		boolean,
  UserType						varchar(25),
  Username						varchar(25),
  UserPassword					varchar(25),
  Name      					varchar(25),
  Email      					varchar(25),
  CreditCardNumber      		int,

  PRIMARY KEY (Username),
  FOREIGN KEY (CreditCardNumber) REFERENCES CREDIT_INFORMATION(CreditCardNumber)
);

INSERT INTO REGISTERED_USERS (isMembershipPaid, UserType, Username, UserPassword, Name, Email, CreditCardNumber  ) VALUES (True, 'R', 'user' ,'password', 'Sylvester Stallone', 'realrambo@hotmail.com', 9999 ) ;
INSERT INTO REGISTERED_USERS (isMembershipPaid, UserType, Username, UserPassword, Name, Email, CreditCardNumber  ) VALUES (Null, 'M', 'admin' ,'admin', 'Big Boy', 'bigboy@hotmail.com', 8888 ) ;

DROP TABLE IF EXISTS VOUCHER;
CREATE TABLE VOUCHER (
  VoucherID				int,
  VoucherValue			double,
  VoucherExpiraryDate	date,
  VoucherActive			boolean,
  PRIMARY KEY (VoucherID)
);
INSERT INTO VOUCHER (VoucherID, VoucherValue, VoucherExpiraryDate, VoucherActive) VALUES (1, 8.99, '2009-01-01 10:40:00',True);


DROP TABLE IF EXISTS RECEIPT;
CREATE TABLE RECEIPT (
  ReceiptID					int,
  ReceiptType				varchar(25),
  TicketID					int,
  CreditCardNumber      	int,
  VoucherID					int,
  Price						double,

  PRIMARY KEY (ReceiptID),
  FOREIGN KEY (TicketID) REFERENCES TICKET(TicketID),
  FOREIGN KEY (VoucherID) REFERENCES VOUCHER(VoucherID),
  FOREIGN KEY (CreditCardNumber) REFERENCES CREDIT_INFORMATION(CreditCardNumber)
);
INSERT INTO RECEIPT (ReceiptID, ReceiptType, TicketID, CreditCardNumber, VoucherID, Price  ) VALUES (1, 'Ticket' , 1, 9999, null, 8.99) ;

DROP TABLE IF EXISTS TICKET_PURCHASES;
CREATE TABLE TICKET_PURCHASES (
  PurchaseID				int,
  TicketID					int,
  VoucherID					int,
  CreditCardNumber      	int,
  ReceiptID					int,

  PRIMARY KEY (TicketID, CreditCardNumber, ReceiptID),
  FOREIGN KEY (TicketID) REFERENCES TICKET(TicketID),
  FOREIGN KEY (ReceiptID) REFERENCES RECEIPT(ReceiptID),
  FOREIGN KEY (CreditCardNumber) REFERENCES CREDIT_INFORMATION(CreditCardNumber)
);
INSERT INTO TICKET_PURCHASES (PurchaseID, TicketID, VoucherID, CreditCardNumber, ReceiptID  ) VALUES (1, 1 , null, 9999, 1) ;















