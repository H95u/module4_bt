package com.example.demo_upload;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class UploadService {
    public void uploadFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        FileCopyUtils.copy(file.getBytes(), new File("D:\\module4\\demo_upload\\src\\main\\webapp\\files\\" + fileName));
    }
}
