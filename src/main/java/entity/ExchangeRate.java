/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Diana
 */
@Entity(name = "EXCHANGE_RATE")
public class ExchangeRate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String currency;
    
    Double rate;
    String date;
    List<ExchangeRate> rates;

    public ExchangeRate() {
    }

    public ExchangeRate(String currency, Double rate, String date) {
        this.currency = currency;
        this.rate = rate;
        this.date = date;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ExchangeRate> getRates() {
        return rates;
    }


}
