package dao;

import config.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Model_Distributor;
import model.Model_Pemesanan;
import model.Model_Pengguna;
import service.Service_Pemesanan;

public class DAO_Pemesanan implements Service_Pemesanan {
    private Connection conn;
    
    public DAO_Pemesanan() {
        conn = koneksi.getConnection();
    }

    @Override
    public void tambahData(Model_Pemesanan mod_bar) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Model_Pemesanan getByid(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Model_Pemesanan> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM pemesanan";
        
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            
            while(rs.next()) {
                Model_Pemesanan psn = new Model_Pemesanan();
                Model_Distributor dst = new Model_Distributor();
                Model_Pengguna pgn = new Model_Pengguna();
                
                psn.setNo_pesan(rs.getString("no_pesan"));
                psn.setTgl_pesan(rs.getString("tgl_pesan"));
                psn.setTotal_pesan(rs.getLong("total_pesan"));
                dst.setId_distributor(rs.getString("id_distributor"));
                pgn.setId_pengguna(rs.getString("id_pengguna"));
                
                psn.setMod_distributor(dst);
                psn.setMod_pengguna(pgn);
                
                list.add(psn);
            } 
            
            return list;
        } catch (SQLException e) {
            Logger.getLogger(DAO_Pemesanan.class.getName()).log(Level.SEVERE, null, e);
            return null;
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    Logger.getLogger(DAO_Pemesanan.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    @Override
    public List<Model_Pemesanan> pencarian(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String nomor() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void hapusData(Model_Pemesanan model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
