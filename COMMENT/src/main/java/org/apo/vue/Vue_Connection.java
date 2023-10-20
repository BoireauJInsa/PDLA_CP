package org.apo.vue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.*;



public class Vue_Connection extends JPanel {

    private ArrayList<String> list_demande;
    private ArrayList<JTextArea> list_champ = new ArrayList<>();

    private  JFrame frame = new JFrame ("Connection");

    public boolean has_choose = false ;

    public void Affiche() {

        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

        frame.setPreferredSize(new Dimension (800, 650));

        ListIterator<String> li = list_demande.listIterator();
        JLabel new_label;
        JTextArea text;

        int n =0;

        while (li.hasNext()){

            n++;
            new_label= new JLabel(li.next());
            new_label.setBounds(350,n*50,100,25);
            frame.add(new_label);

            n++;
            text = new JTextArea();
            text.setBounds(350,n*50,100,25);

            frame.add(text);
            list_champ.add(text);
        }

        JButton btn = new JButton("confirmer");
        btn.setBounds(350,(n+1)*50,100,25);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setHas_choose(true);
            }
        });
        frame.add(btn);

        frame.setLayout(null);
        frame.pack();
        frame.setVisible (true);
    }

    public boolean isHas_choose () {
        return has_choose;
    }

    public  void setHas_choose (boolean a) {
        has_choose=a;
    }

    public  ArrayList<String> Info () {

        ArrayList<String> list_info = new ArrayList<>();

        System.out.println("1");
        ListIterator<JTextArea> li = list_champ.listIterator();

        while (li.hasNext()){
            list_info.add(li.next().getText()) ;
        }
        return list_info;

    }

    public Vue_Connection (ArrayList<String> list) {

        list_demande=list;

    }


}
