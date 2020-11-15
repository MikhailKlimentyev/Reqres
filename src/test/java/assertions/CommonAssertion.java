package assertions;


import org.apache.commons.lang3.StringUtils;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class CommonAssertion {

    public void validateStatusCode(int actualStatusCode, int expectedStatusCode) {
        assertEquals(actualStatusCode, expectedStatusCode,
                String.format("%s status code is not equal expected %s", actualStatusCode, expectedStatusCode));
    }

    public void validateResponse(String actualResponse, String expectedResponse) {
        assertEquals(actualResponse, expectedResponse,
                String.format("%s response body is not equal expected %s", actualResponse, expectedResponse));
    }

    public void validateFieldNotBlank(String actualFieldValue) {
        assertFalse(StringUtils.isBlank(actualFieldValue), "Field is blank");
    }
}
