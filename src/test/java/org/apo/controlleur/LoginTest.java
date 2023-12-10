package org.apo.controlleur;

import org.apo.model.Aideur;
import org.apo.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    @Test
    void TestLogin() {
        User value = LoginController.ConnexionAvecID(1);
        User expected = new Aideur(1);

        assertEquals(expected, value.toString());
    }
}