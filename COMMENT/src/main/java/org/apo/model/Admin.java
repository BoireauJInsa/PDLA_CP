package org.apo.model;
import org.apo.controlleur.DBInterface;

import java.util.HashMap;

public class Admin extends User {

    public Admin(int UID) {
        super(UID);
    }

    @Override
    public HashMap<Integer, Demande> recuperer_demandes_abstract() {
        return recuperer_demandes("SELECT * FROM Valideur WHERE Statut = attente;");
    }

    @Override
    public void RegisterUser(String login, String mdp) {

        super.login=login;
        super.motDePass=mdp;
        super.Status="Admin";

        DBInterface myDB = new DBInterface ();
        String queryPersonne ="INSERT INTO Personnes (Login, Pass, Statut) VALUES ( \"%s\",  \"%s\" , \"Admin\" );".formatted(login, mdp);
        myDB.Update(queryPersonne);

        String queryID = "SELECT * FROM Personnes WHERE Login = %s AND Pass = %s;".formatted("'"+login+"'","'"+mdp+"'");
        this.UID = Integer.parseInt(myDB.ReadSingle(queryID, "ID"));

        String queryUser = "INSERT INTO Admin (ID) VALUES ( \"%s\" );".formatted(this.UID);
        myDB.Update(queryUser);

        myDB.Close();
    }

    public void Action(int Target_UID, String statut) throws ErrorNoPerms{
            DBInterface myDB = new DBInterface();
            String queryModificationStatus = "UPDATE Valideur SET Statut = '%s' WHERE ID = %d;".formatted(statut, Target_UID);
            myDB.Update(queryModificationStatus);
        }

    @Override
    public String toString() {
        return "Admin{" +
                "ID=" + super.getUID() +
                ", Login=" + super.getLogin() +
                ", Mot de pass=" + super.getMotDePass() +
                '}';
    }
}
