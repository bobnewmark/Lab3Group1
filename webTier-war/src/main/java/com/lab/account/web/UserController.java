package com.lab.account.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.database.entities.*;
import com.shop.database.entities.Object;
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
import java.io.IOException;
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
    private ReferenceService referenceService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("current", "/WEB-INF/views/login.jsp");
        return "index";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@RequestParam("email") String login, @RequestParam("password") String password, Model model) {
        System.out.println(login + " login--password" + password);
        Object user = new Object();
        user.setName("user");
        ObjectType ot = objectTypeService.findByName("user");
        user.setObjectType(ot);
        user.setParameters(new ArrayList<Parameter>());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);
        user.getParameters().add(new Parameter(user, attributeService.findByNameAndObjectType("login", ot), login));
        user.getParameters().add(new Parameter(user, attributeService.findByNameAndObjectType("password", ot), encoder.encode(password)));
        user.getParameters().add(new Parameter(user, attributeService.findByNameAndObjectType("role", ot), "USER"));
        try {
            objectService.save(user);
        } catch (RegistrationException e) {
            return "redirect:/registration?reg-err";
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

    @RequestMapping(value = "/phone/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUser(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting User with id " + id);


        objectService.delete(id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }


    @RequestMapping(value = {"/search"})
    public String search(Model model, HttpServletRequest request) {
        model.addAttribute("current", "/WEB-INF/views/search.jsp");
        return "index";
    }

    @RequestMapping(value = {"/request/{keyword}"}, method = RequestMethod.GET)
    public ResponseEntity<List<Object>> searchRequest(@PathVariable("keyword") String keyword) throws URISyntaxException {
        List<Object> result = objectService.findByNameContaining(keyword);
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).getParent() == null) result.remove(result.get(i));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = {"/shop"}, method = RequestMethod.GET)
    public String shop(Model model) throws URISyntaxException {
        model.addAttribute("current", "/WEB-INF/views/shop.jsp");
        return "index";
    }

    @RequestMapping(value = {"/products"}, method = RequestMethod.GET)
    public ResponseEntity<List<Object>> allProducts(Model model) throws URISyntaxException {
        List<Object> result = new ArrayList<>();
        result.addAll(objectService.findByObjectType(objectTypeService.findByName("Phone")));
        result.addAll(objectService.findByObjectType(objectTypeService.findByName("Headphones")));
        result.addAll(objectService.findByObjectType(objectTypeService.findByName("Charger")));
        result.addAll(objectService.findByObjectType(objectTypeService.findByName("Battery")));
        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = {"/contacts"}, method = RequestMethod.GET)
    public String contacts(Model model) {
        model.addAttribute("current", "/WEB-INF/views/contacts.jsp");
        return "index";
    }


// WORKING WITH CART ---------------------------------------------------------------------------------------------------

    @RequestMapping(value = "/addToCart", method = RequestMethod.GET)
    public @ResponseBody
    String addToCart(@RequestParam int itemId) {
        Object cart = securityService.getCart();
        if (cart == null) {
            return "0";
        }
        Reference item = new Reference(cart, objectService.findById(itemId), "item", attributeService.findByNameAndObjectType("item", objectTypeService.findByName("cart")));
        cart.getReferences().add(item);
        referenceService.save(item);
        return String.valueOf(cart.getReferences().size());
    }

    @RequestMapping(value = {"/cart"})
    public String cart(Model model) {
        model.addAttribute("current", "/WEB-INF/views/cart.jsp");
        return "index";
    }

    @RequestMapping(value = {"/showCart"}, method = RequestMethod.GET)
    public ResponseEntity<Object> cartContent() {
        Object cart = securityService.getCart();
        if (cart == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        if (cart.getReferences().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @RequestMapping(value = "/showCart/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> removeFromCart(@PathVariable("id") int id) {
        Object cart = securityService.getCart();
        List<Reference> refToDelete = referenceService.findByObjectAndRefObject(cart, objectService.findById(id));
        for (Reference r: refToDelete) {
            referenceService.delete(r);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = {"/cartIndex"}, method = RequestMethod.GET)
    public ResponseEntity<String> cartIndex() {
        Object cart = securityService.getCart();
        String result = String.valueOf(cart == null ? 0 : cart.getReferences().size());
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    @RequestMapping(value = {"/checkout"}, method = RequestMethod.POST)
    public ResponseEntity<Object> checkout(@RequestBody String checkoutMap) {
        Object cart = securityService.getCart();
        Map<Integer, Integer> itemsToBuy = new HashMap<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            itemsToBuy = mapper.readValue(checkoutMap, new TypeReference<HashMap<Integer, Integer>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Map.Entry<Integer, Integer> entry: itemsToBuy.entrySet()) {
            Object item = objectService.findById(entry.getKey());
            Attribute itemQuantity = attributeService.findByNameAndObjectType("quantity", item.getObjectType());
            Parameter quantityToChange = parameterService.findByObjectAndAttribute(item.getId(), itemQuantity.getId());
            int wasInShop = Integer.parseInt(quantityToChange.getValue());
            quantityToChange.setValue(String.valueOf(wasInShop - entry.getValue()));
            parameterService.save(quantityToChange);
        }
        List<Reference> allInCart = cart.getReferences();
        for (Reference r: allInCart) {
            referenceService.delete(r);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    // WORKING WITH DETAILS ---------------------------------------------------------------------------------------------------

    @RequestMapping(value = {"/detailed/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<Object> itemDetails2(@PathVariable("id") int id) throws URISyntaxException {
        Object object = objectService.findById(id);
        for (Parameter param: object.getParameters()) {
            if ("rating".equals(param.getAttribute().getName())) {
                int num = Integer.parseInt(param.getValue());
                num++;
                param.setValue(String.valueOf(num));
                parameterService.save(param);
            }
        }
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @RequestMapping(value = {"/details/{id}"}, method = RequestMethod.GET)
    public String details(@PathVariable("id") int id, Model model) throws URISyntaxException {
        model.addAttribute("current", "/WEB-INF/views/details.jsp");
        return "index";
    }

    @RequestMapping(value = "/details/buy", method = RequestMethod.GET)
    public ResponseEntity<Object> buy(@RequestParam int itemId, @RequestParam int quantity, Model model) {
        Object cart = securityService.getCart();
        if (cart == null) return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
        Object toBuy = objectService.findById(itemId);
        for (int i = 0; i < quantity; i++) {
            Reference inCartItem = new Reference(cart, toBuy, "item", attributeService.findByNameAndObjectType("item", objectTypeService.findByName("cart")));
            cart.getReferences().add(inCartItem);
            referenceService.save(inCartItem);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = {"/related/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<List<Object>> related(@PathVariable("id") int id) throws URISyntaxException {
        List<Object> sameBrand = objectService.findByParent(objectService.findById(id).getParent());
        List<Object> result = new ArrayList<>();
        int number = 5;
        for (Object aSameBrand : sameBrand) {
            if (aSameBrand != null) {
                if (aSameBrand.getId() == id) continue;
                result.add(aSameBrand);
                number--;
            }
            if (number == 0) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
