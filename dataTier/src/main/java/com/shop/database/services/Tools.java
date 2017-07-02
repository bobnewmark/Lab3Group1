package com.shop.database.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by viko0417 on 7/1/2017.
 */
public interface Tools {
    String fileUpload(MultipartFile file, String name);
}
