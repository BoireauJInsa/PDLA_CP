package org.apo.model;
import org.apo.controlleur.DBInterface;

import java.util.HashMap;

public class Aideur extends User {

    public Aideur(int UID) {
        super(UID);
    }

    @Override
    public HashMap<Integer, Demande> recuperer_demandes_abstract() {
        return recuperer_demandes("SELECT * FROM Demandes WHERE Statut = accepté;");
    }

    @Override
    public void RegisterUser(String login, String mdp) {
        DBInterface myDB = new DBInterface ();
        String queryPersonne ="INSERT INTO Personnes (Login, Pass, Statut) VALUES ( \"%s\",  \"%s\" , \"Aideur\" );".formatted(login, mdp);
        myDB.Update(queryPersonne);

        String queryID = "SELECT * FROM Personnes WHERE Login = %s AND Pass = %s;".formatted("'"+login+"'","'"+mdp+"'");
        this.UID = Integer.parseInt(myDB.ReadSingle(queryID, "ID"));

        String queryUser = "INSERT INTO Aideur (ID) VALUES ( \"%s\" );".formatted(this.UID);
        myDB.Update(queryUser);

        myDB.Close();
    }
}
