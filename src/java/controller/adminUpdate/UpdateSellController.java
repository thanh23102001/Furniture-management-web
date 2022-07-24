/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.adminUpdate;

import controller.BaseRequiredAuthenticationController;
import dal.BillBuyDAO;
import dal.BillSellDAO;
import dal.machineBuyDAO;
import dal.machineSellDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.BillBuy;
import model.BillSell;
import model.machineBuy;
import model.machineSell;

/**
 *
 *  @author ADMIN
 */
public class UpdateSellController extends BaseRequiredAuthenticationController {

    String oldseri;

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
        oldseri = request.getParameter("seri");
        machineSellDAO msd = new machineSellDAO();
        machineSell ms = msd.getSellbySeri(oldseri);
        BillSellDAO bs = new BillSellDAO();
        ArrayList<BillSell> bsd = bs.getallBillSell();
        int action = Integer.parseInt(request.getParameter("action"));
        String bills = request.getParameter("bill_id");
        request.setAttribute("bill_id", bills);
        request.setAttribute("action", action);
        request.setAttribute("bill", bsd);
        request.setAttribute("machinesell", ms);
        request.getRequestDispatcher("../admin/update/updateSell.jsp").forward(request, response);
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
        int raw_bill_id = Integer.parseInt(request.getParameter("bill_id"));
        int raw_price = Integer.parseInt(request.getParameter("price"));
        int raw_newness = Integer.parseInt(request.getParameter("newness"));
        boolean raw_unit = Boolean.parseBoolean(request.getParameter("unit"));
        machineSell ms = new machineSell();
        ms.setBillsell_id(raw_bill_id);
        ms.setPrice(raw_price);
        ms.setNewness(raw_newness);
        ms.setUnit(raw_unit);
        machineSellDAO msd = new machineSellDAO();
        msd.update(ms, oldseri);
        String direct = request.getParameter("direct");
        if (direct.equals("1")) {
            response.sendRedirect("listsell");
        } else {
            response.sendRedirect("listbillsell/listsell?bill_id=" + raw_bill_id);
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
