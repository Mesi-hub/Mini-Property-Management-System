package edu.miu.cs545.api.service;

import edu.miu.cs545.api.entity.BlobStorageInfo;

import java.io.InputStream;
import java.util.List;

public interface BlobStorageService {
    BlobStorageInfo storeFile(InputStream inputStream, Long size, String originalFileName);
    List<BlobStorageInfo> fillTransientInfo(List<BlobStorageInfo> blobStorageInfos);
}
