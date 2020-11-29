package View.ViewControllers;

import Controller.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import CommonMessage.*;

public class GuiController implements MessageConstants {
    public BossController boss;
    public boolean isRegistered = false;
    public boolean isManager = false;

    public GuiController(BossController boss) {
        this.boss = boss;
    }

    public boolean isIsRegistered() {
        return this.isRegistered;
    }

    public boolean getIsRegistered() {
        return this.isRegistered;
    }

    public void setIsRegistered(boolean isRegistered) {
        this.isRegistered = isRegistered;
    }

    public boolean isIsManager() {
        return this.isManager;
    }

    public boolean getIsManager() {
        return this.isManager;
    }

    public void setIsManager(boolean isManager) {
        this.isManager = isManager;
    }

    public Message login(String username, String password) {
        return boss.login(username, password);
    }

    public void logout() {
        boss.logoutUser();
    }

    public Message getMovieList() {
        Message response = boss.getMovieList();
        return response;
    }

    public Message selectMovie(JSONObject movie) {
        Message response = boss.selectMovie(movie);
        return response;
    }

    public Message getTheatreList() {
        return boss.getTheatreList();
    }

    public Message selectTheatre(JSONObject theatre) {
        Message response = boss.selectTheatre(theatre);
        return response;
    }

    public Message selectShowTime(JSONObject showTime) {
        Message response = boss.selectShowTime(showTime);
        return response;
    }

    public Message getShowTimeList() {
        return boss.getShowTimeList();
    }

    public Message getSeatList() {
        return boss.getSeatList();
    }

    public Message selectSeat(JSONObject seat) {
        return boss.selectSeat(seat);
    }

    public boolean isRefundValid(int ticketNum) {
        return false;
    }

    public Message refundTicket(int ticketNum) {
        return boss.refundTicket(ticketNum);
    }

    public Message uploadMovieNews(String fieldText) {
        return null;
    }

    public Message processPayment(String email, String cardNum, String cardType) {
        return null;
    }

    public JSONArray getCartInfo() {
        try {
            Message response = boss.getCartInfo();
            if (response.get(STATUS).equals(ERROR)) {
                return new JSONArray();
            } else {
                return response.getJSONArray(DATA);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public JSONObject getPaymentInfo() {
        try {
            Message response = boss.getPaymentInfo();
            if (response.get(STATUS).equals(ERROR)) {
                return new JSONObject();
            } else {
                return response.getJSONObject(DATA);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Message registerNewUser(String username, String password, String name, String address, String email,
            int cardNum, String cardType) {
        return boss.registerNewUser(false, "R", username, password, name, address, email, cardNum);
    }
}
