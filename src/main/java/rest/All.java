/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.User;
import facades.UserFacade;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import static rest.UserService.gson;
import security.IUserFacade;
import security.PasswordStorage;

/**
 * REST Web Service
 *
 * @author plaul1
 */
@Path("all")
public class All {

  @Context
  private UriInfo context;

  static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    static IUserFacade uf = new UserFacade(Persistence.createEntityManagerFactory("pu_development"));
  
  
  /**
   * Creates a new instance of A
   */
  public All() {
  }

  /**
   * Retrieves representation of an instance of rest.All
   * @return an instance of java.lang.String
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String getText() {
    System.out.println("XXXXXXXX---> "+System.getProperty("java.version"));
    return " {\"message\" : \"result for all\"}";
  }

    @POST
    @Path("/signUp")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addUser(String userJsonStr) throws PasswordStorage.CannotPerformOperationException {
        
        JSONObject data = (JSONObject) JSONValue.parse(userJsonStr);
        String userName = data.get("userName").toString();
        String password = (String) data.get("password");
        entity.User u = new User(userName, password);
        uf.addUser(userName, password);
//        User u = gson.fromJson(userJsonStr, User.class);
//        User newUser =  (User) uf.addUser(userJsonStr, userJsonStr);
//        String jsonResult = gson.toJson(newUser);
        return userJsonStr;
    }
  
}
