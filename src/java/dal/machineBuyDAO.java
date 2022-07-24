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
 * @author ADMIN
 */
public class machineBuyDAO extends DBContext {

    public ArrayList<machineBuy> getBuybySeri(int bill_search, String seri_search, int provider_id, Date dateFrom, Date dateTo) {
        ArrayList<machineBuy> buy = new ArrayList<>();
        try {
            String sql = "select l.seri,l.nameMachine,l.IDBillBuy, l.model,l.price, lb.dateInput,l.newness, pv.Name as pname from listBuy l\n"
                    + "                    join listBillBuy lb on l.IDBillBuy = lb.id\n"
                    + "                    join Provider pv on pv.id = lb.ProviderID";
            if (!seri_search.equals("")) {
                sql += "\n where l.seri = ?";
            }
            if (provider_id != 0) {
                if (seri_search.equals("")) {
                    sql += "\n where pv.id = ?";
                } else {
                    sql += " and pv.id = ?";
                }
            }
            if (seri_search.equals("") && provider_id == 0) {
                sql += "\n where lb.DateInput between ? and ?";
            } else {
                sql += " and lb.DateInput between ? and ?";
            }
            if (bill_search != 0) {
                sql += " and lb.id = ?";
            }
            sql += "\n order by lb.DateInput desc";
            PreparedStatement stm = connection.prepareStatement(sql);
            if (!seri_search.equals("")) {
                stm.setString(1, seri_search);
            }
            if (provider_id != 0) {
                if (seri_search.equals("")) {
                    stm.setInt(1, provider_id);
                } else {
                    stm.setInt(2, provider_id);
                }
            }
            if (seri_search.equals("") && provider_id == 0) {
                stm.setDate(1, dateFrom);
                stm.setDate(2, dateTo);
            } else if (!seri_search.equals("") || provider_id != 0) {
                if (!seri_search.equals("") && provider_id != 0) {
                    stm.setDate(3, dateFrom);
                    stm.setDate(4, dateTo);
                } else {
                    stm.setDate(2, dateFrom);
                    stm.setDate(3, dateTo);
                }
            }
            if (bill_search != 0) {
                if (seri_search.equals("") && provider_id == 0) {
                    stm.setInt(3, bill_search);
                } else if (!seri_search.equals("") || provider_id != 0) {
                    if (!seri_search.equals("") && provider_id != 0) {
                        stm.setInt(5, bill_search);
                    } else {
                        stm.setInt(3, bill_search);
                    }
                }
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Providers pv = new Providers();
                pv.setName(rs.getString("pname"));
                machineBuy d = new machineBuy();
                d.setName(rs.getString("nameMachine"));
                d.setModel(rs.getString("model"));
                d.setSeri(rs.getString("seri"));
                d.setPrice(rs.getInt("price"));
                d.setNewness(rs.getInt("newness"));
                BillBuy bb = new BillBuy();
                bb.setId(rs.getInt("idbillbuy"));
                bb.setDateinput(rs.getDate("dateInput"));
                bb.setProv(pv);
                d.setBb(bb);
                buy.add(d);
            }
        } catch (SQLException ex) {
        }
        return buy;
    }
//    public ArrayList<machineBuy> getBuybySeri(int pageindex, int pagesize, int bill_search, String seri_search, String provider_name, Date dateFrom, Date dateTo) {
//        ArrayList<machineBuy> buy = new ArrayList<>();
//        try {
//            String sql = "select seri, namemachine, idbillbuy, model, price,dateinput, newness, pname from (select ROW_NUMBER() OVER (ORDER BY l.seri ASC) as 'stt', l.seri,l.nameMachine,l.IDBillBuy, l.model,l.price, lb.dateInput,l.newness, pv.Name as pname from listBuy l\n"
//                    + "                                        join listBillBuy lb on l.IDBillBuy = lb.id\n"
//                    + "                                        join Provider pv on pv.id = lb.ProviderID) as tbl\n"
//                    + "                                        where stt >= (?-1)*? + 1\n"
//                    + "                                        and stt <= ? * ?";
//            if (!seri_search.equals("")) {
//                sql += "\n and seri = ?";
//            }
//            if (!provider_name.equals("")) {
//                sql += " and pname = ?";
//            }
//            sql += " and lb.DateInput between ? and ?";
//            if (bill_search != 0) {
//                sql += " and idbillbuy = ?";
//            }
//            sql += "\n order by DateInput desc";
//            PreparedStatement stm = connection.prepareStatement(sql);
//            stm.setInt(1, pageindex);
//            stm.setInt(2, pagesize);
//            stm.setInt(3, pagesize);
//            stm.setInt(4, pageindex);
//            if (!seri_search.equals("")) {
//                stm.setString(5, seri_search);
//            }
//            if (!provider_name.equals("")) {
//                if (seri_search.equals("")) {
//                    stm.setString(5, provider_name);
//                } else {
//                    stm.setString(6, provider_name);
//                }
//            }
//            if (seri_search.equals("") && provider_name.equals("")) {
//                stm.setDate(5, dateFrom);
//                stm.setDate(6, dateTo);
//            } else if (!seri_search.equals("") || !provider_name.equals("")) {
//                if (!seri_search.equals("") && !provider_name.equals("")) {
//                    stm.setDate(7, dateFrom);
//                    stm.setDate(8, dateTo);
//                } else {
//                    stm.setDate(6, dateFrom);
//                    stm.setDate(7, dateTo);
//                }
//            }
//            if (bill_search != 0) {
//                if (seri_search.equals("") && provider_name.equals("")) {
//                    stm.setInt(7, bill_search);
//                } else if (!seri_search.equals("") || !provider_name.equals("")) {
//                    if (!seri_search.equals("") && !provider_name.equals("")) {
//                        stm.setInt(9, bill_search);
//                    } else {
//                        stm.setInt(8, bill_search);
//                    }
//                }
//            }
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                machineBuy d = new machineBuy();
//                Providers pv = new Providers();
//                BillBuy bb = new BillBuy();
//                d.setSeri(rs.getString("seri"));
//                d.setName(rs.getString("namemachine"));
//                bb.setId(rs.getInt("idbillbuy"));
//                d.setModel(rs.getString("model"));
//                d.setPrice(rs.getInt("price"));
//                bb.setDateinput(rs.getDate("dateInput"));
//                d.setNewness(rs.getInt("newness"));
//                pv.setName(rs.getString("pname"));
//                bb.setProv(pv);
//                d.setBb(bb);
//                buy.add(d);
//            }
//        } catch (SQLException ex) {
//        }
//        return buy;
//    }
//    
//    public int count()
//    {
//        String sql = "select COUNT(*) as total from listBuy";
//        try {
//            PreparedStatement stm = connection.prepareStatement(sql);
//            ResultSet rs = stm.executeQuery();
//            if(rs.next())
//            {
//                return rs.getInt("total");
//            }
//            
//        } catch (SQLException ex) {
//        }
//        return 0;
//    }

