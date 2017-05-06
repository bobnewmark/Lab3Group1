package com.lab3.controllers;

import com.lab3.model.Phone;
import com.lab3.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
public class HelloWorldRestController {

    @Autowired
    PhoneService phoneService;

    //-------------------Retrieve All Phones--------------------------------------------------------

    @RequestMapping(value = "/phone", method = RequestMethod.GET)
    public ResponseEntity<List<Phone>> listAllPhones() {
        List<Phone> phones = phoneService.findAllPhones();
        if(phones.isEmpty()){
            return new ResponseEntity<List<Phone>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Phone>>(phones, HttpStatus.OK);
    }



    //-------------------Retrieve Single Phone--------------------------------------------------------

    @RequestMapping(value = "/phone/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Phone> getPhone(@PathVariable("id") long id) {
        System.out.println("Fetching Phone with id " + id);
        Phone phone = phoneService.findById(id);
        if (phone == null) {
            System.out.println("Phone with id " + id + " not found");
            return new ResponseEntity<Phone>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Phone>(phone, HttpStatus.OK);
    }



    //-------------------Create a Phone--------------------------------------------------------

    @RequestMapping(value = "/phone", method = RequestMethod.POST)
    public ResponseEntity<Void> createPhone(@RequestBody Phone phone,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Phone " + phone.getBrand());
        if (phoneService.isPhoneExist(phone)) {
            System.out.println("A Phone with name " + phone.getBrand() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        phoneService.savePhone(phone);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/phone/{id}").buildAndExpand(phone.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }



    //------------------- Update a Phone --------------------------------------------------------

    @RequestMapping(value = "/phone/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Phone> updatePhone(@PathVariable("id") long id, @RequestBody Phone phone) {
        System.out.println("Updating Phone " + id);

        Phone currentPhone = phoneService.findById(id);

        if (currentPhone==null) {
            System.out.println("Phone with id " + id + " not found");
            return new ResponseEntity<Phone>(HttpStatus.NOT_FOUND);
        }

        currentPhone.setBrand(phone.getBrand());
        currentPhone.setModel(phone.getModel());
        currentPhone.setPrice(phone.getPrice());

        phoneService.updatePhone(currentPhone);
        return new ResponseEntity<Phone>(currentPhone, HttpStatus.OK);
    }



    //------------------- Delete a Phone --------------------------------------------------------

    @RequestMapping(value = "/phone/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Phone> deletePhone(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Phone with id " + id);

        Phone phone = phoneService.findById(id);
        if (phone == null) {
            System.out.println("Unable to delete. Phone with id " + id + " not found");
            return new ResponseEntity<Phone>(HttpStatus.NOT_FOUND);
        }

        phoneService.deletePhoneById(id);
        return new ResponseEntity<Phone>(HttpStatus.NO_CONTENT);
    }



    //------------------- Delete All Phones --------------------------------------------------------

    @RequestMapping(value = "/phone", method = RequestMethod.DELETE)
    public ResponseEntity<Phone> deleteAllPhones() {
        System.out.println("Deleting All Phones");

        phoneService.deleteAllPhones();
        return new ResponseEntity<Phone>(HttpStatus.NO_CONTENT);
    }

}
