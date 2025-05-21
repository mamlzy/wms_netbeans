/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import model.Model_BarangKeluar;

/**
 *
 * @author imama
 */
public interface Service_BarangKeluar {
    void tambahData(Model_BarangKeluar mod_keluar); 
    void hapusData (Model_BarangKeluar mod_keluar);
    
    Model_BarangKeluar getByid (String id);
    List<Model_BarangKeluar> getData();     
    List<Model_BarangKeluar> pencarian(String id);     
    String nomor();
}
