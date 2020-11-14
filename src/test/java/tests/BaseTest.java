package tests;

import assertions.StatusCodeAssertion;
import assertions.UserAssertion;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;

public class BaseTest {

    protected static String URL = "https://reqres.in";

    protected String urlPattern = "%s/api/%s";

    protected StatusCodeAssertion statusCodeAssertion = new StatusCodeAssertion();
    protected UserAssertion userAssertion = new UserAssertion();

    public static RequestSpecification getRequestSpecification() {
        return RestAssured.given()
                .config(RestAssured.config()
                        .objectMapperConfig(new ObjectMapperConfig(ObjectMapperType.GSON)));
    }
}
