package View.Views;

import java.awt.Component;
import java.util.HashMap;
import javax.swing.*;
import java.awt.*;
import CommonMessage.MessageConstants;

/**
 * Class to select a movie
 * 
 * @version 1.0
 * @since Nov 29, 2020
 * @author 619 Group 8
 */
public class MovieView extends SubView implements MessageConstants, ViewConstants {
    private JLabel mainLabel;
    private JList resultsList;
    private JButton submitButton;

    public MovieView(Gui gui, String mainPanelKey) {
        super(gui, mainPanelKey);
        buildMainPanel();
        registerButtons();
        registerLists();
        gui.addCard(mainPanel, mainPanelKey);
    }

    @Override
    protected void buildMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        mainLabel = new JLabel("Please select a movie from the list below:");
        mainLabel.setFont(SUBTITLE_FONT);
        mainLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(3, 3, 20, 3); // TOP, RIGHT, BOTTOM LEFT
        mainPanel.add(mainLabel, c);

        c.insets = new Insets(3, 3, 10, 3); // top, right, bottom, left;
        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        DefaultListModel listModel = new DefaultListModel<String>();
        resultsList = new JList<String>(listModel);
        resultsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        resultsList.setLayoutOrientation(JList.VERTICAL);
        resultsList.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(resultsList);
        scrollPane.setPreferredSize(new Dimension(500, 500));
        mainPanel.add(scrollPane, c);

        c.insets = new Insets(3, 3, 3, 3); // top, right, bottom, left;
        c.gridy = 2;
        submitButton = new JButton("Submit");
        submitButton.setActionCommand("submit");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(submitButton, c);

    }

    @Override
    protected void registerFields() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void registerButtons() {
        buttons = new HashMap<String, JButton>();
        buttons.put("submit", submitButton);
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
        lists = new HashMap<String, JList>();
        lists.put("resultsList", resultsList);
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
