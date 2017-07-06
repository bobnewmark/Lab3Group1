package com.shop.database.services.impl;

import com.shop.database.services.Tools;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * <code>ToolsImpl</code> is a realization of the <code>Tools</code> interface,
 * this service uploads file from admin user.
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

    @Override
    public String getRootPath() {
        String resourcePath = null;
        if(System.getProperty("os.name").toLowerCase().contains("Linux".toLowerCase())) {
            resourcePath = "/home/folderForLab3Images/";
        }else if (System.getProperty("os.name").toLowerCase().contains("Windows".toLowerCase())){
            resourcePath = "C:/folderForLab3Images/";
        }
        return resourcePath;
    }
}
