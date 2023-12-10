package org.apo.vue;

import org.apo.controlleur.ConnexionControlleur;
import org.apo.model.Demande;
import org.apo.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;


public class DemandsPanel extends JPanel implements ConnexionControlleur.Observer {

    public interface Observer {

        void SupprimerDemande (ArrayList<Demande> aTraite);
        void ValiderDemande (ArrayList<Demande> aTraite);
        void AccepterDemande (ArrayList<Demande> aTraite);

    }

    private final List<Observer> ListObserver = new ArrayList<>();
    public void AddObserver (Observer obs){
        synchronized (ListObserver){
            ListObserver.add(obs);
        }
    }


    @Override
    public void FinCreation(User nous) {
        type = nous.getClass().getTypeName();
        this.nous=nous;

    }

    private User nous;
    private String type;
    private FrameView frameView;
    private JScrollPane scrollPane;
    private Container demandsContainer;
    private List<DemandPanel> demandPanelList = new ArrayList<>();


    private final JButton newDemandButton;
    private final JButton deleteDemandButton;
    private final JButton acceptDemandButton;
    private final JButton validateDemandButton;



    public DemandsPanel (FrameView frameView) {

        this.frameView=frameView;

        this.setLayout(null);

        newDemandButton = new JButton("Nouvelle demande");
        newDemandButton.setSize(frameView.getWidth()/4, 8* frameView.getHeight()/100);
        newDemandButton.setBounds(frameView.getWidth()/8, frameView.getHeight()/100, newDemandButton.getWidth(), newDemandButton.getHeight());
        newDemandButton.setFocusable(false);
        this.add(newDemandButton);

        deleteDemandButton = new JButton("Supprimer demande(s)");
        deleteDemandButton.setSize(frameView.getWidth()/4, 8*frameView.getHeight()/100);
        deleteDemandButton.setBounds(frameView.getWidth()/8 + frameView.getWidth()/2, frameView.getHeight()/100, deleteDemandButton.getWidth(), deleteDemandButton.getHeight());
        deleteDemandButton.setFocusable(false);
        this.add(deleteDemandButton);

        acceptDemandButton = new JButton("Accepter demande");
        acceptDemandButton.setSize(frameView.getWidth()/4, 8*frameView.getHeight()/100);
        acceptDemandButton.setBounds(frameView.getWidth()/8 + frameView.getWidth()/4, frameView.getHeight()/100, acceptDemandButton.getWidth(), acceptDemandButton.getHeight());
        acceptDemandButton.setFocusable(false);
        this.add(acceptDemandButton);

        validateDemandButton = new JButton("Valider demande");
        validateDemandButton.setSize(frameView.getWidth()/4, 8*frameView.getHeight()/100);
        validateDemandButton.setBounds(frameView.getWidth()/8 + frameView.getWidth()/4, frameView.getHeight()/100, validateDemandButton.getWidth(), validateDemandButton.getHeight());
        validateDemandButton.setFocusable(false);
        this.add(validateDemandButton);

        demandsContainer = new Container();
        demandsContainer.setLayout(new BoxLayout(demandsContainer, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(demandsContainer);
        scrollPane.setBounds(frameView.getWidth()/20, frameView.getHeight()/10, 18*frameView.getWidth()/20, 8*frameView.getHeight()/10);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.add(scrollPane);

        newDemandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
                frameView.getRequestPanel().setVisible(true);
            }
        });

        deleteDemandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Demande> demandes = Extraction();

                synchronized (ListObserver){
                    for (Observer observer : ListObserver){
                        observer.SupprimerDemande(demandes);
                    }
                }

                newSetVisible();
            }
        });

        acceptDemandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Demande> demandes = Extraction();

                synchronized (ListObserver){
                    for (Observer observer : ListObserver){
                        observer.AccepterDemande(demandes);
                    }
                }
                newSetVisible();
            }
        });

        validateDemandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Demande> demandes = Extraction();

                synchronized (ListObserver){
                    for (Observer observer : ListObserver){
                        observer.ValiderDemande(demandes);
                    }
                }
                newSetVisible();
            }
        });

    }

    private ArrayList<Demande> Extraction (){
        ArrayList<Demande> out = new ArrayList<>();

        for(DemandPanel panel: demandPanelList){
            if (panel.getSelect()&&panel.getDemand()!=null){
                out.add(panel.getDemand());
            }
        }

        return out;
    }

    public void newSetVisible(){
        switch (type){
            case "org.apo.model.Aideur" :
                validateDemandButton.setVisible(false);
                newDemandButton.setVisible(false);
                deleteDemandButton.setVisible(false);
                break;

            case "org.apo.model.Demandeur":
                validateDemandButton.setVisible(false);
                acceptDemandButton.setVisible(false);
                break;

            case "org.apo.model.Valideur":
                acceptDemandButton.setVisible(false);
                newDemandButton.setVisible(false);
                deleteDemandButton.setVisible(false);
                break;

            default:
                new PopUpWindow("Mais qui êtes vous ?",JOptionPane.WARNING_MESSAGE);
                throw new RuntimeException("Mais qui êtes vous ?");
        }




        demandPanelList = new ArrayList<>();


        if (type!="Admin"){
            HashMap<Integer, Demande> demandesMap;
            demandesMap = nous.recuperer_demandes_abstract();

            for(Map.Entry demande : demandesMap.entrySet()) {
                demandPanelList.add(new DemandPanel(frameView, (Demande) demande.getValue()));
            }
        }

        for (DemandPanel panel : demandPanelList){
            demandsContainer.add(panel);
        }

        frameView.getDemandsPanel().setVisible(true);
    }
}
