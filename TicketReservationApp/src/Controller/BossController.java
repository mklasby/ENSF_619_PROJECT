package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;

import org.json.JSONException;
import org.json.JSONArray;

import org.json.JSONObject;

import CommonMessage.Message;
import CommonMessage.MessageConstants;
import Model.TheatreModel.*;
import Model.UserModel.*;
import Model.PaymentModel.*;
import Controller.PaymentController.*;

public class BossController implements MessageConstants {

    private Ticket ticket;
    private UserManager userManager;
    private DatabaseController databaseController;
    private FinancialController financialController;
    private Cart cart;

    public BossController(DatabaseController databaseController, FinancialController financialController,
            UserManager userManager) {
        this.databaseController = databaseController;
        this.financialController = financialController;
        this.cart = new Cart();
        this.userManager = userManager;
    }

    public Message login(String username, String password) {
        // TODO: Return STATUS=ERROR if login fails
        // TODO: Return STATUS=OK and DATA="MANAGER"
        // TODO: RETURN STATUS=OK AND DATA="REGISTERED"
        ResultSet user = databaseController.getRegisteredUser(username);
        System.out.print("hello world");
        try {
            if (user == null) { // There is no user with that name
                return new Message(ERROR, "This username does not exists");
            }
            if (!user.getString("UserPassword").equals(password)) {// the username and password exist you can log in!
                // UserManager.setUser(user); // Here SEND THE RESULT SET TO GET FIXED
                return new Message(ERROR, "Password does not match");

            } else {

                if (user.getString("UserType").equals("M")) {
                    userManager.setUser(userManager.parseUserSQL(user));
                    userManager.setIsRegistered(true, "M");
                    return new Message(OK, "Manager");
                } else {
                    userManager.setUser(userManager.parseUserSQL(user));
                    userManager.setIsRegistered(true, "R");
                    return new Message(OK, "Registered");

                }
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }

    public void logoutUser() {
        userManager.logoutUser();
        cart.clearCart();
    }

    public Message selectMovie(JSONObject movie) {
        // TODO: check if user is permitted to book this movie, return ERROR if not
        // TODO: If user can select ticket, return OK message.
        // TODO: Create ticket object and add this movie to it.

        try {
            Movie selectedMovie = new Movie(movie);
            ticket = new Ticket(selectedMovie);
            // Check if the movie is early access
            if (selectedMovie.isEarlyAccess() == true) {
                // Check if the user is registered.
                if (userManager.isRegistered() == true) {
                    ticket.setMovie(selectedMovie);

                } else {
                    return new Message(ERROR, "This is an Early Access Movie, please login");
                }
            } else {
                ticket.setMovie(selectedMovie);
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return new Message(OK, "Movie selected!");
    }

    public Message getMovieList() {
        // TODO: Return OK with list of movies as JSONArray in DATA
        BrowseMovie bm = new BrowseMovie();
        try {
            Message message = bm.getMovieList(databaseController.getMoviesList());
            System.out.println(message.toString());

            return message;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public Message getTheatreList() {
        // TODO: Return OK with list of theatres as JSONArray in DATA
        ResultSet result = databaseController.getTheatreList(ticket.getMovie().getMovieName());
        // todo: SET ST THEATRE LIST BEFORE ASKING FOR ONE!
        SelectTheatre st = new SelectTheatre(result);
        JSONArray data = st.getTheatreList();
        System.out.print(data.toString());
        return new Message(OK, data);
    }

    public Message selectTheatre(JSONObject theatre) {
        // TODO: Return OK message
        // TODO: Add theatre to ticket

        try {
            Theatre selectedTheatre;
            selectedTheatre = new Theatre(theatre);
            ticket.setTheatre(selectedTheatre);
            return new Message(OK, "Theatre Selected!");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public Message getShowTimeList() {
        // TODO: Return OK with list of theatres as JSONArray in DATA
        SelectShowTime sst = new SelectShowTime();

        try {
            Message message = sst.getShowTimeList(databaseController.getShowTimeList(ticket.getMovie().getMovieName(),
                    ticket.getTheatre().getTheatreName()));
            return message;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;

    }

    public Message selectShowTime(JSONObject showTime) {
        // TODO: Return OK and set this ticket to this showtime

        try {
            ShowTime selectShowTime;
            selectShowTime = new ShowTime(showTime);
            ticket.setShowTime(selectShowTime);
            return new Message(OK, "ShowTime Selected!");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;

    }

    public Message selectSeat(JSONObject selection) {
        // TODO: MOVE FUNCTIONALITY TO TICKET CLASS?

        try {
            if (databaseController.reservationsRemaining(ticket.getMovie().getMovieName(),
                    ticket.getTheatre().getTheatreName(), ticket.getShowTime().getShowTimeID(),
                    selection.getInt("seatNum"))) {
                return new Message(ERROR, "Sorry, 10% of seats have already been reserved for this early movie!");
            }
            Seat seat;
            // TODO: how to check for 10% of tickets reserved for early access movie!
            seat = new Seat(selection);
            ticket.setSeat(seat);
            cart.addTicketToCart(ticket);
            return new Message(OK, "Seat Selected!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Message getSeatList() {
        // public JSONArray getSeatList() {
        // TODO: OK return all seats for this theatre, movie, showtime combo\
        // TODO: Return ERROR if all seats full?
        SelectSeat ss = new SelectSeat();
        try {
            ResultSet seats = databaseController.getSeatList(ticket.getMovie().getMovieName(),
                    ticket.getTheatre().getTheatreName(), ticket.getShowTime().getShowTimeID());
            if (seats == null) {
                return new Message(ERROR, "No seats found for that showtime!");
            } else {
                return ss.getSeatList(seats);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;

    }

    public Message registerNewUser(boolean isMemberPaid, String userType, String username, String userPassword,
            String name, String address, String email, int creditCardNumber) {
        // TODO: return OK if successful, return ERROR If username already exists of if
        // supplied type are bad

        ResultSet resultSet = databaseController.getRegisteredUser(username);
        if (resultSet != null) {
            return new Message(ERROR, "Username already exists");
        }
        resultSet = databaseController.setRegisteredUser(isMemberPaid, userType, username, userPassword, name, address,
                email, creditCardNumber); // No matter what happens this shouldnt bounce!
        return new Message(OK, userManager.parseUserSQL(resultSet)); // wait shuold i return the user or should i just
                                                                     // say "New User has been registered"

    }

    public Message getRegisteredUser(String username) {

        ResultSet resultSet = databaseController.getRegisteredUser(username); // this should have all user information
                                                                              // along with payment information
        if (resultSet == null) {
            return new Message(ERROR, "Username does not exists!");
        }
        return new Message(OK, userManager.parseUserSQL(resultSet));

    }

    public Message refundTicket(int ticketNum) {

        try {

            ResultSet resultSetReceipt = databaseController.getReceipt(ticketNum);
            if (resultSetReceipt == null) {
                return new Message(ERROR, "Ticket Number not found");
            } else {

                // here we need to check if we are near 72 hours
                ResultSet resultSetTicket = databaseController.getTicket(ticketNum);
                Timestamp startTime = resultSetTicket.getTimestamp("startTime");
                Timestamp sqlTimestamp = new Timestamp(System.currentTimeMillis());

                if ((startTime.getTime() - sqlTimestamp.getTime()) < (2.592 * Math.pow(10, 8))) {
                    return new Message(ERROR, "Sorry, no refunds within 72 hours of a showtime");
                }

                PaymentManager paymentManager = new PaymentManager(userManager.getUser());
                TicketReceipt ticketReceipt = new TicketReceipt(resultSetReceipt);
                JSONArray voucherAndReceipt = paymentManager.refundTicket(ticketReceipt);
                // do we need to do checking?

                try {

                    Voucher voucher = new Voucher(voucherAndReceipt.getJSONObject(0));
                    // Check if string works when moving around time/dates
                    databaseController.setVoucher(voucher.getInt("voucherID"), voucher.getDouble("amount"),
                            voucher.getString("expiryDate"), true);

                    RefundReceipt refundReceipt = new RefundReceipt(voucherAndReceipt.getJSONObject(1));

                    databaseController.insertReceipt(refundReceipt.getInt("receiptID"),
                            refundReceipt.getString("receiptType"), refundReceipt.getInt("ticketID"),
                            refundReceipt.getInt("creditCardNumber"), refundReceipt.getInt("voucherID"),
                            refundReceipt.getDouble("price"));

                    databaseController.resetTicket(refundReceipt.getInt("ticketID"));
                    // Whats the logic here.
                    return new Message(OK, "Successfully Refunded");

                } catch (JSONException | ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Message(ERROR, "Should be unreachable");

    }

    // public Message refundTicket(int ticketNum) { //hold up ticket num != receipt
    // num can still make it work tho
    // // TODO: Return OK if refund is processed with description of Voucher or
    // refund
    // // TODO: Return ERROR with DATA == "Sorry, no refunds within 72 hours of a
    // // showtime!" OR
    // // DATA == "Ticket Number not found"
    // ResultSet resultSet = databaseController.getReceipt(ticketNum);
    // if(resultSet == null) {// if receipt is not found
    // return new Message(ERROR, "Ticket Number not Found");
    //
    // }else {
    // if(databaseController.getTicket(ticketNum).getStartTime()-whatever time is it
    // now < 72 hours) {
    // return new Message(ERROR, "Sorry, no refunds within 72 hours of showtime");
    // }
    //
    // RefundReceipt refundReceipt = PaymentManager.refund(????);
    // return new Message(OK, refundReceipt);
    // }
    //
    // }

    public Message registerNewUser(boolean isMemberPaid, String userType, String username, String password, String name,
            String address, String email, int creditCardNumber, String creditCardType) {

        ResultSet resultSet = databaseController.getRegisteredUser(username);
        if (resultSet == null) {// username exists send error.
            return new Message(ERROR, "Username already exist, please choose another one!");
        } else {
            resultSet = databaseController.registerMyUser(isMemberPaid, userType, username, password, name, address,
                    email, creditCardNumber, creditCardType);
            return new Message(OK, resultSet);

        }

    }

    public Message getCartInfo() {
        return new Message(OK, cart.getCart());
    }

    public Message getPaymentInfo() {
        if (userManager.getIsRegisteredUser()) {
            return new Message(OK, userManager.getUser());
        }
        return null;
    }

    public Message uploadMovieNews(String fieldText) {
        return new Message(OK, String.format("Success, %s uploaded and emailed to all registered users!", fieldText));
    }

    public Message payAnnual() {
        // TODO: Add logic to check if we have already paid? return STATUS=ERROR if so?
        // TODO: add annual payment to cart. This call can
        // only be invoked after log in (guarded on front end already, no need to check
        // here)
        cart.addAnnualFee(new AnnualFee());
        return new Message(OK, "Success, annual dues added to cart");
    }

    public Message processPayment(String email, int cardNum, String cardType) throws JSONException {
        if (cardType.equals("Voucher")) {
            if (!databaseController.isValidVoucher(cardNum)) {
                return new Message(ERROR, "Voucher number not found!");
            }
        }
        if (financialController.checkPaymentData(cardType, cardNum)) {
            // TODO: ADD PAYMENT TYPE TO USERMGMT / RECIEPT
            // cart.payAll();
            PaymentInfo paymentInfo = new PaymentInfo(cardNum, cardType);
            User thisUser = userManager.getUser();
            thisUser.setEmail(email);
            thisUser.setPaymentInfo(paymentInfo);

            PaymentManager paymentManager = new PaymentManager(thisUser);
            paymentManager.setCart(cart);
            JSONArray paymentReceipts = paymentManager.payForAll();

            for (int i = 0; i < paymentReceipts.length(); i++) {
                if (paymentReceipts.getJSONObject(i).getString("receiptType").equals("Annual")) {
                    RegisteredUser theUser = (RegisteredUser) thisUser;
                    databaseController.payAnnualDues(theUser.getUserName());
                }

                int ticketID;
                try {
                    ticketID = paymentReceipts.getJSONObject(i).getInt("ticketID");
                } catch (JSONException e) {
                    ticketID = -1;
                }

                int receiptID = paymentReceipts.getJSONObject(i).getInt("receiptId");
                String receiptType = paymentReceipts.getJSONObject(i).getString("receiptType");
                int voucherId = -1;
                int creditCardNumber = paymentReceipts.getJSONObject(i).getInt("creditCardNumber");
                double price = paymentReceipts.getJSONObject(i).getDouble("amount");
                databaseController.insertReceipt(receiptID, receiptType, ticketID, creditCardNumber, voucherId, price);
            }

            return new Message(OK, "Success! Thank you for your business!");
        }
        return null;
    }
}