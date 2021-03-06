package com.oliinyk.practice.touragencyserver.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import com.cloudinary.utils.ObjectUtils;
import com.oliinyk.practice.touragencyserver.service.CloudinaryService;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Map;

@Service
@PropertySource("classpath:cloudinary.properties")
public class CloudinaryServiceImpl implements CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl(Environment env) {
        this.cloudinary = new Cloudinary(env.getProperty("cloudinary.env"));
    }

    @Override
    public String uploadFile(MultipartFile file, String folder) {
        Uploader uploader = cloudinary.uploader();

        String originalName = file.getOriginalFilename();
        String fileName = originalName.substring(0, originalName.lastIndexOf("."));

        Map<?, ?> options = ObjectUtils.asMap(
                "public_id", fileName,
                "folder", folder,
                "overwrite", true
        );

        Map<?, ?> result;

        try {
            InputStream inputStream = file.getInputStream();
            result = uploader.uploadLarge(inputStream, options);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return (String) result.get("url");
    }
}
