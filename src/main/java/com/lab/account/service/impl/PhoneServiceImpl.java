package com.lab.account.service.impl;

import com.lab.account.model.Phone;
import com.lab.account.service.PhoneService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Admin on 02.05.2017.
 */
@Service("phoneService")
@Transactional
public class PhoneServiceImpl implements PhoneService {

    private static final AtomicInteger counter = new AtomicInteger();

    private static List<Phone> phones;

    static{
        phones= populateDummyPhones();
    }

    public List<Phone> findAllPhones() {
        return phones;
    }

    public Phone findById(long id) {
        for(Phone user : phones){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

    public Phone findByName(String name) {
        for(Phone user : phones){
            if(user.getBrand().equalsIgnoreCase(name)){
                return user;
            }
        }
        return null;
    }

    public void savePhone(Phone user) {
        user.setId(counter.incrementAndGet());
        phones.add(user);
    }

    public void updatePhone(Phone user) {
        int index = phones.indexOf(user);
        phones.set(index, user);
    }

    public void deletePhoneById(long id) {

        for (Iterator<Phone> iterator = phones.iterator(); iterator.hasNext(); ) {
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
        phones.clear();
    }

    private static List<Phone> populateDummyPhones(){
        List<Phone> users = new ArrayList<Phone>();
        users.add(new Phone(counter.incrementAndGet(),"Iphone", "7s", 1200));
        users.add(new Phone(counter.incrementAndGet(),"Nokia", "3310", 100));
        users.add(new Phone(counter.incrementAndGet(),"Samsung", "Galaxy s", 800));
        return users;
    }

}