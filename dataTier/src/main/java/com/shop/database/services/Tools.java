package com.shop.database.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * <code>Tools</code> is an interface for uploading files
 * when creating new objects under admin role.
 */
public interface Tools {
    String fileUpload(MultipartFile file, String name);
    String getRootPath();
}
