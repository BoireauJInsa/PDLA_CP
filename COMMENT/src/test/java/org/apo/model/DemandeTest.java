package org.apo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DemandeTest {
    @Test
    void testConstructeur() {
        Demande test = new Demande(-1, -1, -1, -1, "","");
    }

    @Test
    void testToString() {
        Demande value = new Demande(0, 1, 2, 3, "texte statut","texte ");
        String expected = "Demande{" +
                "ID=" + 0 +
                ", ID_Demandeur=" + 1 +
                ", ID_Verifieur=" + 2 +
                ", ID_Aideur=" + 3 +
                ", Statut='" + "texte statut" + '\'' +
                ", Message='" + "texte " + '\'' +
                '}';

        assertEquals(expected, value.toString());
    }


}