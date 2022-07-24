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
public class WareHouse {

    machineBuy machinebuy;
    String seri;
    int usedDate;

    public String getSeri() {
        return seri;
    }

    public void setSeri(String seri) {
        this.seri = seri;
    }

    public WareHouse(machineBuy machinebuy, String seri, int usedDate) {
        this.machinebuy = machinebuy;
        this.seri = seri;
        this.usedDate = usedDate;
    }
    
        public WareHouse(String seri) {
        this.seri = seri;
    }

    public WareHouse() {
    }

    public machineBuy getMachinebuy() {
        return machinebuy;
    }

    public void setMachinebuy(machineBuy machinebuy) {
        this.machinebuy = machinebuy;
    }

    public int getUsedDate() {
        return usedDate;
    }

    public void setUsedDate(int usedDate) {
        this.usedDate = usedDate;
    }

}
