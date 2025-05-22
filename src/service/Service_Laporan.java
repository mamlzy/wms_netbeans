/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import javax.swing.JPanel;

/**
 *
 * @author imama
 */
public interface Service_Laporan {
    void suratJalalnPemesanan(String no);
    void transaksiBaranagMasuk(String no);
    void suratBarangKeluar(String no);
    
    void lapDataBarang(JPanel jp);
    void lapTransaksiBarang(JPanel jp);
}
