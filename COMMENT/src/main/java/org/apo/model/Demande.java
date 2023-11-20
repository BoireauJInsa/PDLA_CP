package org.apo.model;

import org.apo.controlleur.DBInterface;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Demande {
    int ID;
    Demandeur demandeur;
    Aideur aider;
    String statut;
    String message;

    public Demande (int ID) {
        DBInterface myDB = new DBInterface ();
        String queryDemande = "SELECT * FROM Demande WHERE ID= %d".formatted(ID);
        ResultSet rs = myDB.Read(queryDemande);

        try {
            while (rs.next()) {
                int ID_demandeur = rs.getInt("ID_Demandeur");
                int ID_Aideur = rs.getInt("ID_Aideur");
                message = rs.getString("Message");
                statut = rs.getString("Statu");

                if (ID_Aideur==0){
                    aider=null;
                }else {
                    aider = null ; ///new Aideur(ID_Aideur);   ////// ATTTENTION C4EST A GERER
                }
                demandeur=null; ///new Demandeur(ID_demandeur);
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

    public Demande(int ID, Demandeur demandeur, Aideur aider, String Statut, String Message) {
        this.ID = ID;
        this.demandeur = demandeur;
        this.aider = aider;
        this.statut = Statut;
        this.message = Message;
    }

    public void RegisterDemande (int ID_Demandeur, String Message) {
        DBInterface myDB = new DBInterface ();
        String queryDemande ="INSERT INTO Demande (ID_Demandeur, Message, Statut, ID_Aideur) VALUES ( %d,  \"%s\" , \"attente\", 0 );".formatted(ID_Demandeur, Message);
        myDB.Update(queryDemande);

    }

    public void ValiderRefuseDemande(int ID_Verifieur, String statut) throws ErrorNoPerms{
        if (ID_Verifieur != this.demandeur.getUIDValideur()) {
            throw (new ErrorNoPerms("ID Invalide -> ChangerStatut"));
        } else if (this.statut=="terminé") {

        }else {
            this.statut = statut;

            DBInterface myDB = new DBInterface();
            String queryModificationStatus = "UPDATE Demande SET Statu = '%s' ".formatted(statut);
            myDB.Update(queryModificationStatus);
        }

    }
    public void UpdateDemande(){};

    @Override
    public String toString() {

        if (aider==null){
            return "Demande{" +
                    "ID=" + ID +
                    ", ID_Demandeur=" + demandeur.toString() +
                    ", Statut='" + statut + '\'' +
                    ", Message='" + message + '\'' +
                    '}';
        }else{
            return "Demande{" +
                    "ID=" + ID +
                    ", ID_Demandeur=" + demandeur.toString() +
                    ", ID_Aideur=" + aider.toString() +
                    ", Statut='" + statut + '\'' +
                    ", Message='" + message + '\'' +
                    '}';
        }

    }
}
