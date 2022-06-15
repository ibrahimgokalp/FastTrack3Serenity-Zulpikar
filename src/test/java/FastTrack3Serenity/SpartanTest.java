package FastTrack3Serenity;

import com.gargoylesoftware.htmlunit.javascript.host.*;
//import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import net.serenitybdd.junit5.*;
import net.serenitybdd.rest.*;
import org.junit.jupiter.api.*;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.hamcrest.Matchers.is;


@SerenityTest
public class SpartanTest {




    // get all spartans
    // https://github.com/zulpikarCydeo/FastTrack3Serenity.git
    // use rest given first, then serenity given
    // run from test method, then run from maven or terminal, see different


    @Test
    public void getAllSpartans(){

        Response response = SerenityRest.given().accept(ContentType.JSON)
                .when().get("http://44.201.135.133:8000/api/spartans");

        System.out.println(response.statusCode());
    }

    @Test
    public void getOneSpartan(){
        SerenityRest.given().accept(ContentType.JSON)
                .pathParam("id",107)
                .when().get("http://44.201.135.133:8000/api/spartans/{id}")
                .then().statusCode(200)
                .and().contentType(ContentType.JSON);

        System.out.println(lastResponse().statusCode());
        Ensure.that("Status code is 200", validatableResponse -> validatableResponse.statusCode(200));
        Ensure.that("Content Type is Json", validatableResponse -> validatableResponse.contentType(ContentType.JSON));
        Ensure.that("Id is 107",validatableResponse -> validatableResponse.body("id",is(107)));

    }

    @Test
    public void mockTest(){
        SerenityRest.given().accept(ContentType.JSON)
                .when().get("https://355471c9-62ea-4013-abdc-b5a766d60361.mock.pstmn.io/students")
                .then().statusCode(200);

        Ensure.that("Mock status code is 200", validatableResponse -> validatableResponse.statusCode(200));
    }
}
