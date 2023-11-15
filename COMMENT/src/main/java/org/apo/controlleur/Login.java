package org.apo.controlleur;

import org.apo.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    public static User ConnexionAvecID(int ID) {

        DBInterface myDB = new DBInterface ();
        String query = "SELECT * FROM Personnes WHERE ID = " + ID + " ;";
        String  typeUser = myDB.ReadSingle(query, "Statut");

        switch (typeUser) {
            case "valideur":
                return new Valideur(ID);
            case "demandeur":
                query = "SELECT * FROM Demandeur WHERE ID = " + ID + " ;";
                String IDString = myDB.ReadSingle(query, "ID_Valideur");
                return new Demandeur(ID, Integer.parseInt(IDString));
            default:
                return new Aideur(ID);
        }
    }

    public static User RegisterUser(String login, String mdp, int typeUser, int IDval) {
        User newUser = null;
        switch (typeUser) {
            case 1: //valideur
                newUser = new Valideur(-1);
                newUser.RegisterUser(login, mdp);
            case 2: // demandeur
                newUser = new Demandeur(-1, IDval);
                newUser.RegisterUser(login, mdp);
            default: // aideur
                newUser = new Valideur(-1);
                newUser.RegisterUser(login, mdp);
        }
        return newUser;
    }

    public static User ConnexionAvecLogin(String login, String mdp) throws SQLException{
        DBInterface myDB = new DBInterface ();
        String queyID = "SELECT * FROM Personnes WHERE Login = %s AND Pass = %s;".formatted(login,mdp);;

        int ID = Integer.parseInt (myDB.ReadSingleThrow(queyID, "ID"));


        myDB.Close();
        return ConnexionAvecID(ID);
    }
}
