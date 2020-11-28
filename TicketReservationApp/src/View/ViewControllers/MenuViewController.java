package View.ViewControllers;

import java.util.HashMap;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import View.Views.*;

public class MenuViewController extends ViewController {

    public MenuViewController(SubView view, GuiController guiController) {
        super(view, guiController);
        view.registerGuiMenuButton(new MenuListener());
        view.registerButtonListener(new ButtonListener());
    }

    public class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            System.out.print(cmd);

            if (cmd == "login") {
                view.display("loginPanel");
            } else if (cmd == "selectMovie") {
                view.display("selectMovePanel");
            } else if (cmd == "checkout") {
                view.display("checkoutPanel");
            } else if (cmd == "registerUser") {
                view.display("registerUserPanel");
            } else if (cmd == "logout") {
                view.display("logoutPanel");
            } else if (cmd == "payAnnual") {
                view.display("payAnnualPanel");
            } else if (cmd == "issueMovieNews") {
                view.display("issueMovieNewsPanel");
            }
        }

    }

    public class MenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MenuView thisView = (MenuView) view;
            if (guiController.getIsRegistered()) {
                thisView.setToRegisteredView();
            } else if (guiController.getIsManager()) {
                thisView.setToManagerView();
            } else {
                thisView.setToUserView();
            }
            view.display();
        }
    }

    @Override
    protected HashMap<String, JTextField> getInfoFields() {
        return null;
    }

    @Override
    protected boolean areInfoFieldsEmpty() {
        return false;
    }

    @Override
    protected void clearInfoFields() {
        return;

    }
}
