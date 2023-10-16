package org.apo.model;


import org.apo.controlleur.DBInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Aideur extends User {

    public Aideur(int UID) {
        super(UID);
    }

    @Override
    public HashMap<Integer, Demande> recuperer_tableau() {
        HashMap<Integer, Demande> DB_Map = new HashMap<Integer, Demande>(); //Create your hashmap

        DBInterface myDB = new DBInterface ();
        ResultSet rs;
        rs = myDB.Read("SELECT * FROM Demandes WHERE Statut = accepté;");

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
        return DB_Map;
    }
}
