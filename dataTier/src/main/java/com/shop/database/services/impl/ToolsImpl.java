package com.shop.database.services.impl;

import com.shop.database.services.Tools;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by viko0417 on 7/1/2017.
 */
@Service
public class ToolsImpl implements Tools {
    public String fileUpload(MultipartFile file, String name) {
        String message;
        if (!file.isEmpty()) {
            try {
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(new File(name)));
                FileCopyUtils.copy(file.getInputStream(), stream);
                stream.close();
                message = "succ";
            } catch (Exception e) {
                message = "errorUpload";
            }
        } else {
            message = "empty";
        }
        return message;
    }
}
