package com.lab.account.web;

import com.shop.database.entities.Object;
import com.shop.database.entities.Parameter;
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
import java.io.*;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;


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

    @RequestMapping(value = {"/brands"}, method = RequestMethod.GET)
    public ResponseEntity<List<Object>> brands() {
        List<Object> brands = objectService.findByObjectType(objectTypeService.findByName("brand"));
        if (brands.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(brands, HttpStatus.OK);
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
            System.out.println(tools.getRootPath() + id + "/" + entry.getKey() + "." + contentEnd);
        }
        return new ResponseEntity(HttpStatus.OK);
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
}
