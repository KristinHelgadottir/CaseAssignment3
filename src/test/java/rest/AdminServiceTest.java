///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package rest;
//
//import entity.User;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import io.restassured.RestAssured;
//import static io.restassured.RestAssured.*;
//import io.restassured.parsing.Parser;
//import static org.hamcrest.Matchers.*;
//
///**
// *
// * @author jarmo
// */
//public class AdminServiceTest {
//
//    public AdminServiceTest() {
//    }
//
//    @BeforeClass
//    public static void setUpBeforeAll() {
//        RestAssured.baseURI = "http://localhost";
//        RestAssured.port = 8080;
//        RestAssured.basePath = "/api/admin";
//        RestAssured.defaultParser = Parser.JSON;
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    @Before
//    public void setUp() {
//    }
//
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Tests that the server is running*
//     */
//    @Test
//    public void serverIsRunning() {
//        given().when().get().then().statusCode(200);
//    }
//
//    /**
//     * Test of getUsers method, of class AdminService.
//     */
////    @Test
////    public void testGetUsers() {
//////        System.out.println("getUsers");
//////        AdminService instance = new AdminService();
//////        String expResult = "";
//////        String result = instance.getUsers();
//////        assertEquals(expResult, result);
//////        // TODO review the generated test code and remove the default call to fail.
//////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of deleteUserByName method, of class AdminService.
////     */
////    @Test
////    public void testDeleteUserByName() {
////        System.out.println("deleteUserByName");
////        String userName = "";
////        AdminService instance = new AdminService();
////        User expResult = null;
////        User result = instance.deleteUserByName(userName);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
//
//}
