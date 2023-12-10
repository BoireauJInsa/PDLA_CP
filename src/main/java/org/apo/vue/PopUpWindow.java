package org.apo.vue;

import javax.swing.*;

public class PopUpWindow extends JOptionPane {

    public PopUpWindow(String message, int code) {

        JOptionPane.showMessageDialog(null, message, "", code);

    }

}
