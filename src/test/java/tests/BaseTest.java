package tests;


import groovy.util.logging.Slf4j;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;
import utilities.PropertyUtil;

@Slf4j
public class BaseTest {

  public RequestSpecification createRequestSpecification() {
    PropertyUtil propertyUtil = PropertyUtil.getInstance();
    String baseUrl = propertyUtil.getConfigProperty("URL");
    RestAssured.defaultParser = Parser.JSON;
    return new RequestSpecBuilder().setBaseUri(baseUrl).build();
  }

}
