/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import model.Model_SemPemesanan;

/**
 *
 * @author imama
 */
public class TableMod_SemPemesanan extends AbstractTableModel {
    private List<Model_SemPemesanan> list = new ArrayList<>();
    
    public void tambahData(Model_SemPemesanan mod_pesan) {
        list.add(mod_pesan);
        fireTableRowsInserted(list.size() - 1, list.size() - 1);
        JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
    }
    
    public void perbaruiData(int row, Model_SemPemesanan mod_pesan) {
        list.add(row, mod_pesan);
        fireTableDataChanged();
        JOptionPane.showMessageDialog(null, "Data berhasil diperbarui");
    }
    
    public void hapusData(int index) {
        list.remove(index);
        fireTableRowsDeleted(index, index); 
        JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
    }
    
    public void clear() {
        list.clear();
        fireTableDataChanged();
    }
    
    public void setData(List<Model_SemPemesanan> list) {
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void setData(int index, Model_SemPemesanan mod_pesan) {
        list.set(index, mod_pesan);
        fireTableRowsUpdated(index, index);
    }
    
    public Model_SemPemesanan getData(int index) {
        return list.get(index);
    }
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: return list.get(rowIndex).getMod_barang().getKode_barang();
            case 1: return list.get(rowIndex).getMod_barang().getNama_barang();
            case 2: return list.get(rowIndex).getMod_barang().getHarga();
            case 3: return list.get(rowIndex).getMod_detpesan().getJml_pesan();
            case 4: return list.get(rowIndex).getMod_detpesan().getSubtotal();
            
            default: return null;
        }
    }
    
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "Kode Barang";
            case 1: return "Nama Barang";
            case 2: return "Harga";
            case 3: return "Jumlah";
            case 4: return "Subtotal";
            default: return null;
        }
    }
}
