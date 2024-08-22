/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ultils.MD5;

/**
 *
 * @author 84797
 */
public class UserDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public User findUser(String emailphone, String password) {
        String sql;
        if (emailphone.contains("@")) {
            sql = "select * from users where email='" + emailphone + "' and password='" + MD5.getMd5(password) + "'";
        } else {
            sql = "select * from users where email='" + emailphone + "' and password='" + MD5.getMd5(password) + "'";
        }
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public User findUser(String emailphone) {
        String sql;
        if (emailphone.contains("@")) {
            sql = "select * from users where email='" + emailphone + "'";
        } else {
            sql = "select * from users where email='" + emailphone + "'";
        }
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insertUser(String name, String email, String phone, String password) {
        String sql = "insert into users(name, email, phone, password, role) values('" + name + "','" + email;
        sql = sql + "','" + phone + "','" + password + "','')";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
