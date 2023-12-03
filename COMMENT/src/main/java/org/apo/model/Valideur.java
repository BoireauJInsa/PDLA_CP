package org.apo.model;
import org.apo.controlleur.DBInterface;
import java.util.HashMap;

public class Valideur extends User {

    int UIDHospital;

    public Valideur(int UID, int UIDHospital) {
        super(UID);
        this.UIDHospital=UIDHospital;
    }

    @Override
    public HashMap<Integer, Demande> recuperer_demandes_abstract() {
        return recuperer_demandes("SELECT * FROM Demande WHERE ID_Valideur = %s AND Statut = 'attente';".formatted(UIDHospital));
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

        String queryUser = "INSERT INTO Valideur (ID, Statut) VALUES ( \"%s\", 'attente' );".formatted(UIDHospital);
        myDB.Update(queryUser);

        myDB.Close();
    }

    public void Action(Demande D, String statut) throws ErrorNoPerms{
        if (D.ID_hospital != this.UIDHospital) {
            throw (new ErrorNoPerms("ID Invalide -> ChangerStatut"));
        } else if (D.statut!="terminé" && D.statut!="prise") {

            D.statut = statut;

            DBInterface myDB = new DBInterface();
            String queryModificationStatus = "UPDATE Demande SET Statut = '%s' WHERE ID = %d ;".formatted(statut, D.ID);

            myDB.Update(queryModificationStatus);
        } else {
            throw (new ErrorNoPerms("Statut invalide"));
        }

    }

    @Override
    public String toString() {
        return "Valideur{" +
                "ID=" + super.getUID() +
                ", Login=" + super.getLogin() +
                ", Mot de pass=" + super.getMotDePass() +
                ", Statut='" +  super.getStatus() + '\'' +
                '}';
    }
}

