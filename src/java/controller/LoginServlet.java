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

/**
 *
 * @author 84797
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("title", "Login page");
        request.getRequestDispatcher("./views/login.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO udao = new UserDAO();

        String emailphone = request.getParameter("emailphone");
        String password = request.getParameter("password");
        User user = udao.findUser(emailphone, password);
        if (user == null) {
            request.getSession().setAttribute("error_login", "your information is incorrect!");
            response.sendRedirect("login");
        } else if (user.getRole().equals("admin")) {
            response.sendRedirect("admin");
        } else {
            request.getSession().setAttribute("user", user);
            request.getSession().removeAttribute("error_login");
            response.sendRedirect("home");
        }
    }
}
