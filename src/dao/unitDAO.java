package dao;

import config.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Unit;

/**
 *
 * @author Agung
 */

public class UnitDAO {
    private Connection connection;

    public UnitDAO() {
        this.connection = Database.getConnection();
    }
    
    public List<Unit> findAll() {
        List<Unit> list = new ArrayList<>();

        String sql = "SELECT * FROM units";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                list.add(new Unit(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
