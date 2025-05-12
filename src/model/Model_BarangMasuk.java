/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author imama
 */
public class Model_BarangMasuk {

    public String getNo_masuk() {
        return no_masuk;
    }

    public void setNo_masuk(String no_masuk) {
        this.no_masuk = no_masuk;
    }

    public String getTgl_masuk() {
        return tgl_masuk;
    }

    public void setTgl_masuk(String tgl_masuk) {
        this.tgl_masuk = tgl_masuk;
    }

    public Long getTotal_masuk() {
        return total_masuk;
    }

    public void setTotal_masuk(Long total_masuk) {
        this.total_masuk = total_masuk;
    }

    public Model_Distributor getMod_distributor() {
        return mod_distributor;
    }

    public void setMod_distributor(Model_Distributor mod_distributor) {
        this.mod_distributor = mod_distributor;
    }

    public Model_Pengguna getMod_pengguna() {
        return mod_pengguna;
    }

    public void setMod_pengguna(Model_Pengguna mod_pengguna) {
        this.mod_pengguna = mod_pengguna;
    }
    private String no_masuk;
    private String tgl_masuk;
    private Long total_masuk;
    private Model_Distributor mod_distributor;
    private Model_Pengguna mod_pengguna;

    
    
}
