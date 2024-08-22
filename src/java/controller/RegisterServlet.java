/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.UserDAO;
import entity.User;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ultils.MD5;

/**
 *
 * @author ACER
 */
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("title", "Register Page");
        request.getRequestDispatcher("./views/register.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        UserDAO udao = new UserDAO();

        String err_email = "", err_phone = "", err_repassword = "";
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String Email_Regex = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
        String Phone_Regex = "^\\d{10}$";
        boolean err = false;

        if (!email.matches(Email_Regex)) {
            err_email = "Email is invalid";
            request.getSession().setAttribute("err_email", err_email);
            err = true;
        } else {
            err_email = "";
            request.getSession().removeAttribute("err_email");
        }

        if (!phone.matches(Phone_Regex)) {
            err_phone = "Phone has 10 digits";
            request.getSession().setAttribute("err_phone", err_phone);
            err = true;
        } else {
            err_phone = "";
            request.getSession().removeAttribute("err_phone");
        }

        if (!repassword.matches(password)) {
            err_repassword = "Not match Password";
            request.getSession().setAttribute("err_repassword", err_repassword);
            err = true;
        } else {
            err_phone = "";
            request.getSession().removeAttribute("err_repassword");
        }
        if (err) {
            response.sendRedirect("register");
        } else {
            if (udao.findUser(phone) != null || udao.findUser(phone) != null) {
                request.getSession().setAttribute("exits_user", "User has existed in Database");
                response.sendRedirect("register");
            } else {
                udao.insertUser(name, email, phone, MD5.getMd5(password));
                User user = udao.findUser(email);
                request.getSession().setAttribute("user", user);
                request.removeAttribute("exits_user");
                response.sendRedirect("home");
            }
        }

    }
}
