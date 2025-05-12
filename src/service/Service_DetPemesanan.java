/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import model.Model_DetPemesanan;

/**
 *
 * @author imama
 */
public interface Service_DetPemesanan {
    void tambahData (Model_DetPemesanan mod_detpesan);
    void sumTotal (Model_DetPemesanan mod_detpesan);
    void hapusSementara (Model_DetPemesanan mod_detpesan);
    
    Model_DetPemesanan getByid(String id);
    List<Model_DetPemesanan> ambilData(String id);
    List<Model_DetPemesanan> pencarian(String id);
}
