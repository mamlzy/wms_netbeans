/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import model.Model_JenisBarang;

/**
 *
 * @author imama
 */
public interface Service_JenisBarang {
    void tambahData (Model_JenisBarang mod_jenbar);
    void perbaruiData (Model_JenisBarang mod_jenbar);
    void hapusData (Model_JenisBarang mod_jenbar); 
    
    Model_JenisBarang getByid (String id);
    
    List<Model_JenisBarang> ambilData();
    List<Model_JenisBarang> pencarian(String id);
    
    String nomor();
    
    boolean validasiNamaJenisBarang(Model_JenisBarang mod_jenbar);
}
