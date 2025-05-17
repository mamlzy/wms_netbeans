/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.koneksi;
import model.Model_JenisBarang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.Service_JenisBarang;
import javax.swing.JOptionPane;

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
    public void tambahData(Model_JenisBarang mokat) {
        PreparedStatement st = null;
        String sql = "INSERT INTO jenis_barang(kode_jenis,nama_jenis) VALUES (?,?)";
        try {
            st = connection.prepareStatement(sql);
            
            st.setString(1, mokat.getKode_jenis());
            st.setString(2, mokat.getNama_jenis());
            
            st.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DAO_JenisBarang.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    Logger.getLogger(DAO_JenisBarang.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    @Override
    public void perbaruiData(Model_JenisBarang mokat) {
        PreparedStatement st = null;
        String sql = "UPDATE jenis_barang SET nama_jenis=? WHERE kode_jenis=?";
        
        try {
            st = connection.prepareStatement(sql);
            
            st.setString(1, mokat.getNama_jenis());
            st.setString(2, mokat.getKode_jenis());
            
                st.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Perbarui data gagal");
            Logger.getLogger(DAO_Barang.class.getName()).log(Level.SEVERE, null, e);
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
    public void hapusData(Model_JenisBarang mokat) {
        PreparedStatement st = null;
        String sql = "DELETE FROM jenis_barang WHERE kode_jenis=?";
        
        try {
            st = connection.prepareStatement(sql);
            
            st.setString(1, mokat.getKode_jenis());
            
            st.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DAO_Barang.class.getName()).log(Level.SEVERE, null, e);
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
    public Model_JenisBarang getByid(String id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        Model_JenisBarang mokat = null;
        String sql = "SELECT jenis_barang FROM kode_jenis=?";
        
        try {
            st = connection.prepareStatement(sql);
            Model_JenisBarang ang = new Model_JenisBarang();
            st.setString(1, ang.getKode_jenis());
            st.setString(2, ang.getNama_jenis());
            
            rs = st.executeQuery();
            while(rs.next()) {
                mokat = new Model_JenisBarang();
                mokat.setKode_jenis(rs.getString("kode_jenis"));
                mokat.setNama_jenis(rs.getString("nama_jenis"));
            }
            
            return mokat;
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
    public List<Model_JenisBarang> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM jenis_barang";
        
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
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM jenis_barang WHERE kode_jenis LIKE '%" + id + "%' OR nama_jenis LIKE '%" + id + "%'";
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()) {
                Model_JenisBarang mokat = new Model_JenisBarang();
                mokat.setKode_jenis(rs.getString("kode_jenis"));
                mokat.setNama_jenis(rs.getString("namaa_jenis"));
                
                list.add(mokat);
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
    public String nomor() {
        PreparedStatement st = null;
        ResultSet rs = null;
        String urutan = null;
        String sql = "SELECT RIGHT (kode_jenis,3)+1 AS Nomor FROM jenis_barang ORDER BY Nomor desc";
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            if(rs.next()) {
                urutan = rs.getString(1);
                while(urutan.length() < 3)
                    urutan = "0" + urutan;
                    urutan = "JB" + urutan;
            } else {
                urutan = "JB" + "001";
            }
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
        
        return urutan;
    }

    @Override
    public boolean validasiNamaJenisBarang(Model_JenisBarang mokat) {
        throw new UnsupportedOperationException("Not supported yet");
//        PreparedStatement st = null;
//        ResultSet rs = null;
//        boolean valid = false;
//        String sql = "SELECT nama_jenis FROM jenis_barang WHERE kode_jenis!='" + mokat.getKode_jenis() + "' AND nama_jenis LIKE BINARY '" + mokat.getNama_jenis() + "';";
//        

    }
    
}
