package dao;

import config.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public void tambahData(Model_Pemesanan mod_pesan) {
        PreparedStatement st = null;
        String sql = "INSERT INTO pemesanan (no_pesan, tgl_pesan, total_pesan, id_distributor, id_pengguna) VALUES (?,?,?,?,?,)";
        try {
            st = conn.prepareStatement(sql);
            
            st.setString(1, mod_pesan.getNo_pesan());
            st.setString(2, mod_pesan.getTgl_pesan());
            st.setLong(3, mod_pesan.getTotal_pesan());
            st.setString(4, mod_pesan.getMod_distributor().getId_distributor());
            st.setString(5, mod_pesan.getMod_pengguna().getId_pengguna());
            
            st.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DAO_Pemesanan.class.getName()).log(Level.SEVERE, null, e);
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
        PreparedStatement st = null;
        ResultSet rs = null;
        String urutan = null;
        
        Date now = new Date();
        SimpleDateFormat tanggal = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat noFormat = new SimpleDateFormat("yyMMdd");
        String tgl = tanggal.format(now);
        String no = noFormat.format(now);
        
        String sql = "SELECT RIGHT(no_pesan, 3) AS Nomor " +
                     "FROM pemesanan " +
                     "WHERE no_pesan LIKE 'PB" + no + "%' " +
                     "ORDER BY no_pesan DESC " +
                     "LIMIT 1";
        
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            
            if(rs.next()) {
                int nomor = Integer.parseInt(rs.getString("Nomor"));
                nomor++;
                urutan = "PB" + no + String.format("%03d", nomor);
            } else {
                urutan = "PB" + no + "001";
            }
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
        
        return urutan;
    }

    @Override
    public void hapusData(Model_Pemesanan model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
