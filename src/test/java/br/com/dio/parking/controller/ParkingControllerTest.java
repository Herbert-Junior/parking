package br.com.dio.parking.controller;

import br.com.dio.parking.model.Parking;
import io.restassured.RestAssured;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerTest extends AbstractContainerBase {

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUpTest() {
        RestAssured.port = randomPort;
    }


    @Test
    void whenInsertAParking(){
        var pk = new Parking();

        pk.setId(getUUID());
        pk.setName("Restaurant Parking");
        pk.setVacancyLimit(13);

        RestAssured.given().when()
                .auth().basic("user", "Dio@12345678")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(pk)
                .post("/parking")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("name", Matchers.equalTo("Restaurant Parking"));
    }

    private static String getUUID() {
        return UUID.randomUUID().toString();
    }

    @Test
    void whenFindAllParking() {
        RestAssured.given().when()
                .auth().basic("user", "Dio@12345678")
                .get("/parking").then()
                .statusCode(HttpStatus.OK.value());
         //.body("id", Matchers.);
    }
}