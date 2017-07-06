package com.lab.account.web;

import com.shop.database.entities.Attribute;
import com.shop.database.entities.Object;
import com.shop.database.entities.ObjectType;
import com.shop.database.entities.Parameter;
import com.shop.database.services.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;


@Controller
public class ObjectTypesController {
    @Autowired
    private ObjectService objectService;
    @Autowired
    private ObjectTypeService objectTypeService;
    private final static Logger LOGGER = Logger.getLogger(ObjectTypesController.class);

    @RequestMapping(value = "/save-type/", method = RequestMethod.POST)
    public ResponseEntity<Integer> saveType(@RequestBody ObjectType objectType) {
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

    @RequestMapping(value = {"/my-icons"}, method = RequestMethod.GET)
    public ResponseEntity<List<Object>> icons() {
        List<Object> icons = objectService.findByObjectType(objectTypeService.findByName("icons"));
        if (icons.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(icons, HttpStatus.OK);
    }
}
