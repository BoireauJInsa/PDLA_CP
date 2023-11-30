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

        this.ID=ID;

        DBInterface myDB = new DBInterface ();
        String queryDemande = "SELECT * FROM Demande WHERE ID= %d;".formatted(ID);
        ResultSet rs = myDB.Read(queryDemande);

        try {
            while (rs.next()) {
                ID_demandeur = rs.getInt("ID_Demandeur");
                ID_Aideur = rs.getInt("ID_Aideur");
                ID_Valideur = rs.getInt("ID_Valideur");
                message = rs.getString("Message");
                statut = rs.getString("Statut");

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

    public void UpdateDemande(){};

    public String GetNameDemandeur(){
        DBInterface myDB = new DBInterface ();
        return myDB.ReadSingle("SELECT * FROM Personnes WHERE id=%d".formatted(ID_demandeur),"Login");
    }

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

    public int getID() {
        return ID;
    }

    public int getID_demandeur() {
        return ID_demandeur;
    }

    public int getID_Aideur() {
        return ID_Aideur;
    }

    public int getID_Valideur() {
        return ID_Valideur;
    }

    public String getStatut() {
        return statut;
    }

    public String getMessage() {
        return message;
    }
}
