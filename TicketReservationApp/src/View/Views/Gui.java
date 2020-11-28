package View.Views;

import java.awt.event.*;
import java.awt.Dimension;
import java.awt.*;
import javax.swing.*;

public class Gui extends JFrame {
    private JPanel picture;
    private JPanel frame;
    private JButton mainMenuButton;
    private JLabel header;

    public Gui() {
        picture = new JPanel();
        frame = new JPanel();
        mainMenuButton = new JButton("Return to Main Menu");
        header = new JLabel("KL Movie Ticket Reservation System");

        // picture is content, frame is menu buttons and header
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // UNCOMMENT ME TO MAKE FULL SCREEN
        // this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setPreferredSize(ViewConstants.FRAME_DIMENSION);

        picture.setPreferredSize(ViewConstants.PICTURE_DIMENSION);
        picture.setLayout(new CardLayout());

        header.setFont(ViewConstants.HEADER_FONT);

        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.LAST_LINE_START;
        frame.add(mainMenuButton, c);

        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        frame.add(picture, c);

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, ViewConstants.X_DIMENSION / 4 - 10, 0, 0); // top, right, bottom, left
        frame.add(header, c);
    }

    public void display() {
        this.add(frame);
        this.pack();
        this.setVisible(true);
    }

    public void addCard(JPanel card, String cardName) {
        this.picture.add(card, cardName);
    }

    public JPanel getPicture() {
        return this.picture;
    }

    public void registerMainMenuButton(ActionListener listener) {
        registerListener(listener, mainMenuButton);
    }

    private void registerListener(ActionListener listener, JButton comp) {
        comp.addActionListener(listener);
    }

    public void setPanel(String panelName) {
        CardLayout pictureLayout = (CardLayout) picture.getLayout();
        pictureLayout.show(picture, panelName);
    }

    public void registerExitButton(WindowAdapter listener) {
        this.addWindowListener(listener);
    }
}