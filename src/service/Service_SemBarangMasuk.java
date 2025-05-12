/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import model.Model_SemBarangMasuk;

/**
 *
 * @author imama
 */
public interface Service_SemBarangMasuk {
    void tambahData (Model_SemBarangMasuk mod_masuk);
    void perbaruiData (Model_SemBarangMasuk mod_masuk);
    void hapusData (Model_SemBarangMasuk mod_masuk); 
    
    Model_SemBarangMasuk getByid (String id);
    
    List<Model_SemBarangMasuk> ambilData();
}
