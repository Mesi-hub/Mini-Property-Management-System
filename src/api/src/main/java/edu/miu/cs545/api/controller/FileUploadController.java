package edu.miu.cs545.api.controller;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import com.azure.storage.blob.models.BlobLeaseRequestConditions;
import com.azure.storage.blob.specialized.BlobLeaseClientBuilder;
import edu.miu.cs545.api.entity.BlobStorageInfo;
import edu.miu.cs545.api.service.BlobStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

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
