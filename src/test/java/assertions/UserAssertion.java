package assertions;

import models.User;

import static org.testng.Assert.assertEquals;

public class UserAssertion {

    public void validateUsersCount(int actualUsersCount, int expectedUsersCount) {
        assertEquals(actualUsersCount, expectedUsersCount,
                String.format("%s users count is not equal expected %s", actualUsersCount, expectedUsersCount));
    }

    public void validateUser(User actualUser, User expectedUser) {
        assertEquals(actualUser, expectedUser,
                String.format("%s user is not equal expected %s", actualUser, expectedUser));
    }
}
