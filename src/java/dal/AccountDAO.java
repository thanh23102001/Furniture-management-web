/*
/**
 *
 * 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.management.relation.Role;
import model.Account;
import model.RoleAccount;

/**
 *
 *  @author ADMIN
 */
public class AccountDAO extends DBContext {

    public Account getLogin(String username, String password) {
        try {
            String sql = "select a.username, a.[password], ra.rid from account a\n"
                    + "join roleaccount ra on ra.username = a.username\n"
                    + "where a.username = ? and a.[password] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Account ac = new Account();
                ac.setUsername(rs.getString("username"));
                ac.setPassword(rs.getString("password"));
                ac.setRoleac(rs.getInt("rid"));
                return ac;
            }
        } catch (Exception ex) {

        }
        return null;
    }
}
