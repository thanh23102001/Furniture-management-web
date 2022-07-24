/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author ADMIN
 */
public abstract class BaseRequiredAuthenticationController extends HttpServlet {

//    private boolean checkAuthentication(HttpServletRequest request)
//    {
//        
//    }
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
        Account account = (Account) request.getSession().getAttribute("acc");
        if (account != null) {
            if (account.getRoleac() == 1 && (request.getRequestURI().contains("admin") || request.getRequestURI().contains(""))) {
                System.out.println(request.getRequestURI());
                processGet(request, response);
            } else if (account.getRoleac() == 1 && !request.getRequestURI().contains("admin")) {
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            } else if (account.getRoleac() == 2 && (request.getRequestURI().contains("staff") || request.getRequestURI().contains(""))) {
                processGet(request, response);
            } else if (account.getRoleac() == 2 && !request.getRequestURI().contains("staff")) {
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            }
        } else {
            response.sendRedirect("/WebManager/login");
        }
    }

    protected abstract void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    protected abstract void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

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
        Account account = (Account) request.getSession().getAttribute("acc");
        if (account != null) {
            if (account.getRoleac() == 1 && request.getRequestURI().contains("admin")) {
                System.out.println(request.getRequestURI());
                processPost(request, response);
            } else if (account.getRoleac() == 1 && !request.getRequestURI().contains("admin")) {
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            } else if (account.getRoleac() == 2 && request.getRequestURI().contains("staff")) {
                processPost(request, response);
            } else if (account.getRoleac() == 2 && !request.getRequestURI().contains("staff")) {
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            }
        } else {
            response.sendRedirect("/WebManager/login");
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
