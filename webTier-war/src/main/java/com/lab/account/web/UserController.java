package com.lab.account.web;

import com.lab.account.validator.UserValidator;
import com.shop.database.entities.Object;
import com.shop.database.entities.ObjectType;
import com.shop.database.entities.Parameter;
import com.shop.database.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = {"/details/{id}"}, method = RequestMethod.GET)
    public String details(@PathVariable("id") int id, Model model) throws URISyntaxException {
        Object object = objectService.findById(id);
        Map<String, String> map = new HashMap<>();
        List<String> icons = new ArrayList<>();
        for (Parameter p: object.getParameters()) {
            if (p.getAttribute().getName().equals("name")) {
                model.addAttribute("name", p.getValue());
            }
            else if (p.getAttribute().getName().equals("price")) {
                model.addAttribute("price", p.getValue());
            }
            else if (p.getAttribute().getName().equals("icon")) {
                model.addAttribute("mainIcon", p.getValue());
            }
            else if (p.getAttribute().getName().startsWith("icon") && !p.getAttribute().getName().equals("icon")) {
                icons.add(p.getValue());
            }
            else {
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
        System.out.println("I'm working just fine");
        Object user = new Object();
        user.setObjectType(objectTypeService.findByName("user"));
        user.setParameters(new ArrayList<Parameter>());
        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder(11);
        user.getParameters().add(new Parameter(user, attributeService.findById(1),null));
        user.getParameters().add(new Parameter(user, attributeService.findById(2), null));
        user.getParameters().add(new Parameter(user, attributeService.findById(3),null));
        model.addAttribute("userForm", user);
        model.addAttribute("current", "/WEB-INF/views/login.jsp");
        return "index";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@RequestParam("email")String login, @RequestParam("password")String password) {
        System.out.println(login+" "+password);
        Object user = new Object();
       user.setName("user");
       user.setObjectType(objectTypeService.findByName("user"));
       user.setParameters(new ArrayList<Parameter>());
        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder(11);
       user.getParameters().add(new Parameter(user, attributeService.findById(4),login));
        user.getParameters().add(new Parameter(user, attributeService.findById(5),encoder.encode(password)));
        user.getParameters().add(new Parameter(user, attributeService.findById(6),"USER"));
        objectService.save(user);
        /*userValidator.validate(userForm, bindingResult);*/

        /*if (bindingResult.hasErrors()) {
            return "registration";
        }*/

       /* userService.save(userForm);*/

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
            return new ResponseEntity<List<Object>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Object>>(objects, HttpStatus.OK);
    }
    @RequestMapping(value = {"/phone"}, method = RequestMethod.GET)
    public ResponseEntity<List<Object>> phones() {
        ObjectType phone = objectTypeService.findByName("phone");
        List<Object> objects = objectService.findByObjectType(phone);
        if (objects.isEmpty()) {
            return new ResponseEntity<List<Object>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Object>>(objects, HttpStatus.OK);
    }

    @RequestMapping(value = {"/search"})
    public String search(Model model, HttpServletRequest request) {
        String keyword = request.getParameter("keyword");
        List<Object> result = objectService.findByNameContaining(keyword);
        List<Map<String,String >> list = new ArrayList<>();
        for (Object o: result) {
            Map<String, String> itemInfo = new HashMap<>();
            List<Parameter> params = o.getParameters();
            for (Parameter p: params) {
                if (p.getAttribute().getName().equals("name")) {
                    itemInfo.put(p.getAttribute().getName(), p.getValue());
                }
                else if (p.getAttribute().getName().equals("icon")) {
                    itemInfo.put(p.getAttribute().getName(), p.getValue());
                }
                else if (p.getAttribute().getName().equals("price")) {
                    itemInfo.put(p.getAttribute().getName(), p.getValue());
                }
            }
            list.add(itemInfo);
        }
        model.addAttribute("searchResult", list);
        model.addAttribute("current", "/WEB-INF/views/search.jsp");
        return "index";
    }




}
