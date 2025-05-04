package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Agung
 */

public class Database {
    private static final String DB_Database = "wms";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/" + DB_Database;
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123123123";
    
    private static Connection connection;
    
    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                System.out.println("Koneksi ke database berhasil.");
            } catch (SQLException e) {
                System.out.println("Gagal koneksi ke database: " + e.getMessage());
            }
        }
        return connection;
    }
}
