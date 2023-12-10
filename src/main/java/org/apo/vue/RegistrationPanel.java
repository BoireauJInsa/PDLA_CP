package org.apo.vue;

import org.apo.model.Demande;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class RegistrationPanel extends JPanel{



    public RegistrationPanel(FrameView frameView) {

        this.setLayout(null);

        JButton signupButton = new JButton("Sign up");
        signupButton.setSize(frameView.getWidth()/5, frameView.getHeight()/10);
        signupButton.setBounds(frameView.getWidth()/4, frameView.getHeight()/2 - signupButton.getHeight()/2, signupButton.getWidth(), signupButton.getHeight());
        signupButton.setFocusable(false);
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
                frameView.getSignupPanel().setVisible(true);
            }
        });
        this.add(signupButton);

        JButton loginButton = new JButton("Login");
        loginButton.setSize(frameView.getWidth()/5, frameView.getHeight()/10);
        loginButton.setBounds(frameView.getWidth()/2 + (frameView.getWidth()/4 - frameView.getWidth()/5), frameView.getHeight()/2 - loginButton.getHeight()/2, loginButton.getWidth(), loginButton.getHeight());
        loginButton.setFocusable(false);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
                frameView.getLoginPanel().setVisible(true);
            }
        });
        this.add(loginButton);

        this.setVisible(false);
    }

}
