package edu.miu.cs545.api.repository;

import edu.miu.cs545.api.entity.BlobStorageInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlobStorageInfoRepository extends JpaRepository<BlobStorageInfo, String> {
}
