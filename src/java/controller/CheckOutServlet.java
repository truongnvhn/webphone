/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.OrderDAO;
import dao.OrderDetailDAO;
import entity.Product;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class CheckOutServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            List<Product> cart = (List<Product>) request.getSession().getAttribute("cart");
            User user = (User) request.getSession().getAttribute("user");
            double totalPrice = 0;
            if(cart != null){
                for (Product product : cart) {
                    totalPrice += product.getPrice() * product.getQuantity();
                }
            }
            
            if (totalPrice == 0.0) {
                request.setAttribute("message", "Please add some product to cart to checkout!");
                request.getRequestDispatcher("./views/cart.jsp").include(request, response);
                return;
            }

            // get Date have
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.sql.Date dateInsert = java.sql.Date.valueOf(dateFormat.format(date));

            OrderDAO orderDAO = new OrderDAO();
            orderDAO.insertOrder(dateInsert + "", user.getId(), totalPrice*1.1);

            int orderID = orderDAO.getOrderID();
            OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
            for (Product product : cart) {
                orderDetailDAO.insertOrderDetails(orderID, product.getId(), product.getPrice(), product.getQuantity());
            }
            request.getSession().removeAttribute("cart");
            request.setAttribute("message", "Order successfull!");
            request.getRequestDispatcher("./views/cart.jsp").include(request, response);
        } catch (Exception e) {
            request.setAttribute("message", "Order fail!");
            request.getRequestDispatcher("./views/cart.jsp").include(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
