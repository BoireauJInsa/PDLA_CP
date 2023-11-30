package org.apo.vue;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RequestPanel extends JPanel {

    private int messageSize;

    public RequestPanel(FrameView frameView) {

        this.setLayout(null);

        JLabel messageLabel = new JLabel("Veuillez entrer votre demande :");
        messageLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setSize(frameView.getWidth(), frameView.getHeight()/10);
        messageLabel.setBounds(frameView.getWidth()/2 - messageLabel.getWidth()/2, frameView.getHeight()/20, messageLabel.getWidth(), messageLabel.getHeight());
        this.add(messageLabel);

        JTextArea messageTextField = new JTextArea();
        messageTextField.setLineWrap(true);
        messageTextField.setWrapStyleWord(true);
        messageTextField.setSize(frameView.getWidth()/2, frameView.getHeight()/2);
        messageTextField.setBounds(frameView.getWidth()/2 - messageTextField.getWidth()/2, frameView.getHeight()/2 - messageTextField.getHeight()/2, messageTextField.getWidth(), messageTextField.getHeight());

        JLabel charCountLabel = new JLabel("0/250");
        charCountLabel.setHorizontalAlignment(JLabel.CENTER);
        charCountLabel.setSize(messageTextField.getWidth()/5, messageTextField.getHeight()/20);
        charCountLabel.setBounds(messageTextField.getX() + messageTextField.getWidth() - charCountLabel.getWidth(), messageTextField.getY() + messageTextField.getHeight(), charCountLabel.getWidth(), charCountLabel.getHeight());
        this.add(charCountLabel);
        messageTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                messageSize = messageTextField.getText().length();
                charCountLabel.setText(messageSize + "/250");
                if (messageSize > 250) {
                    charCountLabel.setForeground(Color.red);
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                messageSize = messageTextField.getText().length();
                charCountLabel.setText(messageSize + "/250");
                if (messageSize <= 250) {
                    charCountLabel.setForeground(Color.black);
                }
            }
            @Override
            public void changedUpdate(DocumentEvent e) {}
        });

        this.add(messageTextField);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFocusable(false);
        cancelButton.setSize(frameView.getWidth()/5, frameView.getHeight()/15);
        cancelButton.setBounds(frameView.getWidth()/4, frameView.getHeight() - 100, cancelButton.getWidth(), cancelButton.getHeight());
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                frameView.getDemandsPanel().setVisible(true);
            }
        });
        this.add(cancelButton);

        JButton submitButton = new JButton("Submit");
        submitButton.setFocusable(false);
        submitButton.setSize(frameView.getWidth()/5, frameView.getHeight()/15);
        submitButton.setBounds(frameView.getWidth()/2 + (frameView.getWidth()/4 - frameView.getWidth()/5), frameView.getHeight() - 100, submitButton.getWidth(), submitButton.getHeight());
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkError()) {

                }
            }
        });
        this.add(submitButton);

        this.setVisible(false);
    }

    public boolean checkError() {
        if (messageSize > 250) {
            new PopUpWindow("Veuillez entrer un message plus court", JOptionPane.INFORMATION_MESSAGE);
        }
        return (messageSize <= 250);
    }

}
