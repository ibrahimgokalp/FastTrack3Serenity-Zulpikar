package FastTrack3Serenity;

import com.github.javafaker.*;
import io.restassured.http.*;
import jnr.posix.*;
import net.serenitybdd.junit5.*;
import net.serenitybdd.rest.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.*;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;


@SerenityTest
public class SpartanPostTest {


    @Test
    public void addSpartan() {



    Faker faker = new Faker();

    Map<String, Object> spartanBody = new HashMap<>();
    spartanBody.put("name",faker.name().firstName());
    spartanBody.put("gender",faker.demographic().sex());
    spartanBody.put("phone",faker.number().numberBetween(5_000_000_000l,10_000_000_000l));


        SerenityRest.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(spartanBody)
                .log().body()
                .when().post("http://44.201.135.133:8000/api/spartans");


        Ensure.that("status code is 201", validatableResponse -> validatableResponse.statusCode(201));
        Ensure.that("Success message",validatableResponse -> validatableResponse.body("success",is("A Spartan is Born!")));
        Ensure.that("Name is correct", validatableResponse -> validatableResponse.body("data.name",is(spartanBody.get("name"))));
        Ensure.that("Id is not null", validatableResponse -> validatableResponse.body("data.id",notNullValue()));
}

}
