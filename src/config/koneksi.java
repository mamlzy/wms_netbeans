package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class koneksi {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/netbeans_wms_imam";
                String user = "root";
                String pass = "secret";

                connection = DriverManager.getConnection(url, user, pass);
            } catch (SQLException e) {
                Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        return connection;
    }
}
