package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;

import org.json.JSONArray;

import org.json.JSONObject;

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

    
    public BossController(DatabaseController databaseController, FinancialController financialController, Cart cart, UserManager userManager) {
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
    	
    	
    	
    	if(user == null) { //There is no user with that name
    		return new Message(ERROR, "This username does not exists");
    	}
		try {
			if(!user.getString("UserPassword").equals(password)) {//the username and password exist you can log in!
				//UserManager.setUser(user); // Here SEND THE RESULT SET TO GET FIXED
				return new Message(ERROR, "Password does not match");


			}else {	
				
				if(user.getString("UserType").equals("M")) {
					return new Message(OK, "Manager");
				}else {
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
            return message;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public Message getTheatreList() {
        // TODO: Return OK with list of theatres as JSONArray in DATA
        SelectTheatre st = new SelectTheatre();
        Message message = st.getTheatreList(databaseController.getTheatreList(ticket.getMovie().getMovieName()));
		return message;

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

    public Message registerNewUser(String username, String password, String name, String address, String email,
            String cardNum, String cardType) {
        // TODO: return OK if successful, return ERROR If username already exists of if
        // supplied type are bad
    	
        return null;
    }

    public Message refundTicket(int ticketNum) {
        // TODO: Return OK if refund is processed with description of Voucher or refund
        // TODO: Return ERROR with DATA == "Sorry, no refunds within 72 hours of a
        // showtime!" OR
        // DATA == "Ticket Number not found"
        return null;
    }
}