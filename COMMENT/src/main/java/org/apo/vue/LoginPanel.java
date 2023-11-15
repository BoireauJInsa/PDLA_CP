package org.apo.vue;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {

    public LoginPanel(FrameView frameView, int width, int height) {

        this.setLayout(null);

        JLabel logInLabel = new JLabel("Log in");
        logInLabel.setHorizontalAlignment(JLabel.CENTER);
        logInLabel.setSize(width/10, height/20);
        logInLabel.setBounds(width/2 - logInLabel.getWidth()/2, 50, logInLabel.getWidth(), logInLabel.getHeight());
        logInLabel.setVisible(true);
        this.add(logInLabel);

        JLabel loginLabel = new JLabel("Login : ");
        loginLabel.setHorizontalAlignment(JLabel.CENTER);
        loginLabel.setSize(width/3, height/15);
        loginLabel.setBounds(0, logInLabel.getY() + 80, loginLabel.getWidth(), loginLabel.getHeight());
        this.add(loginLabel);

        JTextField loginTextField = new JTextField();
        loginTextField.setSize(width/3, height/15);
        loginTextField.setBounds(width/3, logInLabel.getY() + 80, loginTextField.getWidth(), loginTextField.getHeight());
        loginTextField.setVisible(true);
        this.add(loginTextField);

        JLabel passwordLabel = new JLabel("Password : ");
        passwordLabel.setHorizontalAlignment(JLabel.CENTER);
        passwordLabel.setSize(width/3, height/15);
        passwordLabel.setBounds(0, loginLabel.getY() + 50, passwordLabel.getWidth(), passwordLabel.getHeight());
        this.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setSize(width/3, height/15);
        passwordField.setBounds(width/3, loginLabel.getY() + 50, passwordField.getWidth(), passwordField.getHeight());
        passwordField.setVisible(true);
        this.add(passwordField);

        JButton submitButton = new JButton("Submit");
        submitButton.setFocusable(false);
        submitButton.setSize(width/5, height/15);
        submitButton.setBounds(width/2 + (width/4 - width/5), passwordLabel.getY() + 100, submitButton.getWidth(), submitButton.getHeight());
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        this.add(submitButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFocusable(false);
        cancelButton.setSize(width/5, height/15);
        cancelButton.setBounds(width/4, passwordLabel.getY() + 100, cancelButton.getWidth(), cancelButton.getHeight());
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                frameView.getRegistrationPanel().setVisible(true);
            }
        });
        this.add(cancelButton);

        this.setVisible(false);
    }

}
