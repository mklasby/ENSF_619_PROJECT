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

public class TheatreViewController extends ViewController implements MessageConstants {
    int selectedIdx = -1;
    JSONArray theatres;
    ShowTimeViewController nextController;

    public TheatreViewController(SubView view, ShowTimeViewController nextController, GuiController guiController) {
        super(view, guiController);
        this.nextController = nextController;
        view.registerButtonListener(new ButtonListener());
        view.registerListListener(new ResultsListListener());
    }

    public void getTheatreList() {
        Message theatreMessage = guiController.getTheatreList();
        try {
            theatres = theatreMessage.getJSONArray(DATA);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        paintResults();
    }

    private void paintResults() {
        HashMap<String, JList> lists = view.getLists();
        DefaultListModel listModel = (DefaultListModel) lists.get("resultsList").getModel();
        for (int i = 0; i < theatres.length(); i++) {
            try {
                JSONObject theatre = theatres.getJSONObject(i);
                String name = theatre.getString("theatreName");
                String prettyString = String.format("%10s", name);
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
            JSONObject theatre = theatres.getJSONObject(selectedIdx);
            Message response = guiController.selectTheatre(theatre);
            if (isErrorMessage(response)) {
                return;
            } else {
                view.flashSuccessMessage("Success, please select a showtime...");
                nextController.getShowTimes();
                view.display("showTimePanel");
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
