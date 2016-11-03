/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.facade;

import entity.User;

import facades.UserFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import security.IUser;
import security.IUserFacade;
import security.PasswordStorage;

/**
 *
 * @author jarmo
 */
public class UserFacadeTest {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_test");
    private static IUserFacade facade = new UserFacade(emf);

    public UserFacadeTest() {
    }

    @Before
    public void setUp() throws PasswordStorage.CannotPerformOperationException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("delete from User").executeUpdate();
            User u1 = new User("aa", "bb");
            em.persist(u1);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    @Test
    public void testAddUser() throws PasswordStorage.CannotPerformOperationException {
        User u = new User("userName", "password");
    User result = facade.addUser(u.getUserName(), u.getPassword());
    assertNotNull(result.getUserName());
    EntityManager em = emf.createEntityManager();
    try {
      User result2 = em.find(User.class, result.getUserName());
      assertEquals("userName", result2.getUserName());
    } finally {
      em.close();
    }
    }

    @Test
  public void testGetUsers() {
    List<User> users = facade.getUsers(); // need to writte method for getting asll users for admin to see
    assertEquals(2, users.size());  
  }

//    @Test
//    public void testGetUserByUserId() {
//        System.out.println("getUserByUserId");
//        String id = "";
//        UserFacade instance = null;
//        IUser expResult = null;
//        IUser result = instance.getUserByUserId(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of authenticateUser method, of class UserFacade.
//     */
//    @Test
//    public void testAuthenticateUser() {
//        System.out.println("authenticateUser");
//        String userName = "";
//        String password = "";
//        UserFacade instance = null;
//        List<String> expResult = null;
//        List<String> result = instance.authenticateUser(userName, password);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addNewUser method, of class UserFacade.
//     */
    
}
