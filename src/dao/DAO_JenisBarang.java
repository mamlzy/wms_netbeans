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
import model.Model_JenisBarang;
import service.Service_JenisBarang;

/**
 *
 * @author imama
 */
public class DAO_JenisBarang implements Service_JenisBarang {
    private Connection connection;
    
    public DAO_JenisBarang() {
        connection = koneksi.getConnection();
    }

    @Override
    public void tambahData(Model_JenisBarang mod_jenbar) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void perbaruiData(Model_JenisBarang mod_jenbar) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void hapusData(Model_JenisBarang mod_jenbar) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Model_JenisBarang getByid(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Model_JenisBarang> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT kode_jenis, nama_jenis FROM jenis_barang";
        
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            
            while(rs.next()) {
                Model_JenisBarang mo_jenis = new Model_JenisBarang();
                
                mo_jenis.setKode_jenis(rs.getString("kode_jenis"));
                mo_jenis.setNama_jenis(rs.getString("nama_jenis"));
                
                list.add(mo_jenis);
            } 
            
            return list;
        } catch (SQLException e) {
            Logger.getLogger(DAO_Barang.class.getName()).log(Level.SEVERE, null, e);
            return null;
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    Logger.getLogger(DAO_Barang.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    @Override
    public List<Model_JenisBarang> pencarian(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String nomor() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean validasiNamaJenisBarang(Model_JenisBarang mod_jenbar) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
