package org.apo.controlleur;
import java.util.HashMap;

public class DebugTab {
    public static void PrintTab(HashMap Map) {
        Map.forEach((key, value) -> System.out.println(key + " " + value));
    }
}
