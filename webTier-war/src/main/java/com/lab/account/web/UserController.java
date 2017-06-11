package com.lab.account.web;

import com.shop.database.entities.Object;
import com.shop.database.entities.ObjectType;
import com.shop.database.entities.Parameter;
import com.shop.database.entities.Reference;
import com.shop.database.exceptions.RegistrationException;
import com.shop.database.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class UserController {
    @Autowired
    private ObjectService objectService;
    @Autowired
    private ObjectTypeService objectTypeService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private ParameterService parameterService;
    @Autowired
    private AttributeService attributeService;

    @RequestMapping(value = {"/details/{id}"}, method = RequestMethod.GET)
    public String details(@PathVariable("id") int id, Model model) throws URISyntaxException {
        Object object = objectService.findById(id);
        Map<String, String> map = new HashMap<>();
        List<String> icons = new ArrayList<>();
        for (Parameter p : object.getParameters()) {
            if (p.getAttribute().getName().equals("name")) {
                model.addAttribute("name", p.getValue());
            } else if (p.getAttribute().getName().equals("price")) {
                model.addAttribute("price", p.getValue());
            } else if (p.getAttribute().getName().equals("icon")) {
                model.addAttribute("mainIcon", p.getValue());
            } else if (p.getAttribute().getName().startsWith("icon")
                    && !p.getAttribute().getName().equals("icon")
                    && p.getValue() != null) {
                icons.add(p.getValue());
            } else {
                map.put(p.getAttribute().getName(), p.getValue());
            }
        }
        model.addAttribute("item", object);
        model.addAttribute("paramsMap", map);
        model.addAttribute("icons", icons);
        model.addAttribute("current", "/WEB-INF/views/details.jsp");
        return "index";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("current", "/WEB-INF/views/login.jsp");
        return "index";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@RequestParam("email") String login, @RequestParam("password") String password, Model model) {
        System.out.println(login + " " + password);
        Object user = new Object();
        user.setName("user");
        ObjectType ot = objectTypeService.findByName("user");
        user.setObjectType(ot);
        user.setParameters(new ArrayList<Parameter>());

        /*userValidator.validate(userForm, bindingResult);*/
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);
        user.getParameters().add(new Parameter(user, attributeService.findByNameAndObjectType("login", ot), login));
        user.getParameters().add(new Parameter(user, attributeService.findByNameAndObjectType("password", ot), encoder.encode(password)));
        user.getParameters().add(new Parameter(user, attributeService.findByNameAndObjectType("role", ot), "USER"));
        try {
            objectService.save(user);
        } catch (RegistrationException e) {
            return "redirect:/registration?reg-err";
        }
        /*if (bindingResult.hasErrors()) {
            return "registration";
        }*/

       /* userService.save(userForm);*/
        Object cart = new Object("cart", objectTypeService.findByName("cart"), user);
        if (objectService.findByParent(user).size() > 0) {
            cart = objectService.findByParent(user).get(0);
        } else {
            cart.setReferences(new ArrayList<Reference>());
        }
//        cart.getReferences().add(new Reference(user, objectService.findById(7), "item", attributeService.findByNameAndObjectType("item", objectTypeService.findByName("cart"))));
//        cart.getReferences().add(new Reference(user, objectService.findById(8), "item", attributeService.findByNameAndObjectType("item", objectTypeService.findByName("cart"))));
//        cart.getReferences().add(new Reference(user, objectService.findById(9), "item", attributeService.findByNameAndObjectType("item", objectTypeService.findByName("cart"))));
//        cart.getReferences().add(new Reference(user, objectService.findById(9), "item", attributeService.findByNameAndObjectType("item", objectTypeService.findByName("cart"))));
        try {
            objectService.save(cart);
        } catch (RegistrationException e) {
            return "redirect:/registration?reg-err";
        }
        securityService.autologin(login, password);
        return "redirect:/";
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

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String getIndexPage(Model model) {
        model.addAttribute("current", "/WEB-INF/views/main.jsp");
        return "index";
    }

    @RequestMapping(value = {"/welcome"}, method = RequestMethod.GET)
    public ResponseEntity<List<Object>> welcome() {
        List<Object> objects = objectService.findByObjectType(objectTypeService.findById(1));
        if (objects.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(objects, HttpStatus.OK);
    }


    @RequestMapping(value = {"/phone"}, method = RequestMethod.GET)
    public ResponseEntity<List<Object>> phones() {
        ObjectType phone = objectTypeService.findByName("Phone");
        List<Object> objects = objectService.findByObjectType(phone);
        if (objects.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(objects, HttpStatus.OK);
    }
    @RequestMapping(value = "/phone/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateUser(@PathVariable("id") int id, @RequestBody Object object) {
        for(Parameter p: object.getParameters()){
            p.setObject(object);
        }
        try {
            objectService.save(object);
        } catch (RegistrationException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Object>(object, HttpStatus.OK);
    }



    //------------------- Delete a User --------------------------------------------------------

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUser(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting User with id " + id);


        //objectService.de(id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }


    @RequestMapping(value = {"/search"})
    public String search(Model model, HttpServletRequest request) {
        String keyword = request.getParameter("keyword");
        List<Object> result = objectService.findByNameContaining(keyword);
        model.addAttribute("searchResult", prepareForLayout(result));
        model.addAttribute("current", "/WEB-INF/views/search.jsp");
        return "index";
    }

    @RequestMapping(value = {"/shop"}, method = RequestMethod.GET)
    public String allProducts(Model model) throws URISyntaxException {
        List<Object> result = new ArrayList<>();
        result.addAll(objectService.findByObjectType(objectTypeService.findByName("Phone")));
        result.addAll(objectService.findByObjectType(objectTypeService.findByName("Headphones")));
        result.addAll(objectService.findByObjectType(objectTypeService.findByName("Charger")));
        result.addAll(objectService.findByObjectType(objectTypeService.findByName("Battery")));
        model.addAttribute("allProducts", prepareForLayout(result));
        model.addAttribute("current", "/WEB-INF/views/shop.jsp");
        return "index";
    }

    @RequestMapping(value = {"/contacts"}, method = RequestMethod.GET)
    public String contacts(Model model) {
        model.addAttribute("current", "/WEB-INF/views/contacts.jsp");
        return "index";
    }

    private List<Map<String, String>> prepareForLayout(List<Object> objects) {
        List<Map<String, String>> list = new ArrayList<>();
        for (Object o : objects) {
            Map<String, String> itemInfo = new HashMap<>();
            itemInfo.put("id", String.valueOf(o.getId()));
            List<Parameter> params = o.getParameters();
            for (Parameter p : params) {
                switch (p.getAttribute().getName()) {
                    case "name":
                        itemInfo.put(p.getAttribute().getName(), p.getValue());
                        break;
                    case "icon":
                        itemInfo.put(p.getAttribute().getName(), p.getValue());
                        break;
                    case "price":
                        itemInfo.put(p.getAttribute().getName(), p.getValue());
                        break;
                }
            }
            list.add(itemInfo);
        }
        return list;
    }

    @RequestMapping(value = {"/addToCart/{id}"})
    public String addToCart(@PathVariable("id") int id, Model model, @RequestParam("quantity") int quantity) throws URISyntaxException {
        return "index";
    }

    @RequestMapping(value = {"/cart"})
    public String cart() {
        return "cart";
    }
}
