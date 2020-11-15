package tests;

import assertions.ColorAssertion;
import assertions.CommonAssertion;
import assertions.UserAssertion;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;

import static io.restassured.config.ObjectMapperConfig.objectMapperConfig;

public class BaseTest {

    protected static String URL = "https://reqres.in";
    protected static String ID = "id";
    protected static int TIMEOUT_MS = 5000;
    protected String urlPattern = "%s/api/%s";

    protected CommonAssertion commonAssertion = new CommonAssertion();
    protected UserAssertion userAssertion = new UserAssertion();
    protected ColorAssertion colorAssertion = new ColorAssertion();

    public static RequestSpecification getRequestSpecification() {
        return RestAssured.given()
                .config(getGsonConfig())
                .config(getTimeOutConfig());
    }

    private static RestAssuredConfig getGsonConfig() {
        return RestAssuredConfig.config().
                objectMapperConfig(objectMapperConfig().gsonObjectMapperFactory(
                        (type, s) -> new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
                ));
    }

    private static RestAssuredConfig getTimeOutConfig() {
        return RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.socket.timeout", TIMEOUT_MS)
                        .setParam("http.connection.timeout", TIMEOUT_MS));
    }
}
