package org.apo.controlleur;

import org.apo.vue.*;
import org.apo.model.User;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class ConnexionControlleur {

    Vue_Connection visuel;
    User nous;
    FrameView vue;

    ConnexionControlleur(){

        vue = new FrameView();

        RegistrationPanel registeVue = vue.getRegistrationPanel();

        while (registeVue.getMode()==0){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        if (registeVue.getMode()==1){
            Creation();
        }else{
            Connexion();
        }
    }

    private void Connexion () {
        boolean connexion = true;
        String login = "";
        String mdp = "";

        LoginPanel connexionVue = vue.getLoginPanel();

        while (connexion){

            while (!connexionVue.isFini()){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            login = connexionVue.getLogin();
            mdp = connexionVue.getPassword();

            try {

                nous = LoginController.ConnexionAvecLogin(login,mdp);

                connexion=false;
            }catch (SQLException ex){
                System.out.println("Problème de connexion :");
                System.out.println("|  Mauvais login: " + login);
                System.out.println("|  Mauvais mot de passe: " + mdp);
                System.out.println("Veuiller recommencer");
                connexionVue.Recommencer();
            }


        }

    }

    private void Creation (){

        SignupPanel creationVue = vue.getSignupPanel();

        while (!creationVue.isFini()){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        nous = LoginController.RegisterUser(creationVue.getLogin(), creationVue.getPassword(), creationVue.getRole(), creationVue.getHospital());

    }

    public User InfoConnexion () {
        return nous;
    }
}
