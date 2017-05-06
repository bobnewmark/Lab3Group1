package com.lab3.service.impl;

import com.lab3.model.Phone;
import com.lab3.service.PhoneService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Admin on 02.05.2017.
 */
@Service("userService")
@Transactional
public class PhoneServiceImpl implements PhoneService {

    private static final AtomicInteger counter = new AtomicInteger();

    private static List<Phone> users;

    static{
        users= populateDummyPhones();
    }

    public List<Phone> findAllPhones() {
        return users;
    }

    public Phone findById(long id) {
        for(Phone user : users){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

    public Phone findByName(String name) {
        for(Phone user : users){
            if(user.getBrand().equalsIgnoreCase(name)){
                return user;
            }
        }
        return null;
    }

    public void savePhone(Phone user) {
        user.setId(counter.incrementAndGet());
        users.add(user);
    }

    public void updatePhone(Phone user) {
        int index = users.indexOf(user);
        users.set(index, user);
    }

    public void deletePhoneById(long id) {

        for (Iterator<Phone> iterator = users.iterator(); iterator.hasNext(); ) {
            Phone user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
            }
        }
    }

    public boolean isPhoneExist(Phone user) {
        return findByName(user.getBrand())!=null;
    }

    public void deleteAllPhones(){
        users.clear();
    }

    private static List<Phone> populateDummyPhones(){
        List<Phone> users = new ArrayList<Phone>();
        users.add(new Phone(counter.incrementAndGet(),"Iphone", "7s", 1200));
        users.add(new Phone(counter.incrementAndGet(),"Nokia", "3310", 100));
        users.add(new Phone(counter.incrementAndGet(),"Samsung", "Galaxy s", 800));
        return users;
    }

}