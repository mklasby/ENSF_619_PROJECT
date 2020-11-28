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

public class ShowTimeViewController extends ViewController implements MessageConstants {
    int selectedIdx = -1;
    JSONArray showTimes;

    public ShowTimeViewController(SubView view, GuiController guiController) {
        super(view, guiController);
        getShowTimeList();
        view.registerButtonListener(new ButtonListener());
        view.registerListListener(new ResultsListListener());
    }

    private void getShowTimeList() {
        Message showTimeMessage = guiController.getTheatreList();
        try {
            showTimes = showTimeMessage.getJSONArray(DATA);
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
                view.display("seatPanel");
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
