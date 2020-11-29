package View.Views;

import java.awt.Component;
import java.util.HashMap;
import javax.swing.*;
import java.awt.*;
import CommonMessage.MessageConstants;

public class RegisterUserView extends SubView implements MessageConstants, ViewConstants {

    // -usernameField: JTextField
    // -passwordField: JTextField
    // -nameField: JTextField
    // -addressField: JTextField
    // -cardNumField: JTextField
    // -cardTypeField: JTextField
    // -emailField: JTextField

    private JLabel mainLabel;
    private JLabel userNameLabel;
    private JLabel passwordLabel;
    private JLabel nameLabel;
    private JLabel addressLabel;
    private JLabel emailLabel;
    private JLabel cardNumLabel;
    private JLabel cardTypeLabel;
    private JTextField userNameField;
    private JTextField passwordField;
    private JTextField nameField;
    private JTextField addressField;
    private JTextField emailField;
    private JTextField cardNumField;
    private JComboBox cardTypeComboBox;
    private JButton submitButton;
    private JButton clearFieldsButton;

    public RegisterUserView(Gui gui, String mainPanelKey) {
        super(gui, mainPanelKey);
        buildMainPanel();
        registerButtons();
        registerFields();
        registerComboBoxes();
        gui.addCard(mainPanel, mainPanelKey);
    }

    @Override
    protected void buildMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        mainLabel = new JLabel("Please enter your information below: ");
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
        userNameLabel = new JLabel("Username: ");
        userNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(userNameLabel, c);

        c.gridx = 1;
        userNameField = new JTextField(15);
        userNameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(userNameField, c);

        c.insets = new Insets(3, 3, 20, 3); // TOP, RIGHT, BOTTOM LEFT
        c.gridy = 2;
        c.gridx = 0;
        passwordLabel = new JLabel("Password: ");
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(passwordLabel, c);

        c.gridx = 1;
        passwordField = new JTextField(15);
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(passwordField, c);

        c.insets = new Insets(3, 3, 20, 3); // TOP, RIGHT, BOTTOM LEFT
        c.gridy = 3;
        c.gridx = 0;
        nameLabel = new JLabel("Name: ");
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(nameLabel, c);

        c.gridx = 1;
        nameField = new JTextField(15);
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(nameField, c);

        c.insets = new Insets(3, 3, 20, 3); // TOP, RIGHT, BOTTOM LEFT
        c.gridy = 4;
        c.gridx = 0;
        addressLabel = new JLabel("Address: ");
        addressLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(addressLabel, c);

        c.gridx = 1;
        addressField = new JTextField(15);
        addressField.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(addressField, c);

        c.insets = new Insets(3, 3, 20, 3); // TOP, RIGHT, BOTTOM LEFT
        c.gridy = 5;
        c.gridx = 0;
        emailLabel = new JLabel("Email: ");
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(emailLabel, c);

        c.gridx = 1;
        emailField = new JTextField(15);
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(emailField, c);

        c.insets = new Insets(3, 3, 20, 3); // TOP, RIGHT, BOTTOM LEFT
        c.gridy = 6;
        c.gridx = 0;
        cardNumLabel = new JLabel("Card Number: ");
        cardNumLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(cardNumLabel, c);

        c.gridx = 1;
        cardNumField = new JTextField(15);
        cardNumField.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(cardNumField, c);

        c.insets = new Insets(3, 3, 20, 3); // TOP, RIGHT, BOTTOM LEFT
        c.gridy = 7;
        c.gridx = 0;
        cardTypeLabel = new JLabel("Card Type: ");
        cardTypeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(cardTypeLabel, c);

        c.gridx = 1;
        String[] cardType = { "Credit", "Debit" };
        cardTypeComboBox = new JComboBox<String>(cardType);
        mainPanel.add(cardTypeComboBox, c);

        c.gridy = 8;
        c.gridx = 0;
        clearFieldsButton = new JButton("Clear Fields");
        clearFieldsButton.setActionCommand("clearFields");
        clearFieldsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(clearFieldsButton, c);

        c.gridx = 1;
        submitButton = new JButton("Submit");
        submitButton.setActionCommand("submit");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(submitButton, c);
    }

    @Override
    protected void registerFields() {
        fields = new HashMap<String, JTextField>();
        fields.put("username", userNameField);
        fields.put("password", passwordField);
        fields.put("nameField", nameField);
        fields.put("addressField", addressField);
        fields.put("emailField", emailField);
        fields.put("cardNumField", cardNumField);
    }

    @Override
    protected void registerButtons() {
        buttons = new HashMap<String, JButton>();
        buttons.put("submit", submitButton);
        buttons.put("clear", clearFieldsButton);
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
        comboBoxes = new HashMap<String, JComboBox>();
        comboBoxes.put("cardTypeComboBox", cardTypeComboBox);
    }

}