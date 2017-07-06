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

/**
 * <code>OtherController</code> is a controller to handle general user activity.
 */
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

    /**
     * Method for getting to the main page
     * @param model carries main page as inner jsp
     * @return index as outer page
     */
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

    /**
     * Method for getting to the search result page
     * @param model carries search page as inner jsp
     * @return index page with it
     */
    @RequestMapping(value = {"/search"})
    public String search(Model model) {
        model.addAttribute("current", "/WEB-INF/views/search.jsp");
        return "index";
    }

    /**
     * Method for getting to the shop page
     * @param model carries shop page as inner jsp
     * @return index jsp with it
     */
    @RequestMapping(value = {"/shop"}, method = RequestMethod.GET)
    public String shop(Model model) {
        model.addAttribute("current", "/WEB-INF/views/shop.jsp");
        return "index";
    }

    /**
     * Method for getting to the contacts page
     * @param model carries contacts page as inner jsp
     * @return index jsp with it
     */
    @RequestMapping(value = {"/contacts"}, method = RequestMethod.GET)
    public String contacts(Model model) {
        model.addAttribute("current", "/WEB-INF/views/contacts.jsp");
        return "index";
    }

    /**
     * Method for displayng detailed info on a product. It also increases its popularity by 1.
     * @param id is object_id of product
     * @return product to display
     */
    @RequestMapping(value = {"/detailed/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<Object> itemDetails2(@PathVariable("id") int id) {
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

    /**
     * Method to get to the detailed information page.
     * @param id product object_id
     * @param model carries details page as inner jsp
     * @return outer index jsp with it
     */
    @RequestMapping(value = {"/details/{id}"}, method = RequestMethod.GET)
    public String details(@PathVariable("id") int id, Model model) {
        model.addAttribute("current", "/WEB-INF/views/details.jsp");
        return "index";
    }

    /**
     * Method for adding items to cart from the detailed page
     * @param itemId object_id of an item to buy
     * @param quantity is its quantity
     * @return no content
     */
    @RequestMapping(value = "/details/buy", method = RequestMethod.GET)
    public ResponseEntity<Object> buy(@RequestParam int itemId, @RequestParam int quantity) {
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

    /**
     * Method for displaying related products on detailed info page.
     * @param id is object_id of main item on page
     * @return 5 or less related products
     */
    @RequestMapping(value = {"/related/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<List<Object>> related(@PathVariable("id") int id) {
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

    /**
     * Method to display images uploaded by administrator.
     * @param image is image name
     * @param id is id of its object
     * @param end is extension of the file
     * @param request user's HttpRequest
     * @param response HttpResponse to user
     * @throws IOException in case when cannot read the file or get output stream for user
     */
    @RequestMapping(value = "/icons/{id}/{image}.{end}", method = RequestMethod.GET)
    @ResponseBody
    protected void doGet(@PathVariable("image") String image, @PathVariable("id") String id, @PathVariable("end") String end, HttpServletRequest request, HttpServletResponse response) throws IOException {
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