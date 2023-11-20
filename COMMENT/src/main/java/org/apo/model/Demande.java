package org.apo.model;

import org.apo.controlleur.DBInterface;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Demande {
    int ID;
    int ID_demandeur;
    int ID_Aideur;
    int ID_Valideur;
    String statut;
    String message;

    public Demande (int ID) {
        DBInterface myDB = new DBInterface ();
        String queryDemande = "SELECT * FROM Demande WHERE ID= %d".formatted(ID);
        ResultSet rs = myDB.Read(queryDemande);

        try {
            while (rs.next()) {
                ID_demandeur = rs.getInt("ID_Demandeur");
                ID_Aideur = rs.getInt("ID_Aideur");
                ID_Valideur = rs.getInt("ID_Valideur");
                message = rs.getString("Message");
                statut = rs.getString("Statu");

            }
            rs.close();
        } catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        myDB.CloseStatement();
        myDB.Close();

    }

    public Demande(int ID, int ID_demandeur, int ID_Valideur, int ID_Aideur, String statut, String message) {
        this.ID = ID;
        this.ID_demandeur = ID_demandeur;
        this.ID_Aideur = ID_Aideur;
        this.ID_Valideur = ID_Valideur;
        this.statut = statut;
        this.message = message;
    }

    public void RegisterDemande (int ID_Demandeur, String Message) {
        DBInterface myDB = new DBInterface ();
        String queryDemande ="INSERT INTO Demande (ID_Demandeur, Message, Statut, ID_Aideur) VALUES ( %d,  \"%s\" , \"attente\", 0 );".formatted(ID_Demandeur, Message);
        myDB.Update(queryDemande);

    }

    public void ValiderRefuseDemande(int ID_Valideur, String statut) throws ErrorNoPerms{
        if (ID_Valideur != this.ID_Valideur) {
            throw (new ErrorNoPerms("ID Invalide -> ChangerStatut"));
        } else if (this.statut!="terminé") {

            this.statut = statut;

            DBInterface myDB = new DBInterface();
            String queryModificationStatus = "UPDATE Demande SET Statu = '%s' ".formatted(statut);
            myDB.Update(queryModificationStatus);
        }

    }
    public void UpdateDemande(){};

    @Override
    public String toString() {

        return "Demande{" +
                "ID=" + ID +
                ", ID_Demandeur=" + ID_demandeur +
                ", ID_Valideur=" + ID_Valideur +
                ", ID_Aideur=" + ID_Aideur +
                ", Statut='" + statut + '\'' +
                ", Message='" + message + '\'' +
                '}';


    }
}
