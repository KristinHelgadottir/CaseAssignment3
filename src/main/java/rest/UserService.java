package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.UserFacade;
import javax.annotation.security.RolesAllowed;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import entity.User;
import security.IUserFacade;
import security.PasswordStorage;

@Path("demouser")
@RolesAllowed("User")
public class UserService {

    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    static IUserFacade uf = new UserFacade(Persistence.createEntityManagerFactory("pu_development"));
    
    public UserService() {
    }
  
    
    
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String getSomething(){
    return "{\"message\" : \"REST call accesible by only authenticated USERS\"}"; 
  }
 
  @POST
  @Path("/login")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public void addNewUser(String content) throws PasswordStorage.CannotPerformOperationException {
  
      JSONObject data = (JSONObject)JSONValue.parse(content);
      String userName = (String)data.get("userName");
      String password = (String)data.get("password");
      
      entity.User u = new User(userName, password);      
      
  }
  
}