package spring.ecommerceapplication.Validation;

import jakarta.validation.ConstraintValidator;

public class LowercaseValidator implements ConstraintValidator<spring.secondproject.Validation.Lowercase, String> {
    @Override
    public boolean isValid(String value, jakarta.validation.ConstraintValidatorContext context) {
        if(value == null) return true;
        return value.equals(value.toLowerCase());
    }
}
