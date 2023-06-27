package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Util {
    private final String DRIVER = "org.postgresql.Driver";
    private final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private final String USERNAME = "postgres";
    private final String PASSWORD = "postgres";

    public void getConnection() {
        Connection c = null;
        try {
            Class.forName(DRIVER);
            c = DriverManager
                    .getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
}
