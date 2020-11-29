package Controller;

import java.sql.SQLException;

import org.json.JSONException;

import org.json.JSONArray;

import org.json.JSONObject;

import CommonMessage.Message;
import CommonMessage.MessageConstants;
import Model.TheatreModel.BrowseMovie;
import Model.TheatreModel.Movie;
import Model.TheatreModel.Ticket;
import Model.TheatreModel.SelectTheatre;

public class BossController implements MessageConstants {

    private Ticket ticket;
    private UserManager user;

    public BossController() {
        user = new UserManager();
    }

    }

    public Message login(String username, String password) {
        // TODO: Return STATUS=ERROR if login fails
        // TODO: Return STATUS=OK and DATA="MANAGER"
        // TODO: RETURN STATUS=OK AND DATA="REGISTERED"
        return new Message(OK, REGISTERED);
    }

    public void logoutUser() {
        // TODO: Reset user status to normal user
    }

    public Message selectMovie(JSONObject movie) {
        // TODO: check if user is permitted to book this movie, return ERROR if not
        // TODO: If user can select ticket, return OK message.
        // TODO: Create ticket object and add this movie to it.

        try {
            Movie selectedMovie = new Movie(movie);
            ticket = new Ticket(user);
            // Check if the movie is early access
            if (selectedMovie.isEarlyAccess() == true) {
                // Check if the user is registered.
                if (user.isRegistered() == true) {
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
        DatabaseController db = new DatabaseController();
        BrowseMovie bm = new BrowseMovie();
        try {
            Message message = bm.getMovieList(db.getMoviesList());
            return message;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public Message getTheatreList() {
        // TODO: Return OK with list of theatres as JSONArray in DATA
        DatabaseController db = new DatabaseController();
        SelectTheatre st = new SelectThreatre();

        try {
            Message message = st.getTheatreList(db.getTheatreList(ticket.getMovie().getMovieName()));
            return message;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public Message selectTheatre(JSONObject theatre) {
        // TODO: Return OK message
        // TODO: Add theatre to ticket
    	Theatre theatre = new Theatre(theatre);
    	DatabaseController db = new DatabaseController();
    	
    	ticket.
    	
        return new Message(OK, "Theatre Selected!");
    }

    public Message getShowTimeList() {
        // TODO: Return OK with list of theatres as JSONArray in DATA
    	DatabaseController db = new DatabaseController();
    	SelectShowTime sst = new SelectShowTime();
    	
    	try {
			Message message = stt.getShowTimeList(db.getShowTimeList(ticket.getMovie().getMovieName(),
																	ticket.getTheatre().getTheatreName()));
			return message;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

    public Message selectShowTime(JSONObject showTime) {
        // TODO: Return OK and set this ticket to this showtime
        return null;
    }

    public JSONArray getSeatList() {
        // TODO: OK return all seats for this theatre, movie, showtime combo\
        // TODO: Return ERROR if all seats full?
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