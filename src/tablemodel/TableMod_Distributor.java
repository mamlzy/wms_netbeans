/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import model.Model_Distributor;

/**
 *
 * @author imama
 */
public class TableMod_Distributor extends AbstractTableModel {
    private List<Model_Distributor> list = new ArrayList<>();
    
    public void tambahData(Model_Distributor mod_dis) {
        list.add(mod_dis);
        fireTableRowsInserted(list.size() - 1, list.size() - 1);
        JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
    }
    
    public void perbaruiData(int row, Model_Distributor mod_dis) {
        list.add(row, mod_dis);
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
    
    public void setData(List<Model_Distributor> list) {
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void setData(int index, Model_Distributor mod_dis) {
        list.set(index, mod_dis);
        fireTableRowsUpdated(index, index);
    }
    
    public Model_Distributor getData(int index) {
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
            case 0: return list.get(rowIndex).getId_distributor();
            case 1: return list.get(rowIndex).getNama_distributor();
            case 2: return list.get(rowIndex).getTelp_distributor();
            case 3: return list.get(rowIndex).getAlamat_distributor();
            
            default: return null;
        }
    }
    
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "ID Distributor";
            case 1: return "Nama Distributor";
            case 2: return "Telepon Distributor";
            case 3: return "Alamat Distributor";
            default: return null;
        }
    }
}
