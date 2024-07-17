/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAO;
import entity.Category;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "CategoryController", urlPatterns = {"/category"})
public class CategoryController extends HttpServlet {

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
        String cateID = request.getParameter("cid"); // lay category id 

        DAO dao = new DAO();
        Product last = dao.getLast();
        List<Product> list = dao.getProductByCID(cateID);

        
        List<Category> listC = dao.getAllCategory();
        request.setAttribute("listP", list);
        request.setAttribute("listCC", listC);
        request.setAttribute("last", last);
        request.setAttribute("tag", cateID);
        request.getRequestDispatcher("Home.jsp").forward(request, response);
        
        //USE AJAX
        //        PrintWriter out = response.getWriter();
//        for (Product o : list) {
//            out.println("  <div class=\"col-12 col-md-6 col-lg-4\">\n"
//                    + "                                <div class=\"card\">\n"
//                    + "                                    <img class=\"card-img-top\" src=\""+o.getImage()+"\" alt=\"Card image cap\">\n"
//                    + "                                    <div class=\"card-body\">\n"
//                    + "                                        <h4 class=\"card-title show_txt\"><a href=\"detail?product_id="+o.getId()+"\" title=\"View Product\">name}"+o.getName()+"</a></h4>\n"
//                    + "                                        <p class=\"card-text show_txt\">"+o.getTitle()+"</p>\n"
//                    + "                                        <div class=\"row\">\n"
//                    + "                                            <div class=\"col\">\n"
//                    + "                                                <p class=\"btn btn-danger btn-block\">"+o.getPrice()+" $</p>\n"
//                    + "                                            </div>\n"
//                    + "                                            <div class=\"col\">\n"
//                    + "                                                <a href=\"#\" class=\"btn btn-success btn-block\">Add to cart</a>\n"
//                    + "                                            </div>\n"
//                    + "                                        </div>\n"
//                    + "                                    </div>\n"
//                    + "                                </div>\n"
//                    + "                            </div>");
//
//        }
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
