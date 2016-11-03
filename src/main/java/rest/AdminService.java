package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.User;
import facades.UserFacade;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import security.IUserFacade;

@Path("admin")
@RolesAllowed("Admin")
public class AdminService {

    static IUserFacade facade = new UserFacade(Persistence.createEntityManagerFactory("pu_development"));
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public AdminService() {
    }

    
    @GET
    @Path("users")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsers() {
        List<User> users = facade.getUsers();
        return gson.toJson(users);
    }
    
    @DELETE
    @Path("user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User deleteUserByName(@PathParam("userName") String userName){
        return facade.deleteUser(userName);
    }

}
