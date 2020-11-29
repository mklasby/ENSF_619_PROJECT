package View.Views;

import java.awt.Component;
import java.util.HashMap;
import javax.swing.*;
import java.awt.*;
import CommonMessage.MessageConstants;

public class IssueNewsView extends SubView implements MessageConstants, ViewConstants {

    private JLabel mainLabel;
    private JLabel newsFileLabel;
    private JTextField newsFileField;
    private JButton emailButton;
    private JButton submitButton;
    private JButton clearFieldsButton;

    public IssueNewsView(Gui gui, String mainPanelKey) {
        super(gui, mainPanelKey);
        buildMainPanel();
        registerButtons();
        registerFields();
        gui.addCard(mainPanel, mainPanelKey);
    }

    @Override
    protected void buildMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        mainLabel = new JLabel("Issue Movie News:");
        mainLabel.setFont(SUBTITLE_FONT);
        mainLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(3, 3, 20, 3); // TOP, RIGHT, BOTTOM LEFT
        mainPanel.add(mainLabel, c);

        c.insets = new Insets(3, 3, 20, 3); // TOP, RIGHT, BOTTOM LEFT
        c.gridy = 1;
        c.gridwidth = 1;
        newsFileLabel = new JLabel("Movie News File Name: ");
        newsFileLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(newsFileLabel, c);

        c.gridx = 1;
        newsFileField = new JTextField(15);
        newsFileField.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(newsFileField, c);

        c.gridy = 2;
        c.gridx = 0;
        clearFieldsButton = new JButton("Clear Fields");
        clearFieldsButton.setActionCommand("clearFields");
        clearFieldsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(clearFieldsButton, c);

        c.gridx = 1;
        submitButton = new JButton("Upload File");
        submitButton.setActionCommand("submit");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(submitButton, c);

        c.gridwidth = 2;
        c.gridy = 3;
        c.gridx = 0;
        emailButton = new JButton("Email All Users");
        clearFieldsButton.setActionCommand("email");
        clearFieldsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(clearFieldsButton, c);
    }

    @Override
    protected void registerFields() {
        fields = new HashMap<String, JTextField>();
        fields.put("newsFileField", newsFileField);
    }

    @Override
    protected void registerButtons() {
        buttons = new HashMap<String, JButton>();
        buttons.put("submit", submitButton);
        buttons.put("clear", clearFieldsButton);
        buttons.put("email", emailButton);
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
        // TODO Auto-generated method stub

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
