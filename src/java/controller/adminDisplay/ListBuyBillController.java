/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.adminDisplay;

import controller.BaseRequiredAuthenticationController;
import dal.machineBuyDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.machineBuy;

/**
 *
 *  @author ADMIN
 */
public class ListBuyBillController extends BaseRequiredAuthenticationController {
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bill_id = Integer.parseInt(request.getParameter("bill_search"));
        String seri = request.getParameter("seri_search");
        if (seri == null) {
            seri = "";
        }
        machineBuyDAO mbd = new machineBuyDAO();
        ArrayList<machineBuy> mb = mbd.getBuybyBill(bill_id, seri);
        request.setAttribute("listbuy", mb);
        request.setAttribute("bill_id", bill_id);
        request.getRequestDispatcher("../../admin/display/listbuyinbill.jsp").forward(request, response);
    }



    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
