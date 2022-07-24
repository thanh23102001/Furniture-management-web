/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.adminUpdate;

import controller.BaseRequiredAuthenticationController;
import dal.BillBuyDAO;
import dal.ProvidersDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.BillBuy;
import model.Providers;

/**
 *
 * @author XQ
 */
public class UpdateBillBuyController extends BaseRequiredAuthenticationController {
int bill_id;
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
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        bill_id = Integer.parseInt(request.getParameter("bill_id"));
        BillBuyDAO bbd = new BillBuyDAO();
        BillBuy bb = bbd.getBillBuybyID(bill_id);
        ProvidersDAO pvd = new ProvidersDAO();
        ArrayList<Providers> pv = pvd.getallProvider();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        request.setAttribute("datemax", dtf.format(now));
        request.setAttribute("billbuy", bb);
        request.setAttribute("provider", pv);
        request.getRequestDispatcher("../../admin/update/updatebillbuy.jsp").forward(request, response);
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
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Date date_input = Date.valueOf(request.getParameter("date_input"));
        int Amount = Integer.parseInt(request.getParameter("amount_owed"));
        int provider = Integer.parseInt(request.getParameter("provider_id"));
        BillBuy bb = new BillBuy();
        bb.setId(bill_id);
        bb.setDateinput(date_input);
        bb.setAmountowed(Amount);
        bb.setProvider_id(provider);
        BillBuyDAO bbd = new BillBuyDAO();
        bbd.update(bb);
        response.sendRedirect("../listbillbuy");
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
