package org.apo.vue;

import javax.swing.*;

public class PopUpWindow extends JOptionPane {

    public PopUpWindow(String message) {

        JOptionPane.showMessageDialog(null, message, "", JOptionPane.ERROR_MESSAGE);

    }

}
