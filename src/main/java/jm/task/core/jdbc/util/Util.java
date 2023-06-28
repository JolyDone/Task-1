package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Util {
    public Util() {}

    private static final String DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    public static Connection connection;
    public static Statement statement;

    public static Connection getConnection() {
        connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager
                    .getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return connection;
    }

    public static void setConnection(Connection connection) {
        Util.connection = connection;
    }
}