/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import model.Model_BarangKeluar;
import model.Model_Pengguna;
import service.Service_BarangKeluar;

/**
 *
 * @author imama
 */
public class DAO_BarangKeluar implements Service_BarangKeluar {
    private Connection conn;
    
    public DAO_BarangKeluar() {
        conn = koneksi.getConnection();
    }

    @Override
    public void tambahData(Model_BarangKeluar mod_keluar) {
        PreparedStatement st = null;
        String sql = "INSERT INTO barang_keluar(no_keluar, tgl_keluar, total_keluar, id_pengguna) VALUES (?,?,?,?)";
        try {
            st = conn.prepareStatement(sql);
            
            st.setString(1, mod_keluar.getNo_keluar());
            st.setString(2, mod_keluar.getTgl_keluar());
            st.setLong(3, mod_keluar.getTotal_keluar());
            st.setString(4, mod_keluar.getMod_pengguna().getId_pengguna());
            
            st.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DAO_BarangKeluar.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    Logger.getLogger(DAO_BarangKeluar.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
    
    @Override
    public void hapusData(Model_BarangKeluar mod_keluar) {
        PreparedStatement stDetail = null;
        PreparedStatement stKeluar = null;
        String sqlDetailBarangKeluar = "DELETE FROM detail_barang_keluar WHERE no_keluar=?";
        String sqlBarangKeluar = "DELETE FROM barang_keluar WHERE no_keluar=?";

        try {
            // Start transaction
            conn.setAutoCommit(false);

            // Delete detail first (to satisfy foreign key constraints)
            stDetail = conn.prepareStatement(sqlDetailBarangKeluar);
            stDetail.setString(1, mod_keluar.getNo_keluar());
            stDetail.executeUpdate();

            // Then delete main record
            stKeluar = conn.prepareStatement(sqlBarangKeluar);
            stKeluar.setString(1, mod_keluar.getNo_keluar());
            stKeluar.executeUpdate();

            // Commit if both succeed
            conn.commit();
        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback(); // Rollback on error
            } catch (SQLException rollbackEx) {
                Logger.getLogger(DAO_BarangKeluar.class.getName()).log(Level.SEVERE, null, rollbackEx);
            }
            Logger.getLogger(DAO_BarangKeluar.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (stDetail != null) stDetail.close();
                if (stKeluar != null) stKeluar.close();
                conn.setAutoCommit(true); // Restore auto-commit
            } catch (SQLException e) {
                Logger.getLogger(DAO_BarangKeluar.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    @Override
    public Model_BarangKeluar getByid(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Model_BarangKeluar> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM barang_keluar";
        
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            
            while(rs.next()) {
                Model_BarangKeluar keluar = new Model_BarangKeluar();
                Model_Pengguna pgn = new Model_Pengguna();
                
                keluar.setNo_keluar(rs.getString("no_keluar"));
                keluar.setTgl_keluar(rs.getString("tgl_keluar"));
                keluar.setTotal_keluar(rs.getLong("total_keluar"));
                pgn.setId_pengguna(rs.getString("id_pengguna"));
                
                keluar.setMod_pengguna(pgn);
                
                list.add(keluar);
            } 
            
            return list;
        } catch (SQLException e) {
            Logger.getLogger(DAO_BarangKeluar.class.getName()).log(Level.SEVERE, null, e);
            return null;
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    Logger.getLogger(DAO_BarangKeluar.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    @Override
    public List<Model_BarangKeluar> pencarian(String id) {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM barang_keluar WHERE no_keluar LIKE '%" + id + "%'";
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()) {
                Model_BarangKeluar keluar = new Model_BarangKeluar();
                Model_Pengguna pgn = new Model_Pengguna();
                
                keluar.setNo_keluar(rs.getString("no_keluar"));
                keluar.setTgl_keluar(rs.getString("tgl_keluar"));
                keluar.setTotal_keluar(rs.getLong("total_keluar"));
                pgn.setId_pengguna(rs.getString("id_pengguna"));
                
                keluar.setMod_pengguna(pgn);
                
                list.add(keluar);
            }
            return list;
        } catch (SQLException e) {
            Logger.getLogger(DAO_BarangKeluar.class.getName()).log(Level.SEVERE, null, e);
            return null;
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    Logger.getLogger(DAO_BarangKeluar.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
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
        
        String sql = "SELECT RIGHT(no_keluar, 3) AS Nomor " +
                     "FROM barang_keluar " +
                     "WHERE no_keluar LIKE 'BK" + no + "%' " +
                     "ORDER BY no_keluar DESC " +
                     "LIMIT 1";
        
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            
            if(rs.next()) {
                int nomor = Integer.parseInt(rs.getString("Nomor"));
                nomor++;
                urutan = "BK" + no + String.format("%03d", nomor);
            } else {
                urutan = "BK" + no + "001";
            }
        } catch (SQLException e) {
            Logger.getLogger(DAO_BarangKeluar.class.getName()).log(Level.SEVERE, null, e);
            return null;
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    Logger.getLogger(DAO_BarangKeluar.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        
        return urutan;
    }
}
