package org.apo.model;
import org.apo.controlleur.DBInterface;

import java.util.HashMap;

public class Demandeur extends User {

    int UID_Valideur;
    public Demandeur(int UID, int UID_Valideur) {
        super(UID);
        this.UID_Valideur = UID_Valideur;
    }

    @Override
    public HashMap<Integer, Demande> recuperer_demandes_abstract() {
        return recuperer_demandes("SELECT * FROM Demandes WHERE ID_Demandeur = " + this.UID + " ;");
    }

    @Override
    public void RegisterUser(String login, String mdp) {
        DBInterface myDB = new DBInterface ();
        String queryPersonne ="INSERT INTO Personnes (Login, Pass, Statut) VALUES ( \"%s\",  \"%s\" , \"Demandeur\" );".formatted(login, mdp);
        myDB.Update(queryPersonne);

        String queryID = "SELECT * FROM Personnes WHERE Login = %s AND ;".formatted(login);
        this.UID = Integer.parseInt(myDB.ReadSingle(queryID, "ID"));

        String queryUser = "INSERT INTO Demandeur (ID, ID_Valideur) VALUES ( \"%s\",  \"%s\" );".formatted(this.UID, this.UID_Valideur);
        myDB.Update(queryUser);

        myDB.Close();
    }
}
