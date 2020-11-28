USE ticketregistrationdatabase;



INSERT INTO TICKET (TicketID, SeatNumber, IsSeatReserved, ShowTimeID, TheatreName, MovieName,Paid, Price   ) VALUES (2, 2, FALSE, 1, 'Country Hills Theatres' , 'Die Hard',  FALSE, 8.99) ;
INSERT INTO TICKET (TicketID, SeatNumber, IsSeatReserved, ShowTimeID, TheatreName, MovieName,Paid, Price   ) VALUES (3, 3, FALSE, 1, 'Country Hills Theatres' , 'Die Hard',  FALSE, 8.99) ;
INSERT INTO TICKET (TicketID, SeatNumber, IsSeatReserved, ShowTimeID, TheatreName, MovieName,Paid, Price   ) VALUES (4, 4, FALSE, 1, 'Country Hills Theatres' , 'Die Hard',  FALSE, 8.99) ;
INSERT INTO TICKET (TicketID, SeatNumber, IsSeatReserved, ShowTimeID, TheatreName, MovieName,Paid, Price   ) VALUES (5, 5, FALSE, 1, 'Country Hills Theatres' , 'Die Hard',  FALSE, 8.99) ;
INSERT INTO TICKET (TicketID, SeatNumber, IsSeatReserved, ShowTimeID, TheatreName, MovieName,Paid, Price   ) VALUES (6, 1, FALSE, 2, 'Country Hills Theatres' , 'Rambo III',  FALSE, 8.99) ;
INSERT INTO TICKET (TicketID, SeatNumber, IsSeatReserved, ShowTimeID, TheatreName, MovieName,Paid, Price   ) VALUES (7, 2, FALSE, 2, 'Country Hills Theatres' , 'Rambo III',  FALSE, 8.99) ;
INSERT INTO TICKET (TicketID, SeatNumber, IsSeatReserved, ShowTimeID, TheatreName, MovieName,Paid, Price   ) VALUES (8, 3, FALSE, 2, 'Country Hills Theatres' , 'Rambo III',  FALSE, 8.99) ;
INSERT INTO TICKET (TicketID, SeatNumber, IsSeatReserved, ShowTimeID, TheatreName, MovieName,Paid, Price   ) VALUES (9, 4, FALSE, 2, 'Country Hills Theatres' , 'Rambo III',  FALSE, 8.99) ;
INSERT INTO TICKET (TicketID, SeatNumber, IsSeatReserved, ShowTimeID, TheatreName, MovieName,Paid, Price   ) VALUES (10, 5, FALSE, 2, 'Country Hills Theatres' , 'Rambo III',  FALSE, 8.99) ;

SELECT * FROM hosted_by;
SELECT * FROM movie;
SELECT * FROM receipt;
SELECT * FROM registered_users;
SELECT * FROM seats;
SELECT * FROM showings;
SELECT * FROM showtime;
SELECT * FROM theatre;
SELECT * FROM ticket;
SELECT * FROM ticket_purchases;
SELECT * FROM voucher;
