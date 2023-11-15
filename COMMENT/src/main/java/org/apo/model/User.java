package org.apo.model;

import org.apo.controlleur.DBInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public abstract class User {
    int UID;
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
                int ID = rs.getInt("ID");
                int ID_Demandeur = rs.getInt("ID_Demandeur");
                int ID_Valideur = rs.getInt("ID_Valideur");
                int ID_Aideur = rs.getInt("ID_Aideur");
                String Statut = rs.getString("Statut");
                String Message = rs.getString("Message");

                Demande My_Demande = new Demande(ID, ID_Demandeur, ID_Valideur, ID_Aideur, Statut, Message);

                //Store to Map the key and the value
                DB_Map.put(ID, My_Demande);
            }
        } catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        myDB.Close();
        return DB_Map;
    }
    public abstract HashMap<Integer, Demande> recuperer_demandes_abstract();
    public abstract void RegisterUser(String login, String mdp);

    //public abstract void afficher_profil();
}
