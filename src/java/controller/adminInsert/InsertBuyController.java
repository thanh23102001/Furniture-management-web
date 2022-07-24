/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.adminInsert;

import controller.BaseRequiredAuthenticationController;
import dal.BillBuyDAO;
import dal.machineBuyDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.BillBuy;
import model.machineBuy;

/**
 *
 * @author XQ
 */
public class InsertBuyController extends BaseRequiredAuthenticationController {

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
        BillBuyDAO bb = new BillBuyDAO();
        ArrayList<BillBuy> bbd = bb.getallBillBuy();
        int raw_id_bill = Integer.parseInt(request.getParameter("bill_id"));
        request.setAttribute("lastbill", raw_id_bill);
        request.setAttribute("bill", bbd);
        request.getRequestDispatcher("../admin/insert/insertBuy.jsp").forward(request, response);
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
        String raw_id_bill = request.getParameter("bill_id_new");
        String raw_seri = request.getParameter("seri_product");
        String raw_name_machine = request.getParameter("name_machine");
        String raw_model = request.getParameter("model_machine");
        String raw_price = request.getParameter("price_machine");
        String raw_newness = request.getParameter("newness");
        int raw_depreciation = Integer.parseInt(request.getParameter("depreciation"));
        if (raw_newness == null) {
            raw_newness = "0";
        }
        if (raw_price == null) {
            raw_price = "0";
        }
        int id_bill = Integer.parseInt(raw_id_bill);
        int newness = Integer.parseInt(raw_newness);
        int price = Integer.parseInt(raw_price);
        machineBuy mb = new machineBuy();
        mb.setBillbuy_id(id_bill);
        mb.setSeri(raw_seri);
        mb.setModel(raw_model);
        mb.setPrice(price);
        mb.setName(raw_name_machine);
        mb.setNewness(newness);
        mb.setDepreciation(raw_depreciation);
        machineBuyDAO md = new machineBuyDAO();
        md.insert(mb);
        md.insert(raw_seri);
        response.sendRedirect("insertbuy?bill_id=" + id_bill);
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
