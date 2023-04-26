package edu.miu.cs545.api.service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import edu.miu.cs545.api.entity.BlobStorageInfo;
import edu.miu.cs545.api.repository.BlobStorageInfoRepository;
import jakarta.persistence.Transient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Service
public class BlobStorageServiceImpl implements BlobStorageService{
    @Value("${spring.cloud.azure.storage.blob.conn-str}")
    private String connString;
    @Value("${spring.cloud.azure.storage.blob.container-name}")
    private String containerName;

    @Value("${spring.cloud.azure.storage.blob.end-point}/${spring.cloud.azure.storage.blob.container-name}/")
    private String endpoint;

    @Autowired
    private BlobStorageInfoRepository blobStorageInfoRepository;
    @Override
    public BlobStorageInfo storeFile(InputStream inputStream, Long size, String originalFileName) {
        BlobContainerClient container = new BlobContainerClientBuilder()
                .connectionString(connString)
                .containerName(containerName)
                .buildClient();
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString() + "_" + originalFileName;
        BlobClient blob = container.getBlobClient(id);
        blob.upload(inputStream, size, true);
        BlobStorageInfo blobStorageInfo = new BlobStorageInfo();
        blobStorageInfo.setSize(size);
        blobStorageInfo.setOriginalFileName(originalFileName);
        blobStorageInfo.setId(id);
        blobStorageInfoRepository.save(blobStorageInfo);
        blobStorageInfo.setFullPath(endpoint + id);
        return blobStorageInfo;
    }

    @Override
    public List<BlobStorageInfo> fillTransientInfo(List<BlobStorageInfo> blobStorageInfos){
        return blobStorageInfos.stream().map(x-> {
            x.setFullPath(endpoint+x.getId());
            return x;
        }).toList();
    }
}
