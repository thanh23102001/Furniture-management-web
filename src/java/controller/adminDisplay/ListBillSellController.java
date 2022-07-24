/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.adminDisplay;

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
 * @author ADMIN
 */
public class ListBillSellController extends BaseRequiredAuthenticationController {

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
        String bill_search = request.getParameter("bill_search");
        String raw_buyer_id = request.getParameter("buyer_id");
        String raw_datefrom = request.getParameter("datefrom");
        String raw_dateto = request.getParameter("dateto");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        if (bill_search == null) {
            bill_search = "";
        }
        if (raw_buyer_id == null || raw_buyer_id.length() == 0 || raw_buyer_id.equals("")) {
            raw_buyer_id = "0";
        }
        if ((raw_datefrom == null && raw_dateto == null) || ("".equals(raw_datefrom) && "".equals(raw_dateto))) {
            raw_datefrom = "2000-01-01";
            raw_dateto = dtf.format(now);
        }
        try {
            int buyer_id = Integer.parseInt(raw_buyer_id);
            Date datefrom = Date.valueOf(raw_datefrom);
            Date dateto = Date.valueOf(raw_dateto);
            BuyerDAO bb = new BuyerDAO();
            ArrayList<Buyer> buyer = bb.getBuyer();
            BillSellDAO bsd = new BillSellDAO();
            ArrayList<BillSell> billsell = bsd.getBillSellbySearch(bill_search, buyer_id, datefrom, dateto);
            request.setAttribute("datemax", dtf.format(now));
            request.setAttribute("billsell", billsell);
            request.setAttribute("buyer", buyer);
            request.setAttribute("bid", buyer_id);
            request.setAttribute("datefrom", datefrom);
            request.setAttribute("dateto", dateto);
            request.setAttribute("seri_search", bill_search);
            request.getRequestDispatcher("../admin/display/listbillsell.jsp").forward(request, response);
        } catch (Exception ex) {
            request.getRequestDispatcher("../admin/newjsp.jsp").forward(request, response);
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
        processRequest(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
