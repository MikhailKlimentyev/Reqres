package tests;

import io.restassured.response.ValidatableResponse;
import models.Color;
import models.ColorListResponse;
import models.ColorResponse;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.List;

public class ColorTest extends BaseTest {

    private static String UNKNOWNS_URI = "unknown";
    private static String UNKNOWN_URI = "unknown/{id}";

    private Color firstExpectedColor = Color.builder()
            .id(1)
            .name("cerulean")
            .year(2000)
            .color("#98B2D1")
            .pantoneValue("15-4020")
            .build();

    private Color expectedColor = Color.builder()
            .id(2)
            .name("fuchsia rose")
            .year(2001)
            .color("#C74375")
            .pantoneValue("17-2031")
            .build();

    @Test
    public void getListColors() {
        ValidatableResponse response = getRequestSpecification()
                .log().all()
                .when()
                .get(String.format(urlPattern, URL, UNKNOWNS_URI))
                .then()
                .log().all();

        int actualStatusCode = response.extract().statusCode();
        commonAssertion.validateStatusCode(actualStatusCode, HttpStatus.SC_OK);

        ColorListResponse colorsList = response.extract().body().as(ColorListResponse.class);
        List<Color> colors = colorsList.getData();
        commonAssertion.validateCount(colors.size(), 6);

        Color firstColor = colors.get(0);
        colorAssertion.validateColor(firstColor, firstExpectedColor);

        int page = colorsList.getPage();
        commonAssertion.validateField(page, 1);
    }

    @Test
    public void getSingleColor() {
        ValidatableResponse response = getRequestSpecification()
                .log().all()
                .pathParam(ID, "2")
                .when()
                .get(String.format(urlPattern, URL, UNKNOWN_URI))
                .then()
                .log().all();

        int actualStatusCode = response.extract().statusCode();
        commonAssertion.validateStatusCode(actualStatusCode, HttpStatus.SC_OK);

        ColorResponse userResponse = response.extract().body().as(ColorResponse.class);
        Color color = userResponse.getData();
        colorAssertion.validateColor(color, expectedColor);
    }

    @Test
    public void shouldBeNotFoundResponseReturned() {
        ValidatableResponse response = getRequestSpecification()
                .log().all()
                .pathParam(ID, "23")
                .when()
                .get(String.format(urlPattern, URL, UNKNOWN_URI))
                .then()
                .log().all();

        int actualStatusCode = response.extract().statusCode();
        commonAssertion.validateStatusCode(actualStatusCode, HttpStatus.SC_NOT_FOUND);

        String responseBody = response.extract().body().asString();
        commonAssertion.validateField(responseBody, "{}");
    }
}
