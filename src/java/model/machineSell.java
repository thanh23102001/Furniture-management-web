/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author ADMIN
 */
public class machineSell {

    BillSell billsell;
    int billsell_id;
    String seri;
    boolean unit;
    int price;
    int newness;
    String name;
    String model;
    Date datesell;
    String name_buyer;

    public String getName_buyer() {
        return name_buyer;
    }

    public void setName_buyer(String name_buyer) {
        this.name_buyer = name_buyer;
    }

    public Date getDatesell() {
        return datesell;
    }

    public void setDatesell(Date datesell) {
        this.datesell = datesell;
    }

    public int getBillsell_id() {
        return billsell_id;
    }

    public void setBillsell_id(int billsell_id) {
        this.billsell_id = billsell_id;
    }

    public BillSell getBillsell() {
        return billsell;
    }

    public void setBillsell(BillSell billsell) {
        this.billsell = billsell;
    }

    public String getSeri() {
        return seri;
    }

    public void setSeri(String seri) {
        this.seri = seri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isUnit() {
        return unit;
    }

    public void setUnit(boolean unit) {
        this.unit = unit;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNewness() {
        return newness;
    }

    public void setNewness(int newness) {
        this.newness = newness;
    }

}
