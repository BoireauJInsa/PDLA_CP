package org.apo.controlleur;

import org.apo.model.ErrorBadParameters;
import org.apo.model.ErrorDontExist;
import org.apo.vue.*;
import org.apo.model.User;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class ConnexionControlleur implements  LoginPanel.Observer, SignupPanel.Observer{

    public interface Observer {
        void FinCreation ( User nous);

    }

    private User nous;

    private final List<ConnexionControlleur.Observer> ListObserver = new ArrayList<>();
    public void AddObserver (ConnexionControlleur.Observer obs){
        synchronized (ListObserver){
            ListObserver.add(obs);
        }
    }

    public ConnexionControlleur(){

    }

    public void Login ( String login, String password) throws ErrorBadParameters {

            try {
                nous = LoginController.ConnexionAvecLogin(login,password);
            }catch (SQLException ex){
                throw new ErrorBadParameters("Ce n'ai pas les bon login / mot de pass");
            }

        synchronized (ListObserver){
            for (Observer observer : ListObserver){
                observer.FinCreation(nous);
            }
        }

    }

    public void Signup (String login, String password, String role, String hopital) throws ErrorDontExist, ErrorBadParameters {

            if (!LoginController.Existe(login, "Login", "Personnes")){
                if (!Objects.equals(role, "Demandeur") || LoginController.Existe(hopital, "ID", "Valideur")){
                    nous = LoginController.RegisterUser(login, password, role, Integer.parseInt(hopital));

                }else {
                    throw new ErrorDontExist("Cette hopital n'éxiste pas");
                }

            }else {
                throw new ErrorBadParameters("Ce login est déja pris");
            }

        synchronized (ListObserver){
            for (Observer observer : ListObserver){
                observer.FinCreation(nous);
            }
        }

    }

    public User InfoConnexion () {
        return nous;
    }
}
