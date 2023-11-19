package org.apo.vue;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class SignupPanel extends JPanel {

    private boolean fini = false;
    private String login;
    private String password;
    private int hospital = 0;
    private String role;

    public SignupPanel(FrameView frameView, int width, int height) {

        this.setLayout(null);

        JLabel signUpLabel = new JLabel("Sign up");
        signUpLabel.setHorizontalAlignment(JLabel.CENTER);
        signUpLabel.setSize(width/10, height/20);
        signUpLabel.setBounds(width/2 - signUpLabel.getWidth()/2, 50, signUpLabel.getWidth(), signUpLabel.getHeight());
        signUpLabel.setVisible(true);
        this.add(signUpLabel);

        JLabel loginLabel = new JLabel("Login : ");
        loginLabel.setHorizontalAlignment(JLabel.CENTER);
        loginLabel.setSize(width/3, height/15);
        loginLabel.setBounds(0, signUpLabel.getY() + 50, loginLabel.getWidth(), loginLabel.getHeight());
        this.add(loginLabel);

        JTextField loginTextField = new JTextField();
        loginTextField.setSize(width/3, height/15);
        loginTextField.setBounds(width/3, signUpLabel.getY() + 50, loginTextField.getWidth(), loginTextField.getHeight());
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

        JLabel passwordConfirmLabel = new JLabel("Confirm password : ");
        passwordConfirmLabel.setHorizontalAlignment(JLabel.CENTER);
        passwordConfirmLabel.setSize(width/3, height/15);
        passwordConfirmLabel.setBounds(0, passwordLabel.getY() + 50, passwordConfirmLabel.getWidth(), passwordConfirmLabel.getHeight());
        this.add(passwordConfirmLabel);

        JPasswordField passwordConfirmField = new JPasswordField();
        passwordConfirmField.setSize(width/3, height/15);
        passwordConfirmField.setBounds(width/3, passwordLabel.getY() + 50, passwordConfirmField.getWidth(), passwordConfirmField.getHeight());
        passwordConfirmField.setVisible(true);
        this.add(passwordConfirmField);

        JLabel roleLabel = new JLabel("Role : ");
        roleLabel.setHorizontalAlignment(JLabel.CENTER);
        roleLabel.setSize(width/3, height/15);
        roleLabel.setBounds(0, passwordConfirmLabel.getY() + 50, roleLabel.getWidth(), roleLabel.getHeight());
        this.add(roleLabel);

        JLabel hospitalLabel = new JLabel("Hospital : ");
        hospitalLabel.setHorizontalAlignment(JLabel.CENTER);
        hospitalLabel.setSize(width/6, height/15);
        hospitalLabel.setBounds(2*width/3, passwordConfirmLabel.getY() + 50, hospitalLabel.getWidth(), hospitalLabel.getHeight());
        this.add(hospitalLabel);

        JTextField hospitalTextField = new JTextField();
        hospitalTextField.setSize(width/12, height/15);
        hospitalTextField.setBounds(2*width/3 + width/6, passwordConfirmLabel.getY() + 50, hospitalTextField.getWidth(), hospitalTextField.getHeight());
        hospitalTextField.setVisible(true);
        this.add(hospitalTextField);

        String[] roles = {"Demandeur", "Aideur", "Valideur"};
        JComboBox roleComboBox = new JComboBox(roles);
        roleComboBox.setSize(width/3, height/15);
        roleComboBox.setBounds(width/3, passwordConfirmLabel.getY() + 50, roleComboBox.getWidth(), roleComboBox.getHeight());
        roleComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Objects.equals(roleComboBox.getSelectedItem(), roles[0])) {
                    hospitalLabel.setVisible(true);
                    hospitalTextField.setVisible(true);
                } else {
                    hospitalLabel.setVisible(false);
                    hospitalTextField.setVisible(false);
                }
            }
        });
        roleComboBox.setVisible(true);
        this.add(roleComboBox);

        JButton submitButton = new JButton("Submit");
        submitButton.setFocusable(false);
        submitButton.setSize(width/5, height/15);
        submitButton.setBounds(width/2 + (width/4 - width/5), hospitalLabel.getY() + 100, submitButton.getWidth(), submitButton.getHeight());
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login = loginTextField.getText();
                password = new String(passwordField.getPassword());

                role = (String) roleComboBox.getSelectedItem();

                if (Objects.equals(role, "Demandeur")) {
                    hospital = Integer.parseInt(hospitalTextField.getText());
                }

                String passwordConfirm = new String(passwordConfirmField.getPassword());

                if (Objects.equals(password, passwordConfirm)){
                    fini=true;
                }else {
                    System.out.println("Veuiller ressaisir un mot de passe");
                }
            }
        });
        this.add(submitButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFocusable(false);
        cancelButton.setSize(width/5, height/15);
        cancelButton.setBounds(width/4, hospitalLabel.getY() + 100, cancelButton.getWidth(), cancelButton.getHeight());
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

    public int getHospital() {
        return hospital;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public boolean isFini() {
        return fini;
    }
}
