package View.ViewControllers;

import java.util.HashMap;
import View.Views.*;
import java.awt.event.*;

import javax.swing.JTextField;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import CommonMessage.Message;

public class SeatViewController extends ViewController {
    int selectedIdx = -1;
    JSONArray seats;

    public SeatViewController(SubView view, GuiController guiController) {
        super(view, guiController);
        view.registerButtonListener(new ButtonListener());
    }

    public void getSeatList() {
        JSONObject seatMessage = guiController.getSeatList();
        try {
            seats = seatMessage.getJSONArray(DATA);
            setSeatStatus();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setSeatStatus() {
        SeatView thisView = (SeatView) view;
        try {
            for (int i = 0; i < seats.length(); i++) {
                JSONObject thisSeat = seats.getJSONObject(i);
                int seatId = thisSeat.getInt("seatNum");
                boolean isReserved = thisSeat.getBoolean("isReserved");
                thisView.setSeatStatus(seatId, isReserved);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void selectSeat(String cmd) {
        int seatSelected = Integer.parseInt(cmd);
        selectedIdx = seatSelected;
        try {
            JSONObject seat = seats.getJSONObject(selectedIdx);
            Message response = guiController.selectSeat(seat);
            if (isErrorMessage(response)) {
                return;
            } else {
                // TODO: Add prompt to choose between payment or more tickets?
                view.flashSuccessMessage("Success! Ticket reserved. Returning to main menu...");
                view.display("menuPanel");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            System.out.print(cmd);
            selectSeat(cmd);
        }

    }

    @Override
    protected HashMap<String, JTextField> getInfoFields() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected boolean areInfoFieldsEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected void clearInfoFields() {
        // TODO Auto-generated method stub

    }

}
