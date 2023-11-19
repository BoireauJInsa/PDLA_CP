package org.apo.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class FrameView extends JFrame{

    private int width = 800;
    private int height = 500;
    private final RegistrationPanel registrationPanel;
    private final SignupPanel signupPanel;
    private final LoginPanel loginPanel;
    private final RequestPanel requestPanel;

    public FrameView() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(width, height));
        this.setLayout(null);
        this.setResizable(false);


        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension newSize = e.getComponent().getSize();
                width = (int) newSize.getWidth();
                height = (int) newSize.getHeight();
                registrationPanel.setBounds(0, 0, width, height);
                signupPanel.setBounds(0, 0, width, height);
                loginPanel.setBounds(0, 0, width, height);
                requestPanel.setBounds(0, 0, width, height);
            }
        });

        registrationPanel = new RegistrationPanel(this);
        this.getContentPane().add(registrationPanel);
        registrationPanel.setVisible(true);

        signupPanel = new SignupPanel(this);
        this.getContentPane().add(signupPanel);
        signupPanel.setVisible(false);

        loginPanel = new LoginPanel(this);
        this.getContentPane().add(loginPanel);
        loginPanel.setVisible(false);

        requestPanel = new RequestPanel(this);
        this.getContentPane().add(requestPanel);
        requestPanel.setVisible(false);

        this.setVisible(true);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public RegistrationPanel getRegistrationPanel() {
        return registrationPanel;
    }

    public SignupPanel getSignupPanel() {
        return signupPanel;
    }

    public LoginPanel getLoginPanel() {
        return loginPanel;
    }

    public RequestPanel getRequestPanel() {
        return requestPanel;
    }

    public static void main(String[] args) {
        new FrameView();
    }
}
