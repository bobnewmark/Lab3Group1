package com.lab.account;

import com.shop.database.repositories.ObjectRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:appconfig-root.xml")
public class Testes {
    //    @Autowired
//    private ObjectRepository service;
    public Testes(){}
    @Test
    public void test(){
        for (int i = 0; i < 1; i++) {
            System.out.println("OJDBC DRIVER FROM RESOURCE DIRECTORY IS NOT UNDERSTOOD BY SEMAPHORE");
        }
//       for(Parameter p: service.findByAttrAndObjectType("Phone", "name", "APPLE IPHONE 7 32GB ROSE GOLD").get(0).getParameters()){
//            System.out.println(p.getAttribute().getName()+" "+p.getValue());
//       }
        assertEquals("1", "1");
    }
}