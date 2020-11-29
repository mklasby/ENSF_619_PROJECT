package View.ViewControllers;

import java.util.HashMap;
import CommonMessage.*;
import View.Views.*;

import javax.swing.*;
import javax.swing.event.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.event.*;
import CommonMessage.MessageConstants;

public class PaymentViewController extends ViewController implements MessageConstants {

    public PaymentViewController(SubView view, GuiController guiController) {
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
            String email = view.getFieldText("email");
            int cardNum;
            try {
                cardNum = Integer.parseInt(view.getFieldText("cardNum"));
            } catch (NumberFormatException e) {
                view.flashErrorMessage("Please enter credit card info as an Integer!");
                return;
            }
            String cardType = view.getComboBox("cardTypeComboBox").getSelectedItem().toString();

            Message response = guiController.processPayment(email, cardNum, cardType);
            if (isErrorMessage(response)) {
                return;
            } else {
                view.flashSuccessMessage(
                        "Success, payment received. You have been emailed a copy of your Ticket and Receipt. Returning to main menu...");
                view.clearFields();
                view.display("menuPanel");
            }

        }

    }

    public void setCartInfo(JSONArray cartContents) {
        HashMap<String, JList> lists = view.getLists();
        DefaultListModel listModel = (DefaultListModel) lists.get("resultsList").getModel();
        for (int i = 0; i < cartContents.length(); i++) {
            try {
                // TODO: Pretty print
                listModel.add(i, cartContents.get(i).toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void setPaymentInfo(JSONObject paymentInfo) {
        try {
            String cardNum = paymentInfo.getString("cardNum");
            String cardType = paymentInfo.getString("cardType");
            String email = paymentInfo.getString("email");
            HashMap<String, JTextField> fields = view.getFields();
            for (String key : fields.keySet()) {
                if (key.equals("cardNumField")) {
                    fields.get(key).setText(cardNum);
                } else if (key.equals("email")) {
                    fields.get(key).setText(cardNum);
                }
            }
            JComboBox comboBox = view.getComboBox("cardTypeComboBox");
            if (cardType.equals("Credit")) {
                comboBox.setSelectedIndex(0);
            } else {
                comboBox.setSelectedIndex(1);
            }
        } catch (JSONException e) {
            e.printStackTrace();
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
