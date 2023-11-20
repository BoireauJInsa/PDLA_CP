package org.apo.vue;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class SignupPanel extends JPanel {

    private boolean fini = false;
    private String login;
    private String password;
    private String passwordConfirm;
    private String hospital;
    private String role;

    public SignupPanel(FrameView frameView) {

        this.setLayout(null);

        JLabel signUpLabel = new JLabel("Sign up");
        signUpLabel.setHorizontalAlignment(JLabel.CENTER);
        signUpLabel.setSize(frameView.getWidth()/10, frameView.getHeight()/20);
        signUpLabel.setBounds(frameView.getWidth()/2 - signUpLabel.getWidth()/2, 50, signUpLabel.getWidth(), signUpLabel.getHeight());
        signUpLabel.setVisible(true);
        this.add(signUpLabel);

        JLabel loginLabel = new JLabel("Login : ");
        loginLabel.setHorizontalAlignment(JLabel.CENTER);
        loginLabel.setSize(frameView.getWidth()/3, frameView.getHeight()/15);
        loginLabel.setBounds(0, signUpLabel.getY() + 50, loginLabel.getWidth(), loginLabel.getHeight());
        this.add(loginLabel);

        JTextField loginTextField = new JTextField();
        loginTextField.setSize(frameView.getWidth()/3, frameView.getHeight()/15);
        loginTextField.setBounds(frameView.getWidth()/3, signUpLabel.getY() + 50, loginTextField.getWidth(), loginTextField.getHeight());
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

        JLabel passwordConfirmLabel = new JLabel("Confirm password : ");
        passwordConfirmLabel.setHorizontalAlignment(JLabel.CENTER);
        passwordConfirmLabel.setSize(frameView.getWidth()/3, frameView.getHeight()/15);
        passwordConfirmLabel.setBounds(0, passwordLabel.getY() + 50, passwordConfirmLabel.getWidth(), passwordConfirmLabel.getHeight());
        this.add(passwordConfirmLabel);

        JPasswordField passwordConfirmField = new JPasswordField();
        passwordConfirmField.setSize(frameView.getWidth()/3, frameView.getHeight()/15);
        passwordConfirmField.setBounds(frameView.getWidth()/3, passwordLabel.getY() + 50, passwordConfirmField.getWidth(), passwordConfirmField.getHeight());
        passwordConfirmField.setVisible(true);
        this.add(passwordConfirmField);

        JLabel roleLabel = new JLabel("Role : ");
        roleLabel.setHorizontalAlignment(JLabel.CENTER);
        roleLabel.setSize(frameView.getWidth()/3, frameView.getHeight()/15);
        roleLabel.setBounds(0, passwordConfirmLabel.getY() + 50, roleLabel.getWidth(), roleLabel.getHeight());
        this.add(roleLabel);

        JLabel hospitalLabel = new JLabel("Hospital : ");
        hospitalLabel.setHorizontalAlignment(JLabel.CENTER);
        hospitalLabel.setSize(frameView.getWidth()/6, frameView.getHeight()/15);
        hospitalLabel.setBounds(2*frameView.getWidth()/3, passwordConfirmLabel.getY() + 50, hospitalLabel.getWidth(), hospitalLabel.getHeight());
        this.add(hospitalLabel);

        JTextField hospitalTextField = new JTextField();
        hospitalTextField.setSize(frameView.getWidth()/12, frameView.getHeight()/15);
        hospitalTextField.setBounds(2*frameView.getWidth()/3 + frameView.getWidth()/6, passwordConfirmLabel.getY() + 50, hospitalTextField.getWidth(), hospitalTextField.getHeight());
        hospitalTextField.setVisible(true);
        this.add(hospitalTextField);

        String[] roles = {"Demandeur", "Aideur", "Valideur"};
        JComboBox roleComboBox = new JComboBox(roles);
        roleComboBox.setSize(frameView.getWidth()/3, frameView.getHeight()/15);
        roleComboBox.setBounds(frameView.getWidth()/3, passwordConfirmLabel.getY() + 50, roleComboBox.getWidth(), roleComboBox.getHeight());
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
        submitButton.setSize(frameView.getWidth()/5, frameView.getHeight()/15);
        submitButton.setBounds(frameView.getWidth()/2 + (frameView.getWidth()/4 - frameView.getWidth()/5), hospitalLabel.getY() + 100, submitButton.getWidth(), submitButton.getHeight());
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login = loginTextField.getText();
                password = new String(passwordField.getPassword());

                role = (String) roleComboBox.getSelectedItem();

                if (Objects.equals(role, "Demandeur")) {
                    hospital = hospitalTextField.getText();
                }

                passwordConfirm = new String(passwordConfirmField.getPassword());

                if (checkError()) {
                    fini = true;
                    setVisible(false);
                    if (role.equals("Demandeur")) {
                        frameView.getRequestPanel().setVisible(true);
                        System.out.println(getHospital());
                    }
                    System.out.println("jsdgsgs");
                }

                /*
                if (Objects.equals(password, passwordConfirm)){
                    fini = true;
                }else {
                    new PopUpWindow("Veuillez ressaisir un mot de passe");
                    System.out.println("Veuillez ressaisir un mot de passe");
                }

                if (fini) {
                    setVisible(false);
                    if (role.equals("Demandeur")) {
                        frameView.getRequestPanel().setVisible(true);
                    }
                }

                 */

            }
        });
        this.add(submitButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFocusable(false);
        cancelButton.setSize(frameView.getWidth()/5, frameView.getHeight()/15);
        cancelButton.setBounds(frameView.getWidth()/4, hospitalLabel.getY() + 100, cancelButton.getWidth(), cancelButton.getHeight());
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
        return Integer.parseInt(hospital);
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

    public boolean checkError() {
        String errorMessage = "";
        if (login.isEmpty()) {
            errorMessage = "Veuillez entrer un login";
        } else if (password.isEmpty()) {
            errorMessage = "Veuillez entrer un mod de passe valide";
        } else if (!password.equals(passwordConfirm)) {
            errorMessage = "Confirmation du mot de passe invalide";
        } else if (role.equals("Demandeur")) {
            if (hospital.isEmpty()) {
                errorMessage = "Veuillez entrer le numéro de votre hôpital";
            } else {
                try {
                    Integer.parseInt(hospital);
                } catch (NumberFormatException e) {
                    errorMessage = "Veuillez entrer un numéro d'hôpital valide";
                }
            }
        }
        if (!errorMessage.isEmpty()) {
            new PopUpWindow(errorMessage);
            return false;
        }
        return true;
    }
}
