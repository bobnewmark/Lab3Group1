package com.lab.account;

import com.shop.database.entities.Parameter;
import com.shop.database.repositories.ObjectRepository;
import com.shop.database.services.ObjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

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
for(Parameter p: service.findByIdAttr("name", "nokia", "phone").get(0).getParameters()){
    System.out.println(p.getAttribute().getName()+" "+p.getValue());
}
        assertEquals(service.findById(1).getName(), "nokia");
    }
}