package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    public void testSubstringsBetweenBasic() {
        String[] result = StringUtils.substringsBetween("[abc]", "[", "]");
        assertArrayEquals(new String[]{"abc"}, result);
    }

    @Test
    public void testSubstringsBetweenNullCases() {
        assertNull(StringUtils.substringsBetween(null, "[", "]"));
        assertNull(StringUtils.substringsBetween("abc", null, "]"));
        assertNull(StringUtils.substringsBetween("abc", "[", ""));
    }
}