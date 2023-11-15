package org.apo.controlleur;

import org.apo.model.Aideur;
import org.apo.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConnexionControlleurTest {


    @Test
    void TestConnexion () {
        ConnexionControlleur controlleur = new ConnexionControlleur();

        User real = controlleur.InfoConnexion();

        User expected = new Aideur(1);
        assertEquals(expected.toString(), real.toString());
    }




}
