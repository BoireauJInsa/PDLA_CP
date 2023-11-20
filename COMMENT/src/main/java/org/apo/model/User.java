package org.apo.model;

import org.apo.controlleur.DBInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public abstract class User {
    int UID;
    String login;
    String motDePass;
    String Status;

    public User(int UID) {
        this.UID = UID;
    }

    public HashMap<Integer, Demande> recuperer_demandes(String Query) {
        HashMap<Integer, Demande> DB_Map = new HashMap<Integer, Demande>();

        DBInterface myDB = new DBInterface ();
        ResultSet rs;
        rs = myDB.Read(Query);

        try {
            while (rs.next()) {
                int ID_demande = rs.getInt("ID");

                Demande My_Demande = new Demande(ID_demande);

                //Store to Map the key and the value
                DB_Map.put(ID_demande, My_Demande);
            }
        } catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        myDB.CloseStatement();
        myDB.Close();
        return DB_Map;
    }
    public abstract HashMap<Integer, Demande> recuperer_demandes_abstract();
    public abstract void RegisterUser(String login, String mdp);

    public int getUID() {
        return UID;
    }

    public String getLogin() {
        return login;
    }

    public String getMotDePass() {
        return motDePass;
    }

    public String getStatus() {
        return Status;
    }

    public abstract String toString ();
}
