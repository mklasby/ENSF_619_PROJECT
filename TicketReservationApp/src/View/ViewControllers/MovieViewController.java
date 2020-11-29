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

public class MovieViewController extends ViewController implements MessageConstants {
    int selectedIdx = -1;
    JSONArray movies;
    TheatreViewController nextController;

    public MovieViewController(SubView view, TheatreViewController nextController, GuiController guiController) {
        super(view, guiController);
        this.nextController = nextController;
        getMovieList();
        paintResults();
        view.registerButtonListener(new ButtonListener());
        view.registerListListener(new ResultsListListener());
    }

    private void getMovieList() {
        Message movieMessage = guiController.getMovieList();
        try {
            movies = movieMessage.getJSONArray(DATA);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void paintResults() {
        HashMap<String, JList> lists = view.getLists();
        DefaultListModel listModel = (DefaultListModel) lists.get("resultsList").getModel();
        for (int i = 0; i < movies.length(); i++) {
            try {
                JSONObject movie = movies.getJSONObject(i);
                String name = movie.getString("movieName");
                Double price = movie.getDouble("moviePrice");
                Boolean isEarlyAccess = movie.getBoolean("isEarlyAccess");

                String prettyString = String.format("%10s, %4f, Early Access movie: %b", name, price, isEarlyAccess);

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
            JSONObject movie = movies.getJSONObject(selectedIdx);
            Message response = guiController.selectMovie(movie);
            if (isErrorMessage(response)) {
                return;
            } else {
                view.flashSuccessMessage("Success, please select a theatre...");
                nextController.getTheatreList();
                view.display("theatrePanel");
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
