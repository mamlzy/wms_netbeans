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
 * @author asus
 */
public class UnitDAO {
    private Connection connection;

    public UnitDAO() {
        this.connection = Database.getConnection();
    }

    public List<Unit> findAll() {
        List<Unit> list = new ArrayList<>();

        String sql = "SELECT * FROM units i";


        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Unit item = new Unit(
                        rs.getInt("id"),
                        rs.getString("name")
                );

                list.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
