package com.keithcaff.Ur2Lng.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UrlValidator implements ConstraintValidator<UrlConstraint, String> {

    @Override
    public void initialize(UrlConstraint url) {
    }

    @Override
    public boolean isValid(String urlField, ConstraintValidatorContext cxt) {
        String[] schemes = {"http", "https"};
        org.apache.commons.validator.routines.UrlValidator validator =
                new org.apache.commons.validator.routines.UrlValidator(schemes);
        return validator.isValid(urlField);
    }
}