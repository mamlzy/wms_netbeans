/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import model.Model_Barang;

/**
 *
 * @author imama
 */
public class TabelMod_Barang extends AbstractTableModel {
    private List<Model_Barang> list = new ArrayList<>();
    
    public void tambahData(Model_Barang mod_bar) {
        list.add(mod_bar);
        fireTableRowsInserted(list.size() - 1, list.size() - 1);
        JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
    }
    
    public void perbaruiData(int row, Model_Barang mod_bar) {
        list.add(row, mod_bar);
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
    
    public void setData(List<Model_Barang> list) {
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void setData(int index, Model_Barang mod_bar) {
        list.set(index, mod_bar);
        fireTableRowsUpdated(index, index);
    }
    
    public Model_Barang getData(int index) {
        return list.get(index);
    }
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: return list.get(rowIndex).getKode_barang();
            case 1: return list.get(rowIndex).getJns_barang().getKode_jenis();
            case 2: return list.get(rowIndex).getJns_barang().getNama_jenis();
            case 3: return list.get(rowIndex).getNama_barang();
            case 4: return list.get(rowIndex).getSatuan();
            case 5: return list.get(rowIndex).getHarga();
            case 6: return list.get(rowIndex).getStok();
            
            default: return null;
        }
    }
    
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "Kode Barang";
            case 1: return "Kode Jenis";
            case 2: return "Nama Jenis";
            case 3: return "Nama Barang";
            case 4: return "Satuan";
            case 5: return "Harga";
            case 6: return "Stok";
            default: return null;
        }
    }
    
}
