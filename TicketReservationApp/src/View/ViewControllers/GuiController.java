package View.ViewControllers;

import CommonMessage.Message;

public class GuiController {
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

    public Message login(String fieldText, String fieldText2) {
    }
}
