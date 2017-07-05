package com.lab.account.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.database.entities.*;
import com.shop.database.entities.Object;
import com.shop.database.exceptions.RegistrationException;
import com.shop.database.services.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.net.URISyntaxException;
import java.util.*;


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

    private final static Logger LOGGER = Logger.getLogger(UserController.class);

    @Autowired
    private Tools tools;
    private final String HOME = "C:/folderForLab3Images/";

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

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
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

    @RequestMapping(value = "/save-type/", method = RequestMethod.POST)
    public ResponseEntity<Integer> saveType( @RequestBody ObjectType objectType) {
        objectType.setProduct(true);
        for(Attribute a: objectType.getAttributes()){
            a.setObjectType(objectType);

        }
        objectType = objectTypeService.save(objectType);
        return new ResponseEntity<>(objectType.getId(), HttpStatus.OK);
    }

    @RequestMapping(value = {"/types"}, method = RequestMethod.GET)
    public ResponseEntity<List<Object>> types() {
        List<ObjectType> types = objectTypeService.findAll();
        List<Object> objects = new ArrayList<>();
        for (ObjectType oType : types) {
            Object obj = new Object();
            obj.setObjectType(oType);
            obj.setName(oType.getName());
            for (Attribute attr : oType.getAttributes()) {
                Parameter param = new Parameter();
                param.setAttribute(attr);
                param.setObject(obj);
                obj.getParameters().add(param);
            }
            objects.add(obj);
        }
        if (objects.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(objects, HttpStatus.OK);
    }

    @RequestMapping(value = {"/brands"}, method = RequestMethod.GET)
    public ResponseEntity<List<Object>> brands() {
        List<Object> brands = objectService.findByObjectType(objectTypeService.findByName("brand"));
        if (brands.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }

    @RequestMapping(value = {"/my-icons"}, method = RequestMethod.GET)
    public ResponseEntity<List<Object>> icons() {
        List<Object> icons = objectService.findByObjectType(objectTypeService.findByName("icons"));
        if (icons.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(icons, HttpStatus.OK);
    }
    @RequestMapping(value = {"/phone"}, method = RequestMethod.GET)
    public ResponseEntity<List<Object>> phones() {
        List<Object> objects = objectService.getObjectByAttribute("Phone", "rating", new PageRequest(0, 5));
        if (objects.isEmpty()) {
            LOGGER.info("No items to display.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(objects, HttpStatus.OK);
    }


    @RequestMapping(value = {"/fileUpload"}, method = RequestMethod.POST)
    public ResponseEntity upload(MultipartHttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Object object = objectService.findById(id);
        File path = new File(HOME + id);
        if (!path.exists()) {
            boolean status = path.mkdirs();
        }
        for (Map.Entry<String, MultipartFile> entry : request.getFileMap().entrySet()) {
            String contentName = entry.getValue().getContentType().split("/")[0];
            String contentEnd = entry.getValue().getContentType().split("/")[1];
            for (Parameter param : object.getParameters()) {
                if (param.getAttribute().getName().equals(entry.getKey())) {
                    param.setValue("/icons/" + id + "/" + entry.getKey() + "." + contentEnd);
                    object.getMapParameters().put(entry.getKey(), param);
                    parameterService.save(param);
                }
            }
            if (!contentName.equals("image")) {
                new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
            tools.fileUpload(entry.getValue(), HOME + id + "/" + entry.getKey() + "." + contentEnd);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/icons/{id}/{image}.{end}", method = RequestMethod.GET)
    @ResponseBody
    protected void doGet(@PathVariable("image") String image, @PathVariable("id") String id, @PathVariable("end") String end, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filePath = request.getRequestURI();
        File file = new File(HOME + id + "/" + image + "." + end);
        InputStream input = new FileInputStream(file);
        response.setContentLength((int) file.length());
        response.setContentType(new MimetypesFileTypeMap().getContentType(file));
        OutputStream output = response.getOutputStream();
        byte[] bytes = new byte[4096];
        int read = 0;
        while ((read = input.read(bytes, 0, 4096)) != -1) {
            output.write(bytes, 0, read);
            output.flush();
        }
        input.close();
        output.close();
    }

    @RequestMapping(value = "/phone/", method = RequestMethod.POST)
    public ResponseEntity<Integer> updateUser(@RequestBody Object object) {
        for (Parameter param : object.getParameters()) {
            param.setObject(object);
            LOGGER.info("Saving object " + param.getAttribute().getName() + " " + param.getValue());
            if (param.getAttribute().getName().equals("rating")) {
                param.setValue("0");
            }
        }
        try {
            object = objectService.save(object);
        } catch (RegistrationException e) {
            LOGGER.error("Cannot save changes to object " + object.getName() + ", ", e);
        }
        return new ResponseEntity<>(object.getId(), HttpStatus.OK);
    }

    @RequestMapping(value = "/phone/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUser(@PathVariable("id") int id) {
        LOGGER.info("Fetching & Deleting Object with id " + id);
        objectService.delete(id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = {"/search"})
    public String search(Model model, HttpServletRequest request) {
        model.addAttribute("current", "/WEB-INF/views/search.jsp");
        return "index";
    }

    @RequestMapping(value = {"/request/{keyword}/{page}"}, method = RequestMethod.GET)
    public ResponseEntity<Page<Object>> searchRequest(@PathVariable("keyword") String keyword, @PathVariable("page") int page) throws URISyntaxException {
        Page<Object> result = objectService.findByNameContaining(keyword, new PageRequest(page - 1, 4));
        if (result.getTotalPages() < page && result.getTotalPages() > 0) {
            result = objectService.findByNameContaining(keyword, new PageRequest(result.getTotalPages() - 1, 4));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = {"/products/{class}/{page}"}, method = RequestMethod.GET)
    public ResponseEntity<Page<Object>> classOfProducts(@PathVariable("class") String clas, @PathVariable("page") int page) throws URISyntaxException {
        Page<Object> result = objectService.getObjectByType(clas, new PageRequest(page-1, 4));
        if(result.getTotalPages()<page && result.getTotalPages()>0){
            result = objectService.getObjectByType(clas, new PageRequest(result.getTotalPages()-1, 4));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = {"/shop"}, method = RequestMethod.GET)
    public String shop(Model model) throws URISyntaxException {
        model.addAttribute("current", "/WEB-INF/views/shop.jsp");
        return "index";
    }


    @RequestMapping(value = {"/products/{page}"}, method = RequestMethod.GET)
    public ResponseEntity<Page<Object>> allProducts(@PathVariable("page") int page, Model model) throws URISyntaxException {
        Page<Object> result = objectService.getAllProducts( new PageRequest(page-1, 4));
        if(result.getTotalPages()<page && result.getTotalPages()>0){
            result = objectService.getAllProducts(new PageRequest(result.getTotalPages()-1, 4));
        }
        if (result.getContent().isEmpty()) {
            LOGGER.info("No products to display.");

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
        ObjectType cartOT = objectTypeService.findByName("cart");
        Reference item = new Reference(cart, objectService.findById(itemId), "item", attributeService.findByNameAndObjectType("item", cartOT));
        int amountOfItemInCart = 0;
        for (Map.Entry<String, Parameter> entry : item.getRefObject().getMapParameters().entrySet()) {
            if (entry.getKey().equals("quantity")) {
                amountOfItemInCart = Integer.parseInt(entry.getValue().getValue());
            }
        }
        if (referenceService.findByObjectAndRefObject(cart, item.getRefObject()).size() < amountOfItemInCart) {
            cart.getReferences().add(item);
            referenceService.save(item);
        }
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
        for (Reference ref: cart.getReferences()) {
            int availableInShop = Integer.parseInt(ref.getRefObject().getMapParameters().get("quantity").getValue());
            if (referenceService.findByObjectAndRefObject(cart, ref.getRefObject()).size() > availableInShop) {
                for (int i = 0; i < referenceService.findByObjectAndRefObject(cart, ref.getRefObject()).size() - availableInShop ; i++) {
                    referenceService.delete(referenceService.findByObjectAndRefObject(cart, ref.getRefObject()).get(0));
                }
            }
        }
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @RequestMapping(value = "/showCart/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> removeFromCart(@PathVariable("id") int id) {
        Object cart = securityService.getCart();
        List<Reference> refToDelete = referenceService.findByObjectAndRefObject(cart, objectService.findById(id));
        for (Reference ref : refToDelete) {
            referenceService.delete(ref);
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
    public ResponseEntity<Map<String, String>> checkout(@RequestBody String checkoutMap) {
        Object cart = securityService.getCart();
        Map<Integer, Integer> itemsToBuy = new HashMap<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            itemsToBuy = mapper.readValue(checkoutMap, new TypeReference<HashMap<Integer, Integer>>() {
            });
        } catch (IOException e) {
            LOGGER.error("Cannot proceed with checkout, ", e);
        }

        // Checking if customer is trying to buy too much
        boolean buyingTooMuch = false;
        for (Map.Entry<Integer, Integer> entry : itemsToBuy.entrySet()) {
            Object item = objectService.findById(entry.getKey());
            Attribute itemQuantity = attributeService.findByNameAndObjectType("quantity", item.getObjectType());
            Parameter quantityToChange = parameterService.findByObjectAndAttribute(item.getId(), itemQuantity.getId());
            int wasInShop = Integer.parseInt(quantityToChange.getValue());
            if (entry.getValue() > wasInShop) {
                buyingTooMuch = true;
                for (Reference ref : cart.getReferences()) {
                    if (entry.getKey() == ref.getRefObject().getId()) {
                        if (referenceService.findByObjectAndRefObject(cart, ref.getRefObject()).size() > wasInShop) {
                            for (int i = 0; i < referenceService.findByObjectAndRefObject(cart, ref.getRefObject()).size() - wasInShop; i++) {
                                referenceService.delete(referenceService.findByObjectAndRefObject(cart, ref.getRefObject()).get(0));
                            }
                        }
                    }
                }
            }
        }
        // If shop doesn't have enough items customer gets a notification
        if (buyingTooMuch) {
            Map<String, String> map = new HashMap<>();
            map.put("message", "Trying to buy too much!");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        // Otherwise customer buys items - quantity decreases
        else {
            for (Map.Entry<Integer, Integer> entry : itemsToBuy.entrySet()) {
                Object item = objectService.findById(entry.getKey());
                Attribute itemQuantity = attributeService.findByNameAndObjectType("quantity", item.getObjectType());
                Parameter quantityToChange = parameterService.findByObjectAndAttribute(item.getId(), itemQuantity.getId());
                int wasInShop = Integer.parseInt(quantityToChange.getValue());
                quantityToChange.setValue(String.valueOf(wasInShop - entry.getValue()));
                parameterService.save(quantityToChange);
            }
        }
        // All references are removed
        List<Reference> allInCart = cart.getReferences();
        for (Reference ref : allInCart) {
            referenceService.delete(ref);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    // WORKING WITH DETAILS ---------------------------------------------------------------------------------------------------

    @RequestMapping(value = {"/detailed/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<Object> itemDetails2(@PathVariable("id") int id) throws URISyntaxException {
        Object object = objectService.findById(id);
        for (Parameter param : object.getParameters()) {
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
        Object object = objectService.findById(id);
        for (Parameter param : object.getParameters()) {
            if ("rating".equals(param.getAttribute().getName())) {
                int num = Integer.parseInt(param.getValue());
                num++;
                param.setValue(String.valueOf(num));
                parameterService.save(param);
            }
        }
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
