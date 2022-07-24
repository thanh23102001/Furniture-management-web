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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.BillBuy;
import model.Providers;
import model.machineBuy;

/**
 *
 * 
 */
public class BillBuyDAO extends DBContext {

    public ArrayList<BillBuy> getallBillBuy() {
        ArrayList<BillBuy> billbuy = new ArrayList<>();
        try {
            String sql = "select * from listBillBuy";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                BillBuy bb = new BillBuy();
                bb.setId(rs.getInt("id"));
                bb.setDateinput(rs.getDate("DateInput"));
                bb.setAmountowed(rs.getInt("AmountOwed"));
                billbuy.add(bb);
            }
        } catch (SQLException ex) {
        }
        return billbuy;
    }
    
    public BillBuy getBillBuybyID(int bill_id) {
        try {
            String sql = "select * from listBillBuy\n"
                    + "where id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, bill_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                BillBuy bb = new BillBuy();
                bb.setId(rs.getInt("id"));
                bb.setDateinput(rs.getDate("DateInput"));
                bb.setAmountowed(rs.getInt("AmountOwed"));
                bb.setProvider_id(rs.getInt("ProviderID"));
                return bb;
            }
        } catch (SQLException ex) {
        }
        return null;
    }

    public ArrayList<BillBuy> getBillBuybySearch(String bill_search, int provider_id, Date dateFrom, Date dateTo) {
        ArrayList<BillBuy> bbuy = new ArrayList<>();
        try {
            String sql = "select table1.*, table2.DateInput,table2.AmountOwed,table2.Name from (select lbb.id, SUM(lb.Price) as Total_money, Count(lb.Seri) as quantity from listBillBuy lbb\n"
                    + "                    join listBuy lb on lb.IDBillBuy = lbb.id\n"
                    + "                    group by lbb.id) as table1\n"
                    + "                    JOIN (select lbs.id, lbs.dateinput, lbs.amountowed, p.name, p.id as pid from listBillBuy lbs\n"
                    + "                    join Provider p on p.id = lbs.ProviderID) as table2 ON table1.id = table2.id";
            if (!bill_search.equals("")) {
                sql += "\n where table1.id = ?";
            }
            if (provider_id != 0) {
                if (bill_search.equals("")) {
                    sql += "\n where table2.pid = ?";
                } else {
                    sql += " and table2.pid = ?";
                }
            }
            if (bill_search.equals("") && provider_id == 0) {
                sql += "\n where table2.DateInput between ? and ?";
            } else {
                sql += " and table2.DateInput between ? and ?";
            }
            sql += "\n order by table2.DateInput desc";
            PreparedStatement stm = connection.prepareStatement(sql);
            if (!bill_search.equals("")) {
                stm.setString(1, bill_search);
            }
            if (provider_id != 0) {
                if (bill_search.equals("")) {
                    stm.setInt(1, provider_id);
                } else {
                    stm.setInt(2, provider_id);
                }
            }
            if (bill_search.equals("") && provider_id == 0) {
                stm.setDate(1, dateFrom);
                stm.setDate(2, dateTo);
            } else if (!bill_search.equals("") || provider_id != 0) {
                if (!bill_search.equals("") && provider_id != 0) {
                    stm.setDate(3, dateFrom);
                    stm.setDate(4, dateTo);
                } else {
                    stm.setDate(2, dateFrom);
                    stm.setDate(3, dateTo);
                }
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Providers pv = new Providers();
                pv.setName(rs.getString("name"));
                BillBuy bb = new BillBuy();
                bb.setSum_money(rs.getInt("total_money"));
                bb.setQuantity(rs.getInt("quantity"));
                bb.setId(rs.getInt("id"));
                bb.setDateinput(rs.getDate("DateInput"));
                bb.setAmountowed(rs.getInt("AmountOwed"));
                bb.setProv(pv);
                bbuy.add(bb);
            }
        } catch (SQLException ex) {
        }
        return bbuy;
    }

    public void insert(BillBuy bb) {
        try {
            String sql = "INSERT INTO [projectWeb].[dbo].[listBillBuy]\n"
                    + "           ([id]\n"
                    + "           ,[DateInput]\n"
                    + "           ,[AmountOwed]\n"
                    + "           ,[ProviderID])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, bb.getId());
            stm.setDate(2, bb.getDateinput());
            stm.setInt(3, bb.getAmountowed());
            stm.setInt(4, bb.getProv().getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Loi");
        }

    }

    public void update(BillBuy bb) {
        try {
            String sql = "UPDATE [projectWeb].[dbo].[listBillBuy]\n"
                    + "   SET [DateInput] = ?\n"
                    + "      ,[AmountOwed] = ?\n"
                    + "      ,[ProviderID] = ?\n"
                    + " WHERE id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDate(1, bb.getDateinput());
            stm.setInt(2, bb.getAmountowed());
            stm.setInt(3, bb.getProvider_id());
            stm.setInt(4, bb.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BillBuyDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
