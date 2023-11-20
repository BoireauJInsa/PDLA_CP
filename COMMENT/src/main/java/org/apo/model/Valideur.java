package org.apo.model;
import org.apo.controlleur.DBInterface;
import java.util.HashMap;

public class Valideur extends User {

    public Valideur(int UID) {
        super(UID);
    }

    @Override
    public HashMap<Integer, Demande> recuperer_demandes_abstract() {
        return recuperer_demandes("SELECT * FROM Demandes WHERE ID_Valideur = " + this.UID + " ;");
    }

    @Override
    public void RegisterUser(String login, String mdp) {

        super.login=login;
        super.motDePass=mdp;
        super.Status="Valideur";

        DBInterface myDB = new DBInterface ();
        String queryPersonne ="INSERT INTO Personnes (Login, Pass, Statut) VALUES ( \"%s\",  \"%s\" , \"Valideur\" );".formatted(login, mdp);
        myDB.Update(queryPersonne);

        String queryID = "SELECT * FROM Personnes WHERE Login = %s AND Pass = %s;".formatted("'"+login+"'","'"+mdp+"'");
        this.UID = Integer.parseInt(myDB.ReadSingle(queryID, "ID"));

        String queryUser = "INSERT INTO Valideur (ID) VALUES ( \"%s\" );".formatted(this.UID);
        myDB.Update(queryUser);

        myDB.Close();
    }

    @Override
    public String toString() {
        return "Aideur{" +
                "ID=" + super.getUID() +
                ", Login=" + super.getLogin() +
                ", Mot de pass=" + super.getMotDePass() +
                ", Statut='" +  super.getStatus() + '\'' +
                '}';
    }
}

