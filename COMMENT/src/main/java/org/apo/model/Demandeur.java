package org.apo.model;


import org.apo.controlleur.DBInterface;
import org.apo.controlleur.*;

import java.sql.ResultSet;

public class Demandeur extends User {

    public Demandeur(int UID) {
        super(UID);
    }

    @Override
    public void afficher_tableau() {
        DBInterface myDB = new DBInterface ();

        ResultSet result;
        result = myDB.Read("SELECT * FROM Demandes WHERE ID_Demandeur = " + this.UID + " ;");


    }
}
