package org.apo.vue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.*;
import javax.swing.*;



public class Vue_Connection extends JPanel {



        private JLabel jcomp1;
        private JTextArea Login;
        private JLabel jcomp3;
        private JLabel jcomp4;
        private JLabel jcomp5;


        public void Mypanel() {

            //construct components
            jcomp1 = new JLabel ("Login");
            Login = new JTextArea (10, 10);
            jcomp3 = new JLabel ("Password");
            jcomp4 = new JLabel ("newLabel");
            jcomp5 = new JLabel ("newLabel");

            //adjust size and set layout
            setPreferredSize (new Dimension (862, 635));
            setLayout (null);

            //add components
            add (jcomp1);
            add (Login);
            add (jcomp3);
            add (jcomp4);
            add (jcomp5);

            //set component bounds (only needed by Absolute Positioning)
            jcomp1.setBounds (365, 50, 100, 25);
            Login.setBounds (305, 80, 175, 30);
            jcomp3.setBounds (350, 120, 100, 25);
            jcomp4.setBounds (320, 215, 100, 25);
            jcomp5.setBounds (315, 275, 100, 25);

        }

    public Vue_Connection () {
        JFrame frame = new JFrame ("Connection");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add  MyPanel());
        frame.pack();
        frame.setVisible (true);
    }


}
