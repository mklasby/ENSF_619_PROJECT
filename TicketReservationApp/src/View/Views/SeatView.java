package View.Views;

import java.awt.Component;
import java.util.HashMap;
import javax.swing.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import CommonMessage.MessageConstants;

/**
 * Class to select seats
 * 
 * @version 1.0
 * @since Nov 29, 2020
 * @author 619 Group 8
 */
public class SeatView extends SubView implements MessageConstants, ViewConstants {
    private JLabel mainLabel;
    private JButton submitButton;
    private HashMap<Integer, JButton> seatButtons;

    public SeatView(Gui gui, String mainPanelKey) {
        super(gui, mainPanelKey);
        buildMainPanel();
        registerButtons();
        gui.addCard(mainPanel, mainPanelKey);
    }

    @Override
    protected void buildMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        mainLabel = new JLabel("Please select a seat:");
        mainLabel.setFont(SUBTITLE_FONT);
        mainLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 10;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(3, 3, 20, 40); // TOP, RIGHT, BOTTOM LEFT
        mainPanel.add(mainLabel, c);

        c.gridwidth = 1;
        c.insets = new Insets(1, 1, 1, 1);
        seatButtons = new HashMap<Integer, JButton>();
        int seatId = 1;
        for (int row = 0; row < 1; row++) {
            for (int col = 0; col < 10; col++) {
                c.gridx = col;
                c.gridy = 1 + row;
                JButton thisSeat = new JButton("Seat " + seatId);
                thisSeat.setActionCommand("" + seatId);
                mainPanel.add(thisSeat, c);
                seatButtons.put(seatId, thisSeat);
                seatId++;
            }
        }
    }

    public void setSeatStatus(int seatId, boolean isReserved) {
        JButton seat = seatButtons.get(seatId);
        if (isReserved) {
            seat.setEnabled(false);
        } else {
            seat.setEnabled(true);
        }
    }

    @Override
    protected void registerFields() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void registerButtons() {
        buttons = new HashMap<String, JButton>();
        for (int key : seatButtons.keySet()) {
            buttons.put(seatButtons.get(key).getActionCommand(), seatButtons.get(key));
        }
    }

    @Override
    protected void registerRadioButtons() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void registerButtonGroups() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void registerLists() {

    }

    @Override
    protected void registerLabels() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void registerComboBoxes() {
        // TODO Auto-generated method stub

    }

}
