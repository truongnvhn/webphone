/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 84797
 */
public class ProductDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Product> findAll() {
        List<Product> listProduct = new ArrayList<>();
        String sql = "select * from products where status = 1";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int id_category = rs.getInt("id_category");
                String name = rs.getString("name");
                String image = rs.getString("image");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                boolean status = rs.getBoolean("status");
                listProduct.add(new Product(id, id_category, name, image, price, quantity, status));
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

    public Product findProduct(int id_product) {
        String sql = "select * from products where id=" + id_product;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Product(rs);
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Product> findAllByName(String nameSearch) {
        List<Product> listProduct = new ArrayList<>();
        String sql = "select * from products where status = 1 ";
        if (nameSearch != null && !nameSearch.trim().equals("")) {
            sql += "and name like ?";
        }
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            if (nameSearch != null && !nameSearch.trim().equals("")) {
                ps.setString(1, "%" + nameSearch + "%");
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int id_category = rs.getInt("id_category");
                String name = rs.getString("name");
                String image = rs.getString("image");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                boolean status = rs.getBoolean("status");
                listProduct.add(new Product(id, id_category, name, image, price, quantity, status));
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

    public List<Product> findAllByNameAndCategoryPagination(String nameSearch, String categoryId, String sort, String priceRange, Integer pageIndex, Integer pageSize) {
        List<Product> listProduct = new ArrayList<>();
        String sql = "select * from products where status = 1 ";
        if ((nameSearch != null && !nameSearch.trim().equals("")) && categoryId != null && !categoryId.trim().equals("")) {
            sql += "and name like ? and id_category = ? ";
        } else if (nameSearch != null && !nameSearch.trim().equals("")) {
            sql += "and name like ? ";
        } else if (categoryId != null && !categoryId.trim().equals("")) {
            sql += "and id_category = ? ";
        }

        if (priceRange.equals("1")) {
            sql += "and price between 0 and 300";
        } else if (priceRange.equals("2")) {
            sql += "and price between 300 and 500";
        } else if (priceRange.equals("3")) {
            sql += "and price between 500 and 1000";
        } else if (priceRange.equals("4")) {
            sql += "and price > 1000";
        }

        if (pageIndex != null && pageSize != null) {
            if (sort != null && sort.equals("asc")) {
                sql += " ORDER BY price OFFSET\n"
                        + "                    (?*?-?) ROWS FETCH NEXT ? ROWS ONLY";
            } else if (sort != null && sort.equals("desc")) {
                sql += " ORDER BY price desc OFFSET\n"
                        + "                    (?*?-?) ROWS FETCH NEXT ? ROWS ONLY";
            } else {
                sql += " ORDER BY pID OFFSET\n"
                        + "                    (?*?-?) ROWS FETCH NEXT ? ROWS ONLY";
            }
        } else {
            if (sort != null && sort.equals("asc")) {
                sql += " ORDER BY price";
            } else if (sort != null && sort.equals("desc")) {
                sql += " ORDER BY price desc";
            }
        }

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            if ((nameSearch != null && !nameSearch.trim().equals("")) && categoryId != null && !categoryId.trim().equals("")) {
                ps.setString(1, "%" + nameSearch + "%");
                ps.setString(2, categoryId);
                if (pageIndex != null && pageSize != null) {
                    ps.setInt(3, pageIndex);
                    ps.setInt(4, pageSize);
                    ps.setInt(5, pageSize);
                    ps.setInt(6, pageSize);
                }
            } else if (nameSearch != null && !nameSearch.trim().equals("")) {
                ps.setString(1, "%" + nameSearch + "%");
                if (pageIndex != null && pageSize != null) {
                    ps.setInt(2, pageIndex);
                    ps.setInt(3, pageSize);
                    ps.setInt(4, pageSize);
                    ps.setInt(5, pageSize);
                }
            } else if (categoryId != null && !categoryId.trim().equals("")) {
                ps.setString(1, categoryId);
                if (pageIndex != null && pageSize != null) {
                    ps.setInt(2, pageIndex);
                    ps.setInt(3, pageSize);
                    ps.setInt(4, pageSize);
                    ps.setInt(5, pageSize);
                }
            } else {
                if (pageIndex != null && pageSize != null) {
                    ps.setInt(1, pageIndex);
                    ps.setInt(2, pageSize);
                    ps.setInt(3, pageSize);
                    ps.setInt(4, pageSize);
                }
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int id_category = rs.getInt("id_category");
                String name = rs.getString("name");
                String image = rs.getString("image");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                boolean status = rs.getBoolean("status");
                listProduct.add(new Product(id, id_category, name, image, price, quantity, status));
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

    public void addNewProduct(Product p) {
        String sql = "INSERT into products (name, [image], price, quantity, status, id_category)\n"
                + "VALUES (?, ?, ?, ?, 1, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, p.getName());
            ps.setString(2, p.getImage());
            ps.setDouble(3, p.getPrice());
            ps.setInt(4, p.getQuantity());
            ps.setInt(5, p.getId_category());
            ps.execute();
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateProduct(Product p) {
        String sql = "UPDATE products set name = ?, image = ?, price = ?, quantity = ?, id_category = ? where id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, p.getName());
            ps.setString(2, p.getImage());
            ps.setDouble(3, p.getPrice());
            ps.setInt(4, p.getQuantity());
            ps.setInt(5, p.getId_category());
            ps.setInt(6, p.getId());
            ps.execute();
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteProduct(int id) {
        String sql = "UPDATE products set status = 0 where id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Product getProductById(int id_product) {
        String sql = "select * from products where id=" + id_product;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                int id_category = rs.getInt("id_category");
                String name = rs.getString("name");
                String image = rs.getString("image");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                boolean status = rs.getBoolean("status");
                return new Product(id, id_category, name, image, price, quantity, status);
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
