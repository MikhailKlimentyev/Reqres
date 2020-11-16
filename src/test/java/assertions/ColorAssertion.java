package assertions;

import models.Color;

import static org.testng.Assert.assertEquals;

public class ColorAssertion {

    public void validateColor(Color actualColor, Color expectedColor) {
        assertEquals(actualColor, expectedColor,
                String.format("%s color is not equal expected %s", actualColor, expectedColor));
    }
}
