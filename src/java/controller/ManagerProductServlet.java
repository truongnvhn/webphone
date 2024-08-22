/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CategoryDAO;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.ProductDAO;
import entity.Category;
import entity.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ManagerProductServlet extends HttpServlet {

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
        ProductDAO productDAO = new ProductDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        
        String productId = request.getParameter("productId");
        String message = (String) request.getAttribute("message");

        String action = request.getParameter("action");
        if(action != null && action.equals("delete")){
            //delete
            productDAO.deleteProduct(Integer.parseInt(productId));
            message = "Delete successfull!";
        }else{
            //show product detail
            Boolean showEditDialog = (Boolean) request.getAttribute("showEditDialog");
            if(productId != null && showEditDialog == null){
                request.setAttribute("showEditDialog", true);
                Product p = productDAO.getProductById(Integer.parseInt(productId));
                request.setAttribute("productDetail", p);
            }
        }
        
        request.setAttribute("message", message);
        
        List<Product> allProduct = productDAO.findAll();
        request.setAttribute("listProduct", allProduct);
        List<Category> listCategory = categoryDAO.findAll();
        request.setAttribute("listCategory", listCategory);
        
        
        
        int numberProduct = allProduct.size();
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        int numberItemsSolid = orderDetailDAO.getNumberItemsSolid();
        OrderDAO orderDAO = new OrderDAO();
        double totalEarnings = orderDAO.getTotalEarnings();
        request.setAttribute("numberP", numberProduct);
        request.setAttribute("numberI", numberItemsSolid);
        request.setAttribute("totalE", totalEarnings);
        
        request.getRequestDispatcher("./views/managerProduct.jsp").include(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        ProductDAO productDAO = new ProductDAO();
        String action = request.getParameter("action");

        String productId = request.getParameter("productId");
        String name = request.getParameter("name");
        String image = request.getParameter("image");
        String price = request.getParameter("price");
        String quantity = request.getParameter("quantity");
        String category = request.getParameter("category");
        switch (action) {
            case "Add" -> {
                Product pAdd = new Product(Integer.parseInt(category), name, image, Double.parseDouble(price), Integer.parseInt(quantity));
                productDAO.addNewProduct(pAdd);
                request.setAttribute("message", "Create successfull!");
                processRequest(request, response);
            }
           case "Save" -> {
               Product pEdit = new Product(Integer.parseInt(productId), Integer.parseInt(category), name, image, Double.parseDouble(price), Integer.parseInt(quantity));
               productDAO.updateProduct(pEdit);
               request.setAttribute("message", "Update successfull!");
               request.setAttribute("showEditDialog", false);
               processRequest(request, response);
            }
        }
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
