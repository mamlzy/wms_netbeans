/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.koneksi;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import service.Service_Laporan;

/**
 *
 * @author imama
 */
public class DAO_Laporan implements Service_Laporan {
    private Connection conn;
    
    public DAO_Laporan() {
        conn = koneksi.getConnection();
    }

    @Override
    public void suratJalanPemesanan(String no) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void transaksiBaranagMasuk(String no) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void suratBarangKeluar(String no) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void lapDataBarang(JPanel jp) {
        try {
            String reportPath = "src/report/Report_DataBarang.jasper";
            Connection conn = koneksi.getConnection();
            
            HashMap<String, Object> parameters = new HashMap<>();
            JasperPrint print = JasperFillManager.fillReport(reportPath, parameters, conn);
            //JasperViewer viewer = new JasperViewer(print, false);
            //viewer.setVisible(true);
            jp.setLayout(new BorderLayout());
            jp.repaint();
            jp.add(new JRViewer(print));
            jp.revalidate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error displaying report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void lapTransaksiBarang(JPanel jp) {
        try {
            String reportPath = "src/report/Report_StokBarang.jasper";
            Connection conn = koneksi.getConnection();
            
            HashMap<String, Object> parameters = new HashMap<>();
            JasperPrint print = JasperFillManager.fillReport(reportPath, parameters, conn);
            //JasperViewer viewer = new JasperViewer(print, false);
            //viewer.setVisible(true);
            jp.setLayout(new BorderLayout());
            jp.repaint();
            jp.add(new JRViewer(print));
            jp.revalidate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error displaying report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void lapPemesananPerTransaksi(JPanel jp, String no) {
        try {
            String reportPath = "src/report/Report_PesanPerTransaksi.jasper";
            Connection conn = koneksi.getConnection();
            
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("no", no);
            
            JasperPrint print = JasperFillManager.fillReport(reportPath, parameters, conn);
            //JasperViewer viewer = new JasperViewer(print, false);
            //viewer.setVisible(true);
            jp.setLayout(new BorderLayout());
            jp.repaint();
            jp.add(new JRViewer(print));
            jp.revalidate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error displaying report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void lapPemesananPerPeriode(JPanel jp, String tgl1, String tgl2) {
        try {
            String reportPath = "src/report/Report_PesanPerPeriode.jasper";
            Connection conn = koneksi.getConnection();
            
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("tgl1", tgl1);
            parameters.put("tgl2", tgl2);
            
            JasperPrint print = JasperFillManager.fillReport(reportPath, parameters, conn);
            //JasperViewer viewer = new JasperViewer(print, false);
            //viewer.setVisible(true);
            jp.setLayout(new BorderLayout());
            jp.repaint();
            jp.add(new JRViewer(print));
            jp.revalidate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error displaying report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void lapPemesananPerBulan(JPanel jp, String dt1, int dt2) {
        try {
            String reportPath = "src/report/Report_PesanPerBulan.jasper";
            Connection conn = koneksi.getConnection();
            
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("dt1", dt1);
            parameters.put("dt2", dt2);
            
            JasperPrint print = JasperFillManager.fillReport(reportPath, parameters, conn);
            //JasperViewer viewer = new JasperViewer(print, false);
            //viewer.setVisible(true);
            jp.setLayout(new BorderLayout());
            jp.repaint();
            jp.add(new JRViewer(print));
            jp.revalidate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error displaying report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void lapPemesananPerDistributor(JPanel jp, String id) {
        try {
            String reportPath = "src/report/Report_PesanPerDistributor.jasper";
            Connection conn = koneksi.getConnection();
            
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("id", id);
            
            JasperPrint print = JasperFillManager.fillReport(reportPath, parameters, conn);
            //JasperViewer viewer = new JasperViewer(print, false);
            //viewer.setVisible(true);
            jp.setLayout(new BorderLayout());
            jp.repaint();
            jp.add(new JRViewer(print));
            jp.revalidate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error displaying report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