    public machineBuy getBuybySeri(String seri) {
        try {
            String sql = "SELECT [IDBillBuy]\n"
                    + "      ,[Seri]\n"
                    + "      ,[nameMachine]\n"
                    + "      ,[model]\n"
                    + "      ,[Price]\n"
                    + "      ,[Newness]\n"
                    + "      ,[DepreciationPeriod]\n"
                    + "  FROM [projectWeb].[dbo].[listBuy]\n"
                    + "where Seri = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, seri);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                machineBuy mb = new machineBuy();
                mb.setBillbuy_id(rs.getInt("IDBillBuy"));
                mb.setSeri(rs.getString("Seri"));
                mb.setName(rs.getString("nameMachine"));
                mb.setModel(rs.getString("model"));
                mb.setPrice(rs.getInt("Price"));
                mb.setNewness(rs.getInt("Newness"));
                mb.setDepreciation(rs.getInt("DepreciationPeriod"));
                return mb;
            }
        } catch (Exception ex) {
        }
        return null;
    }

    public ArrayList<machineBuy> getBuybyBill(int bill_id, String seri) {
        ArrayList<machineBuy> buy = new ArrayList<>();
        try {
            String sql = "SELECT [Seri],[nameMachine],[model],[Price],[Newness],[DepreciationPeriod] FROM [projectWeb].[dbo].[listBuy]\n"
                    + "where IDBillBuy = ? ";
            if (!seri.equals("")) {
                sql += " and seri = ?";
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, bill_id);
            if (!seri.equals("")) {
                stm.setString(2, seri);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                machineBuy mb = new machineBuy();
                mb.setSeri(rs.getString("Seri"));
                mb.setName(rs.getString("nameMachine"));
                mb.setModel(rs.getString("model"));
                mb.setPrice(rs.getInt("Price"));
                mb.setNewness(rs.getInt("Newness"));
                mb.setDepreciation(rs.getInt("DepreciationPeriod"));
                buy.add(mb);
            }
        } catch (Exception ex) {
        }
        return buy;
    }

    public void insert(machineBuy mb) {
        try {
            String sql = "INSERT INTO [projectWeb].[dbo].[listBuy]\n"
                    + "           ([IDBillBuy]\n"
                    + "           ,[Seri]\n"
                    + "           ,[nameMachine]\n"
                    + "           ,[model]\n"
                    + "           ,[Price]\n"
                    + "           ,[Newness]\n"
                    + "           ,[DepreciationPeriod])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, mb.getBillbuy_id());
            stm.setString(2, mb.getSeri());
            stm.setString(3, mb.getName());
            stm.setString(4, mb.getModel());
            stm.setInt(5, mb.getPrice());
            stm.setInt(6, mb.getNewness());
            stm.setInt(7, mb.getDepreciation());
            stm.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    public void insert(String seri) {
        try {
            String sql = "INSERT INTO [dbo].[warehouse] \n"
                    + "           ([Seri]) \n"
                    + "     VALUES \n"
                    + "           (?)";
            PreparedStatement stm = connection.prepareStatement(sql);
                    stm.setString(1, seri);
            stm.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    public void update(machineBuy mb, String seri) {
        try {
            String sql = "UPDATE [projectWeb].[dbo].[listBuy]\n"
                    + "   SET [IDBillBuy] = ?\n"
                    + "      ,[Seri] = ?\n"
                    + "      ,[nameMachine] = ?\n"
                    + "      ,[model] = ?\n"
                    + "      ,[Price] = ?\n"
                    + "      ,[Newness] = ?\n"
                    + "      ,[DepreciationPeriod] = ?"
                    + " WHERE Seri = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, mb.getBillbuy_id());
            stm.setString(2, mb.getSeri());
            stm.setString(3, mb.getName());
            stm.setString(4, mb.getModel());
            stm.setInt(5, mb.getPrice());
            stm.setInt(6, mb.getNewness());
            stm.setInt(7, mb.getDepreciation());
            stm.setString(8, seri);
            stm.executeUpdate();
        } catch (Exception ex) {
        }
    }

    public void delete(String seri) {
        try {
            String sql = "DELETE FROM [projectWeb].[dbo].[listBuy]\n"
                    + "      WHERE Seri = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, seri);
            stm.executeUpdate();
        } catch (Exception ex) {

        }
    }

    public boolean checkWarehouse(String seri) {
        try {
            String sql = "select * from warehouse\n"
                    + "      where Seri = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, seri);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
}
