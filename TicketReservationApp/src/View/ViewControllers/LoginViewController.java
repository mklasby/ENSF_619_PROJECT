package View.ViewControllers;

import java.util.HashMap;
import CommonMessage.*;
import View.Views.*;

import javax.swing.*;
import javax.swing.event.*;

import org.json.JSONException;

import java.awt.event.*;
import CommonMessage.MessageConstants;

public class LoginViewController extends ViewController implements MessageConstants {

    public LoginViewController(SubView view, GuiController guiController) {
        super(view, guiController);
        view.registerButtonListener(new ButtonListener());
    }

    public class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            System.out.print(cmd);

            if (cmd == "submit") {
                submit();
            } else if (cmd == "clearFields") {
                clearFields();
            }
        }

    }

    @Override
    protected HashMap<String, JTextField> getInfoFields() {
        // TODO Auto-generated method stub
        return null;
    }

    public void submit() {
        if (areFieldsEmpty()) {
            view.flashErrorMessage("Please enter your username and password");
            return;
        } else {
            Message response = guiController.login(view.getFieldText("username"), view.getFieldText("password"));
            if (isErrorMessage(response)) {
                return;
            } else {
                view.flashSuccessMessage("Success, you have logged in. Returning to main menu...");
                try {
                    if (response.get(DATA).equals("Manager")) {
                        guiController.setIsManager(true);
                        guiController.setIsRegistered(false);
                    } else {
                        guiController.setIsRegistered(true);
                        guiController.setIsManager(false);
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                guiController.setIsRegistered(true);
                view.clearFields();
                view.display("menuPanel");

            }

        }

    }

    @Override
    protected boolean areInfoFieldsEmpty() {
        return false;
    }

    @Override
    protected void clearInfoFields() {
        // TODO Auto-generated method stub

    }
}
