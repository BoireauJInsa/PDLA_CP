package org.apo.model;
import java.util.HashMap;


import org.apo.controlleur.DBInterface;
import org.apo.controlleur.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Demandeur extends User {

    public Demandeur(int UID) {
        super(UID);
    }

    @Override
    public HashMap<Integer, Demande> recuperer_tableau() {
        HashMap<Integer, Demande> DB_Map = new HashMap<Integer, Demande>();

        DBInterface myDB = new DBInterface ();
        ResultSet rs;
        rs = myDB.Read("SELECT * FROM Demandes WHERE ID_Demandeur = " + this.UID + " ;");

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
