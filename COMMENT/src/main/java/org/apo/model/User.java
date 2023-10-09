package org.apo.model;

public abstract class User {
    int UID;
    public User(int UID) {
        this.UID = UID;
    }

    public abstract void afficher_tableau();

    //public abstract void afficher_profil();
}
