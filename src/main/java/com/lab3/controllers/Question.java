package com.lab3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Admin on 01.05.2017.
 */
@Controller
public class Question {
    @RequestMapping(value="/answer", method=RequestMethod.POST)
    public @ResponseBody String answers(@RequestParam("newName") String newName){
        System.out.println("привет");
        ModelAndView mw = new ModelAndView();
        mw.addObject("name", newName);
        System.out.println(newName);
        String response = "{\"message\":\"Post With ngResource - name: " + newName + "\"}";
        return response;
    }
}
