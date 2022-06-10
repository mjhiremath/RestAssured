package tests;

import groovy.util.logging.Slf4j;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
public class PostTest extends BaseTest {

    public Response CreateUsers() {

        HashMap<String, String> requestParameter = new HashMap<>();
        requestParameter.put("name", "Mrityunjaya");
        requestParameter.put("job", "Software Engineer");

        return given().spec(createRequestSpecification())
                .and()
                .contentType(ContentType.JSON)
                .body(requestParameter)
                .post("/api/users")
                .then().extract().response();
    }

    @Test(description = "Verify status code")
    public void verifyCreatetUsersStatusCode() {
        assertThat(CreateUsers().statusCode(), Matchers.equalTo(201));
    }

    @Test(description = "Validate the body")
    public void verifyCreateuserBody(){
        String user = CreateUsers().getBody().path("name");
        assertThat(user,Matchers.equalTo("Mrityunjaya"));
    }

}
