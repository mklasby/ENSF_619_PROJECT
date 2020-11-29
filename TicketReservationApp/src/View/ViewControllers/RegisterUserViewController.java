package View.ViewControllers;

import java.util.HashMap;
import CommonMessage.*;
import View.Views.*;

import javax.swing.*;
import javax.swing.event.*;

import org.json.JSONException;

import java.awt.event.*;
import CommonMessage.MessageConstants;

public class RegisterUserViewController extends ViewController implements MessageConstants {

    public RegisterUserViewController(SubView view, GuiController guiController) {
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

    public void submit() {
        if (areFieldsEmpty()) {
            view.flashErrorMessage("Please enter info into all fields!");
            return;
        } else {
            String username = view.getFieldText("username");
            String password = view.getFieldText("password");
            String name = view.getFieldText("name");
            String address = view.getFieldText("address");
            String email = view.getFieldText("email");
            Integer cardNum = Integer.parseInt(view.getFieldText("cardNum"));
            String cardType = view.getComboBox("cardTypeComboBox").getSelectedItem().toString();

            Message response = guiController.registerNewUser(username, password, name, address, email, cardNum,
                    cardType);

            if (isErrorMessage(response)) {
                return;
            } else {
                view.flashSuccessMessage("Success, you have been registered! Please log in from the main menu!");
                view.clearFields();
                view.display("menuPanel");
            }
        }
    }

    @Override
    protected HashMap<String, JTextField> getInfoFields() {
        // TODO Auto-generated method stub
        return null;
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
