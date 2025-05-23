package model;

import java.math.BigInteger;

/**
 *
 * @author Agung
 */
public class Item {

    private int id;
    private String name;
    private BigInteger price;
    private int quantity;
    private int min_quantity;
    private int unit;
    private String unitName;

    // Constructor tanpa id (untuk insert)
    public Item(String name, BigInteger price, int quantity, int min_quantity, int unit) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.min_quantity = min_quantity;
        this.unit = unit;
    }

// Constructor lengkap (misalnya saat ambil dari DB)
    public Item(int id, String name, BigInteger price, int quantity, int min_quantity, int unit) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.min_quantity = min_quantity;
        this.unit = unit;
    }

    // Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMin_quantity() {
        return min_quantity;
    }

    public void setMin_quantity(int min_quantity) {
        this.min_quantity = min_quantity;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }
    
    // setter untuk unitName
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitName() {
        return unitName;
    }
}
