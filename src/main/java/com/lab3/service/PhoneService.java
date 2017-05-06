package com.lab3.service;

import com.lab3.model.Phone;

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

    public boolean isPhoneExist(Phone user);

}
