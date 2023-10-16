package org.apo.model;


import org.apo.controlleur.DBInterface;

import java.sql.ResultSet;

public class Aideur extends User {

    public Aideur(int UID) {
        super(UID);
    }

    @Override
    public void afficher_tableau() {
        DBInterface myDB = new DBInterface ();

        ResultSet result;
        result = myDB.Read("SELECT * FROM Demandes WHERE Statur = \"accepté\";");


    }
}
