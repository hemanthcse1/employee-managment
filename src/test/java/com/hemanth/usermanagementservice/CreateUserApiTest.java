package com.hemanth.usermanagementservice;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.hemanth.usermanagementservice.model.CreateUserRequest;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;


public class CreateUserApiTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8080); // Port number for the mock server

    @Test
    public void testCreateUser() {
        // Setup WireMock stub
        stubFor(post(urlEqualTo("/create"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(containing("firstName"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\": 1, \"firstName\": \"John\", \"lastName\": \"Doe\", \"email\": \"john.doe@example.com\", \"mobile\": \"1234567890\"}")));

        // Test the /create endpoint
        given()
                .contentType("application/json")
                .body(new CreateUserRequest("John", "Doe", "john.doe@example.com", "1234567890"))
                .when()
                .post("http://localhost:8080/create")
                .then()
                .assertThat()
                .statusCode(200)
                .body("firstName", equalTo("John"))
                .body("lastName", equalTo("Doe"))
                .body("email", equalTo("john.doe@example.com"))
                .body("mobile", equalTo("1234567890"));
    }


    @Test
    public void testCreateUserSuccess() {
        stubFor(post(urlEqualTo("/create"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(containing("firstName"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\": 1, \"firstName\": \"John\", \"lastName\": \"Doe\", \"email\": \"john.doe@example.com\", \"mobile\": \"1234567890\"}")));

        given()
                .contentType("application/json")
                .body(new CreateUserRequest("John", "Doe", "john.doe@example.com", "1234567890"))
                .when()
                .post("http://localhost:8080/create")
                .then()
                .statusCode(200)
                .body("firstName", equalTo("John"));
    }


    @Test
    public void testCreateUserInvalidInput() {
        stubFor(post(urlEqualTo("/create"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(containing("invalidField"))
                .willReturn(aResponse()
                        .withStatus(400)));

        given()
                .contentType("application/json")
                .body(new CreateUserRequest("", "", "invalid", ""))
                .when()
                .post("http://localhost:8080/create")
                .then()
                .statusCode(400);
    }

    @Test
    public void testCreateUserServerError() {
        stubFor(post(urlEqualTo("/create"))
                .willReturn(aResponse()
                        .withStatus(500)));

        given()
                .contentType("application/json")
                .body(new CreateUserRequest("John", "Doe", "john.doe@example.com", "1234567890"))
                .when()
                .post("http://localhost:8080/create")
                .then()
                .statusCode(500);
    }
}
