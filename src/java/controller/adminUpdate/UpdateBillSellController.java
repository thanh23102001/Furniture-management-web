/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.adminUpdate;

import controller.BaseRequiredAuthenticationController;
import dal.BillBuyDAO;
import dal.BillSellDAO;
import dal.BuyerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.BillBuy;
import model.BillSell;
import model.Buyer;

/**
 *
 * @author XQ
 */
public class UpdateBillSellController extends BaseRequiredAuthenticationController {

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
        BuyerDAO bd = new BuyerDAO();
        ArrayList<Buyer> be = bd.getBuyer();
        BillSellDAO bsd = new BillSellDAO();
        BillSell bs = bsd.getBillsellbyId(bill_id);
        request.setAttribute("billsell", bs);
        request.setAttribute("buyer", be);
        request.setAttribute("bill_id", bill_id);
        request.getRequestDispatcher("../../admin/update/updatebillsell.jsp").forward(request, response);
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
        Date date_sell = Date.valueOf(request.getParameter("date_sell"));
        int Amount = Integer.parseInt(request.getParameter("amount_owed"));
        int buyer = Integer.parseInt(request.getParameter("buyer_id"));
        BillSell bs = new BillSell();
        bs.setId(bill_id);
        bs.setDatesell(date_sell);
        bs.setAmountowed(Amount);
        bs.setBuyerid(buyer);
        BillSellDAO bsd = new BillSellDAO();
        bsd.update(bs);
        response.sendRedirect("../listbillsell");
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
