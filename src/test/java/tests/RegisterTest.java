package tests;

import io.restassured.response.ValidatableResponse;
import models.ErrorResponse;
import models.LoginPayload;
import models.TokenWithIdResponse;
import org.apache.http.HttpStatus;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {

    private static String REGISTER_URI = "register";

    @Ignore
    @Test
    public void tokenShouldBeReturned() {
        ValidatableResponse response = getRequestSpecification()
                .log().all()
                .body(new LoginPayload("eve.holt@reqres.in", "pistol"))
                .when()
                .post(String.format(urlPattern, URL, REGISTER_URI))
                .then()
                .log().all();

        int actualStatusCode = response.extract().statusCode();
        commonAssertion.validateStatusCode(actualStatusCode, HttpStatus.SC_OK);

        TokenWithIdResponse tokenWithIdResponse = response.extract().body().as(TokenWithIdResponse.class);
        int id = tokenWithIdResponse.getId();
        commonAssertion.validateFieldNotBlank(String.valueOf(id));

        String token = tokenWithIdResponse.getToken();
        commonAssertion.validateField(token, "QpwL5tke4Pnpja7X4");
    }

    @Ignore
    @Test
    public void badRequestResponseShouldBeReturnedIfPasswordNotPassed() {
        LoginPayload loginPayload = LoginPayload.builder()
                .email("sydney@fife")
                .build();

        ValidatableResponse response = getRequestSpecification()
                .log().all()
                .body(loginPayload)
                .when()
                .post(String.format(urlPattern, URL, REGISTER_URI))
                .then()
                .log().all();

        int actualStatusCode = response.extract().statusCode();
        commonAssertion.validateStatusCode(actualStatusCode, HttpStatus.SC_BAD_REQUEST);

        ErrorResponse errorResponse = response.extract().body().as(ErrorResponse.class);
        String errorMessage = errorResponse.getError();
        commonAssertion.validateField(errorMessage, "Missing email or username");
    }
}
