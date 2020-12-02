package View.ViewControllers;

import java.util.HashMap;

import javax.swing.*;
import javax.swing.event.*;

import org.json.JSONException;

import CommonMessage.Message;
import Controller.BossController;

import java.awt.event.*;
import View.Views.*;

public class MenuViewController extends ViewController {
    PaymentViewController nextController;

    public MenuViewController(SubView view, PaymentViewController nextController, GuiController guiController) {
        super(view, guiController);
        this.nextController = nextController;
        MenuView thisView = (MenuView) view;
        thisView.setToUserView();
        view.registerGuiMenuButton(new MenuListener());
        view.registerButtonListener(new ButtonListener());
    }

    public class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            System.out.print(cmd);

            if (cmd.equals("login")) {
                view.display("loginPanel");
            } else if (cmd.equals("selectMovie")) {
                view.display("moviePanel");
            } else if (cmd.equals("checkout")) {
                setUpPaymentPanel();
                view.display("paymentPanel");
            } else if (cmd.equals("registerUser")) {
                view.display("registerUserPanel");
            } else if (cmd.equals("logout")) {
                logout();
            } else if (cmd.equals("payAnnual")) {
                payAnnual();
            } else if (cmd.equals("issueNews")) {
                view.display("issueMovieNewsPanel");
            } else if (cmd.equals("refund")) {
                view.display("refundPanel");
            }
        }

        private void payAnnual() {
            Message response = guiController.payAnnual();
            if (isErrorMessage(response)) {
                return;
            } else {
                try {
                    view.flashSuccessMessage(response.getString(DATA));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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

    public void setUpPaymentPanel() {
        nextController.setCartInfo(guiController.getCartInfo());
        nextController.setPaymentInfo(guiController.getPaymentInfo());

    }

    public void logout() {
        guiController.logout();
        MenuView thiView = (MenuView) view;
        thiView.setToUserView();
        view.flashSuccessMessage("You have logged out. Returning to Main Menu...");
        view.display();
    }

    @Override
    protected boolean areInfoFieldsEmpty() {
        return false;
    }

    @Override
    protected void clearInfoFields() {
        return;

    }

    public void setUserStatus() {
        MenuView thisView = (MenuView) view;
        if (guiController.getIsManager()) {
            thisView.setToManagerView();
        } else if (guiController.getIsRegistered()) {
            thisView.setToRegisteredView();
        } else {
            thisView.setToUserView();
        }
    }
}
