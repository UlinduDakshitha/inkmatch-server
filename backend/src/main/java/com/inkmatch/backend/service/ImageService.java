package com.inkmatch.backend.service;
import com.inkmatch.backend.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.UUID;

@Service
public class ImageService {

    public String upload(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new ResourceNotFoundException("Image file is required");
        }

        // Placeholder upload URL. Replace with Cloudinary/S3 integration when configured.
        String safeName = file.getOriginalFilename() == null ? "image" : file.getOriginalFilename();
        return "/uploads/" + UUID.randomUUID() + "-" + safeName;
    }
}

