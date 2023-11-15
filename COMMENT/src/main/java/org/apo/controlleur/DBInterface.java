package org.apo.controlleur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
public class DBInterface {


    Connection conn = null;
    Statement stmt = null;
    public  DBInterface () {

        try {
            conn = DriverManager.getConnection("jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_023", "projet_gei_023" ,"voag5Noo");

            // Do something with the Connection
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public ResultSet Read (String Query) {
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(Query);
            stmt.close();
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return rs;
    }
    public String ReadSingle (String Query1, String Query2) {
        ResultSet rs;
        String result = "";
        rs = this.Read(Query1);
        try {
            while (rs.next()) {
                result = rs.getString(Query2);
            }
        } catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return result;
    }

    public String ReadSingleThrow (String Query1, String Query2) throws SQLException {
        ResultSet rs;
        String result = "";
        rs = this.Read(Query1);

        while (rs.next()) {
            result = rs.getString(Query2);
        }

        return result;
    }

    public void Update (String Query) {
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(Query);
            stmt.close();
        }
        catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public void Close () {
        try {
            conn.close();
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

}
