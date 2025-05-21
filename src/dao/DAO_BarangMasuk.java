/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Model_BarangMasuk;
import model.Model_Distributor;
import model.Model_Pengguna;
import service.Service_BarangMasuk;

/**
 *
 * @author imama
 */
public class DAO_BarangMasuk implements Service_BarangMasuk {
    private Connection conn;
    
    public DAO_BarangMasuk() {
        conn = koneksi.getConnection();
    }

    @Override
    public void tambahData(Model_BarangMasuk mod_masuk) {
        PreparedStatement st = null;
        String sql = "INSERT INTO barang_masuk (no_masuk, tgl_masuk, total_masuk, id_distributor, id_pengguna) VALUES (?,?,?,?,?)";
        try {
            st = conn.prepareStatement(sql);
            
            st.setString(1, mod_masuk.getNo_masuk());
            st.setString(2, mod_masuk.getTgl_masuk());
            st.setLong(3, mod_masuk.getTotal_masuk());
            st.setString(4, mod_masuk.getMod_distributor().getId_distributor());
            st.setString(5, mod_masuk.getMod_pengguna().getId_pengguna());
            
            st.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    @Override
    public void hapusData(Model_BarangMasuk model) {
        PreparedStatement stDetail = null;
        PreparedStatement stMasuk = null;
        String sqlDetailBarangMasuk = "DELETE FROM detail_barang_masuk WHERE no_masuk=?";
        String sqlBarangMasuk = "DELETE FROM barang_masuk WHERE no_masuk=?";

        try {
            // Start transaction
            conn.setAutoCommit(false);

            // Delete detail first (to satisfy foreign key constraints)
            stDetail = conn.prepareStatement(sqlDetailBarangMasuk);
            stDetail.setString(1, model.getNo_masuk());
            stDetail.executeUpdate();

            // Then delete main record
            stMasuk = conn.prepareStatement(sqlBarangMasuk);
            stMasuk.setString(1, model.getNo_masuk());
            stMasuk.executeUpdate();

            // Commit if both succeed
            conn.commit();
        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback(); // Rollback on error
            } catch (SQLException rollbackEx) {
                Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, rollbackEx);
            }
            Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (stDetail != null) stDetail.close();
                if (stMasuk != null) stMasuk.close();
                conn.setAutoCommit(true); // Restore auto-commit
            } catch (SQLException e) {
                Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }


    @Override
    public void perbaruiStatus(String kode_barang) {
       PreparedStatement st = null;
    String sql = "UPDATE detail_pemesanan SET status = 'Barang Telah Datang' WHERE kode_barang='"+kode_barang+"'";
    
        try {
            st = conn.prepareStatement(sql);
            st.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal perbarui status barangn");
            Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public Model_BarangMasuk getByid(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Model_BarangMasuk> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM barang_masuk";
        
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            
            while(rs.next()) {
                Model_BarangMasuk masuk = new Model_BarangMasuk();
                Model_Distributor dst = new Model_Distributor();
                Model_Pengguna pgn = new Model_Pengguna();
                
                masuk.setNo_masuk(rs.getString("no_masuk"));
                masuk.setTgl_masuk(rs.getString("tgl_masuk"));
                masuk.setTotal_masuk(rs.getLong("total_masuk"));
                dst.setId_distributor(rs.getString("id_distributor"));
                pgn.setId_pengguna(rs.getString("id_pengguna"));
                
                masuk.setMod_distributor(dst);
                masuk.setMod_pengguna(pgn);
                
                list.add(masuk);
            } 
            
            return list;
        } catch (SQLException e) {
            Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, e);
            return null;
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    @Override
    public List<Model_BarangMasuk> pencarian(String id) {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM barang_masuk WHERE no_masuk LIKE '%" + id + "%'";
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()) {
                Model_BarangMasuk msk = new Model_BarangMasuk();
                Model_Distributor dst = new Model_Distributor();
                Model_Pengguna pgn = new Model_Pengguna();
                
                msk.setNo_masuk(rs.getString("no_masuk"));
                msk.setTgl_masuk(rs.getString("tgl_masuk"));
                msk.setTotal_masuk(rs.getLong("total_masuk"));
                dst.setId_distributor(rs.getString("id_distributor"));
                pgn.setId_pengguna(rs.getString("id_pengguna"));
                
                msk.setMod_distributor(dst);
                msk.setMod_pengguna(pgn);
                
                list.add(msk);
            }
            return list;
        } catch (SQLException e) {
            Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, e);
            return null;
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, e);
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
        
        String sql = "SELECT RIGHT(no_masuk, 3) AS Nomor " +
                     "FROM barang_masuk " +
                     "WHERE no_masuk LIKE 'BM" + no + "%' " +
                     "ORDER BY no_masuk DESC " +
                     "LIMIT 1";
        
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            
            if(rs.next()) {
                int nomor = Integer.parseInt(rs.getString("Nomor"));
                nomor++;
                urutan = "BM" + no + String.format("%03d", nomor);
            } else {
                urutan = "BM" + no + "001";
            }
        } catch (SQLException e) {
            Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, e);
            return null;
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        
        return urutan;
    }
    
}
