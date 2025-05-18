/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import model.Model_SemPemesanan;

/**
 *
 * @author imama
 */
public interface Service_SemPemesanan {
    void tambahData (Model_SemPemesanan mod_pesan);
    void perbaruiData (Model_SemPemesanan mod_pesan);
    void hapusData (Model_SemPemesanan mod_pesan); 
    
    Model_SemPemesanan getByid (String id);
    List<Model_SemPemesanan> getData();
}
