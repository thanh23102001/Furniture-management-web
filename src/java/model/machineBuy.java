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
public class machineBuy {
    String seri;
    int price;
    int newness;
    BillBuy bb;
    int billbuy_id;
    String name;
    String model;
    int depreciation;

    public int getDepreciation() {
        return depreciation;
    }

    public void setDepreciation(int depreciation) {
        this.depreciation = depreciation;
    }

    public int getBillbuy_id() {
        return billbuy_id;
    }

    public void setBillbuy_id(int billbuy_id) {
        this.billbuy_id = billbuy_id;
    }

    public String getSeri() {
        return seri;
    }

    public void setSeri(String seri) {
        this.seri = seri;
    }

    public int getNewness() {
        return newness;
    }

    public void setNewness(int newness) {
        this.newness = newness;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public BillBuy getBb() {
        return bb;
    }

    public void setBb(BillBuy bb) {
        this.bb = bb;
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

}
