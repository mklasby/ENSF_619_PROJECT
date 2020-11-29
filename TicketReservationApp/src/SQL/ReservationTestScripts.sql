USE ticketregistrationdatabase;


-- Get Movie List
-- SELECT * FROM MOVIE ORDER BY MovieName;


-- Get Movie List through Ticket???? 
-- SELECT DISTINCT MovieName FROM TICKET ORDER BY MovieName


-- Get a List of Theatres Hosting Die Hard
-- SELECT TheatreName FROM HOSTED_BY as H WHERE H.MovieName = 'Die Hard'


-- Get a List of Show Time at the Countrill Hills Theatres for DieHard
-- SELECT ST.ShowTimeID, ST.StartTime, ST.EndTime FROM SHOWTIME AS ST, SHOWINGS AS SW WHERE SW.MovieName = 'Die Hard' AND SW.TheatreName = 'Country Hills Theatres' AND SW.ShowTimeID = ST.ShowTimeID



-- Get List of Seats for that movie at that theatre
-- SELECT T.SeatNumber, T.isSeatReserved FROM TICKET AS T WHERE T.MovieName = 'Die Hard' AND T.TheatreName = 'Country Hills Theatres' AND T.ShowTimeID = 1;


-- Get Registered USer

-- SELECT * FROM REGISTERED_USERS WHERE Username = 'admin'; 


-- add get voucher.
-- SELECT * FROM Voucher WHERE VoucherID = 1;


-- Change voucher to deactivated , VOUCHER AS V  AND C.CreditCardNumber = T.CreditCardNumber
-- UPDATE VOUCHER SET isVoucherActive = 1 WHERE VoucherID = 1; AND R.VoucherID = V.VoucherID


-- SELECT * FROM RECEIPT AS R WHERE R.ReceiptID = 1;
-- SELECT * FROM RECEIPT AS R, VOUCHER AS V WHERE R.ReceiptID = 1 AND V.VoucherID = R.VoucherID;


SELECT * FROM RECEIPT AS R, TICKET AS T, CREDIT_INFORMATION AS C WHERE R.ReceiptID = 1 AND R.TicketID = T.TicketID AND C.CreditCardNumber = T.CreditCardNumber;
 