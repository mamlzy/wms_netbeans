/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import model.Model_SemBarangKeluar;

/**
 *
 * @author imama
 */
public interface Service_SemBarangKeluar {
    void tambahData (Model_SemBarangKeluar mod_keluar);
    void perbaruiData (Model_SemBarangKeluar mod_keluar);
    void hapusData (Model_SemBarangKeluar mod_keluar); 
    
    Model_SemBarangKeluar getByid (String id);
    
    List<Model_SemBarangKeluar> ambilData();
}
