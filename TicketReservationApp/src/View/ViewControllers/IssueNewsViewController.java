package View.ViewControllers;

import java.util.HashMap;
import CommonMessage.*;
import View.Views.*;

import javax.swing.*;
import javax.swing.event.*;

import org.json.JSONException;

import java.awt.event.*;
import CommonMessage.MessageConstants;

public class IssueNewsViewController extends ViewController implements MessageConstants {

    public IssueNewsViewController(SubView view, GuiController guiController) {
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
            } else if (cmd.equals("email")) {
                emailAllUsers();
            }
        }

    }

    @Override
    protected HashMap<String, JTextField> getInfoFields() {
        // TODO Auto-generated method stub
        return null;
    }

    public void emailAllUsers() {
        view.flashSuccessMessage("All users emailed!");
    }

    public void submit() {
        if (areFieldsEmpty()) {
            view.flashErrorMessage("Please enter a movie news file!");
            return;
        } else {
            Message response = guiController.uploadMovieNews(view.getFieldText("newsFileField"));
            if (isErrorMessage(response)) {
                return;
            } else {
                view.flashSuccessMessage("Success! Movie news file uploaded.");
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
