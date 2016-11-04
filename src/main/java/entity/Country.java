package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity(name = "Country")
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @JoinColumn(name = "countryId", referencedColumnName = "countries")
    @ManyToOne(optional = false)
    private String countryId;
    
    private Double rate;
    private String currency;

    public Country() {
    }

    public Country(String countryId, Double rate, String currency) {
        this.countryId = countryId;
        this.rate = rate;
        this.currency = currency;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
    

  

   
}
