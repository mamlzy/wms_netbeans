/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import model.Model_JenisBarang;

/**
 *
 * @author imama
 */
public class TableMod_JenisBarang extends AbstractTableModel {
    private List<Model_JenisBarang> list = new ArrayList<>();
    
    public void tambahData(Model_JenisBarang mod_jenbar) {
        list.add(mod_jenbar);
        fireTableRowsInserted(list.size() - 1, list.size() - 1);
        JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
    }
    
    public void perbaruiData(int row, Model_JenisBarang mod_jenbar) {
        list.add(row, mod_jenbar);
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
    
    public void setData(List<Model_JenisBarang> list) {
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void setData(int index, Model_JenisBarang mod_jenbar) {
        list.set(index, mod_jenbar);
        fireTableRowsUpdated(index, index);
    }
    
    public Model_JenisBarang getData(int index) {
        return list.get(index);
    }
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: return list.get(rowIndex).getKode_jenis();
            case 1: return list.get(rowIndex).getNama_jenis();
            
            default: return null;
        }
    }
    
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "Kode Jenis Barang";
            case 1: return "Nama Jenis Barang";
            default: return null;
        }
    }
}
