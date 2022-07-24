/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.adminInsert;

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
public class InsertBillBuyController extends BaseRequiredAuthenticationController {

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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        request.setAttribute("datemax", dtf.format(now));
        ProvidersDAO pd = new ProvidersDAO();
        ArrayList<Providers> provider = pd.getallProvider();
        request.setAttribute("provider", provider);
        request.getRequestDispatcher("../admin/insert/insertbillBuy.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String raw_bill_id = request.getParameter("id_bill");
        String raw_date_input = request.getParameter("date_input");
        String raw_amount_owed = request.getParameter("amount_owed");
        String raw_provider_id = request.getParameter("provider_id");
        if (raw_bill_id == null) {
            raw_bill_id = "0";
        }
        if (raw_date_input == null) {
            raw_date_input = "0";
        }
        if (raw_amount_owed == null) {
            raw_amount_owed = "0";
        }
        int id_bill = Integer.parseInt(raw_bill_id);
        Date date_input = Date.valueOf(raw_date_input);
        int amount_owed = Integer.parseInt(raw_amount_owed);
        int provider_id = Integer.parseInt(raw_provider_id);
        Providers pv = new Providers();
        pv.setId(provider_id);
        BillBuy bb = new BillBuy();
        bb.setId(id_bill);
        bb.setDateinput(date_input);
        bb.setAmountowed(amount_owed);
        bb.setProv(pv);
        BillBuyDAO bd = new BillBuyDAO();
        bd.insert(bb);
        response.sendRedirect("insertbuy?bill_id=" + id_bill);
    }
}
