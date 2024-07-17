/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAO;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

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
        //B1: get user, pass from cookie
        Cookie arr[] = request.getCookies();
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("userC")) {
                    request.setAttribute("username", o.getValue());
                }
                if (o.getName().equals("passC")) {
                    request.setAttribute("password", o.getValue());
                }

            }

            //b2 : set user, pass to login form
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
            String username = request.getParameter("user");
            String password = request.getParameter("pass");
            String remenber = request.getParameter("remember");
            DAO dao = new DAO();
            Account acoount = dao.login(username, password);

            if (acoount == null) {
                request.setAttribute("statusCreate", "Wrong userName or password");
                request.getRequestDispatcher("Login.jsp").forward(request, response); // chuyển trang có truyền dữ liệu
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("acc", acoount);
                Cookie u = new Cookie("userC", username);
                Cookie p = new Cookie("passC", password);

                u.setMaxAge(60);
                p.setMaxAge(60);

                if (remenber != null) {
                    p.setMaxAge(60);
                } else {
                    p.setMaxAge(0);
                }
                response.addCookie(u); // luu u va p len tren browes
                response.addCookie(p);

                response.sendRedirect("home"); // chuyển trang ko truyền dữ liệu
            }
        }
    }
    

