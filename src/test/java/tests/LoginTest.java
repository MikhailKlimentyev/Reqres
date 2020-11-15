package tests;

import io.restassured.response.ValidatableResponse;
import models.ErrorResponse;
import models.LoginPayload;
import models.TokenResponse;
import org.apache.http.HttpStatus;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private static String LOGIN_URI = "login";

    @Ignore
    @Test
    public void tokenShouldBeReturnedAfterSuccessfulLogin() {
        ValidatableResponse response = getRequestSpecification()
                .log().all()
                .body(new LoginPayload("eve.holt@reqres.in", "cityslicka"))
                .when()
                .post(String.format(urlPattern, URL, LOGIN_URI))
                .then()
                .log().all();

        int actualStatusCode = response.extract().statusCode();
        commonAssertion.validateStatusCode(actualStatusCode, HttpStatus.SC_OK);

        TokenResponse tokenResponse = response.extract().body().as(TokenResponse.class);
        String token = tokenResponse.getToken();
        commonAssertion.validateField(token, "QpwL5tke4Pnpja7X4");
    }

    @Test
    public void badRequestResponseShouldBeReturnedIfPasswordNotPassed() {
        LoginPayload loginPayload = LoginPayload.builder()
                .email("peter@klaven")
                .build();

        ValidatableResponse response = getRequestSpecification()
                .log().all()
                .body(loginPayload)
                .when()
                .post(String.format(urlPattern, URL, LOGIN_URI))
                .then()
                .log().all();

        int actualStatusCode = response.extract().statusCode();
        commonAssertion.validateStatusCode(actualStatusCode, HttpStatus.SC_BAD_REQUEST);

        ErrorResponse errorResponse = response.extract().body().as(ErrorResponse.class);
        String errorMessage = errorResponse.getError();
        commonAssertion.validateField(errorMessage, "Missing email or username");
    }
}
