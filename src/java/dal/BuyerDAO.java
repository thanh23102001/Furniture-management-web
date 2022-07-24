/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Buyer;
import model.Providers;

/**
 *
 * 
 */
public class BuyerDAO extends DBContext {

    public ArrayList<Buyer> getBuyer() {
        ArrayList<Buyer> buyer = new ArrayList<>();
        try {
            String sql = "select * from buyer";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Buyer b = new Buyer();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setAddress(rs.getString("address"));
                buyer.add(b);
            }
        } catch (SQLException ex) {
        }
        return buyer;
    }

    public ArrayList<Buyer> getBuyerbyName(String name) {
        ArrayList<Buyer> buyer = new ArrayList<>();
        try {
            String sql = "select table2.id, table2.name, table2.[Address], table1.sum as debt from (select lbs.Buyer, sum(lbs.AmountNotReceived) as sum from listBillSell lbs\n"
                    + "group by lbs.Buyer) as table1\n"
                    + "right join (select b.id, b.name, b.[Address] from Buyer b \n"
                    + "left join listBillSell lbs on b.id = lbs.Buyer\n"
                    + "group by b.id, b.name, b.[Address]) as table2\n"
                    + "on table1.Buyer = table2.id";
            if (!name.equals("")) {
                sql += "\n where table2.name like ?";
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            if (!name.equals("")) {
                stm.setString(1, "%" + name + "%");
            }
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Buyer be = new Buyer();
                be.setId(rs.getInt("id"));
                be.setName(rs.getString("name"));
                be.setAddress(rs.getString("address"));
                be.setDebt(rs.getInt("debt"));
                buyer.add(be);
            }
        } catch (SQLException ex) {
        }
        return buyer;
    }

    public void delete(String id) {
        try {
            String sql = "DELETE FROM [projectWeb].[dbo].[Buyer]\n"
                    + " WHERE id = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            stm.executeQuery();
        } catch (Exception ex) {

        }
    }

    public void insert(Buyer be) {
        try {
            String sql = "insert into Buyer (name, address) values (?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, be.getName());
            stm.setString(2, be.getAddress());
            stm.executeQuery();
        } catch (Exception ex) {
        }
    }
}
