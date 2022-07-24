/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.requestGroup;

/**
 *
 *  @author ADMIN
 */
public class requestDAO extends DBContext {

    public ArrayList<requestGroup> getallRequest() {
        ArrayList<requestGroup> rg = new ArrayList<>();
        try {
            String sql = "select r.id, g.id as group_id, r.content, r.[status] from request r\n"
                    + "join [group] g on r.leaderRequest = g.leader\n"
                    + "order by [status] asc";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                requestGroup rq = new requestGroup();
                rq.setId(rs.getInt("id"));
                rq.setId_group(rs.getInt("group_id"));
                rq.setContent(rs.getString("content"));
                rq.setStatus(rs.getInt("status"));
                rg.add(rq);
            }
        } catch (Exception e) {
        }
        return rg;
    }

    public ArrayList<requestGroup> getRequestbyUsername(String username) {
        ArrayList<requestGroup> rg = new ArrayList<>();
        try {
            String sql = "select * from request\n"
                    + "where leaderRequest = ? \n"
                    + "order by [status] asc";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                requestGroup rgr = new requestGroup();
                rgr.setId(rs.getInt("id"));
                rgr.setContent(rs.getString("content"));
                rgr.setStatus(rs.getInt("status"));
                rg.add(rgr);
            }
        } catch (Exception e) {
        }
        return rg;
    }

    public void insert(String username, String content) {
        try {
            String sql = "INSERT INTO [projectWeb].[dbo].[request]\n"
                    + "           ([leaderRequest]\n"
                    + "           ,[content]\n"
                    + "           ,[status])\n"
                    + "     VALUES (?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, content);
            stm.setInt(3, 0);
            stm.executeQuery();
        } catch (Exception e) {
        }
    }

    public void update(String id, String status) {
        try {
            String sql = "UPDATE [projectWeb].[dbo].[request]\n"
                    + "   SET [status] = ?\n"
                    + " WHERE id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, status);
            stm.setString(2, id);
            stm.execute();
        } catch (Exception e) {
        }
    }
}
