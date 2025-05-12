package com.yam.backend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.yam.backend.service.enums.MediaType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class MediaService {

    private final Cloudinary cloudinary;

    public String uploadMedia(MultipartFile file, MediaType type) {
        try {
            return cloudinary.uploader()
                    .upload(file.getBytes(), ObjectUtils.asMap("resource_type", type.toString().toLowerCase()))
                    .get("url").toString();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
