package org.apo.controlleur;

import org.apo.model.Aideur;
import org.apo.model.ErrorBadParameters;
import org.apo.model.ErrorDontExist;
import org.apo.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConnexionControlleurTest {


    @Test
    void TestConnexion () {
        ConnexionControlleur controlleur = new ConnexionControlleur();
        try {
            controlleur.Signup("TestLogin","TestPassword","Aideur","0");
        } catch (ErrorDontExist e) {
            assert false;
        } catch (ErrorBadParameters e) {
            try {
                controlleur.Login("TestLogin","TestPassword");
            } catch (ErrorBadParameters ex) {
                assert false;
            }
        }

        User premier = controlleur.InfoConnexion();


        try {
            controlleur.Login("TestLogin","TestPassword");
        } catch (ErrorBadParameters ex) {
            assert false;
        }
        User  deuxieme = controlleur.InfoConnexion();
        System.out.println(deuxieme.getClass() + " " +deuxieme.getUID());

        assertEquals(deuxieme.getUID(),premier.getUID());
    }




}
