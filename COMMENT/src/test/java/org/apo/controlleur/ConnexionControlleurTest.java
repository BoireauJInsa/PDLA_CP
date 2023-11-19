package org.apo.controlleur;

import org.apo.model.Aideur;
import org.apo.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConnexionControlleurTest {


    @Test
    void TestConnexion () {
        ConnexionControlleur controlleur = new ConnexionControlleur();
        User  premier = controlleur.InfoConnexion();
        System.out.println(premier.getClass() + " " +premier.getUID());

        controlleur = new ConnexionControlleur();
        User  deuxieme = controlleur.InfoConnexion();
        System.out.println(deuxieme.getClass() + " " +deuxieme.getUID());

        assertEquals(deuxieme.getUID(),premier.getUID());
    }




}
