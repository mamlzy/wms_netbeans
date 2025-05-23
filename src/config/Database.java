package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

/**
 *
 * @author Agung
 */


public class Database {
    
    private static final Dotenv dotenv = Dotenv.load();
    private static final String DB_Database = "wms";
    private static final String DB_URL = "jdbc:mysql://mysql-8cf3a2-wms-project.i.aivencloud.com:11912/" + DB_Database;
    private static final String DB_USER = "avnadmin";
    private static final String DB_PASSWORD = dotenv.get("DB_PASSWORD");
    
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
