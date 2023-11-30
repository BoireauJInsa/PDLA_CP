package org.apo.controlleur;

import org.apo.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class LoginController {
    public static User ConnexionAvecID(int ID) {

        DBInterface myDB = new DBInterface ();
        String query = "SELECT * FROM Personnes WHERE ID = %d ;".formatted(ID);
        String  typeUser = myDB.ReadSingle(query, "Statut");
        myDB.Close();

        switch (typeUser) {
            case "valideur":
                return new Valideur(ID);

            case "demandeur":
                myDB = new DBInterface ();
                query = "SELECT * FROM Demandeur WHERE ID = %d ;".formatted(ID);
                String IDString = myDB.ReadSingle(query, "ID_Valideur");
                return new Demandeur(ID, Integer.parseInt(IDString));

            default:
                return new Aideur(ID);
        }
    }

    public static User RegisterUser(String login, String mdp, String  typeUser, int IDval) {
        User newUser = null;
        switch (typeUser) {
            case "Valideur":
                newUser = new Valideur(-1);
                newUser.RegisterUser(login, mdp);
                break;
            case "Demandeur":
                newUser = new Demandeur(-1, IDval);
                newUser.RegisterUser(login, mdp);
                break;
            default: // Aideur
                newUser = new Aideur(-1);
                newUser.RegisterUser(login, mdp);
        }
        return newUser;
    }

    public static User ConnexionAvecLogin(String login, String mdp) throws SQLException{
        DBInterface myDB = new DBInterface ();
        String queyID = "SELECT * FROM Personnes WHERE Login = %s AND Pass = %s;".formatted("'"+login+"'","'"+mdp+"'");

        int ID = Integer.parseInt (myDB.ReadSingleThrow(queyID, "ID"));


        myDB.Close();
        return ConnexionAvecID(ID);
    }

    public static boolean Existe (String aVerifier, String collone, String table){
        DBInterface myDB = new DBInterface();
        String queryLogin = "SELECT * FROM %s".formatted(table);


        ResultSet rs = myDB.Read(queryLogin);
        boolean sortie = false;

        try {
            while (rs.next()) {
                String verifieur = rs.getString(collone);

                if (Objects.equals(aVerifier, verifieur)){
                    sortie=true;

                }
            }
        } catch (SQLException ex){
            // handle any errors
            sortie=false;
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        myDB.CloseStatement();
        myDB.Close();
        return sortie;
    }
}