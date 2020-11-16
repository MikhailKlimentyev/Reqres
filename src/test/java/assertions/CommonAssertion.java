package assertions;


import org.apache.commons.lang3.StringUtils;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class CommonAssertion {

    public void validateStatusCode(int actualStatusCode, int expectedStatusCode) {
        assertEquals(actualStatusCode, expectedStatusCode,
                String.format("%s status code is not equal expected %s", actualStatusCode, expectedStatusCode));
    }

    public void validateField(String actualFieldValue, String expectedFieldValue) {
        assertEquals(actualFieldValue, expectedFieldValue,
                String.format("%s field value is not equal expected %s", actualFieldValue, expectedFieldValue));
    }

    public void validateField(int actualFieldValue, int expectedFieldValue) {
        assertEquals(actualFieldValue, expectedFieldValue,
                String.format("%s field value is not equal expected %s", actualFieldValue, expectedFieldValue));
    }

    public void validateFieldNotBlank(String actualFieldValue) {
        assertFalse(StringUtils.isBlank(actualFieldValue), "Field is blank");
    }

    public void validateCount(int actualCount, int expectedCount) {
        assertEquals(actualCount, expectedCount,
                String.format("%s count is not equal expected %s", actualCount, expectedCount));
    }
}
