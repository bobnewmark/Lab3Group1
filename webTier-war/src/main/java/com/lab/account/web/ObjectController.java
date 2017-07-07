package com.lab.account.web;

import com.shop.database.entities.Object;
import com.shop.database.entities.Parameter;
import com.shop.database.exceptions.RegistrationException;
import com.shop.database.services.ObjectService;
import com.shop.database.services.ObjectTypeService;
import com.shop.database.services.ParameterService;
import com.shop.database.services.Tools;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

/**
 * <code>ObjectController</code> is a controller to handle general object-related operations.
 */
@Controller
public class ObjectController {
    @Autowired
    private ObjectService objectService;
    @Autowired
    private ObjectTypeService objectTypeService;
    @Autowired
    private ParameterService parameterService;
    private final static Logger LOGGER = Logger.getLogger(ObjectController.class);

    @Autowired
    private Tools tools;

    /**
     * Method returns all brands registered in database.
     * @return all brands
     */
    @RequestMapping(value = {"/brands"}, method = RequestMethod.GET)
    public ResponseEntity<List<Object>> brands() {
        List<Object> brands = objectService.findByObjectType(objectTypeService.findByName("brand"));
        if (brands.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }

    /**
     * Method returns 5 most popular products. They are displayed on the main page of the website.
     * @return most popular products
     */
    @RequestMapping(value = {"/phone"}, method = RequestMethod.GET)
    public ResponseEntity<List<Object>> phones() {
        List<Object> objects = objectService.getObjectByAttribute("Phone", "rating", new PageRequest(0, 5));
        if (objects.isEmpty()) {
            LOGGER.info("No items to display.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(objects, HttpStatus.OK);
    }

    /**
     * Method to upload file for creating/editing an item. Admin feature only.
     * @param request carries id of an object for which file is uploaded
     * @return confirmation message
     */
    @RequestMapping(value = {"/fileUpload"}, method = RequestMethod.POST)
    public ResponseEntity upload(MultipartHttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Object object = objectService.findById(id);
        File path = new File(tools.getRootPath() + id);
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
            tools.fileUpload(entry.getValue(), tools.getRootPath() + id + "/" + entry.getKey() + "." + contentEnd);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Method for saving changes on object in the database. Admin feature only
     * @param object new object to save
     * @return id of saved object
     */
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

    /**
     * Method deletes item from the database. Admin feature only
     * @param id is object_id of item to delete
     * @return no content
     */
    @RequestMapping(value = "/phone/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUser(@PathVariable("id") int id) {
        LOGGER.info("Fetching & Deleting Object with id " + id);
        objectService.delete(id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

    /**
     * Method for making a search by in names of items.
     * @param keyword is a user input in search field
     * @param page is a page number of result to display
     * @return result of the search
     * @throws URISyntaxException in case when string could not be parsed as a URI reference.
     */
    @RequestMapping(value = {"/request/{keyword}/{page}"}, method = RequestMethod.GET)
    public ResponseEntity<Page<Object>> searchRequest(@PathVariable("keyword") String keyword, @PathVariable("page") int page) throws URISyntaxException {
        Page<Object> result = objectService.findByNameContaining(keyword, new PageRequest(page - 1, 4));
        if (result.getTotalPages() < page && result.getTotalPages() > 0) {
            result = objectService.findByNameContaining(keyword, new PageRequest(result.getTotalPages() - 1, 4));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Method for displaying items of a definite type user chose.
     * @param clas is object type to display
     * @param page is a page number of result to display
     * @return result of the request
     * @throws URISyntaxException in case when string could not be parsed as a URI reference.
     */
    @RequestMapping(value = {"/products/{class}/{page}"}, method = RequestMethod.GET)
    public ResponseEntity<Page<Object>> classOfProducts(@PathVariable("class") String clas, @PathVariable("page") int page) throws URISyntaxException {
        Page<Object> result = objectService.getObjectByType(clas, new PageRequest(page-1, 4));
        if(result.getTotalPages()<page && result.getTotalPages()>0){
            result = objectService.getObjectByType(clas, new PageRequest(result.getTotalPages()-1, 4));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Method for displaying the shop content by page.
     * @param page is a page number of result to display
     * @return items to display on the curent page
     * @throws URISyntaxException in case when string could not be parsed as a URI reference.
     */
    @RequestMapping(value = {"/products/{page}"}, method = RequestMethod.GET)
    public ResponseEntity<Page<Object>> allProducts(@PathVariable("page") int page) throws URISyntaxException {
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
}