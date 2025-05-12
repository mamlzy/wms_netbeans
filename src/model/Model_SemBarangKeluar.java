/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author imama
 */
public class Model_SemBarangKeluar {
    private Model_Barang mod_barang;
    private Model_DetBarangKeluar mod_detkeluar;

    public Model_Barang getMod_barang() {
        return mod_barang;
    }

    public void setMod_barang(Model_Barang mod_barang) {
        this.mod_barang = mod_barang;
    }

    public Model_DetBarangKeluar getMod_detkeluar() {
        return mod_detkeluar;
    }

    public void setMod_detkeluar(Model_DetBarangKeluar mod_detkeluar) {
        this.mod_detkeluar = mod_detkeluar;
    }
    
}
