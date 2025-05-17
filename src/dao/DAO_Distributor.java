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
import model.Model_Distributor;
import service.Service_Distributor;

public class DAO_Distributor implements Service_Distributor {
    private Connection connection;
    
    public DAO_Distributor() {
        connection = koneksi.getConnection();
    }

    @Override
    public void tambahData(Model_Distributor model) {
        PreparedStatement st = null;
        String sql = "INSERT INTO distributor(id_distributor,nama_distributor,telp_distributor,alamat_distributor) VALUES (?,?,?,?)";
        try {
            st = connection.prepareStatement(sql);
            
            st.setString(1, model.getId_distributor());
            st.setString(2, model.getNama_distributor());
            st.setString(3, model.getTelp_distributor());
            st.setString(4, model.getAlamat_distributor());
            
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
    public void perbaruiData(Model_Distributor model) {
        PreparedStatement st = null;
        String sql = "UPDATE distributor SET id_distributor=?, nama_distributor=?, telp_distributor=?, alamat_distributor=? WHERE id_distributor=?";
        
        try {
            st = connection.prepareStatement(sql);
            
            st.setString(1, model.getId_distributor());
            st.setString(2, model.getNama_distributor());
            st.setString(3, model.getTelp_distributor());
            st.setString(4, model.getAlamat_distributor());
            st.setString(5, model.getId_distributor());
            
            st.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Perbarui data gagal");
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
    public void hapusData(Model_Distributor model) {
        PreparedStatement st = null;
        String sql = "DELETE FROM distributor WHERE id_distributor=?";
        
        try {
            st = connection.prepareStatement(sql);
            
            st.setString(1, model.getId_distributor());
            
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
    public Model_Distributor getByid(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Model_Distributor> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM distributor";
        
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            
            while(rs.next()) {
                Model_Distributor model = new Model_Distributor();
                
                model.setId_distributor(rs.getString("id_distributor"));
                model.setNama_distributor(rs.getString("nama_distributor"));
                model.setTelp_distributor(rs.getString("telp_distributor"));
                model.setAlamat_distributor(rs.getString("alamat_distributor"));
                
                list.add(model);
            } 
            
            return list;
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
    }

    @Override
    public List<Model_Distributor> pencarian(String id) {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM distributor WHERE id_distributor LIKE '%" + id + "%' OR nama_distributor LIKE '%" + id + "%'";
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()) {
                Model_Distributor model = new Model_Distributor();
                model.setId_distributor(rs.getString("id_distributor"));
                model.setNama_distributor(rs.getString("nama_distributor"));
                model.setAlamat_distributor(rs.getString("telp_distributor"));
                model.setTelp_distributor(rs.getString("alamat_distributor"));
                
                list.add(model);
            }
            return list;
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
        
        String sql = "SELECT RIGHT(id_distributor, 3) AS Nomor " +
                     "FROM distributor " +
                     "WHERE id_distributor LIKE 'DST" + no + "%' " +
                     "ORDER BY id_distributor DESC " +
                     "LIMIT 1";
        
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            
            if(rs.next()) {
                int nomor = Integer.parseInt(rs.getString("Nomor"));
                nomor++;
                urutan = "DST" + no + String.format("%03d", nomor);
            } else {
                urutan = "DST" + no + "001";
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
    
}
