package org.apo.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DemandsPanel extends JPanel {

    private JScrollPane scrollPane;
    private Container demandsContainer;
    private List<DemandPanel> demandPanelList;
    public DemandsPanel (FrameView frameView) {

        this.setLayout(null);

        JButton newDemandButton = new JButton("Nouvelle demande");
        newDemandButton.setSize(frameView.getWidth()/4, 8* frameView.getHeight()/100);
        newDemandButton.setBounds(frameView.getWidth()/8, frameView.getHeight()/100, newDemandButton.getWidth(), newDemandButton.getHeight());
        newDemandButton.setFocusable(false);
        this.add(newDemandButton);

        JButton deleteDemandButton = new JButton("Supprimer demande(s)");
        deleteDemandButton.setSize(frameView.getWidth()/4, 8*frameView.getHeight()/100);
        deleteDemandButton.setBounds(frameView.getWidth()/8 + frameView.getWidth()/2, frameView.getHeight()/100, deleteDemandButton.getWidth(), deleteDemandButton.getHeight());
        deleteDemandButton.setFocusable(false);
        this.add(deleteDemandButton);

        JButton acceptDemandButton = new JButton("Accepter demande");
        acceptDemandButton.setSize(frameView.getWidth()/4, 8*frameView.getHeight()/100);
        acceptDemandButton.setBounds(frameView.getWidth()/8 + frameView.getWidth()/4, frameView.getHeight()/100, acceptDemandButton.getWidth(), acceptDemandButton.getHeight());
        acceptDemandButton.setFocusable(false);
        this.add(acceptDemandButton);

        JButton validateDemandButton = new JButton("Valider demande");
        validateDemandButton.setSize(frameView.getWidth()/4, 8*frameView.getHeight()/100);
        validateDemandButton.setBounds(frameView.getWidth()/8 + frameView.getWidth()/4, frameView.getHeight()/100, validateDemandButton.getWidth(), validateDemandButton.getHeight());
        validateDemandButton.setFocusable(false);
        this.add(validateDemandButton);

        List<Color> colors = new ArrayList<>();
        colors.add(Color.red);
        colors.add(Color.green);
        colors.add(Color.blue);
        colors.add(Color.gray);
        colors.add(Color.yellow);
        colors.add(Color.pink);
        colors.add(Color.orange);
        colors.add(Color.black);
        colors.add(Color.cyan);
        colors.add(Color.magenta);

        demandsContainer = new Container();
        demandsContainer.setLayout(new BoxLayout(demandsContainer, BoxLayout.Y_AXIS));

        demandPanelList = new ArrayList<>();

        /*
        for (int i = 0; i < 10; i++) {
            DemandPanel demandPanel = new DemandPanel(frameView, this, );
            demandsContainer.add(demandPanel);
            demandPanelList.add(demandPanel);
        }

         */

        scrollPane = new JScrollPane(demandsContainer);
        scrollPane.setBounds(frameView.getWidth()/20, frameView.getHeight()/10, 18*frameView.getWidth()/20, 8*frameView.getHeight()/10);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.add(scrollPane);

        newDemandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        deleteDemandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        acceptDemandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        validateDemandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }
}
