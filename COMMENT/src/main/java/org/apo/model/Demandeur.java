package org.apo.model;


import org.apo.controlleur.DBInterface;
import org.apo.controlleur.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Demandeur extends User {

    public Demandeur(int UID) {
        super(UID);
    }

    @Override
    public void afficher_tableau() {
        DBInterface myDB = new DBInterface ();

        ResultSet result;
        result = myDB.Read("SELECT * FROM Demandes WHERE ID_Demandeur = " + this.UID + " ;");


        try {
            while (result.next()){
                String Message = result.getString("Message");
                String Statut = result.getString("Statut");
                System.out.println(Message + " : " + Statut );

            }
        }catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

    }
}
