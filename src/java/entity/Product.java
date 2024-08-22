/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author 84797
 */
public class Product {
    int id;
    int id_category;
    String name;
    String image;
    double price;
    int quantity;
    boolean status;

    public Product(int id, int id_category, String name, String image, double price, int quantity, boolean status) {
        this.id = id;
        this.id_category = id_category;
        this.name = name;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
    }
    
    public Product(int id, int id_category, String name, String image, double price, int quantity) {
        this.id = id;
        this.id_category = id_category;
        this.name = name;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
    }
    
    public Product(int id_category, String name, String image, double price, int quantity) {
        this.id_category = id_category;
        this.name = name;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
    }
        
    public Product(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.name = rs.getString("name");
        this.image = rs.getString("image");
        this.price = rs.getDouble("price");
        this.quantity = 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        BigDecimal bd = new BigDecimal(price).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
