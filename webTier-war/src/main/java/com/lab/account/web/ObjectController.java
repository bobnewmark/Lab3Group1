package com.lab.account.web;

import com.shop.database.entities.Attribute;
import com.shop.database.entities.Object;
import com.shop.database.entities.ObjectType;
import com.shop.database.entities.Parameter;
import com.shop.database.services.AttributeService;
import com.shop.database.services.ObjectService;
import com.shop.database.services.ObjectTypeService;
import com.shop.database.services.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ObjectController {

    @Autowired
    private ObjectService objectService;

    @Autowired
    private ObjectTypeService objectTypeService;

    @Autowired
    private AttributeService attributeService;

    @Autowired
    private ParameterService parameterService;

    @RequestMapping(value = "/phones", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> allPhones() {
        //CREATING OBJECT TYPE FOR SMARTPHONES
        objectTypeService.save(new ObjectType("Smartphone", null, null));
        //CREATING ATTRIBUTES
        attributeService.save(new Attribute("price", objectTypeService.findByName("Smartphone").get(0)));
        attributeService.save(new Attribute("info", objectTypeService.findByName("Smartphone").get(0)));
        //CREATING TWO SMARTPHONES
        objectService.save(new Object("HTC", objectTypeService.findByName("Smartphone").get(0), null));
        objectService.save(new Object("Nokia", objectTypeService.findByName("Smartphone").get(0), null));
        //FILLING PARAMETERS FOR THEM
        parameterService.save(new Parameter(objectService.findByName("HTC").get(0), attributeService.findByName("price").get(0), "50$"));
        parameterService.save(new Parameter(objectService.findByName("HTC").get(0), attributeService.findByName("info").get(0), "Nice phone, good battery"));
        parameterService.save(new Parameter(objectService.findByName("Nokia").get(0), attributeService.findByName("price").get(0), "60$"));
        parameterService.save(new Parameter(objectService.findByName("Nokia").get(0), attributeService.findByName("info").get(0), "Nice phone, good display"));

        List<Object> phones = objectService.findByObjectType(objectTypeService.findByName("Smartphone").get(0));
        if (phones.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }

        return new ResponseEntity<>(phones, HttpStatus.OK);
    }



}
