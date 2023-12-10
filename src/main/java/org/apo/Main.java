package org.apo;

import org.apo.controlleur.ConnexionControlleur;
import org.apo.controlleur.DemandeController;
import org.apo.model.User;
import org.apo.vue.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    private static User nous;
    private static FrameView vue;

    public static void main(String[] args) {

        ConnexionControlleur connexionControlleur = new ConnexionControlleur();
        vue = new FrameView();

        LoginPanel connexionVue = vue.getLoginPanel();
        connexionVue.AddObserver(connexionControlleur);

        SignupPanel creationVue = vue.getSignupPanel();
        creationVue.AddObserver(connexionControlleur);

        DemandeController demandeController = new DemandeController();

        DemandsPanel demandeVue = vue.getDemandsPanel();
        demandeVue.AddObserver(demandeController);

        connexionControlleur.AddObserver(demandeController);
        connexionControlleur.AddObserver(demandeVue);

        RequestPanel requestVue = vue.getRequestPanel();
        requestVue.AddObserver(demandeController);
    }

}