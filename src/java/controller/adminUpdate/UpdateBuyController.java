/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.adminUpdate;

import controller.BaseRequiredAuthenticationController;
import dal.BillBuyDAO;
import dal.machineBuyDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
public class UpdateBuyController extends BaseRequiredAuthenticationController {

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
        machineBuyDAO mbd = new machineBuyDAO();
        machineBuy mb = mbd.getBuybySeri(oldseri);
        boolean check = mbd.checkWarehouse(oldseri);
        BillBuyDAO bb = new BillBuyDAO();
        ArrayList<BillBuy> bbd = bb.getallBillBuy();
        if (request.getParameter("action") != null) {
            int action = Integer.parseInt(request.getParameter("action"));
            request.setAttribute("action", action);
        }
        String bills = request.getParameter("bill_search");
        request.setAttribute("bill_search", bills);
        request.setAttribute("check", check);
        request.setAttribute("bill", bbd);
        request.setAttribute("machinebuy", mb);
        request.getRequestDispatcher("../admin/update/updateBuy.jsp").forward(request, response);
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
        String raw_new_seri = request.getParameter("seri");
        int raw_bill_id = Integer.parseInt(request.getParameter("bill_id"));
        String raw_name = request.getParameter("name");
        String raw_model = request.getParameter("model");
        int raw_price = Integer.parseInt(request.getParameter("price"));
        int raw_newness = Integer.parseInt(request.getParameter("newness"));
        int raw_depreciation = Integer.parseInt(request.getParameter("depreciation"));
        machineBuy mb = new machineBuy();
        mb.setBillbuy_id(raw_bill_id);
        mb.setSeri(raw_new_seri);
        mb.setName(raw_name);
        mb.setModel(raw_model);
        mb.setPrice(raw_price);
        mb.setNewness(raw_newness);
        mb.setDepreciation(raw_depreciation);
        machineBuyDAO mbd = new machineBuyDAO();
        mbd.update(mb, oldseri);
        String direct = request.getParameter("direct");
        if (direct.equals("1")){
        response.sendRedirect("listbuy");
        }else if (direct.equals("2")){
            response.sendRedirect("listwarehouse");
        }else{
             response.sendRedirect("listbillbuy/listbuy?bill_search="+ raw_bill_id);
                 mbd.update(mb, oldseri);
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
