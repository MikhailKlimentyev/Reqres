package assertions;


import static org.testng.Assert.assertEquals;

public class StatusCodeAssertion {

    public void validateStatusCode(int actualStatusCode, int expectedStatusCode) {
        assertEquals(actualStatusCode, expectedStatusCode,
                String.format("%s status code is not equal expected %s", actualStatusCode, expectedStatusCode));
    }
}
