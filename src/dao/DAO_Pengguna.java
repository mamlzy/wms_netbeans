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
import javax.swing.JOptionPane;
import model.Model_Pengguna;
import service.Service_Pengguna;

/**
 *
 * @author imama
 */
public class DAO_Pengguna implements Service_Pengguna {
    private Connection connection;
    
    public DAO_Pengguna() {
        connection = koneksi.getConnection();
    }

    @Override
    public void tambahData(Model_Pengguna model) {
        PreparedStatement st = null;
        String sql = "INSERT INTO pengguna(id_pengguna,nama_pengguna,username,password,telp_pengguna,alamat_pengguna,level) VALUES (?,?,?,?,?,?,?)";
        try {
            st = connection.prepareStatement(sql);
            
            st.setString(1, model.getId_pengguna());
            st.setString(2, model.getNama_pengguna());
            st.setString(3, model.getUsername());
            st.setString(4, Encrypt.getmd5java(model.getPassword()));
            st.setString(5, model.getTelp_pengguna());
            st.setString(6, model.getAlamat_pengguna());
            st.setString(7, model.getLevel());
            
            st.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DAO_Pengguna.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    Logger.getLogger(DAO_Pengguna.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    @Override
    public void perbaruiData(Model_Pengguna model) {
        PreparedStatement st = null;
        String sql = "UPDATE pengguna SET nama_pengguna=?, username=?, telp_pengguna=?, alamat_pengguna=?, level=? WHERE id_pengguna=?";
        
        try {
            st = connection.prepareStatement(sql);

            st.setString(1, model.getNama_pengguna());
            st.setString(2, model.getUsername());
//            st.setString(4, Encrypt.getmd5java(model.getPassword()));
            st.setString(3, model.getTelp_pengguna());
            st.setString(4, model.getAlamat_pengguna());
            st.setString(5, model.getLevel());
            st.setString(6, model.getId_pengguna());
            
            st.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Perbarui data gagal");
            Logger.getLogger(DAO_Pengguna.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    Logger.getLogger(DAO_Pengguna.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    @Override
    public void hapusData(Model_Pengguna model) {
        PreparedStatement st = null;
        String sql = "DELETE FROM pengguna WHERE id_pengguna=?";
        
        try {
            st = connection.prepareStatement(sql);
            
            st.setString(1, model.getId_pengguna());
            
            st.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DAO_Pengguna.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    Logger.getLogger(DAO_Pengguna.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    @Override
    public Model_Pengguna getByid(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Model_Pengguna> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM pengguna";
        
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            
            while(rs.next()) {
                Model_Pengguna model = new Model_Pengguna();
                
                model.setId_pengguna(rs.getString("id_pengguna"));
                model.setNama_pengguna(rs.getString("nama_pengguna"));
                model.setUsername(rs.getString("username"));
                model.setTelp_pengguna(rs.getString("telp_pengguna"));
                model.setAlamat_pengguna(rs.getString("alamat_pengguna"));
                model.setLevel(rs.getString("level"));
                
                list.add(model);
            } 
            
            return list;
        } catch (SQLException e) {
            Logger.getLogger(DAO_Pengguna.class.getName()).log(Level.SEVERE, null, e);
            return null;
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    Logger.getLogger(DAO_Pengguna.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    @Override
    public List<Model_Pengguna> pencarian(String id) {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM pengguna WHERE id_pengguna LIKE '%" + id + "%' OR nama_pengguna LIKE '%" + id + "%'";
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()) {
                Model_Pengguna model = new Model_Pengguna();
                
                model.setId_pengguna(rs.getString("id_pengguna"));
                model.setNama_pengguna(rs.getString("nama_pengguna"));
                model.setUsername(rs.getString("username"));
                model.setTelp_pengguna(rs.getString("telp_pengguna"));
                model.setAlamat_pengguna(rs.getString("alamat_pengguna"));
                model.setLevel(rs.getString("level"));
                
                list.add(model);
            }
            return list;
        } catch (SQLException e) {
            Logger.getLogger(DAO_Pengguna.class.getName()).log(Level.SEVERE, null, e);
            return null;
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    Logger.getLogger(DAO_Pengguna.class.getName()).log(Level.SEVERE, null, e);
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
        SimpleDateFormat noFormat = new SimpleDateFormat("yyMM");
        String tgl = tanggal.format(now);
        String no = noFormat.format(now);
        
        String sql = "SELECT RIGHT(id_pengguna, 3) AS Nomor " +
                     "FROM pengguna " +
                     "WHERE id_pengguna LIKE 'USR" + no + "%' " +
                     "ORDER BY id_pengguna DESC " +
                     "LIMIT 1";
        
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            
            if(rs.next()) {
                int nomor = Integer.parseInt(rs.getString("Nomor"));
                nomor++;
                urutan = "USR" + no + String.format("%03d", nomor);
            } else {
                urutan = "USR" + no + "001";
            }
        } catch (SQLException e) {
            Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE, null, e);
            return null;
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        
        return urutan;
    }

    @Override
    public boolean validateOldPassword(String username, String oldPassword) {
        String encryptedOldPassword = Encrypt.getmd5java(oldPassword);
        try {
            String sql = "SELECT * FROM pengguna WHERE usernamee=? AND password=?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, encryptedOldPassword);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        String encryptedOldPassword = Encrypt.getmd5java(oldPassword);
        String encryptedNewPassword = Encrypt.getmd5java(newPassword);
        
        try {
            String sql = "SELECT * FROM pengguna WHERE usernamee=? AND password=?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, encryptedOldPassword);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String sqlUpdate = "UPDATE pengguna SET password=? WHERE username=?";
                PreparedStatement stUpdate = connection.prepareStatement(sql);
                stUpdate.setString(1, encryptedNewPassword);
                stUpdate.setString(2, username);
                int result = stUpdate.executeUpdate();
                return result > 0;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
