///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package rest;
//
//import entity.User;
//import static io.restassured.RestAssured.given;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import io.restassured.RestAssured;
//import static io.restassured.RestAssured.*;
//import io.restassured.http.ContentType;
//import io.restassured.parsing.Parser;
//import static org.hamcrest.Matchers.*;
//
///**
// *
// * @author jarmo
// */
//public class UserServiceTest {
//
//    public UserServiceTest() {
//    }
//
//    @BeforeClass
//    public static void setUpBeforeAll() {
//        RestAssured.baseURI = "http://localhost:8080/seedMaven";
//        RestAssured.port = 8080;
//        RestAssured.basePath = "/api/user";
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
//        given().when().get("http://localhost:8080/seedMaven/").then().statusCode(200);
//    }
//
//    /**
//     * Test of addUser method, of class UserService.
//     */
//    @Test
//    public void testAddUser() throws Exception {
//        User u = new User("User1", "test");
//        User newUser = given()
//                .contentType("application/json")
//                .body(u)
//                .when().post("/signUp")
//                .as(User.class);
//        assertNotNull(newUser.getUserName());
//        //Use username from result above
//        given()
//                .contentType(ContentType.JSON)
//                .when().get("/api/user/" + newUser.getUserName()).then()
////                .body("userName", notNullValue())
//                .body("userName", hasItem("User1"));
//
//    }
//
//}
