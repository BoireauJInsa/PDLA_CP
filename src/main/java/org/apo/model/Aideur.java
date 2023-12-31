package org.apo.model;
import org.apo.controlleur.DBInterface;

import java.util.HashMap;
import java.util.Objects;

public class Aideur extends User {

    public Aideur(int UID) {
        super(UID);
    }

    @Override
    public HashMap<Integer, Demande> recuperer_demandes_abstract() {
        return recuperer_demandes("SELECT * FROM Demande WHERE Statut = 'accepté';");
    }

    @Override
    public void RegisterUser(String login, String mdp) {

        super.login=login;
        super.motDePass=mdp;
        super.Status="Aideur";

        DBInterface myDB = new DBInterface ();
        String queryPersonne ="INSERT INTO Personnes (Login, Pass, Statut) VALUES ( \"%s\",  \"%s\" , \"Aideur\" );".formatted(login, mdp);
        myDB.Update(queryPersonne);

        String queryID = "SELECT * FROM Personnes WHERE Login = %s AND Pass = %s;".formatted("'"+login+"'","'"+mdp+"'");
        this.UID = Integer.parseInt(myDB.ReadSingle(queryID, "ID"));

        String queryUser = "INSERT INTO Aideur (ID) VALUES ( \"%s\" );".formatted(this.UID);
        myDB.Update(queryUser);

        myDB.Close();
    }

    public void Action(Demande D) throws ErrorNoPerms{
        if (!Objects.equals(D.statut, "accepté")) {
            System.out.println(D.statut);
            throw (new ErrorNoPerms("Statut invalide -> demande pas encore accepté"));
        } else if (D.statut.equals("prise")) {
            System.out.println(D.statut);
            throw (new ErrorNoPerms("Statut invalide -> demande déja prise"));
        } else {
            D.statut = "prise";

            DBInterface myDB = new DBInterface();
            String queryModificationStatus = "UPDATE Demande SET Statut = '%s', ID_Aideur = %d WHERE ID = %d ;".formatted("prise", this.UID, D.ID);
            myDB.Update(queryModificationStatus);
        }
    }

    @Override
    public String toString() {
        return "Aideur{" +
                "ID=" + super.getUID() +
                ", Login=" + super.getLogin() +
                ", Mot de pass=" + super.getMotDePass() +
                '}';
    }
}
