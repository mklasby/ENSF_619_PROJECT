package View.Views;

import java.awt.Component;
import java.util.HashMap;
import javax.swing.*;
import java.awt.*;
import CommonMessage.MessageConstants;

/**
 * Class to refund an existing ticket
 * 
 * @version 1.0
 * @since Nov 29, 2020
 * @author 619 Group 8
 */
public class RefundView extends SubView implements MessageConstants, ViewConstants {

    private JLabel mainLabel;
    private JLabel ticketNumberLabel;
    private JTextField ticketNumberField;
    private JButton submitButton;
    private JButton clearFieldsButton;

    public RefundView(Gui gui, String mainPanelKey) {
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

        mainLabel = new JLabel("Please enter a ticket number to process refund:");
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
        ticketNumberLabel = new JLabel("Ticket Number: ");
        ticketNumberLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(ticketNumberLabel, c);

        c.gridx = 1;
        ticketNumberField = new JTextField(15);
        ticketNumberField.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(ticketNumberField, c);

        c.gridy = 3;
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
        fields.put("ticketNumberField", ticketNumberField);
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
        // TODO Auto-generated method stub

    }

}
