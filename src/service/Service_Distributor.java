/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import model.Model_Distributor;

/**
 *
 * @author imama
 */
public interface Service_Distributor {
    void tambahData (Model_Distributor mod_dis);
    void perbaruiData (Model_Distributor mod_dis);
    void hapusData (Model_Distributor mod_dis); 
    
    Model_Distributor getByid (String id);
    
    List<Model_Distributor> ambilData();
    List<Model_Distributor> pencarian(String id);
    
    String nomor();
}
