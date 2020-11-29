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
import java.sql.Timestamp;
import java.text.DateFormat;
import java.sql.Date;

public class ShowTimeViewController extends ViewController implements MessageConstants {
    int selectedIdx = -1;
    JSONArray showTimes;
    SeatViewController nextController;

    public ShowTimeViewController(SubView view, SeatViewController nextController, GuiController guiController) {
        super(view, guiController);
        this.nextController = nextController;
        view.registerButtonListener(new ButtonListener());
        view.registerListListener(new ResultsListListener());
    }

    public void getShowTimes() {
        Message showTimeMessage = guiController.getShowTimeList();
        try {
            showTimes = showTimeMessage.getJSONArray(DATA);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        paintResults();
    }

    private void paintResults() {
        HashMap<String, JList> lists = view.getLists();
        DefaultListModel listModel = (DefaultListModel) lists.get("resultsList").getModel();
        for (int i = 0; i < showTimes.length(); i++) {
            try {
                JSONObject showTime = showTimes.getJSONObject(i);
                String time = showTime.get("startTime").toString();
                String prettyString = String.format("%10s", time);

                listModel.add(i, prettyString);
            } catch (JSONException e) {
                e.printStackTrace();
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
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected void clearInfoFields() {
        // TODO Auto-generated method stub

    }

    public void submit() {
        try {
            JSONObject showTime = showTimes.getJSONObject(selectedIdx);
            Message response = guiController.selectShowTime(showTime);
            if (isErrorMessage(response)) {
                return;
            } else {
                view.flashSuccessMessage("Success, please select a seat...");
                nextController.getSeatList();
                view.display("seatPanel");
                clearLists();
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

            if (cmd == "submit") {
                submit();
            }
        }

    }

    public class ResultsListListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            JList list = (JList) e.getSource();
            selectedIdx = list.getSelectedIndex();
            System.out.printf("LIST SELECTION: Index %d\n", selectedIdx);
        }
    }
}
