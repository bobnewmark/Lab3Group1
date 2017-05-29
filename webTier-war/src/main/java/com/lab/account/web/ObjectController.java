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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

//    @RequestMapping(value = "/phones", method = RequestMethod.GET)
//    public ResponseEntity<List<Object>> allPhones() {
//        //CREATING OBJECT TYPE FOR SMARTPHONES
//        objectTypeService.save(new ObjectType("Smartphone", null, null));
//        //CREATING ATTRIBUTES
//        attributeService.save(new Attribute("price", objectTypeService.findByName("Smartphone").get(0)));
//        attributeService.save(new Attribute("info", objectTypeService.findByName("Smartphone").get(0)));
//        //CREATING TWO SMARTPHONES
//        objectService.save(new Object("HTC", objectTypeService.findByName("Smartphone").get(0), null));
//        objectService.save(new Object("Nokia", objectTypeService.findByName("Smartphone").get(0), null));
//        //FILLING PARAMETERS FOR THEM
//        parameterService.save(new Parameter(objectService.findByName("HTC").get(0), attributeService.findByName("price").get(0), "50$"));
//        parameterService.save(new Parameter(objectService.findByName("HTC").get(0), attributeService.findByName("info").get(0), "Nice phone, good battery"));
//        parameterService.save(new Parameter(objectService.findByName("Nokia").get(0), attributeService.findByName("price").get(0), "60$"));
//        parameterService.save(new Parameter(objectService.findByName("Nokia").get(0), attributeService.findByName("info").get(0), "Nice phone, good display"));
//
//        List<Object> phones = objectService.findByObjectType(objectTypeService.findByName("Smartphone").get(0));
//        if (phones.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
//        }
//
//        return new ResponseEntity<>(phones, HttpStatus.OK);
//    }

    @RequestMapping(value = {"/phones", "/laba/phones"}, method = RequestMethod.GET)
    public String showPage(Model model) {

        objectTypeService.save(new ObjectType("Smartphone", null, null));

        attributeService.save(new Attribute("price", objectTypeService.findByName("Smartphone").get(0)));
        attributeService.save(new Attribute("info", objectTypeService.findByName("Smartphone").get(0)));
        attributeService.save(new Attribute("icon", objectTypeService.findByName("Smartphone").get(0)));

        objectService.save(new Object("Apple Iphone 6S Gold", objectTypeService.findByName("Smartphone").get(0), null));
        objectService.save(new Object("LG K500 X VIEW", objectTypeService.findByName("Smartphone").get(0), null));

        parameterService.save(new Parameter(objectService.findByName("Apple Iphone 6S Gold").get(0), attributeService.findByName("icon").get(0), "${contextPath}/resources/images/phones/APPLE IPHONE 6S 128GB GOLD.jpg"));
        parameterService.save(new Parameter(objectService.findByName("Apple Iphone 6S Gold").get(0), attributeService.findByName("price").get(0), "950$"));
        parameterService.save(new Parameter(objectService.findByName("Apple Iphone 6S Gold").get(0), attributeService.findByName("info").get(0), "Nice phone for those who don't like android"));
        parameterService.save(new Parameter(objectService.findByName("LG K500 X VIEW").get(0), attributeService.findByName("icon").get(0), "${contextPath}/resources/images/phones/LG K500 X VIEW WHITE.jpg"));
        parameterService.save(new Parameter(objectService.findByName("LG K500 X VIEW").get(0), attributeService.findByName("price").get(0), "60$"));
        parameterService.save(new Parameter(objectService.findByName("LG K500 X VIEW").get(0), attributeService.findByName("info").get(0), "Nice phone, good display"));



        List<Object> list = objectService.findByObjectType(objectTypeService.findByName("Smartphone").get(0));
        model.addAttribute("allPhones", list);
        return "phones";
    }


}
