package tests;

import groovy.util.logging.Slf4j;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
public class UsersTest extends BaseTest {

  public Response ListUsers() {

    return given().spec(createRequestSpecification())
      .and()
      .queryParams("page",2)
      .get("/api/users")
      .then().extract().response();
  }

  @Test(description = "Verify status code")
  public void verifyListUsersStatusCode() {
    assertThat(ListUsers().statusCode(), Matchers.equalTo(200));
  }

  @Test(description = "Validate per page")
  public void verifyPerPage(){
    System.out.println(ListUsers().body().prettyPrint());
    assertThat(ListUsers().body().path("page"),Matchers.equalTo(2));
    ArrayList List = ListUsers().body().path("data");
    assertThat(List.size(),Matchers.equalTo(ListUsers().body().path("per_page")));
  }
}
