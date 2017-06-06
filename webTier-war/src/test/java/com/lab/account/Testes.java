package com.lab.account;

import com.shop.database.entities.Parameter;
import com.shop.database.repositories.ObjectRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;


/**
 * Created by Admin on 08.09.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:appconfig-root.xml")
public class Testes {
    @Autowired
    private ObjectRepository service;
   public Testes(){}
    @Test
    public void test(){
       for(Parameter p: service.findByIdAttr("Phone", "name", "IPHONE 7 32GB ROSE GOLD").get(0).getParameters()){
            System.out.println(p.getAttribute().getName()+" "+p.getValue());
       }
        assertEquals("1", "1");
    }
}