package com.zied.bankingApp.exceptions;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ObjectsValidator <T> {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    public void validate(T objectToValidate) {
        Set<ConstraintViolation<T>> violations = validator.validate(objectToValidate);
        if (!violations.isEmpty()){
            Set <String> errorMessages = violations.stream()
                    .map(ConstraintViolation ::getMessage)
                    .collect(Collectors.toSet());
            throw new ObjetValidationException(errorMessages, objectToValidate.getClass().getName());
        }

    }
}
