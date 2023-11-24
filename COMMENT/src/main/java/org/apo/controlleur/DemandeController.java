package org.apo.controlleur;

import org.apo.model.*;
import org.apo.vue.PopUpWindow;
import org.apo.vue.Vue_Connection;

import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class DemandeController {


    private Vue_Connection vue;

    private ArrayList<Integer> aTraiter;

    public DemandeController (User nous){



        vue=new Vue_Connection(nous.recuperer_demandes_abstract());

        while (!vue.PriseInfoCo() && !vue.ActionSupp()){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


        if (vue.ActionSupp()){
            switch (nous.getClass().getName()){

                case "Demandeur" : CreerDemande((Demandeur) nous);
                    break;

                default:
                    new PopUpWindow("Vous n'êtes pas un utilisateur normal", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            aTraiter=vue.ListModif();

            switch (nous.getClass().getName()){
                case "Aideur" : AideurDemande((Aideur) nous);
                break;

                case "Valideur" : ValideurDemande((Valideur) nous);
                    break;

                case "Demandeur" : DemandeurDemande((Demandeur) nous);
                    break;

                default:
                    new PopUpWindow("Vous n'êtes pas un utilisateur normal", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void ValideurDemande ( Valideur nous){

        for (int IDdemande : aTraiter){

            try {
                nous.Action(new Demande(IDdemande),"accepté");
            } catch (ErrorNoPerms e) {
                new PopUpWindow("Vous n'avez pas les droit de modifier cette demande", JOptionPane.WARNING_MESSAGE);
            }

        }

    }

    private void AideurDemande ( Aideur nous){

        for (int IDdemande : aTraiter) {

            try {
                nous.Action(new Demande(IDdemande));
            } catch (ErrorNoPerms e) {
                new PopUpWindow("Vous n'avez pas les droit de modifier cette demande", JOptionPane.WARNING_MESSAGE);

            }

        }
    }

    private void DemandeurDemande ( Demandeur nous){

        for (int IDdemande : aTraiter){

            try {
                nous.Action(new Demande(IDdemande));
            } catch (ErrorNoPerms e) {
                new PopUpWindow("Vous n'avez pas les droit de modifier cette demande", JOptionPane.WARNING_MESSAGE);

            }
        }

    }

    private void CreerDemande (Demandeur nous) {

        while (!vue.PriseInfoCo()){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        nous.AjoutDemande(vue.getString());
    }

}
