package Controller;

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
    }

}
