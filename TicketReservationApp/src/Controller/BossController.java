package Controller;

import CommonMessage.Message;
import CommonMessage.MessageConstants;

public class BossController implements MessageConstants {

    public Message login(String username, String password) {
        // TODO: Return STATUS=ERROR if login fails
        // TODO: Return STATUS=OK and DATA="MANAGER"
        // TODO: RETURN STATUS=OK AND DATA="REGISTERED"
        return new Message(OK, REGISTERED);
    }

}
