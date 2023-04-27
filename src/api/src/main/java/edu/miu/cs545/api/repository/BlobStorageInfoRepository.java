package edu.miu.cs545.api.repository;

import edu.miu.cs545.api.entity.BlobStorageInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlobStorageInfoRepository extends JpaRepository<BlobStorageInfo, String> {
}
