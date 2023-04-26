package edu.miu.cs545.api.dto;

import edu.miu.cs545.api.entity.BlobStorageInfo;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ImageFileInfoConverter implements Converter<BlobStorageInfo, ImageFileInfoDto> {
    @Value("${spring.cloud.azure.storage.blob.end-point}/${spring.cloud.azure.storage.blob.container-name}/")
    private String endpoint;

    @Override
    public ImageFileInfoDto convert(MappingContext<BlobStorageInfo, ImageFileInfoDto> context) {
        BlobStorageInfo s = context.getSource();
        ImageFileInfoDto d = context.getDestination();
        if(s== null)
            return null;
        if(d == null)
            d = new ImageFileInfoDto();
        d.setId(s.getId());
        d.setFullPath(endpoint + s.getId());
        return d;
    }
}