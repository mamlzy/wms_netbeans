package dao;

import model.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.Database;
import java.math.BigDecimal;

/**
 *
 * @author Agung
 */

public class ItemDAO {
    
    private Connection connection;
    
    public ItemDAO(){
        this.connection = Database.getConnection();
    }
    
    public List<Item> findAll(){
        List<Item> list = new ArrayList<>();
        
        String sql = "SELECT * FROM items";
        
        try {
            PreparedStatement  stmt = this.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                list.add(new Item(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getBigDecimal("price").toBigInteger(),
                        rs.getInt("quantity"),
                        rs.getInt("min_quantity"),
                        rs.getString("unit")
                ));
            }
                    
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    public boolean insert(Item item){
        String sql = "INSERT INTO items (name, price, quantity, min_quantity, unit) VALUES (?,?,?,?,?)";
        try(PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setString(1, item.getName());
            stmt.setBigDecimal(2, new BigDecimal(item.getPrice()));
            stmt.setInt(3, item.getQuantity());
            stmt.setInt(4, item.getMin_quantity());
            stmt.setString(5, item.getUnit());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception  e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean update(Item item) {
    String sql = "UPDATE items SET name = ?, price = ?, quantity = ?, min_quantity = ?, unit = ? WHERE id = ?";
    try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
        stmt.setString(1, item.getName());
        stmt.setBigDecimal(2, new BigDecimal(item.getPrice()));
        stmt.setInt(3, item.getQuantity());
        stmt.setInt(4, item.getMin_quantity());
        stmt.setString(5, item.getUnit());
        stmt.setInt(6, item.getId()); // pastikan item punya id
        
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
    
}
