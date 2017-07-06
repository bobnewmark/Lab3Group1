package com.lab.account.web;


import com.shop.database.entities.*;
import com.shop.database.entities.Object;
import com.shop.database.exceptions.RegistrationException;
import com.shop.database.services.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@Controller
public class UserController {
    @Autowired
    private ObjectService objectService;
    @Autowired
    private ObjectTypeService objectTypeService;
    @Autowired
    private SecurityService securityService;

    private final static Logger LOGGER = Logger.getLogger(UserController.class);

    @Autowired
    private Tools tools;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("current", "/WEB-INF/views/login.jsp");
        return "index";
    }

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
