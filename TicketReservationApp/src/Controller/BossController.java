package Controller;

import org.json.JSONArray;
import org.json.JSONObject;

import CommonMessage.Message;
import CommonMessage.MessageConstants;

public class BossController implements MessageConstants {

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
        return new Message(OK, "Movie selected!");
    }

    public Message getMovieList() {
        // TODO: Return OK with list of movies as JSONArray in DATA
        return null;
    }

    public Message getTheatreList() {
        // TODO: Return OK with list of theatres as JSONArray in DATA
        return null;
    }

    public Message selectTheatre(JSONObject theatre) {
        // TODO: Return OK message
        // TODO: Add theatre to ticket
        return new Message(OK, "Theatre Selected!");
    }

    public Message selectShowTime(JSONObject showTime) {
        // TODO: Return ok if seats remain available ?
        // TODO: Add showtime to ticket
        //
        return new Message(OK, "Showtime selected!");
    }

    public JSONArray getSeatList() {
        // TODO: Return ok if seat selection is good, error otherwise
        // TODO: Add selected seat to ticket
        return null;
    }

}
