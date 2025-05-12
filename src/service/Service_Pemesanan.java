/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import model.Model_Pemesanan;

/**
 *
 * @author imama
 */
public interface Service_Pemesanan {
    void tambahData (Model_Pemesanan mod_bar);
    
    Model_Pemesanan getByid (String id);
    
    List<Model_Pemesanan> ambilData();
    List<Model_Pemesanan> pencarian(String id);
    
    String nomor();
}
