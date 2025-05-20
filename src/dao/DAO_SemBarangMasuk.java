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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Model_Barang;
import model.Model_DetBarangMasuk;
import model.Model_SemBarangMasuk;
import service.Service_SemBarangMasuk;

/**
 *
 * @author imama
 */
public class DAO_SemBarangMasuk implements Service_SemBarangMasuk {
    private Connection conn;
    
    public DAO_SemBarangMasuk() {
        conn = koneksi.getConnection();
    }

    @Override
    public void tambahData(Model_SemBarangMasuk mod_masuk) {
        PreparedStatement st = null;
        String sql = "INSERT INTO sementara_barang_masuk (kode_barang,nama_barang,harga,jml_pmasuk,subtotal_masuk) VALUES (?,?,?,?,?)";
        try {
            st = conn.prepareStatement(sql);
            
            st.setString(1, mod_masuk.getMod_barang().getKode_barang());
            st.setString(2, mod_masuk.getMod_barang().getNama_barang());
            st.setLong(3, mod_masuk.getMod_barang().getHarga());
            st.setLong(4, mod_masuk.getMod_masuk().getJml_masuk());
            st.setLong(5, mod_masuk.getMod_masuk().getSubtotal_masuk());
            
            st.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DAO_SemBarangMasuk.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    Logger.getLogger(DAO_SemBarangMasuk.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    @Override
    public void perbaruiData(Model_SemBarangMasuk mod_masuk) {
        PreparedStatement st = null;
        String sql = "UPDATE sementara_barang_masuk SET nama_barang=?, harga=?, jml_masuk=?, subtotal_masuk=? WHERE kode_barang=?";
        
        try {
            st = conn.prepareStatement(sql);
            
            st.setString(1, mod_masuk.getMod_barang().getNama_barang());
            st.setLong(2, mod_masuk.getMod_barang().getHarga());
            st.setLong(3, mod_masuk.getMod_masuk().getJml_masuk());
            st.setLong(4, mod_masuk.getMod_masuk().getSubtotal_masuk());
            st.setString(5, mod_masuk.getMod_barang().getKode_barang());
            
            st.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Perbarui data gagal");
            Logger.getLogger(DAO_SemBarangMasuk.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    Logger.getLogger(DAO_SemBarangMasuk.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    @Override
    public void hapusData(Model_SemBarangMasuk mod_masuk) {
        PreparedStatement st = null;
        String sql = "DELETE FROM sementara_barang_masuk WHERE kode_barang=? AND nama_barang=? AND harga=? AND jml_masuk=? AND subtotal_masuk=?";
        
        try {
            st = conn.prepareStatement(sql);
            
            st.setString(1, mod_masuk.getMod_barang().getKode_barang());
            st.setString(2, mod_masuk.getMod_barang().getNama_barang());
            st.setLong(3, mod_masuk.getMod_barang().getHarga());
            st.setLong(4, mod_masuk.getMod_masuk().getSubtotal_masuk());
            st.setString(5, mod_masuk.getMod_barang().getKode_barang());
            
            st.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DAO_SemBarangMasuk.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    Logger.getLogger(DAO_SemBarangMasuk.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    @Override
    public Model_SemBarangMasuk getByid(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Model_SemBarangMasuk> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM sementara_pesan";
        
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            
            while(rs.next()) {
                Model_SemBarangMasuk smt = new Model_SemBarangMasuk();
                Model_Barang brg = new Model_Barang();
                Model_DetBarangMasuk det_masuk = new Model_DetBarangMasuk();
                
                brg.setKode_barang(rs.getString("kode_barang"));
                brg.setNama_barang(rs.getString("nama_barang"));
                brg.setHarga(rs.getLong("harga"));
                
                det_masuk.setJml_masuk(rs.getInt("jml_masuk"));
                det_masuk.setSubtotal_masuk(rs.getLong("subtotal_masuk"));
                
                smt.setMod_barang(brg);
                smt.setMod_masuk(det_masuk);
                
                list.add(smt);
            } 
            
            return list;
        } catch (SQLException e) {
            Logger.getLogger(DAO_SemBarangMasuk.class.getName()).log(Level.SEVERE, null, e);
            return null;
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    Logger.getLogger(DAO_SemBarangMasuk.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
    
}
