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
import model.Model_Barang;
import model.Model_BarangMasuk;
import model.Model_DetBarangMasuk;
import service.Service_DetBarangMasuk;

/**
 *
 * @author imama
 */
public class DAO_DetBarangMasuk implements Service_DetBarangMasuk {
    private Connection conn;
    
    public DAO_DetBarangMasuk() {
        conn = koneksi.getConnection();
    }

    @Override
    public void tambahData(Model_DetBarangMasuk mod_detmasuk) {
        PreparedStatement st = null;
        String sql = "INSERT INTO detail_barang_masuk (no_masuk, kode_barang, jml_masuk, subtotal_masuk) SELECT '" + mod_detmasuk.getMod_masuk().getNo_masuk()+ "', kode_barang, jml_masuk, subtotal_masuk FROM sementara_barang_masuk";
        try {
            st = conn.prepareStatement(sql);
            st.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DAO_DetBarangMasuk.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    Logger.getLogger(DAO_DetBarangMasuk.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    @Override
    public void sumTotal(Model_DetBarangMasuk mod_detmasuk) {
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT SUM(subtotal_masuk) FROM sementara_barang_masuk";
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            if (rs.next()) {
                mod_detmasuk.setSubtotal_masuk(rs.getLong(1));
            }
        } catch (SQLException e) {
            Logger.getLogger(DAO_DetBarangMasuk.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    Logger.getLogger(DAO_DetBarangMasuk.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    @Override
    public void hapusSementara(Model_DetBarangMasuk mod_detmasuk) {
        PreparedStatement st = null;
        String sql = "DELETE FROM sementara_barang_masuk";
        
        try {
            st = conn.prepareStatement(sql);
            st.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DAO_DetBarangMasuk.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    Logger.getLogger(DAO_DetBarangMasuk.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    @Override
    public Model_DetBarangMasuk getByid(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Model_DetBarangMasuk> getData(String id) {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT det_masuk.no_masuk, det_masuk.kode_barang, brg.nama_barang,"
                + "brg.harga, det_masuk.jml_masuk, det_masuk.subtotal_masuk "
                + "FROM detail_barang_masuk det_masuk "
                + "INNER JOIN barang_masuk masuk ON masuk.no_masuk=det_masuk.no_masuk "
                + "INNER JOIN barang brg ON brg.kode_barang = det_masuk.kode_barang "
                + "WHERE det_masuk.no_masuk='" + id + "' ORDER BY no_masuk ASC";
        
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            
            while(rs.next()) {
                Model_BarangMasuk masuk = new Model_BarangMasuk();
                Model_DetBarangMasuk det_masuk = new Model_DetBarangMasuk();
                Model_Barang brg = new Model_Barang();
                
                masuk.setNo_masuk(String.valueOf(rs.getString("det_masuk.no_masuk")));
                det_masuk.setMod_masuk(masuk);
                
                brg.setKode_barang(rs.getString("kode_barang"));
                brg.setNama_barang(rs.getString("nama_barang"));
                brg.setHarga(rs.getLong("harga"));
                det_masuk.setJml_masuk(rs.getInt("jml_masuk"));
                det_masuk.setSubtotal_masuk(rs.getLong("subtotal_masuk"));
                
                det_masuk.setMod_barang(brg);
                
                list.add(det_masuk);
            } 
            
            return list;
        } catch (SQLException e) {
            Logger.getLogger(DAO_DetBarangMasuk.class.getName()).log(Level.SEVERE, null, e);
            return null;
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    Logger.getLogger(DAO_DetBarangMasuk.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
    
}
