package tests;

import io.restassured.response.ValidatableResponse;
import models.User;
import models.UsersList;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.List;

public class UsersTests extends BaseTest {

    private static String USERS_URI = "users";

    private User firstExpectedUser = User.builder()
            .id(1)
            .email("george.bluth@reqres.in")
            .firstName("George")
            .lastName("Bluth")
            .avatar("https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg")
            .build();

    @Test
    public void getListUsers() {
        ValidatableResponse response = getRequestSpecification().log().all()
                .when()
                .get(String.format(urlPattern, URL, USERS_URI))
                .then()
                .log().all();

        int actualStatusCode = response.extract().statusCode();
        statusCodeAssertion.validateStatusCode(actualStatusCode, HttpStatus.SC_OK);

        UsersList usersList = response.extract().body().as(UsersList.class);
        List<User> users = usersList.getData();
        userAssertion.validateUsersCount(users.size(), 6);

        User firstUser = users.get(0);
        userAssertion.validateUser(firstUser, firstExpectedUser);
    }
}
