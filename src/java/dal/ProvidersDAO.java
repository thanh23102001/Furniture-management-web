/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.security.Provider;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Providers;

/**
 *
 *  @author ADMIN
 */
public class ProvidersDAO extends DBContext {

    public ArrayList<Providers> getallProvider() {
        ArrayList<Providers> provider = new ArrayList<>();
        try {
            String sql = "select * from provider";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Providers pv = new Providers();
                pv.setId(rs.getInt("id"));
                pv.setName(rs.getString("name"));
                pv.setAddress(rs.getString("address"));
                provider.add(pv);
            }
        } catch (SQLException ex) {
        }
        return provider;
    }

    public ArrayList<Providers> getProviderbyName(String name) {
        ArrayList<Providers> provider = new ArrayList<>();
        try {
            String sql = "select table2.id, table2.name, table2.[Address], table1.sum as debt from (select lbs.ProviderID, sum(lbs.AmountOwed) as sum from listBillBuy lbs\n"
                    + "group by lbs.ProviderID) as table1\n"
                    + "right join (select b.id, b.name, b.[Address] from Provider b \n"
                    + "left join listBillBuy lbs on b.id = lbs.ProviderID\n"
                    + "group by b.id, b.name, b.[Address]) as table2\n"
                    + "on table1.ProviderID = table2.id";
            if (!name.equals("")) {
                sql += "\n where table2.name like ?";
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            if (!name.equals("")) {
                stm.setString(1, "%" + name + "%");
            }
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Providers pv = new Providers();
                pv.setId(rs.getInt("id"));
                pv.setName(rs.getString("name"));
                pv.setAddress(rs.getString("address"));
                pv.setDebt(rs.getInt("debt"));
                provider.add(pv);
            }
        } catch (SQLException ex) {
        }
        return provider;
    }

    public void insert(Providers pv) {
        try {
            String sql = "insert into provider(name,address) values(?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, pv.getName());
            stm.setString(2, pv.getAddress());
            stm.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    public void delete(String id) {
        try {
            String sql = "DELETE FROM [projectWeb].[dbo].[Provider]\n"
                    + "      WHERE id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            stm.executeUpdate();
        } catch (Exception ex) {
        }
    }
}
