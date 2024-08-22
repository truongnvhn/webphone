/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CategoryDAO;
import dao.ProductDAO;
import entity.Category;
import entity.Product;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 84797
 */
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int numberProductInPage = 6;
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> listCategory = categoryDAO.findAll();
        request.setAttribute("listCategory", listCategory);

        String txtSearch = request.getParameter("txtSearch");
        request.setAttribute("txtSearch", txtSearch);

        String id_category = (String) request.getParameter("id_category");

        String sort = request.getParameter("sort");
        sort = (sort == null || sort.equals("")) ? "asc" : sort;
        String priceRange = request.getParameter("priceRange");
        priceRange = (priceRange == null || priceRange.equals("")) ? "0" : priceRange;

        // pagining
        ProductDAO productDAO = new ProductDAO();
        List<Product> allProduct = productDAO.findAllByNameAndCategoryPagination(txtSearch, id_category, sort, priceRange, null, null);
        int numberOfProduct = allProduct.size();
        int pageSize = getPageSize(numberProductInPage, numberOfProduct);
        String index = request.getParameter("pageIndex");
        int pageIndex = 0;
        if (index == null) {
            pageIndex = 1;
        } else {
            pageIndex = Integer.parseInt(index);
        }

        List<Product> listProduct = productDAO.findAllByNameAndCategoryPagination(txtSearch, id_category, sort, priceRange, pageIndex, numberProductInPage);
        request.setAttribute("totalPage", pageSize);
        request.setAttribute("numberProduct", 8);
        request.setAttribute("pageIndex", pageIndex);

        request.setAttribute("listProduct", listProduct);

        Category c = null;
        if (id_category != null) {
            for (Category category : listCategory) {
                if ((category.getId() + "").equals(id_category)) {
                    c = category;
                    break;
                }
            }
        }
//        request.setAttribute("id_category", id_category);
        request.setAttribute("category", c);
        request.setAttribute("sort", sort);
        request.setAttribute("priceRange", priceRange);

        addProductToCart(request);

        request.setAttribute("title", "home page");
        request.getRequestDispatcher("./views/home.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    void addProductToCart(HttpServletRequest request) {
        ProductDAO productDAO = new ProductDAO();
        int id_product;
        try {
            id_product = Integer.parseInt(request.getParameter("id_product"));
        } catch (Exception e) {
            id_product = 0;
        }
        List<Product> cart = (List<Product>) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        if (id_product > 0) {
            Product product = productDAO.findProduct(id_product);
            boolean isProductInCart = false;
            for (Product pro : cart) {
                if (pro.getId() == id_product) {
                    pro.setQuantity(pro.getQuantity() + 1);
                    isProductInCart = true;
                }
            }
            if (!isProductInCart) {
                cart.add(product);
            }

        }
        request.getSession().setAttribute("cart", cart);
    }

    public int getPageSize(int numberProduct, int allProduct) {
        int pageSize = allProduct / numberProduct;
        if (allProduct % numberProduct != 0) {
            pageSize = (allProduct / numberProduct) + 1;
        }
        return pageSize;
    }

}
