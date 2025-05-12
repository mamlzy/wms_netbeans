/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import model.Model_BarangKeluar;

/**
 *
 * @author imama
 */
public class TableMod_BarangKeluar extends AbstractTableModel {
    private List<Model_BarangKeluar> list = new ArrayList<>();
    
    public void tambahData(Model_BarangKeluar mod_keluar) {
        list.add(mod_keluar);
        fireTableRowsInserted(list.size() - 1, list.size() - 1);
        JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
    }
    
    public void perbaruiData(int row, Model_BarangKeluar mod_keluar) {
        list.add(row, mod_keluar);
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
    
    public void setData(List<Model_BarangKeluar> list) {
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void setData(int index, Model_BarangKeluar mod_keluar) {
        list.set(index, mod_keluar);
        fireTableRowsUpdated(index, index);
    }
    
    public Model_BarangKeluar getData(int index) {
        return list.get(index);
    }
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: return list.get(rowIndex).getNo_keluar();
            case 1: return list.get(rowIndex).getTgl_keluar()   ;
            case 2: return list.get(rowIndex).getTotal_keluar();
            case 3: return list.get(rowIndex).getMod_pengguna().getId_pengguna();
            
            default: return null;
        }
    }
    
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "No Keluar";
            case 1: return "Tanggal Keluar";
            case 2: return "Total Keluar";
            case 3: return "ID Pengguna";
            default: return null;
        }
    }
    
}
