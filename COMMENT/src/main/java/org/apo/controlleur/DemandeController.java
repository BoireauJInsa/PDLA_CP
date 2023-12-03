package org.apo.controlleur;

import org.apo.model.*;
import org.apo.vue.DemandsPanel;
import org.apo.vue.PopUpWindow;
import org.apo.vue.RequestPanel;

import javax.swing.*;
import java.util.ArrayList;

public class DemandeController implements DemandsPanel.Observer, ConnexionControlleur.Observer, RequestPanel.Observer {


    private User nous;

    @Override
    public void AccepterDemande(ArrayList<Demande> aTraiter) {
        Aideur local = (Aideur) nous;

        for (Demande demande : aTraiter) {

            try {
                local.Action(demande);
            } catch (ErrorNoPerms e) {
                new PopUpWindow(e.getMessage(), JOptionPane.WARNING_MESSAGE);

            }

        }
    }

    @Override
    public void CreerDemande(String message) {
        Demandeur local = (Demandeur) nous;

        local.AjoutDemande(message);
    }

    @Override
    public void SupprimerDemande(ArrayList<Demande> aTraiter) {
        Demandeur local = (Demandeur) nous;

        for (Demande demande : aTraiter){

            try {
                local.Action(demande);
            } catch (ErrorNoPerms e) {
                new PopUpWindow(e.getMessage(), JOptionPane.WARNING_MESSAGE);

            }
        }
    }

    @Override
    public void ValiderDemande(ArrayList<Demande> aTraiter) {

        Valideur local = (Valideur) nous;

        for (Demande demande : aTraiter){

            try {
                local.Action(demande,"accepté");
            } catch (ErrorNoPerms e) {
                new PopUpWindow(e.getMessage(), JOptionPane.WARNING_MESSAGE);
            }

        }
    }

    public DemandeController (){}

    @Override
    public void FinCreation(User nous) {
        this.nous=nous;
    }
}
