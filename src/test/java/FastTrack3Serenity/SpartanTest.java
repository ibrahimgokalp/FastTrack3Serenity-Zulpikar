package FastTrack3Serenity;

import com.gargoylesoftware.htmlunit.javascript.host.*;
//import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import net.serenitybdd.junit5.*;
import net.serenitybdd.rest.*;
import org.junit.jupiter.api.*;

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
        Response response = SerenityRest.given().accept(ContentType.JSON)
                .pathParam("id",107)
                .when().get("http://44.201.135.133:8000/api/spartans/{id}");

        Assertions.assertEquals(response.statusCode(),200);
    }
}
