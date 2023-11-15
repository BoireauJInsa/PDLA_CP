package org.apo.controlleur;

import org.apo.vue.Vue_Connection;
import org.apo.model.User;

import java.sql.SQLException;

public class ConnexionControlleur {

    Vue_Connection visuel;
    User nous;

    ConnexionControlleur(){
        visuel= new Vue_Connection(1);

        if (visuel.Selection()==1){
           Connexion();
        }else{
            Creation();
        }
    }

    private void Connexion () {
        boolean connexion = true;
        String login = "";
        String mdp = "";

        while (connexion){

            try {
                visuel.PriseInfoCo();
                login=visuel.login;
                mdp=visuel.motDePasse;

                nous = Login.ConnexionAvecLogin(login,mdp);

                connexion=false;
            }catch (SQLException ex){
                System.out.println("Problème de connexion :");
                System.out.println("|  Mauvais login: " + login);
                System.out.println("|  Mauvais mot de passe: " + mdp);
            }


        }

    }

    private void Creation (){
        visuel.PriseInfoCr();
        nous = Login.RegisterUser(visuel.login,visuel.motDePasse,visuel.type,visuel.idSupp);
    }

    public User InfoConnexion () {
        return nous;
    }
}
