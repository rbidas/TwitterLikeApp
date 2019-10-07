package com.esencjal.twitter.like.post;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostControllerTest {

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    @Order(1)
    public void createPost() {
        String myJson = "{\"message\":\"#wiadomoćś testow\"}";
        given()
                .header("Content-Type", "application/json")
                .body(myJson)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("message", equalTo("#wiadomoćś testow"));
    }

    @Test
    @Order(2)
    public void test_get_post_return_200_with_pagination_expected_contnet_size_2() {
        when()
                .get("/post?page=0&size=2")
                .then()
                .statusCode(200)
                .body("totalElements", equalTo(10),
                        "numberOfElements", equalTo(2));
    }
}