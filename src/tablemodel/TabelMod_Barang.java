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
    private final List<Model_Barang> list = new ArrayList<>();
    
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
    
    private final String[] columnNames = {"No", "Kode Barang", "Kode Jenis", "Nama Jenis", "Nama Barang", "Satuan", "Harga", "Stok"};

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0) {
            return "    " + (rowIndex + 1);
        } else {
            return switch (columnIndex - 1) {
                case 0 -> list.get(rowIndex).getKode_barang();
                case 1 -> list.get(rowIndex).getJns_barang().getKode_jenis();
                case 2 -> list.get(rowIndex).getJns_barang().getNama_jenis();
                case 3 -> list.get(rowIndex).getNama_barang();
                case 4 -> list.get(rowIndex).getSatuan();
                case 5 -> list.get(rowIndex).getHarga();
                case 6 -> list.get(rowIndex).getStok();
                default -> null;
            };
        }
    }
    
    @Override
    public String getColumnName(int column) {
        if (column == 0) {
            return "    " + columnNames[column];
        } else {
            return columnNames[column];
        }
    }
    
}
