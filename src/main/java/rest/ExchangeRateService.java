/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Country;
import facades.CountryFacade;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Diana
 */
@Path("currency")
@RolesAllowed("User")
public class CurrencyService {

    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    static CountryFacade facade = new CountryFacade(Persistence.createEntityManagerFactory("pu_development"));

    public CurrencyService() {
    }

    @GET
    @Path("/dailyrates")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_JSON)
    public String getDailyrates() {
               List<Country> rates = facade.getRates();
               return gson.toJson(rates);
    }

    @GET
    @Path("/calculator")
    @Consumes(MediaType.APPLICATION_XML)
    public String returnsCalculatedAmount() {
        return null;
    }
}
