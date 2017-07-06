package com.lab.account.web;


import com.shop.database.entities.*;
import com.shop.database.entities.Object;
import com.shop.database.exceptions.RegistrationException;
import com.shop.database.services.ObjectService;
import com.shop.database.services.ObjectTypeService;
import com.shop.database.services.SecurityService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

/**
 * <code>UserController</code> is a controller to handle user-related operations.
 */
@Controller
public class UserController {
    @Autowired
    private ObjectService objectService;
    @Autowired
    private ObjectTypeService objectTypeService;
    @Autowired
    private SecurityService securityService;

    private final static Logger LOGGER = Logger.getLogger(UserController.class);

    /**
     * Takes user to the login page.
     * @param model carries inner jsp
     * @return outer page with it
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("current", "/WEB-INF/views/login.jsp");
        return "index";
    }

    /**
     * Method for registering new user in the database. It also creates user's shopping cart
     * and automatically performs sign in after user is created.
     * @param user is a new user to register sent from client
     * @return confirmation ResponseEntity
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<Object> registration(@RequestBody Object user) {
        for (Parameter param : user.getParameters()) {
            param.setObject(user);
            if (param.getAttribute().getName().equals("role")) {
                param.setValue("USER");
            }
        }
        try {
            objectService.save(user);
            LOGGER.info("User " + user.getName() + " saved succsessfully.");
        } catch (RegistrationException e) {
            LOGGER.error("Cannot save user " + user.getName() + ", ", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Object cart = new Object("cart", objectTypeService.findByName("cart"), user);
        if (objectService.findByParent(user).size() > 0) {
            cart = objectService.findByParent(user).get(0);
        } else {
            cart.setReferences(new ArrayList<Reference>());
        }
        try {
            objectService.save(cart);
        } catch (RegistrationException e) {
            LOGGER.error("Cannot save cart for user " + user.getName() + ", ", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        securityService.autologin(user.getParameters().get(0).getValue(), user.getParameters().get(1).getValue());
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * Method returns new Object of user type
     * @return blank user
     */
    @RequestMapping(value = {"/user"}, method = RequestMethod.GET)
    public ResponseEntity<Object> user() {
        ObjectType type = objectTypeService.findById(6);
        Object obj = new Object();
        obj.setObjectType(type);
        obj.setName(type.getName());
        for (Attribute attr : type.getAttributes()) {
            Parameter param = new Parameter();
            param.setObject(obj);
            param.setAttribute(attr);
            obj.getParameters().add(param);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    /**
     * Method for logging in/out actions. Depending on the result, user gets a message.
     * @param model is a Model for sending data back to client
     * @param error in case of incorrect user/password
     * @param logout in case of logging out
     * @return login page
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");
        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");
        model.addAttribute("current", "/WEB-INF/views/login.jsp");
        return "login";
    }
}
