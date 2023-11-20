package org.apo.model;
import org.apo.controlleur.DBInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Demandeur extends User {

    private int UIDValideur;

    public Demandeur(int UID, int UIDValideur) {
        super(UID);
        this.UIDValideur = UIDValideur;
    }


    @Override
    public HashMap<Integer, Demande> recuperer_demandes_abstract() {
        return recuperer_demandes("SELECT * FROM Demandes WHERE ID_Demandeur = " + this.UID + " ;");
    }

    @Override
    public void RegisterUser(String login, String mdp) {

        super.login=login;
        super.motDePass=mdp;
        super.Status="Demandeur";

        DBInterface myDB = new DBInterface ();
        String queryPersonne ="INSERT INTO Personnes (Login, Pass, Statut) VALUES ( \"%s\",  \"%s\" , \"Demandeur\" );".formatted(login, mdp);
        myDB.Update(queryPersonne);

        String queryID = "SELECT * FROM Personnes WHERE Login = %s AND Pass = %s;".formatted("'"+login+"'","'"+mdp+"'");
        this.UID = Integer.parseInt(myDB.ReadSingle(queryID, "ID"));

        String queryUser = "INSERT INTO Demandeur (ID, ID_Valideur) VALUES ( \"%s\",  \"%s\" );".formatted(this.UID, this.UIDValideur);
        myDB.Update(queryUser);

        myDB.Close();
    }

    public void Action (String message) {
        DBInterface myDB = new DBInterface ();
        String queryDemande ="INSERT INTO Demande (ID_Demandeur, Message, Statut, ID_Valideur, ID_Aideur) VALUES ( %d,  \"%s\" , \"attente\", %d, 0);".formatted(this.UID, message, this.UIDValideur);
        myDB.Update(queryDemande);

    }

    @Override
    public String toString() {
        return "Aideur{" +
                "ID=" + super.getUID() +
                ", Login=" + super.getLogin() +
                ", Mot de pass=" + super.getMotDePass() +
                ", Valideur=" + UIDValideur +
                '}';
    }

    public int getUIDValideur() {
        return UIDValideur;
    }
}
