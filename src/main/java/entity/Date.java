package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Entity(name = "CURRENT_DATE")
public class Date implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String dateId;
  
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Country")
    List<Country> countries;
    
    public Date() {
    }

    public Date(String dateId, List<Country> countries) {
        this.dateId = dateId;
        this.countries = countries;
    }

    public String getDateId() {
        return dateId;
    }

    public void setDateId(String dateId) {
        this.dateId = dateId;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
   
    
}
