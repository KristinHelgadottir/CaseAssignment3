package facades;

import security.IUserFacade;
import entity.User;
import static entity.User_.userName;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import security.IUser;
import security.PasswordStorage;

public class UserFacade implements IUserFacade {

    EntityManagerFactory emf;

    public UserFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void addEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public IUser getUserByUserId(String userName) {
        EntityManager em = getEntityManager();
        try {
            return em.find(User.class, userName);
        } finally {
            em.close();
        }
    }

    @Override
    public List<String> authenticateUser(String userName, String password) {
        IUser user = getUserByUserId(userName);
        try {
            return user != null && PasswordStorage.verifyPassword(password, user.getPassword()) ? user.getRolesAsStrings() : null;
        } catch (PasswordStorage.CannotPerformOperationException | PasswordStorage.InvalidHashException ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    //Method for adding the new user
    @Override
    public IUser addUser(String userName, String password) {
        User u = new User();
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            u.addUser(userName, password);
            em.persist(u);
            em.getTransaction().commit();
            return u;
        } finally {
            em.close();
        }
    }

    @Override
    public IUser getPassword() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IUser getUserName() { 
// not sure if we need this as we have method above getUserByUserId where Id id userName
        User u;
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT u FROM User u WHERE u.userName = :userName");
            query.setParameter("userName", userName);
            u = (User) query.getSingleResult();
            return u;

        } finally {
            em.close();
        }
    }

    @Override
    public List<User> getUsers() {
        // GETTING ALL USERS AS LIST FOR ADMIN TO SEE
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("select u from User u");
            List<User> users = query.getResultList();
            if (users != null) {
                System.out.println("Got Users: " + users.size());
            } else {
                System.out.println("Got NULL");
            }
            return users;
        } finally {
            em.close();
        }
    }

    @Override
    public User deleteUser(String userName) {
        EntityManager em = getEntityManager();
         User u = em.find(User.class, userName);
    try{
        em.getTransaction().begin();
        em.remove(u);
        em.getTransaction().commit();
        return u;
    }finally{
      em.close();
    }
    }
    

}
