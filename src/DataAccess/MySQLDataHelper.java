package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class MySQLDataHelper {
    // private static String DB_URL =
    // "jdbc:mysql://93.127.213.145:3306/sistema_medico_epn";
    // private static String DB_USER = System.getenv("DB_USER");
    // private static String DB_PASSWORD = System.getenv("DB_PASSWORD");
    // private static Connection conn = null;
    private static String DB_URL = "jdbc:sqlite:database/sistema_medico.sqlite";
    private static Connection conn = null;

    protected MySQLDataHelper() {
    }

    protected static synchronized Connection openConnection() throws Exception {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(DB_URL);
            }
        } catch (SQLException e) {
            throw new Exception("Failed to connect to the database", e);
        }
        return conn;
    }

    protected static void closeConnection() throws Exception {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            throw new Exception("Failed to close the database connection", e);
        }
    }
}