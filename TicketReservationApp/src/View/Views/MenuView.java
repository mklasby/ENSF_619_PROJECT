package View.Views;

import java.awt.Component;
import java.util.HashMap;
import javax.swing.*;
import java.awt.*;

import CommonMessage.MessageConstants;

public class MenuView extends SubView implements MessageConstants, ViewConstants {
    private JLabel menuLabel;
    private JButton loginButton;
    private JButton selectMovieButton;
    private JButton checkoutButton;
    private JButton registerUserButton;
    private JButton logoutButton;
    private JButton payAnnualButton;
    private JButton issueMovieNewsButton;

    public MenuView(Gui gui, String mainPanelKey) {
        super(gui, mainPanelKey);
        buildMainPanel();
        registerButtons();
        // setToUserView();
        setToRegisteredView();
        // setToManagerView();
        gui.addCard(mainPanel, mainPanelKey);
    }

    @Override
    protected void buildMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        menuLabel = new JLabel("Main Menu");
        menuLabel.setFont(SUBTITLE_FONT);
        menuLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(3, 3, 20, 3); // TOP, RIGHT, BOTTOM LEFT
        mainPanel.add(menuLabel, c);

        c.insets = new Insets(3, 3, 20, 3); // TOP, RIGHT, BOTTOM LEFT
        c.gridy = 1;
        loginButton = new JButton("Login");
        loginButton.setActionCommand("login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(loginButton, c);

        c.gridy = 2;
        selectMovieButton = new JButton("Select Movie");
        selectMovieButton.setActionCommand("selectMovie");
        selectMovieButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(selectMovieButton, c);

        c.gridy = 3;
        checkoutButton = new JButton("Checkout");
        checkoutButton.setActionCommand("checkout");
        checkoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(checkoutButton, c);

        c.gridy = 4;
        registerUserButton = new JButton("Register User");
        registerUserButton.setActionCommand("registerUser");
        registerUserButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(registerUserButton, c);

        c.gridy = 5;
        logoutButton = new JButton("Logout");
        logoutButton.setActionCommand("logout");
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(logoutButton, c);

        c.gridy = 6;
        payAnnualButton = new JButton("Pay Annual Membership Dues");
        payAnnualButton.setActionCommand("payAnnual");
        payAnnualButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(payAnnualButton, c);

        c.gridy = 7;
        issueMovieNewsButton = new JButton("Issue Movie News");
        issueMovieNewsButton.setActionCommand("Issue Movie News");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(issueMovieNewsButton, c);

    }

    public void setToManagerView() {
        setToRegisteredView();
        issueMovieNewsButton.setEnabled(true);
    }

    public void setToRegisteredView() {
        issueMovieNewsButton.setEnabled(false);
        registerUserButton.setEnabled(false);
        logoutButton.setEnabled(true);
        payAnnualButton.setEnabled(true);
    }

    public void setToUserView() {
        registerUserButton.setEnabled(true);
        logoutButton.setEnabled(false);
        payAnnualButton.setEnabled(false);
        issueMovieNewsButton.setEnabled(false);
    }

    @Override
    protected void registerFields() {

    }

    @Override
    protected void registerButtons() {
        buttons = new HashMap<String, JButton>();
        buttons.put("loginButton", loginButton);
        buttons.put("selectMovieButton", selectMovieButton);
        buttons.put("checkoutButton", checkoutButton);
        buttons.put("registerUserButton", registerUserButton);
        buttons.put("logoutButton", logoutButton);
        buttons.put("payAnnualButton", payAnnualButton);
        buttons.put("issueMovieNewsButton", issueMovieNewsButton);
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
