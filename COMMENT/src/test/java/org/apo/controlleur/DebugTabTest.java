package org.apo.controlleur;

import org.junit.jupiter.api.Test;

import org.apo.model.Demande;

import java.util.HashMap;

import static org.apo.controlleur.DebugTab.PrintTab;

class DebugTabTest {

    @Test
    void PrintToTermTest() {
        Demande D1 = new Demande(1, 2, 3, 3, "texte statut","texte ");
        Demande D2 = new Demande(1, 2, 3, 3, "texte statut","texte ");
        Demande D3 = new Demande(1, 2, 3, 3, "texte statut","texte ");
        Demande D4 = new Demande(1, 2, 3, 3, "texte statut","texte ");

        HashMap<Integer, Demande> DB_Testmap = new HashMap<Integer, Demande>();
        DB_Testmap.put(1, D1);
        DB_Testmap.put(2, D2);
        DB_Testmap.put(3, D3);
        DB_Testmap.put(4, D4);

        PrintTab(DB_Testmap);
    }
}