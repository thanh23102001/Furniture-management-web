/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.adminInsert;

import controller.BaseRequiredAuthenticationController;
import dal.BillSellDAO;
import dal.WareHouseDAO;
import dal.machineSellDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.BillSell;
import model.WareHouse;
import model.machineSell;

/**
 *
 * @author XQ
 */
public class InsertSellController extends BaseRequiredAuthenticationController {

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
        BillSellDAO bb = new BillSellDAO();
        ArrayList<BillSell> bsd = bb.getallBillSell();
        int lastbill = Integer.parseInt(request.getParameter("bill_id"));
        WareHouseDAO whd = new WareHouseDAO();
        ArrayList<WareHouse> wh = whd.getallWareHouse();
        request.setAttribute("seri", wh);
        request.setAttribute("bill", bsd);
        request.setAttribute("lastbill", lastbill);
        request.getRequestDispatcher("../admin/insert/insertSell.jsp").forward(request, response);
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
        String raw_id_bill = request.getParameter("bill_id");
        String raw_seri = request.getParameter("seri_product");
        String raw_unit = request.getParameter("unit");
        String raw_price = request.getParameter("price_machine");
        String raw_newness = request.getParameter("newness");
        if (raw_newness == null) {
            raw_newness = "0";
        }
        if (raw_price == null) {
            raw_price = "0";
        }
        int id_bill = Integer.parseInt(raw_id_bill);
        int newness = Integer.parseInt(raw_newness);
        int price = Integer.parseInt(raw_price);
        boolean unit = Boolean.parseBoolean(raw_unit);
        machineSell ms = new machineSell();
        ms.setBillsell_id(id_bill);
        ms.setSeri(raw_seri);
        ms.setUnit(unit);
        ms.setPrice(price);
        ms.setNewness(newness);
        machineSellDAO md = new machineSellDAO();
        md.insert(ms);
        md.updatewh(raw_seri);
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
