package org.apo.vue;

import org.apo.model.ErrorBadParameters;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LoginPanel extends JPanel {

    public interface Observer {
        void Login ( String login, String password) throws ErrorBadParameters;

    }

    private final List<LoginPanel.Observer> ListObserver = new ArrayList<>();
    public void AddObserver (LoginPanel.Observer obs){
        synchronized (ListObserver){
            ListObserver.add(obs);
        }
    }

    private String login;
    private String password;

    public LoginPanel(FrameView frameView) {

        this.setLayout(null);

        JLabel logInLabel = new JLabel("Log in");
        logInLabel.setHorizontalAlignment(JLabel.CENTER);
        logInLabel.setSize(frameView.getWidth()/10, frameView.getHeight()/20);
        logInLabel.setBounds(frameView.getWidth()/2 - logInLabel.getWidth()/2, 50, logInLabel.getWidth(), logInLabel.getHeight());
        logInLabel.setVisible(true);
        this.add(logInLabel);

        JLabel loginLabel = new JLabel("Login : ");
        loginLabel.setHorizontalAlignment(JLabel.CENTER);
        loginLabel.setSize(frameView.getWidth()/3, frameView.getHeight()/15);
        loginLabel.setBounds(0, logInLabel.getY() + 80, loginLabel.getWidth(), loginLabel.getHeight());
        this.add(loginLabel);

        JTextField loginTextField = new JTextField();
        loginTextField.setSize(frameView.getWidth()/3, frameView.getHeight()/15);
        loginTextField.setBounds(frameView.getWidth()/3, logInLabel.getY() + 80, loginTextField.getWidth(), loginTextField.getHeight());
        loginTextField.setVisible(true);
        this.add(loginTextField);

        JLabel passwordLabel = new JLabel("Password : ");
        passwordLabel.setHorizontalAlignment(JLabel.CENTER);
        passwordLabel.setSize(frameView.getWidth()/3, frameView.getHeight()/15);
        passwordLabel.setBounds(0, loginLabel.getY() + 50, passwordLabel.getWidth(), passwordLabel.getHeight());
        this.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setSize(frameView.getWidth()/3, frameView.getHeight()/15);
        passwordField.setBounds(frameView.getWidth()/3, loginLabel.getY() + 50, passwordField.getWidth(), passwordField.getHeight());
        passwordField.setVisible(true);
        this.add(passwordField);

        JButton submitButton = new JButton("Submit");
        submitButton.setFocusable(false);
        submitButton.setSize(frameView.getWidth()/5, frameView.getHeight()/15);
        submitButton.setBounds(frameView.getWidth()/2 + (frameView.getWidth()/4 - frameView.getWidth()/5), passwordLabel.getY() + 100, submitButton.getWidth(), submitButton.getHeight());
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                login = loginTextField.getText();
                password = new String(passwordField.getPassword());

                boolean ok =true;

                synchronized (ListObserver){
                    for (Observer observer : ListObserver){
                        try {
                            observer.Login(login,password);
                        } catch (ErrorBadParameters ex) {
                            new PopUpWindow(ex.getMessage(),JOptionPane.WARNING_MESSAGE);
                            ok = false;
                        }
                    }
                }

                if (ok){
                    setVisible(false);
                    frameView.getDemandsPanel().newSetVisible();
                }

            }
        });
        this.add(submitButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFocusable(false);
        cancelButton.setSize(frameView.getWidth()/5, frameView.getHeight()/15);
        cancelButton.setBounds(frameView.getWidth()/4, passwordLabel.getY() + 100, cancelButton.getWidth(), cancelButton.getHeight());
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
