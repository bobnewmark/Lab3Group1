package com.lab.account.validator;

import com.shop.database.entities.Object;
import com.shop.database.services.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * <code>UserValidator</code> is a class for reassuring that user's login is unique withing the project database.
 */
@Component
public class UserValidator implements Validator {
    @Autowired
    private ObjectService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Object.class.equals(aClass);
    }

    @Override
    public void validate(java.lang.Object obj, Errors errors) {
        Object user = (Object) obj;
        if (userService.findByNameAttrAndObjectType("login", "user", user.getMapParameters().get("login").getValue()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }
    }
}
