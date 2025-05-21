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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Model_Barang;
import model.Model_DetBarangKeluar;
import model.Model_SemBarangKeluar;
import service.Service_SemBarangKeluar;

/**
 *
 * @author imama
 */
public class DAO_SemBarangKeluar implements Service_SemBarangKeluar {
     private Connection conn;
    
    public DAO_SemBarangKeluar() {
        conn = koneksi.getConnection();
    }

    @Override
    public void tambahData(Model_SemBarangKeluar mod_keluar) {
        PreparedStatement st = null;
        String sql = "INSERT INTO sementara_keluar (kode_barang,nama_barang,harga,jml_keluar,subtotal_keluar) VALUES (?,?,?,?,?)";
        try {
            st = conn.prepareStatement(sql);
            
            st.setString(1, mod_keluar.getMod_barang().getKode_barang());
            st.setString(2, mod_keluar.getMod_barang().getNama_barang());
            st.setLong(3, mod_keluar.getMod_barang().getHarga());
            st.setLong(4, mod_keluar.getMod_detkeluar().getJml_keluar());
            st.setLong(5, mod_keluar.getMod_detkeluar().getSubtotal_keluar());
            
            st.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DAO_SemBarangKeluar.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    Logger.getLogger(DAO_SemBarangKeluar.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    @Override
    public void perbaruiData(Model_SemBarangKeluar mod_keluar) {
        PreparedStatement st = null;
        String sql = "UPDATE sementara_keluar SET nama_barang=?, harga=?, jml_keluar=?, subtotal_keluar=? WHERE kode_barang=?";
        
        try {
            st = conn.prepareStatement(sql);
            
            st.setString(1, mod_keluar.getMod_barang().getNama_barang());
            st.setLong(2, mod_keluar.getMod_barang().getHarga());
            st.setLong(3, mod_keluar.getMod_detkeluar().getJml_keluar());
            st.setLong(4, mod_keluar.getMod_detkeluar().getSubtotal_keluar());
            st.setString(5, mod_keluar.getMod_barang().getKode_barang());
            
            st.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Perbarui data gagal");
            Logger.getLogger(DAO_SemBarangKeluar.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    Logger.getLogger(DAO_SemBarangKeluar.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    @Override
    public void hapusData(Model_SemBarangKeluar mod_keluar) {
        PreparedStatement st = null;
        String sql = "DELETE FROM sementara_keluar WHERE kode_barang=? AND nama_barang=? AND harga=? AND jml_keluar=? AND subtotal_keluar=?";
        
        try {
            st = conn.prepareStatement(sql);
            
            st.setString(1, mod_keluar.getMod_barang().getKode_barang());
            st.setString(2, mod_keluar.getMod_barang().getNama_barang());
            st.setLong(3, mod_keluar.getMod_barang().getHarga());
            st.setLong(4, mod_keluar.getMod_detkeluar().getJml_keluar());
            st.setLong(5, mod_keluar.getMod_detkeluar().getSubtotal_keluar());
            
            st.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DAO_SemBarangKeluar.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    Logger.getLogger(DAO_SemBarangKeluar.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    @Override
    public Model_SemBarangKeluar getByid(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Model_SemBarangKeluar> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM sementara_keluar";
        
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            
            while(rs.next()) {
                Model_SemBarangKeluar smt = new Model_SemBarangKeluar();
                Model_Barang brg = new Model_Barang();
                Model_DetBarangKeluar det = new Model_DetBarangKeluar();
                
                brg.setKode_barang(rs.getString("kode_barang"));
                brg.setNama_barang(rs.getString("nama_barang"));
                brg.setHarga(rs.getLong("harga"));
                
                det.setJml_keluar(rs.getInt("jml_keluar"));
                det.setSubtotal_keluar(rs.getLong("subtotal_keluar"));
                
                smt.setMod_barang(brg);
                smt.setMod_detkeluar(det);
                
                list.add(smt);
            } 
            
            return list;
        } catch (SQLException e) {
            Logger.getLogger(DAO_SemBarangKeluar.class.getName()).log(Level.SEVERE, null, e);
            return null;
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    Logger.getLogger(DAO_SemBarangKeluar.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
    
}
