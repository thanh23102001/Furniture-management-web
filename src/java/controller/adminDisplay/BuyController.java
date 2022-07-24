/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.adminDisplay;

import controller.BaseRequiredAuthenticationController;
import dal.ProvidersDAO;
import dal.machineBuyDAO;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Providers;
import model.machineBuy;

/**
 *
 *  @author ADMIN
 */
public class BuyController extends BaseRequiredAuthenticationController {

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
//        String r_pagesize = getServletContext().getInitParameter("pagesize");
//        int pagesize = Integer.parseInt(r_pagesize);
//        String p_index = request.getParameter("page");
//        if (p_index == null) {
//            p_index = "1";
//        }
        String bill_search = request.getParameter("bill_search");
        String seri_search = request.getParameter("seri_search");
        String raw_provider_id = request.getParameter("providers_id");
        String raw_datefrom = request.getParameter("datefrom");
        String raw_dateto = request.getParameter("dateto");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        if (bill_search == null) {
            bill_search = "0";
        }
        if (seri_search == null) {
            seri_search = "";
        }
        if (raw_provider_id == null || raw_provider_id.length() == 0) {
            raw_provider_id = "0";
        }
        if ((raw_datefrom == null && raw_dateto == null) || ("".equals(raw_datefrom) && "".equals(raw_dateto))) {
            raw_datefrom = "2000-01-01";
            raw_dateto = dtf.format(now);
        }
        try {
//            int pageindex = Integer.parseInt(p_index);
            int provider_id = Integer.parseInt(raw_provider_id);
            int bill_id = Integer.parseInt(bill_search);
            Date datefrom = Date.valueOf(raw_datefrom);
            Date dateto = Date.valueOf(raw_dateto);
            ProvidersDAO pb = new ProvidersDAO();
            ArrayList<Providers> provider = pb.getallProvider();
            machineBuyDAO db = new machineBuyDAO();
//            int num_records = db.count();
//            int totalpage = (num_records % pagesize == 0)
//                    ? num_records / pagesize
//                    : (num_records / pagesize) + 1;
//            ArrayList<machineBuy> buy = db.getBuybySeri(pageindex, pagesize, bill_id, seri_search, provider_name, datefrom, dateto);
            ArrayList<machineBuy> buy = db.getBuybySeri(bill_id, seri_search, provider_id, datefrom, dateto);
            request.setAttribute("datemax", dtf.format(now));
//            request.setAttribute("totalpage", totalpage);
//            request.setAttribute("pageindex", pageindex);
            request.setAttribute("buy", buy);
            request.setAttribute("provider", provider);
            request.setAttribute("pvid", provider_id);
            request.setAttribute("datefrom", datefrom);
            request.setAttribute("dateto", dateto);
            request.setAttribute("seri_search", seri_search);
            
            request.setAttribute("bill_search", bill_search);
            
            request.getRequestDispatcher("../admin/display/buy.jsp").forward(request, response);
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
