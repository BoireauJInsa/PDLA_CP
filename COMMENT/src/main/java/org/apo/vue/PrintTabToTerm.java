package org.apo.vue;

import org.apo.model.Demande;

import java.util.HashMap;

public class PrintTabToTerm {
    public void PrintTab(HashMap<Integer, Object> Map) {
        Map.forEach((key, value) -> System.out.println(key + " " + value));
    }
}
