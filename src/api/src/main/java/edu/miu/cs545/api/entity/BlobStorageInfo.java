package edu.miu.cs545.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Data
public class BlobStorageInfo {
    @Id
    String id;
    @Transient
    String fullPath;
    String relativePath;
    long size;
    String originalFileName;
}
