package org.apo.model;

import java.util.HashMap;

public abstract class User {
    int UID;
    public User(int UID) {
        this.UID = UID;
    }

    public abstract HashMap<Integer, Demande> recuperer_tableau();

    //public abstract void afficher_profil();
}
