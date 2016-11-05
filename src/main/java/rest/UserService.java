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
import entity.User;
import java.util.List;
import security.IUserFacade;
import security.PasswordStorage;

@Path("user")
@RolesAllowed("User")
public class UserService {

    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    static IUserFacade uf = new UserFacade(Persistence.createEntityManagerFactory("pu_development"));

    public UserService() {
    }

    

}
