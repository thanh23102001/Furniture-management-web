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
public class BillBuy {

    int id;
    Date dateinput;
    int amountowed;
    int provider_id;
    Providers prov;
    int sum_money;
    int quantity;

    public BillBuy() {
    }

    
    
    public BillBuy(int id, Date dateinput, int amountowed, int provider_id, Providers prov) {
        this.id = id;
        this.dateinput = dateinput;
        this.amountowed = amountowed;
        this.provider_id = provider_id;
        this.prov = prov;
    }

    public int getSum_money() {
        return sum_money;
    }

    public void setSum_money(int sum_money) {
        this.sum_money = sum_money;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public int getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(int provider_id) {
        this.provider_id = provider_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateinput() {
        return dateinput;
    }

    public void setDateinput(Date dateinput) {
        this.dateinput = dateinput;
    }

    public int getAmountowed() {
        return amountowed;
    }

    public void setAmountowed(int amountowed) {
        this.amountowed = amountowed;
    }

    public Providers getProv() {
        return prov;
    }

    public void setProv(Providers prov) {
        this.prov = prov;
    }
    
    

}
