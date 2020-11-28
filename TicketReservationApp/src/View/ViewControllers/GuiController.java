package View.ViewControllers;

import Controller.*;

import org.json.JSONArray;
import org.json.JSONObject;

import CommonMessage.Message;

public class GuiController {
    public BossController boss = new BossController();
    public boolean isRegistered = false;
    public boolean isManager = false;

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

    public JSONArray getMovieList() {
        return null;
    }

    public void selectMovie(JSONObject movie) {
        boss.selectMovie(movie);
    }
}
