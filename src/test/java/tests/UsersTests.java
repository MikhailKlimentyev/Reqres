package tests;

import io.restassured.response.ValidatableResponse;
import models.*;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class UsersTests extends BaseTest {

    private static String USERS_URI = "users";
    private static String USER_URI = "users/{id}";

    private User firstExpectedUser = User.builder()
            .id(1)
            .email("george.bluth@reqres.in")
            .firstName("George")
            .lastName("Bluth")
            .avatar("https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg")
            .build();

    @Test
    public void getListUsers() {
        ValidatableResponse response = getRequestSpecification()
                .log().all()
                .when()
                .get(String.format(urlPattern, URL, USERS_URI))
                .then()
                .log().all();

        int actualStatusCode = response.extract().statusCode();
        commonAssertion.validateStatusCode(actualStatusCode, HttpStatus.SC_OK);

        UserListResponse usersList = response.extract().body().as(UserListResponse.class);
        List<User> users = usersList.getData();
        userAssertion.validateUsersCount(users.size(), 6);

        User firstUser = users.get(0);
        userAssertion.validateUser(firstUser, firstExpectedUser);
    }

    @Test
    public void getListUsersWithDelay() {
        ValidatableResponse response = getRequestSpecification()
                .log().all()
                .param("delay", "3")
                .when()
                .get(String.format(urlPattern, URL, USERS_URI))
                .then()
                .log().all();

        int actualStatusCode = response.extract().statusCode();
        commonAssertion.validateStatusCode(actualStatusCode, HttpStatus.SC_OK);

        UserListResponse usersList = response.extract().body().as(UserListResponse.class);
        List<User> users = usersList.getData();
        userAssertion.validateUsersCount(users.size(), 6);

        User firstUser = users.get(0);
        userAssertion.validateUser(firstUser, firstExpectedUser);
    }

    @Test
    public void getSingleUser() {
        ValidatableResponse response = getRequestSpecification()
                .log().all()
                .pathParam(ID, "1")
                .when()
                .get(String.format(urlPattern, URL, USER_URI))
                .then()
                .log().all();

        int actualStatusCode = response.extract().statusCode();
        commonAssertion.validateStatusCode(actualStatusCode, HttpStatus.SC_OK);

        UserResponse userResponse = response.extract().body().as(UserResponse.class);
        User user = userResponse.getData();
        userAssertion.validateUser(user, firstExpectedUser);
    }

    @Test
    public void shouldBeNotFoundResponseReturned() {
        ValidatableResponse response = getRequestSpecification()
                .log().all()
                .pathParam(ID, "24")
                .when()
                .get(String.format(urlPattern, URL, USER_URI))
                .then()
                .log().all();

        int actualStatusCode = response.extract().statusCode();
        commonAssertion.validateStatusCode(actualStatusCode, HttpStatus.SC_NOT_FOUND);

        String responseBody = response.extract().body().asString();
        commonAssertion.validateResponse(responseBody, "{}");
    }

    @Test
    public void userShouldBeCreated() {
        ValidatableResponse response = getRequestSpecification()
                .log().all()
                .body(new UserPayload("morpheus", "leader"))
                .when()
                .post(String.format(urlPattern, URL, USERS_URI))
                .then()
                .log().all();

        int actualStatusCode = response.extract().statusCode();
        commonAssertion.validateStatusCode(actualStatusCode, HttpStatus.SC_CREATED);

        UserCreatedResponse userCreatedResponse = response.extract().body().as(UserCreatedResponse.class);
        String createdAt = userCreatedResponse.getCreatedAt();
        String actualCreatedAt = truncateDateTimeToMinutes(createdAt);
        String expectedCreatedAt = getStringCurrentDateTime(ZoneOffset.UTC);
        commonAssertion.validateResponse(actualCreatedAt, expectedCreatedAt);

        String id = userCreatedResponse.getId();
        commonAssertion.validateFieldNotBlank(id);
    }

    @Test
    public void userShouldBeUpdated() {
        ValidatableResponse response = getRequestSpecification()
                .log().all()
                .pathParam(ID, "2")
                .body(new UserPayload("morpheus", "zion resident"))
                .when()
                .put(String.format(urlPattern, URL, USER_URI))
                .then()
                .log().all();

        int actualStatusCode = response.extract().statusCode();
        commonAssertion.validateStatusCode(actualStatusCode, HttpStatus.SC_OK);

        UserUpdatedResponse userUpdatedResponse = response.extract().body().as(UserUpdatedResponse.class);
        String updatedAt = userUpdatedResponse.getUpdatedAt();
        String actualUpdatedAt = truncateDateTimeToMinutes(updatedAt);
        String expectedUpdatedAt = getStringCurrentDateTime(ZoneOffset.UTC);
        commonAssertion.validateResponse(actualUpdatedAt, expectedUpdatedAt);
    }

    @Test
    public void userShouldBePatched() {
        ValidatableResponse response = getRequestSpecification()
                .log().all()
                .pathParam(ID, "2")
                .body(new UserPayload("morpheus", "zion resident"))
                .when()
                .patch(String.format(urlPattern, URL, USER_URI))
                .then()
                .log().all();

        int actualStatusCode = response.extract().statusCode();
        commonAssertion.validateStatusCode(actualStatusCode, HttpStatus.SC_OK);

        UserUpdatedResponse userUpdatedResponse = response.extract().body().as(UserUpdatedResponse.class);
        String updatedAt = userUpdatedResponse.getUpdatedAt();
        String actualUpdatedAt = truncateDateTimeToMinutes(updatedAt);
        String expectedUpdatedAt = getStringCurrentDateTime(ZoneOffset.UTC);
        commonAssertion.validateResponse(actualUpdatedAt, expectedUpdatedAt);
    }

    @Test
    public void userShouldBeDeleted() {
        ValidatableResponse response = getRequestSpecification()
                .log().all()
                .pathParam(ID, "2")
                .when()
                .delete(String.format(urlPattern, URL, USER_URI))
                .then()
                .log().all();

        int actualStatusCode = response.extract().statusCode();
        commonAssertion.validateStatusCode(actualStatusCode, HttpStatus.SC_NO_CONTENT);
    }

    private String truncateDateTimeToMinutes(String dateTime) {
        String actualCreatedAt = dateTime.substring(0, dateTime.length() - 8);
        return actualCreatedAt;
    }

    private String getStringCurrentDateTime(ZoneId zoneId) {
        return LocalDateTime.now(zoneId).truncatedTo(ChronoUnit.MINUTES).toString();
    }
}
