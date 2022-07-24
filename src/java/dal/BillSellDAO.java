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
import model.BillSell;
import model.Buyer;
import model.Providers;

/**
 *
 *  @author ADMIN
 */
public class BillSellDAO extends DBContext {

    public ArrayList<BillSell> getallBillSell() {
        ArrayList<BillSell> billsell = new ArrayList<>();
        try {
            String sql = "select id, DateSell, AmountNotReceived as AmountOwed, Buyer from listBillSell";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                BillSell bs = new BillSell();
                bs.setId(rs.getInt("id"));
                bs.setDatesell(rs.getDate("DateSell"));
                bs.setAmountowed(rs.getInt("AmountOwed"));
                bs.setBuyerid(rs.getInt("Buyer"));
                billsell.add(bs);
            }
        } catch (SQLException ex) {
        }
        return billsell;
    }

    public ArrayList<BillSell> getBillSellbySearch(String bill_search, int buyer_id, Date dateFrom, Date dateTo) {
        ArrayList<BillSell> bsell = new ArrayList<>();
        try {
            String sql = "select table1.*, table2.DateSell,table2.AmountNotReceived,table2.Name from (select lbs.id, SUM(ls.Price) as Total_money, Count(ls.Seri) as quantity from listBillSell lbs\n"
                    + "                                                            left join listSell ls on ls.IDBillSell = lbs.id\n"
                    + "                                                            group by lbs.id) as table1\n"
                    + "                                                            JOIN (select lbs.id, lbs.DateSell, lbs.AmountNotReceived, b.name, b.id as bid from listBillSell lbs\n"
                    + "                                                            join Buyer b on b.id = lbs.Buyer) as table2 ON table1.id = table2.id";
            if (!bill_search.equals("")) {
                sql += "\n where table1.id = ?";
            }
            if (buyer_id != 0) {
                if (bill_search.equals("")) {
                    sql += "\n where table2.bid = ?";
                } else {
                    sql += " and table2.bid = ?";
                }
            }
            if (bill_search.equals("") && buyer_id == 0) {
                sql += "\n where table2.DateSell between ? and ?";
            } else {
                sql += " and table2.DateSell between ? and ?";
            }
            sql += "\n order by table2.DateSell desc";
            PreparedStatement stm = connection.prepareStatement(sql);
            if (!bill_search.equals("")) {
                stm.setString(1, bill_search);
            }
            if (buyer_id != 0) {
                if (bill_search.equals("")) {
                    stm.setInt(1, buyer_id);
                } else {
                    stm.setInt(2, buyer_id);
                }
            }
            if (bill_search.equals("") && buyer_id == 0) {
                stm.setDate(1, dateFrom);
                stm.setDate(2, dateTo);
            } else if (!bill_search.equals("") || buyer_id != 0) {
                if (!bill_search.equals("") && buyer_id != 0) {
                    stm.setDate(3, dateFrom);
                    stm.setDate(4, dateTo);
                } else {
                    stm.setDate(2, dateFrom);
                    stm.setDate(3, dateTo);
                }
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Buyer be = new Buyer();
                be.setName(rs.getString("name"));
                BillSell bs = new BillSell();
                bs.setTotal_money(rs.getInt("total_money"));
                bs.setQuantity(rs.getInt("quantity"));
                bs.setId(rs.getInt("id"));
                bs.setDatesell(rs.getDate("DateSell"));
                bs.setAmountowed(rs.getInt("AmountNotReceived"));
                bs.setBuyer(be);
                bsell.add(bs);
            }
        } catch (SQLException ex) {
        }
        return bsell;
    }

    public BillSell getBillsellbyId(int bill_id) {
        try {
            String sql = "select * from listBillSell\n"
                    + "where id = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, bill_id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                BillSell bs = new BillSell();
                bs.setId(rs.getInt("id"));
                bs.setDatesell(rs.getDate("DateSell"));
                bs.setAmountowed(rs.getInt("AmountNotReceived"));
                bs.setBuyerid(rs.getInt("Buyer"));
                return bs;
            }
        } catch (Exception ex) {

        }
        return null;
    }

    public void insert(BillSell bs) {
        try {
            String sql = "INSERT INTO [projectWeb].[dbo].[listBillSell]\n"
                    + "           ([id]\n"
                    + "           ,[DateSell]\n"
                    + "           ,[AmountNotReceived]\n"
                    + "           ,[Buyer])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, bs.getId());
            stm.setDate(2, bs.getDatesell());
            stm.setInt(3, bs.getAmountowed());
            stm.setInt(4, bs.getBuyer().getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Loi");
        }
    }

    public void update(BillSell bs) {
        try {
            String sql = "UPDATE [projectWeb].[dbo].[listBillSell]\n"
                    + "   SET [DateSell] = ?\n"
                    + "      ,[AmountNotReceived] = ?\n"
                    + "      ,[Buyer] = ?\n"
                    + " WHERE id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDate(1, bs.getDatesell());
            stm.setInt(2, bs.getAmountowed());
            stm.setInt(3, bs.getBuyerid());
            stm.setInt(4, bs.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BillBuyDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
