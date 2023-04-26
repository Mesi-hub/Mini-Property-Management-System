package edu.miu.cs545.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
@NoArgsConstructor
public class ImageFileInfoDto {
    String id;
    String fullPath;
}
