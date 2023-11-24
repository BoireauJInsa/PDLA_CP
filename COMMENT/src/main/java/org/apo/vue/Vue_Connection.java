package org.apo.vue;


import org.apo.model.Demande;

import java.util.ArrayList;
import java.util.HashMap;

public class Vue_Connection  {



    public HashMap<Integer, Demande> info;
    public ArrayList<Integer> data = new ArrayList<>();
    public String login;
    public String motDePasse;
    public int idSupp;

     public Vue_Connection(HashMap<Integer, Demande> dem){
        info = dem;
        idSupp=0;
     }


    public HashMap<Integer, Demande> Selection () {
         return info;
    }

    public boolean PriseInfoCo() {
         return true;
    }

    public ArrayList<Integer> PriseInfoCr(){

        return data;
    }
}
