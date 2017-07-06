package com.lab.account.web;

import com.shop.database.entities.Object;
import com.shop.database.entities.Parameter;
import com.shop.database.entities.Reference;
import com.shop.database.services.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class OtherController {

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

    private final static Logger LOGGER = Logger.getLogger(OtherController.class);

    @Autowired
    private Tools tools;


    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public String getIndexPage(Model model) {
        model.addAttribute("current", "/WEB-INF/views/main.jsp");
        return "index";
    }

    @RequestMapping(value = {"/welcome"}, method = RequestMethod.GET)
    public ResponseEntity<List<Object>> welcome() {
        List<Object> objects = objectService.findByObjectType(objectTypeService.findById(1));
        if (objects.isEmpty()) {
            LOGGER.info("");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(objects, HttpStatus.OK);
    }

    @RequestMapping(value = {"/search"})
    public String search(Model model, HttpServletRequest request) {
        model.addAttribute("current", "/WEB-INF/views/search.jsp");
        return "index";
    }

    @RequestMapping(value = {"/shop"}, method = RequestMethod.GET)
    public String shop(Model model) throws URISyntaxException {
        model.addAttribute("current", "/WEB-INF/views/shop.jsp");
        return "index";
    }
    @RequestMapping(value = {"/contacts"}, method = RequestMethod.GET)
    public String contacts(Model model) {
        model.addAttribute("current", "/WEB-INF/views/contacts.jsp");
        return "index";
    }

// WORKING WITH CART ---------------------------------------------------------------------------------------------------



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

    @RequestMapping(value = "/icons/{id}/{image}.{end}", method = RequestMethod.GET)
    @ResponseBody
    protected void doGet(@PathVariable("image") String image, @PathVariable("id") String id, @PathVariable("end") String end, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filePath = request.getRequestURI();
        File file = new File(tools.getRootPath() + id + "/" + image + "." + end);
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
}
