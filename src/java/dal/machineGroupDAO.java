/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.Group;
import model.machineGroup;

/**
 *
 *  @author ADMIN
 */
public class machineGroupDAO extends DBContext {
    
    public ArrayList<machineGroup> getMachineinGroup(String username, String seri) {
        ArrayList<machineGroup> mg = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        try {
            String sql = "select mg.machineseri, lb.nameMachine, lb.model,mg.dateStart, DATEDIFF(day, mg.dateStart, '" + dtf.format(now) + "') AS UsedDate from machine_group mg\n"
                    + "join listBuy lb on lb.Seri = mg.machineseri\n"
                    + "join [group] g on g.id = mg.id\n"
                    + "where g.leader = ?";
            if (!seri.equals("")) {
                sql += " and mg.machineseri = ? ";
            }
            sql += "\n order by mg.dateStart desc";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            if (!seri.equals("")) {
                stm.setString(2, seri);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                machineGroup mgl = new machineGroup();
                mgl.setMachineSeri(rs.getString("machineseri"));
                mgl.setNameMachine(rs.getString("namemachine"));
                mgl.setModel(rs.getString("model"));
                mgl.setDateStart(rs.getDate("dateStart"));
                mgl.setDateUsed(rs.getInt("UsedDate"));
                mg.add(mgl);
            }
        } catch (Exception e) {
        }
        return mg;
    }
    
    public int getGroup(String username) {
        try {
            String sql = "select id from [group]\n"
                    + "where leader = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                return id;
            }
        } catch (Exception e) {
        }
        return 0;
    }
    
    public void insert(String id, String seri, Date date) {
        try {
            String sql = "INSERT INTO [projectWeb].[dbo].[machine_group]\n"
                    + "           ([id]\n"
                    + "           ,[machineSeri]\n"
                    + "           ,[dateStart])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            stm.setString(2, seri);
            stm.setDate(3, date);
            stm.executeQuery();
        } catch (Exception e) {
        }
    }
    
    public void delete(String seri) {
        try {
            String sql = "DELETE FROM [projectWeb].[dbo].[machine_group]\n"
                    + "      WHERE machineSeri = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, seri);
            stm.executeQuery();
        } catch (Exception e) {
        }
    }
    
    public ArrayList<Group> getallGroup() {
        ArrayList<Group> group = new ArrayList<>();
        try {
            String sql = "select * from [group]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Group g = new Group();
                g.setId(rs.getInt("id"));
                g.setLeader(rs.getString("leader"));
                group.add(g);
            }
        } catch (Exception e) {
        }
        return group;
    }
    
    public ArrayList<machineGroup> getMachinebyId(String id, String seri) {
        ArrayList<machineGroup> mg = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        try {
            String sql = "select mg.machineSeri,lb.nameMachine,lb.model, mg.dateStart, DATEDIFF(day, datestart, '" + dtf.format(now) + "') as UsedDay  from machine_group mg\n"
                    + "join listBuy lb on mg.machineSeri = lb.Seri\n"
                    + "where mg.id = ? \n";
            if (!seri.equals("")) {
                sql += " and mg.machineSeri = ?";
            }
            sql += " order by UsedDay asc";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            if (!seri.equals("")) {
                stm.setString(2, seri);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                machineGroup mgl = new machineGroup();
                mgl.setMachineSeri(rs.getString("machineSeri"));
                mgl.setNameMachine(rs.getString("nameMachine"));
                mgl.setModel(rs.getString("model"));
                mgl.setDateStart(rs.getDate("dateStart"));
                mgl.setDateUsed(rs.getInt("UsedDay"));
                mg.add(mgl);
            }
            
        } catch (Exception e) {
        }
        return mg;
    }
}
