package com.yam.backend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class MediaService {

    private final Cloudinary cloudinary;

    public String uploadMedia(MultipartFile file) {
        try {
            return cloudinary.uploader()
                    .upload(file.getBytes(), ObjectUtils.emptyMap())
                    .get("url").toString();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
