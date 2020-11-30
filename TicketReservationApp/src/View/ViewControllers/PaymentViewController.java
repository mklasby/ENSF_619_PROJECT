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
    JSONArray cartContents;

    public PaymentViewController(SubView view, GuiController guiController) {
        super(view, guiController);
        view.registerButtonListener(new ButtonListener());
        view.registerGuiMenuButton(new ButtonListener());
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
            } else {
                clearLists();
            }
        }

    }

    public void submit() {
        if (areFieldsEmpty()) {
            view.flashErrorMessage("Please enter info into all fields!");
            return;
        } else if (cartContents.length() == 0) {
            view.flashErrorMessage("Error! No items in cart!");
            return;
        } else {
            String email = view.getFieldText("email");
            int cardNum;
            try {
                cardNum = Integer.parseInt(view.getFieldText("cardNumField"));
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
                view.display("menuPanel");
                clearLists();
            }

        }

    }

    public void setCartInfo(JSONArray cartContents) {
        this.cartContents = cartContents;
        HashMap<String, JList> lists = view.getLists();
        DefaultListModel listModel = (DefaultListModel) lists.get("resultsList").getModel();
        for (int i = 0; i < cartContents.length(); i++) {
            try {
                System.out.print(cartContents.get(i).toString());

                String prettyText = new String();
                // check if a ticket
                try {
                    JSONObject thisObject = cartContents.getJSONObject(i);
                    String movieName = thisObject.getString("movieName");
                    int seatNumber = thisObject.getInt("seatNumber");
                    String showTime = thisObject.getString("showTime");
                    String theatreName = thisObject.getString("theatreName");
                    double price = thisObject.getDouble("price");
                    prettyText = String.format("%10s, %15s, %15s, %2d, %4.2f", movieName, theatreName, showTime,
                            seatNumber, price);
                } catch (JSONException e) {
                    System.out.println("Not a ticket");
                }
                try {
                    JSONObject thisObject = cartContents.getJSONObject(i);
                    String name = thisObject.getString("name");
                    double amount = thisObject.getDouble("amount");
                    prettyText = String.format("%10s, %4.2f", name, amount);
                } catch (JSONException e) {
                    System.out.println("Not a pay annual");
                }
                listModel.add(i, prettyText);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    public void setPaymentInfo(JSONObject user) {
        if (user == null) {
            return;
        }
        try {
            System.out.print(user.toString());

            JSONObject paymentInfo = user.getJSONObject("paymentInfo");
            int cardNum = paymentInfo.getInt("cardNumber");
            String cardType = paymentInfo.getString("creditCardType");
            String email = user.getString("email");
            HashMap<String, JTextField> fields = view.getFields();
            for (String key : fields.keySet()) {
                if (key.equals("cardNumField")) {
                    fields.get(key).setText("" + cardNum);
                } else if (key.equals("email")) {
                    fields.get(key).setText(email);
                }
            }
            JComboBox comboBox = view.getComboBox("cardTypeComboBox");
            if (cardType.equals("C")) {
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
