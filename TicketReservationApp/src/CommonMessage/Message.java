package CommonMessage;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Wrapper class to send messages with meta data b/w front and back end
 * 
 * @author Mike Lasby
 * @since Nov. 28, 2020
 * @version 1.0
 */
public class Message extends JSONObject implements MessageConstants {
    public String status;
    public Object data;

    public Message(String status, Object data) {
        super();
        this.status = status;
        this.data = data;
        putFields();
    }

    public String stringEncode() {
        putFields();
        return this.toString();
    }

    public JSONObject jsonEncode() {
        putFields();
        return this;
    }

    private void putFields() {
        try {
            put(STATUS, status);
            put(DATA, data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Message(String rawData) throws JSONException {
        super(rawData);
    };

    public void quitMessage() throws JSONException {
        put(QUIT, true);
    }
}
