package com.shop.database.services;


import com.shop.database.entities.Phone;

import java.util.List;

/**
 * Created by Admin on 02.05.2017.
 */
public interface PhoneService {

    Phone findById(long id);

    Phone findByName(String name);

    void savePhone(Phone user);

    void updatePhone(Phone user);

    void deletePhoneById(long id);

    List<Phone> findAllPhones();

    void deleteAllPhones();

    boolean isPhoneExist(Phone user);

}
