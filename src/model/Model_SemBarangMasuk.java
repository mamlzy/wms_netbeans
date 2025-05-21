/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author imama
 */
public class Model_SemBarangMasuk {
    private Model_Barang mod_barang;
    private Model_DetBarangMasuk mod_detmasuk;

    public Model_Barang getMod_barang() {
        return mod_barang;
    }

    public void setMod_barang(Model_Barang mod_barang) {
        this.mod_barang = mod_barang;
    }

    public Model_DetBarangMasuk getMod_detmasuk() {
        return mod_detmasuk;
    }

    public void setMod_detmasuk(Model_DetBarangMasuk mod_detmasuk) {
        this.mod_detmasuk = mod_detmasuk;
    }
}
