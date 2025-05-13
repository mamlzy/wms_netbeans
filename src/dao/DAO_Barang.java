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
import java.util.logging. Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Model_Barang;
import model.Model_JenisBarang;
import service.Service_Barang;

public class DAO_Barang implements Service_Barang {
    private Connection conn;
    
    public DAO_Barang() {
        conn = koneksi.getConnection();
    }

    @Override
    public void tambahData(Model_Barang mod_bar) {
        PreparedStatement st = null;
        String sql = "INSERT INTO barang (kode_barnag, kode_jenis, nama_baranng, satuan, harga, stok) VALUES (?,?,?,?,?,?)";
        try {
            
        } catch (Exception e) {
        }
    }

    @Override
    public void perbaruiData(Model_Barang mod_bar) {
        
    }

    @Override
    public void hapusData(Model_Barang mod_bar) {
        
    }

    @Override
    public Model_Barang getByid(String id) {
        
    }

    @Override
    public List<Model_Barang> ambilData() {
        
    }

    @Override
    public List<Model_Barang> ambilData2() {
        
    }

    @Override
    public List<Model_Barang> pencarian(String id) {
        
    }

    @Override
    public List<Model_Barang> pencarian2(String id) {
        
    }

    @Override
    public String nomor() {
        
    }

    @Override
    public String nomor2() {
        
    }
    
    
}
