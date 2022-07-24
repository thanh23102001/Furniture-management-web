/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.adminDisplay;

import controller.BaseRequiredAuthenticationController;
import dal.WareHouseDAO;
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
import model.WareHouse;

/**
 *
 *  @author ADMIN
 */
public class WareHouseController extends BaseRequiredAuthenticationController {

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
        String seri = request.getParameter("seri_search");
        String raw_datefrom = request.getParameter("datefrom");
        String raw_dateto = request.getParameter("dateto");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        if (seri == null || seri.length() == 0) {
            seri = "";
        }
        if ((raw_datefrom == null && raw_dateto == null) || ("".equals(raw_datefrom) && "".equals(raw_dateto))) {
            raw_datefrom = "2000-01-01";
            raw_dateto = dtf.format(now);
        }
        Date datefrom = Date.valueOf(raw_datefrom);
        Date dateto = Date.valueOf(raw_dateto);
        WareHouseDAO wd = new WareHouseDAO();
        ArrayList<WareHouse> warehouse = wd.getWarehouse(seri, datefrom, dateto);
        request.setAttribute("datemax", dtf.format(now));
        request.setAttribute("warehouse", warehouse);
        request.setAttribute("datefrom", datefrom);
        request.setAttribute("dateto", dateto);
        request.setAttribute("seri_search", seri);
        request.getRequestDispatcher("../admin/display/warehouse.jsp").forward(request, response);
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
