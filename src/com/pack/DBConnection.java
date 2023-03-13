package com.pack;

import java.sql.*;

public class DBConnection {
    public static Connection getConnection() {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/atmmanagementsystem", "postgres", "#VikasH1276250");
        }catch(Exception e) {
            System.out.println(e);
        }
        return c;
    }
}
