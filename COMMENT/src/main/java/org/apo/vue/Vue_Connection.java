package org.apo.vue;


public class Vue_Connection  {

    int info;

    public int id;
    public int type;
    public String login;
    public String motDePasse;
    public int idSupp;

     public Vue_Connection(int choixVue){
        info = choixVue;
        idSupp=0;
     }


    public int Selection () {
         return info;
    }

    public void PriseInfoCo() {
         id= 3;
    }

    public void PriseInfoCr(){
         id=4;
         type=1;
         login ="cc";
         motDePasse="aa";
    }
}
