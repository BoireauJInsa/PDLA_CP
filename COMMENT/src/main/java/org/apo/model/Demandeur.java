package org.apo.model;
import org.apo.controlleur.DBInterface;

import java.util.HashMap;

public class Demandeur extends User {

    private int UIDHospital;

    public Demandeur(int UID, int UIDValideur) {
        super(UID);
        this.UIDHospital = UIDValideur;
    }


    @Override
    public HashMap<Integer, Demande> recuperer_demandes_abstract() {
        return recuperer_demandes("SELECT * FROM Demande WHERE ID_Demandeur = %d AND Statut <> 'terminé';".formatted(this.UID));
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

        String queryUser = "INSERT INTO Demandeur (ID, ID_hospital) VALUES ( \"%s\",  \"%s\" );".formatted(this.UID, this.UIDHospital);
        myDB.Update(queryUser);

        myDB.Close();
    }

    public void AjoutDemande (String message) {
        DBInterface myDB = new DBInterface ();
        String queryDemande ="INSERT INTO Demande (ID_Demandeur, Message, Statut, ID_hospital, ID_Aideur) VALUES ( %d,  \"%s\" , \"attente\", %d, 0);".formatted(this.UID, message, this.UIDHospital);
        myDB.Update(queryDemande);

    }


    public void Action (Demande D) throws ErrorNoPerms {


        if (D.ID_demandeur!=UID) {
            throw (new ErrorNoPerms("Statut invalide -> demande ne vous appartient pas"));
        } else {
            D.statut = "terminé";

            DBInterface myDB = new DBInterface();
            String queryModificationStatus = "UPDATE Demande SET Statut = '%s' WHERE ID = %d ;".formatted("terminé", D.ID);
            myDB.Update(queryModificationStatus);
        }


    }

    @Override
    public String toString() {
        return "Aideur{" +
                "ID=" + super.getUID() +
                ", Login=" + super.getLogin() +
                ", Mot de pass=" + super.getMotDePass() +
                ", Valideur=" + UIDHospital +
                '}';
    }

    public int getUIDHospital() {
        return UIDHospital;
    }
}
