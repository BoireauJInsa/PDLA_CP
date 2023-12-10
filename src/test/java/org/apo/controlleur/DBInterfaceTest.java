package org.apo.controlleur;

import org.junit.jupiter.api.Test;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DBInterfaceTest {
    @Test
    void TestCreationDBInterface() {
        DBInterface value = new DBInterface();
    }

    @Test
    void TestRead() {
        TestReadGen("SELECT * FROM Test WHERE ID = 3;", "admin" , "Statut");
        TestReadGen("SELECT * FROM Test WHERE ID = 2;", "2", "info");
    }
    void TestReadGen(String query, String expected, String filtre) {
        DBInterface MyDB = new DBInterface();
        ResultSet resultQuery = MyDB.Read(query);
        String value = null;
        try {
            resultQuery.next();
            value = resultQuery.getString(filtre);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        assertEquals(expected, value);
    }

    @Test
    void TestUpdate() {
        DBInterface MyDB = new DBInterface();
        MyDB.Update("UPDATE Test SET Statut = \"valideur\" Where ID = 3;");
        TestReadGen("SELECT * FROM Test WHERE ID = 3;", "valideur" , "Statut");
        MyDB.Update("UPDATE Test SET Statut = \"admin\" Where ID = 3;");
        TestReadGen("SELECT * FROM Test WHERE ID = 3;", "admin" , "Statut");
    }
}