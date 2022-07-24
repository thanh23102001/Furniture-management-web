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
import model.BillSell;
import model.Buyer;
import model.machineBuy;
import model.machineSell;

/**
 *
 * @author ADMIN
 */
public class machineSellDAO extends DBContext {

    public ArrayList<machineSell> getSellbySearcch(String seri_search, int buyer_id, Date dateFrom, Date dateTo) {
        ArrayList<machineSell> sell = new ArrayList<>();
        try {
            String sql = "select ls.seri, lb.nameMachine, lb.model,ls.price,ls.Unit,lbs.DateSell, ls.newness, b.name as buyer from listSell ls\n"
                    + "                                        join listBillSell lbs on ls.IDBillSell = lbs.id\n"
                    + "                                        join Buyer b on b.id = lbs.Buyer\n"
                    + "                                        join listBuy lb on lb.Seri = ls.Seri";
            if (!seri_search.equals("")) {
                sql += "\n where ls.seri = ?";
            }
            if (buyer_id != 0) {
                if (seri_search.equals("")) {
                    sql += "\n where b.id = ?";
                } else {
                    sql += " and b.id = ?";
                }
            }
            if (seri_search.equals("") && buyer_id == 0) {
                sql += "\n where lbs.DateSell between ? and ?";
            } else {
                sql += " and lbs.DateSell between ? and ?";
            }
            sql += "\n order by lbs.DateSell desc";
            PreparedStatement stm = connection.prepareStatement(sql);
            if (!seri_search.equals("")) {
                stm.setString(1, seri_search);
            }
            if (buyer_id != 0) {
                if (seri_search.equals("")) {
                    stm.setInt(1, buyer_id);
                } else {
                    stm.setInt(2, buyer_id);
                }
            }
            if (seri_search.equals("") && buyer_id == 0) {
                stm.setDate(1, dateFrom);
                stm.setDate(2, dateTo);
            } else if (!seri_search.equals("") || buyer_id != 0) {
                if (!seri_search.equals("") && buyer_id != 0) {
                    stm.setDate(3, dateFrom);
                    stm.setDate(4, dateTo);
                } else {
                    stm.setDate(2, dateFrom);
                    stm.setDate(3, dateTo);
                }
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                machineSell ms = new machineSell();
                ms.setSeri(rs.getString("seri"));
                ms.setPrice(rs.getInt("price"));
                ms.setNewness(rs.getInt("newness"));
                ms.setName(rs.getString("nameMachine"));
                ms.setModel(rs.getString("model"));
                ms.setUnit(rs.getBoolean("Unit"));
                Buyer b = new Buyer();
                b.setName(rs.getString("buyer"));
                BillSell bs = new BillSell();
                bs.setBuyer(b);
                bs.setDatesell(rs.getDate("DateSell"));
                ms.setBillsell(bs);
                sell.add(ms);
            }
        } catch (SQLException ex) {
        }
        return sell;
    }

    public machineSell getSellbySeri(String seri) {
        try {
            String sql = "SELECT [IDBillSell]\n"
                    + "      ,[Seri]\n"
                    + "      ,[Unit]\n"
                    + "      ,[Price]\n"
                    + "      ,[Newness]\n"
                    + "  FROM [projectWeb].[dbo].[listSell]\n"
                    + "  where Seri = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, seri);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                machineSell ms = new machineSell();
                ms.setBillsell_id(rs.getInt("IDBillSell"));
                ms.setSeri(rs.getString("Seri"));
                ms.setUnit(rs.getBoolean("Unit"));
                ms.setPrice(rs.getInt("Price"));
                ms.setNewness(rs.getInt("Newness"));
                return ms;
            }
        } catch (Exception ex) {
        }
        return null;
    }

    public ArrayList<machineSell> getSellbyBill(String bill_id) {
        ArrayList<machineSell> msl = new ArrayList<>();
        try {
            String sql = "select ls.Seri, lb.nameMachine, lb.model,ls.Price, ls.Unit,ls.Newness from listSell ls\n"
                    + "                    join listBillSell lbs on ls.IDBillSell = lbs.id\n"
                    + "                    join listBuy lb on ls.Seri = lb.Seri\n"
                    + "where lbs.id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, bill_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                machineSell ms = new machineSell();
                ms.setSeri(rs.getString("Seri"));
                ms.setName(rs.getString("nameMachine"));
                ms.setModel(rs.getString("model"));
                ms.setUnit(rs.getBoolean("Unit"));
                ms.setPrice(rs.getInt("Price"));
                ms.setNewness(rs.getInt("Newness"));
                msl.add(ms);
            }
        } catch (Exception ex) {
        }
        return msl;
    }

    public void insert(machineSell ms) {
        try {
            String sql = "INSERT INTO [projectWeb].[dbo].[listSell]\n"
                    + "           ([IDBillSell]\n"
                    + "           ,[Seri]\n"
                    + "           ,[Unit]\n"
                    + "           ,[Price]\n"
                    + "           ,[Newness])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, ms.getBillsell_id());
            stm.setString(2, ms.getSeri());
            stm.setBoolean(3, ms.isUnit());
            stm.setInt(4, ms.getPrice());
            stm.setInt(5, ms.getNewness());
            stm.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    public void updatewh(String seri) {
        try {
            String sql = "DELETE FROM [dbo].[warehouse] \n"
                    + "      WHERE Seri = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, seri);
            stm.executeUpdate();
        } catch (Exception ex) {
        }
    }

    public void insertwh(String seri) {
        try {
            String sql = "INSERT INTO [dbo].[warehouse]\n"
                    + "           ([Seri])\n"
                    + "     VALUES\n"
                    + "           (?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, seri);
            stm.executeUpdate();
        } catch (Exception ex) {
        }
    }

    public void update(machineSell ms, String seri) {
        try {
            String sql = "UPDATE [projectWeb].[dbo].[listSell]\n"
                    + "   SET [IDBillSell] = ?\n"
                    + "      ,[Unit] = ?\n"
                    + "      ,[Price] = ?\n"
                    + "      ,[Newness] = ?\n"
                    + " WHERE Seri = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, ms.getBillsell_id());
            stm.setBoolean(2, ms.isUnit());
            stm.setInt(3, ms.getPrice());
            stm.setInt(4, ms.getNewness());
            stm.setString(5, seri);
            stm.executeUpdate();
        } catch (Exception ex) {
        }
    }

    public void delete(String seri) {
        try {
            String sql = "DELETE FROM [projectWeb].[dbo].[listSell]\n"
                    + "      WHERE Seri = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, seri);
            stm.executeUpdate();
        } catch (Exception ex) {

        }
    }
}
