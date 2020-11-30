package View.Views;

import java.awt.Component;
import java.util.HashMap;
import javax.swing.*;
import java.awt.*;
import CommonMessage.MessageConstants;

/**
 * Class to checkout cart and enter payment info
 * 
 * @version 1.0
 * @since Nov 29, 2020
 * @author 619 Group 8
 */
public class PaymentView extends SubView implements MessageConstants, ViewConstants {

    private JLabel mainLabel;
    private JLabel cartLabel;
    private JLabel emailLabel;
    private JLabel cardNumLabel;
    private JLabel cardTypeLabel;
    private JList resultsList;
    private JTextField emailField;
    private JTextField cardNumField;
    private JComboBox cardTypeComboBox;
    private JButton submitButton;
    private JButton clearFieldsButton;

    public PaymentView(Gui gui, String mainPanelKey) {
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

        mainLabel = new JLabel("Please enter your payment information below: ");
        mainLabel.setFont(SUBTITLE_FONT);
        mainLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(3, 3, 20, 3); // TOP, RIGHT, BOTTOM LEFT
        mainPanel.add(mainLabel, c);

        c.insets = new Insets(3, 3, 3, 3); // TOP, RIGHT, BOTTOM LEFT
        c.gridy = 1;
        c.gridwidth = 2;
        cartLabel = new JLabel("Cart Contents: ");
        cartLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(cartLabel, c);

        c.gridy = 2;
        c.gridwidth = 2;
        c.gridheight = 2;
        DefaultListModel listModel = new DefaultListModel<String>();
        resultsList = new JList<String>(listModel);
        resultsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        resultsList.setLayoutOrientation(JList.VERTICAL);
        resultsList.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(resultsList);
        scrollPane.setPreferredSize(new Dimension(500, 100));
        mainPanel.add(scrollPane, c);

        c.insets = new Insets(20, 3, 3, 3); // TOP, RIGHT, BOTTOM LEFT
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridy = 4;
        c.gridx = 0;
        emailLabel = new JLabel("Email: ");
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(emailLabel, c);

        c.gridx = 1;
        emailField = new JTextField(15);
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(emailField, c);

        c.gridy = 5;
        c.gridx = 0;
        cardNumLabel = new JLabel("Card / Voucher Number: ");
        cardNumLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(cardNumLabel, c);

        c.gridx = 1;
        cardNumField = new JTextField(15);
        cardNumField.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(cardNumField, c);

        c.gridy = 6;
        c.gridx = 0;
        cardTypeLabel = new JLabel("Card Type: ");
        cardTypeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(cardTypeLabel, c);

        c.gridx = 1;
        String[] cardType = { "Credit", "Debit", "Voucher" };
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
        fields.put("username", emailField);
        fields.put("password", cardNumField);
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