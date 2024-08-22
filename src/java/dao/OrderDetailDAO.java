/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.OrderDetail;
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
 * @author Admin
 */
public class OrderDetailDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void insertOrderDetails(int orderID, int productID, double unitPrice, int quantity) {
        String sql = "insert into orderdetails(orderId, productId, unitPrice, quantity) values (?, ?, ?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, orderID);
            ps.setInt(2, productID);
            ps.setDouble(3, unitPrice);
            ps.setInt(4, quantity);
            ps.execute();
        } catch (Exception ex) {
            Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<OrderDetail> getOrderDetailByOrderID(int orderId) {
        String query = "SELECT od.*, p.image, p.name, c.name from orderdetails od\n"
                + "left join products p on od.productId = p.id\n"
                + "left join categories c on p.id_category = c.id\n"
                + "where orderId = ?";
        try {
            List<OrderDetail> ls = new ArrayList<>();
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();
            while (rs.next()) {
                ls.add(new OrderDetail(rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)));
            }
            return ls;
        } catch (Exception e) {
        }
        return null;
    }

    public int getNumberItemsSolid() {
        int n = 0;
        String query = "select SUM(quantity) from orderdetails";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            if (rs.next()) {
                n = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return n;
    }
}
