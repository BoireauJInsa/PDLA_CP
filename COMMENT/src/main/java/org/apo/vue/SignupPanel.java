package org.apo.vue;

import org.apo.model.ErrorBadParameters;
import org.apo.model.ErrorDontExist;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SignupPanel extends JPanel {

    public interface Observer {
        void Signup ( String login, String password, String role, String hopital) throws ErrorDontExist, ErrorBadParameters;

    }

    private final List<SignupPanel.Observer> ListObserver = new ArrayList<>();
    public void AddObserver (SignupPanel.Observer obs){
        synchronized (ListObserver){
            ListObserver.add(obs);
        }
    }

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

        JLabel hospitalLabel = new JLabel("Hôpital : ");
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
                if (Objects.equals(roleComboBox.getSelectedItem(), roles[1])) {
                    hospitalLabel.setVisible(false);
                    hospitalTextField.setVisible(false);

                } else {
                    hospitalLabel.setVisible(true);
                    hospitalTextField.setVisible(true);
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
                    hospital=hospitalTextField.getText();
                }else {
                    hospital="0";
                }

                passwordConfirm = new String(passwordConfirmField.getPassword());

                if (checkError()) {

                    boolean ok =true;

                    synchronized (ListObserver){
                        for (Observer observer : ListObserver){
                            try {
                                observer.Signup(login,password,role,hospital);
                            } catch (ErrorDontExist | ErrorBadParameters ex) {
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


    public boolean checkError() {
        String errorMessage = "";
        if (login.isEmpty()) {
            errorMessage = "Veuillez entrer un login";
        } else if (password.isEmpty()) {
            errorMessage = "Veuillez entrer un mod de passe valide";
        } else if (!password.equals(passwordConfirm)) {
            errorMessage = "Confirmation du mot de passe invalide";
        } else if (role.equals("Demandeur")) {
            if (hospital=="") {
                errorMessage = "Veuillez entrer le numéro de votre hôpital";
            }else {
                try{
                    Integer.parseInt(hospital);
                }
                catch(Exception E){
                    errorMessage = "Veuillez entrer un numéro valide";
                }

            }
        }
        if (!errorMessage.isEmpty()) {
            new PopUpWindow(errorMessage, JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
