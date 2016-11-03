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
import java.util.List;
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
    @Path("/allUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsers() {
        List<User> users = uf.getUsers();
        return gson.toJson(users);
    }

    @POST
    @Path("/signUp")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addUser(String userJsonStr) throws PasswordStorage.CannotPerformOperationException {
//        JSONObject data = (JSONObject) JSONValue.parse(content);
//        String userName = (String) data.get("userName");
//        String password = (String) data.get("password");
//        entity.User u = new User(userName, password);

        User u = gson.fromJson(userJsonStr, User.class);
        User newUser =  (User) uf.addUser(userJsonStr, userJsonStr);
        String jsonResult = gson.toJson(newUser);
        return jsonResult;
    }

}
