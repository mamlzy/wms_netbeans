package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Supplier;
import config.Database;
import lib.Utils;

/**
 *
 * @author asus
 */
public class SupplierDAO {

    private Connection connection;

    public SupplierDAO() {
        this.connection = Database.getConnection();
    }

    public List<Supplier> findAll(String keyword) {
        List<Supplier> list = new ArrayList<>();

        String sql = "SELECT * FROM suppliers s";
        
        // Menambahkan WHERE jika ada query
        if (keyword != null && !keyword.trim().isEmpty()) {
            sql += " WHERE s.name LIKE '%" + keyword + "%'"
                    + " OR s.phone LIKE '%" + keyword + "%'"
                    + " OR s.address LIKE '%" + keyword + "%'";
        }

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Supplier item = new Supplier(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("address")
                );

                list.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public Supplier findById(int id) {
        Supplier supplier = null;
        String sql = "SELECT * FROM suppliers WHERE id = ?";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                supplier = new Supplier(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("address")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return supplier; // bisa null kalau tidak ditemukan
    }

    public boolean insert(Supplier supplier) {
        String sql = "INSERT INTO suppliers (name, phone, address) VALUES (?,?,?)";
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getPhone());
            stmt.setString(3, supplier.getAddress());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Supplier supplier) {
        String sql = "UPDATE suppliers SET name = ?, phone = ?, address = ?  WHERE id = ?";
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getPhone());
            stmt.setString(3, supplier.getAddress());
            stmt.setInt(4, supplier.getId()); // pastikan item punya id

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM suppliers WHERE id = ?";
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
