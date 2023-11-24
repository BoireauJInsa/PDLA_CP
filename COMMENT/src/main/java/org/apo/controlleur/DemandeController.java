package org.apo.controlleur;

import org.apo.model.*;
import org.apo.vue.Vue_Connection;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class DemandeController {


    private Vue_Connection vue;

    private ArrayList<Integer> aTraiter;

    public DemandeController (User nous){

        vue=new Vue_Connection(nous.recuperer_demandes_abstract());

        while (!vue.PriseInfoCo()){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        aTraiter=vue.PriseInfoCr();
    }

    private void ValideurDemande (int IDdemande, Valideur nous){

        try {
            nous.Action(new Demande(IDdemande),"accepté");
        } catch (ErrorNoPerms e) {
            System.out.println("Vous n'avez pas les droit de modifier cette demande");
        }

    }

    private void AideurDemande (int IDdemande, Aideur nous){

        try {
            nous.Action(new Demande(IDdemande));
        } catch (ErrorNoPerms e) {
            System.out.println("Vous n'avez pas les droit de modifier cette demande");
        }

    }
}
