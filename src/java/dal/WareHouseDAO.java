/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.BillBuy;
import model.WareHouse;
import model.machineBuy;

/**
 *
 *  @author ADMIN
 */
public class WareHouseDAO extends DBContext {

    public ArrayList<WareHouse> getWarehouse(String seri, Date datefrom, Date dateto) {
        ArrayList<WareHouse> warehouse = new ArrayList<>();
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            String sql = "select w.seri,l.nameMachine, l.Model, lb.dateInput, DATEDIFF(day, lb.dateinput, '" + dtf.format(now) + "') AS UsedDate from warehouse w\n"
                    + "join listBuy l on l.seri = w.seri\n"
                    + "join listBillBuy lb on l.IDBillBuy = lb.id\n";
            if (!seri.equals("")) {
                sql += "\n where w.seri = ?";
            }
            if (seri.equals("")) {
                sql += "\n where lb.DateInput between ? and ?";
            } else if (!seri.equals("")) {
                sql += " and lb.DateInput between ? and ?";
            }
            sql += "\n order by lb.DateInput desc";
            PreparedStatement stm = connection.prepareStatement(sql);
            if (!seri.equals("")) {
                stm.setString(1, seri);
            }
            if (seri.equals("")) {
                stm.setDate(1, datefrom);
                stm.setDate(2, dateto);
            } else if (!seri.equals("")) {
                stm.setDate(2, datefrom);
                stm.setDate(3, dateto);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                WareHouse wh = new WareHouse();
                wh.setUsedDate(rs.getInt("UsedDate"));
                BillBuy bb = new BillBuy();
                bb.setDateinput(rs.getDate("dateinput"));
                machineBuy mb = new machineBuy();
                mb.setSeri(rs.getString("seri"));
                mb.setBb(bb);
                mb.setName(rs.getString("nameMachine"));
                mb.setModel(rs.getString("model"));
                wh.setMachinebuy(mb);
                warehouse.add(wh);
            }
        } catch (SQLException ex) {
            Logger.getLogger(WareHouseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return warehouse;
    }
    
    public ArrayList<WareHouse> getallWareHouse(){
        ArrayList<WareHouse> wh = new ArrayList<>();
        try {
            String sql = "select * from warehouse";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                WareHouse listwh = new WareHouse();
                listwh.setSeri(rs.getString("Seri"));
                wh.add(listwh);
            }
        } catch (Exception e) {
        }
        return wh;
    }
    
    public void delete(String seri) {
        try {
            String sql = "DELETE FROM [projectWeb].[dbo].[warehouse]\n"
                    + "      WHERE Seri = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, seri);
            stm.executeUpdate();
        } catch (Exception ex) {

        }
    }
}
