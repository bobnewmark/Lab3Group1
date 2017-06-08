package com.lab.account.validator;

import com.shop.database.entities.Object;
import com.shop.database.services.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Autowired
    private ObjectService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Object.class.equals(aClass);
    }

    @Override
    public void validate(java.lang.Object o, Errors errors) {
        Object user = (Object) o;
        if (userService.findByNameAttrAndObjectType("login", "user", user.getMapParameters().get("login").getValue()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }
    }
}
