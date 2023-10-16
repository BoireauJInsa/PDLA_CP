package org.apo.model;

public class Demande {
    int ID;
    int ID_Demandeur;
    int ID_Verifieur;
    int ID_Aideur;
    String Statut;
    String Message;

    public Demande(int ID, int ID_Demandeur, int ID_Verifieur, int ID_Aideur, String Statut, String Message) {
        this.ID = ID;
        this.ID_Demandeur = ID_Demandeur;
        this.ID_Verifieur = ID_Verifieur;
        this.ID_Aideur = ID_Aideur;
        this.Statut = Statut;
        this.Message = Message;
    }

    @Override
    public String toString() {
        return "Demande{" +
                "ID=" + ID +
                ", ID_Demandeur=" + ID_Demandeur +
                ", ID_Verifieur=" + ID_Verifieur +
                ", ID_Aideur=" + ID_Aideur +
                ", Statut='" + Statut + '\'' +
                ", Message='" + Message + '\'' +
                '}';
    }
}
