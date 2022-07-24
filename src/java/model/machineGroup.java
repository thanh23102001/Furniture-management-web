/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 *  @author ADMIN
 */
public class machineGroup {

    String machineSeri;
    String nameMachine;
    String model;
    Date dateStart;
    int dateUsed;

    public machineGroup() {
    }

    public machineGroup(String machineSeri, String nameMachine, String model, Date dateStart, int dateUsed) {
        this.machineSeri = machineSeri;
        this.nameMachine = nameMachine;
        this.model = model;
        this.dateStart = dateStart;
        this.dateUsed = dateUsed;
    }

    public String getMachineSeri() {
        return machineSeri;
    }

    public void setMachineSeri(String machineSeri) {
        this.machineSeri = machineSeri;
    }

    public String getNameMachine() {
        return nameMachine;
    }

    public void setNameMachine(String nameMachine) {
        this.nameMachine = nameMachine;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public int getDateUsed() {
        return dateUsed;
    }

    public void setDateUsed(int dateUsed) {
        this.dateUsed = dateUsed;
    }

}
