/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.adminInsert;

import controller.BaseRequiredAuthenticationController;
import dal.BillBuyDAO;
import dal.BillSellDAO;
import dal.BuyerDAO;
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
import model.BillSell;
import model.Buyer;
import model.Providers;

/**
 *
 * @author XQ
 */
public class InsertBillSellController extends BaseRequiredAuthenticationController {

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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        request.setAttribute("datemax", dtf.format(now));
        BuyerDAO bd = new BuyerDAO();
        ArrayList<Buyer> buyer = bd.getBuyer();
        request.setAttribute("buyer", buyer);
        request.getRequestDispatcher("../admin/insert/insertbillSell.jsp").forward(request, response);
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
        request.setCharacterEncoding("utf-8");
        String raw_bill_id = request.getParameter("id_billsell");
            String raw_date_output = request.getParameter("date_output");
            String raw_amount_owed = request.getParameter("amount_owed");
            String raw_buyer_id = request.getParameter("buyer_id");
            if (raw_bill_id == null) {
                raw_bill_id = "0";
            }
            if (raw_date_output == null) {
                raw_date_output = "0";
            }
            if (raw_amount_owed == null) {
                raw_amount_owed = "0";
            }
            int id_bill = Integer.parseInt(raw_bill_id);
            Date date_output = Date.valueOf(raw_date_output);
            int amount_owed = Integer.parseInt(raw_amount_owed);
            int buyer_id = Integer.parseInt(raw_buyer_id);
            Buyer be = new Buyer();
            be.setId(buyer_id);
            BillSell bs = new BillSell();
            bs.setId(id_bill);
            bs.setDatesell(date_output);
            bs.setAmountowed(amount_owed);
            bs.setBuyer(be);
            BillSellDAO bd = new BillSellDAO();
            bd.insert(bs);
            response.sendRedirect("insertsell?bill_id=" + id_bill);
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
