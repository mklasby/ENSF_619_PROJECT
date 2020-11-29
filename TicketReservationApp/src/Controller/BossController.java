package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import org.json.JSONException;

import org.json.JSONArray;

import org.json.JSONObject;

import com.mysql.cj.protocol.Resultset;

import CommonMessage.Message;
import CommonMessage.MessageConstants;
import Model.TheatreModel.*;
import Model.UserModel.*;
import Model.PaymentModel.*;

public class BossController implements MessageConstants {

    private Ticket ticket;
    private UserManager userManager;
    private DatabaseController databaseController;
    private FinancialController financialController;
    private Cart cart;

    public BossController(DatabaseController databaseController, FinancialController financialController, Cart cart,
            UserManager userManager) {
        this.databaseController = databaseController;
        this.financialController = financialController;
        this.cart = cart;
        this.userManager = userManager;
    }

    public Message login(String username, String password) {
        // TODO: Return STATUS=ERROR if login fails
        // TODO: Return STATUS=OK and DATA="MANAGER"
        // TODO: RETURN STATUS=OK AND DATA="REGISTERED"
        ResultSet user = databaseController.getRegisteredUser(username);

        if (user == null) { // There is no user with that name
            return new Message(ERROR, "This username does not exists");
        }
        try {
            if (!user.getString("UserPassword").equals(password)) {// the username and password exist you can log in!
                // UserManager.setUser(user); // Here SEND THE RESULT SET TO GET FIXED
                return new Message(ERROR, "Password does not match");

            } else {

                if (user.getString("UserType").equals("M")) {
                    return new Message(OK, "Manager");
                } else {
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
        // TODO: Reset user status to normal user
        User.setUser(null);
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

    public Message getSeatList() {
        // public JSONArray getSeatList() {
        // TODO: OK return all seats for this theatre, movie, showtime combo\
        // TODO: Return ERROR if all seats full?
        SelectSeat ss = new SelectSeat();
        try {
            Message message = ss.getSeatList(databaseController.getSeatList(ticket.getMovie().getMovieName(),
                    ticket.getTheatre().getTheatreName(), ticket.getShowTime().getShowTimeID()));
            return message;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;

    }

    public Message registerNewUser(boolean isMemberPaid, String userType, String username, String userPassword,
            String name, String email, int creditCardNumber) {
        // TODO: return OK if successful, return ERROR If username already exists of if
        // supplied type are bad

        ResultSet resultSet = databaseController.getRegisteredUser(username);
        if (resultSet != null) {
            return new Message(ERROR, "Username already exists");
        }
        resultSet = databaseController.setRegisteredUser(isMemberPaid, userType, username, userPassword, name, email,
                creditCardNumber); // No matter what happens this shouldnt bounce!
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

    public Message refundTicket(int ticketNum) { //hold up ticket num != receipt num can still make it work tho
        // TODO: Return OK if refund is processed with description of Voucher or refund
        // TODO: Return ERROR with DATA == "Sorry, no refunds within 72 hours of a
        // showtime!" OR
        // DATA == "Ticket Number not found"
    	ResultSet resultSet = databaseController.getReceipt(ticketNum);
    	if(resultSet == null) {// if receipt is not found
    		return new Message(ERROR, "Ticket Number not Found");
    		
    	}else {
    		if(databaseController.getTicket(ticketNum).getStartTime()-whatever time is it now < 72 hours) {
    			return new Message(ERROR, "Sorry, no refunds within 72 hours of showtime");
    		}
    		
    		RefundReceipt refundReceipt = PaymentManager.refund(????);
    		return new Message(OK, refundReceipt);
    	}

    }

    public Message registerNewUser(String username, String password, String name, String address, String email,
            String cardNum, String cardType) {
        return null;
    }

    public Message getCartInfo() {
        return null;
    }

    public Message getPaymentInfo() {
        return null;
    }
}