package edu.miu.cs545.api.controller;

import edu.miu.cs545.api.entity.BlobStorageInfo;
import edu.miu.cs545.api.service.BlobStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/blob")
public class FileUploadController {
    //TODO delete once tested
    @Autowired
    BlobStorageService blobStorageService;

    @PostMapping("/upload")
    public BlobStorageInfo uploadFile(@RequestParam("file") MultipartFile file) throws Exception{
        BlobStorageInfo info = blobStorageService.storeFile(file.getInputStream(), file.getSize(), file.getOriginalFilename());
        return info;
    }
}
