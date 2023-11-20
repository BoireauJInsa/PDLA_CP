package org.apo.controlleur;

import org.apo.vue.*;
import org.apo.model.User;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Objects;
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
                new PopUpWindow ("Erreur, ce n'est pas les bon authentifiant", JOptionPane.ERROR_MESSAGE);
            }


        }

    }

    private void Creation (){
        boolean reassayer = true;
        SignupPanel creationVue = vue.getSignupPanel();

        while (reassayer){
            while (!creationVue.isFini()){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            if (!LoginController.Existe(creationVue.getLogin(), "Login", "Personnes")){
                if (!Objects.equals(creationVue.getRole(), "Demandeur") || LoginController.Existe(String.valueOf( creationVue.getHospital()), "ID", "Valideur")){
                    nous = LoginController.RegisterUser(creationVue.getLogin(), creationVue.getPassword(), creationVue.getRole(), creationVue.getHospital());
                    reassayer=false;
                }else {
                    new PopUpWindow ("Cet hôpital n'existe pas", JOptionPane.ERROR_MESSAGE);
                }

            }else {
                new PopUpWindow ("Erreur, le login est déjà pris", JOptionPane.ERROR_MESSAGE);
            }

        }


    }

    public User InfoConnexion () {
        return nous;
    }
}
