package com.keithcaff.Ur2Lng.unit;

import com.keithcaff.Ur2Lng.validators.UrlValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UrlValidatorTest {

    private UrlValidator urlValidator = new UrlValidator();

    @Test
    @DisplayName("Should allow http and https Url schemes")
    public void allowValidUrlSchemes() {
        assertTrue(urlValidator.isValid("https://github.com/keithcaff",null));
        assertTrue(urlValidator.isValid("http://127.0.0.1:8080/ur2lng",null));
    }

    @Test
    @DisplayName("Should NOT allow invalid url schemes")
    public void rejectInvalidUrlSchemes() {
        assertFalse(urlValidator.isValid("httpps://github.com/keithcaff",null));
        assertFalse(urlValidator.isValid("127.0.0.1:8080/ur2lng",null));
    }

    @Test
    @DisplayName("Should NOT allow empty urls")
    public void rejectEmptyUrls() {
        assertFalse(urlValidator.isValid(null,null));
        assertFalse(urlValidator.isValid("  ",null));
        assertFalse(urlValidator.isValid("",null));
    }
}