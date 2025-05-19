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
import model.Model_DetPemesanan;
import model.Model_Barang;
import model.Model_SemPemesanan;
import service.Service_SemPemesanan;

/**
 *
 * @author imama
 */
public class DAO_SemPemesanan implements Service_SemPemesanan {
    private Connection conn;
    
    public DAO_SemPemesanan() {
        conn = koneksi.getConnection();
    }

    @Override
    public void tambahData(Model_SemPemesanan mod_pesan) {
        PreparedStatement st = null;
        String sql = "INSERT INTO sementara_pesan (kode_barang,nama_barang,harga,jml_pesan,subtotal_pesan,status) VALUES (?,?,?,?,?,?)";
        try {
            st = conn.prepareStatement(sql);
            
            st.setString(1, mod_pesan.getMod_barang().getKode_barang());
            st.setString(2, mod_pesan.getMod_barang().getNama_barang());
            st.setLong(3, mod_pesan.getMod_barang().getHarga());
            st.setLong(4, mod_pesan.getMod_detpesan().getJml_pesan());
            st.setLong(5, mod_pesan.getMod_detpesan().getSubtotal());
            st.setString(6, mod_pesan.getMod_detpesan().getStatus());
            
            st.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DAO_SemPemesanan.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    Logger.getLogger(DAO_SemPemesanan.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    @Override
    public void perbaruiData(Model_SemPemesanan mod_pesan) {
        PreparedStatement st = null;
        String sql = "UPDATE sementara_pesan SET nama_barang=?, harga=?, jml_pesan=?, subtotal_pesan=? WHERE kode_barang=?";
        
        try {
            st = conn.prepareStatement(sql);
            
            st.setString(1, mod_pesan.getMod_barang().getNama_barang());
            st.setLong(2, mod_pesan.getMod_barang().getHarga());
            st.setLong(3, mod_pesan.getMod_detpesan().getJml_pesan());
            st.setLong(4, mod_pesan.getMod_detpesan().getSubtotal());
            st.setString(5, mod_pesan.getMod_barang().getKode_barang());
            
            st.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Perbarui data gagal");
            Logger.getLogger(DAO_SemPemesanan.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    Logger.getLogger(DAO_SemPemesanan.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    @Override
    public void hapusData(Model_SemPemesanan mod_pesan) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Model_SemPemesanan getByid(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Model_SemPemesanan> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM sementara_pesan";
        
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            
            while(rs.next()) {
                Model_SemPemesanan smt = new Model_SemPemesanan();
                Model_Barang brg = new Model_Barang();
                Model_DetPemesanan det_psn = new Model_DetPemesanan();
                
                brg.setKode_barang(rs.getString("kode_barang"));
                brg.setNama_barang(rs.getString("nama_barang"));
                brg.setHarga(rs.getLong("harga"));
                
                det_psn.setJml_pesan(rs.getLong("jml_pesan"));
                det_psn.setSubtotal(rs.getLong("subtotal_pesan"));
                
                smt.setMod_barang(brg);
                smt.setMod_detpesan(det_psn);
                
                list.add(smt);
            } 
            
            return list;
        } catch (SQLException e) {
            Logger.getLogger(DAO_SemPemesanan.class.getName()).log(Level.SEVERE, null, e);
            return null;
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    Logger.getLogger(DAO_SemPemesanan.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    @Override
    public void sumTotal(Model_DetPemesanan mod_det) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
