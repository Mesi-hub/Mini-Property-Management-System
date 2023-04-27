
package edu.miu.cs545.api.controller;

import edu.miu.cs545.api.dto.ImageFileInfoDto;
import edu.miu.cs545.api.dto.PropertyDto;
import edu.miu.cs545.api.entity.BlobStorageInfo;
import edu.miu.cs545.api.service.BlobStorageService;
import edu.miu.cs545.api.service.PropertyService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    @Autowired
    PropertyService propertyService;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    BlobStorageService blobStorageService;
    @Autowired
    ControllerSecurityUtil controllerSecurityUtil;
    @Value("${spring.cloud.azure.storage.blob.end-point}/${spring.cloud.azure.storage.blob.container-name}/${spring.cloud.azure.storage.blob.property.stockimage}")
    private String stockImagePath;
    @Value("${spring.cloud.azure.storage.blob.property.stockimage}")
    private String stockImageName;
    @GetMapping
    public List<PropertyDto> getProperties(@RequestParam(value = "city" ,required = false) String city,
                                        @RequestParam(value = "max" ,required = false) Double max,
                                        @RequestParam(value = "min" ,required = false) Double min,
                                        @RequestParam(value = "room" ,required = false) Integer room) {

        return addStockImage(this.propertyService.findProperByFilter(city, max, min, room));
    }
    @GetMapping("/{id}")
    public PropertyDto getPropertyById(@PathVariable("id") Long id) {
        return addStockImage(this.propertyService.findPropertyById(id));
    }

    @PostMapping()
    @PreAuthorize("hasRole('OWNER')")
    public PropertyDto createProperty(@RequestBody PropertyDto property) {
        return addStockImage(this.propertyService.createProperty( property, controllerSecurityUtil.getLoggedinUser()));
    }
    @PutMapping
    @PreAuthorize("hasRole('OWNER')")
    public PropertyDto updateProperty(@RequestBody PropertyDto property) {
        return addStockImage(this.propertyService.updateProperty(property));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public void deleteProperty(@PathVariable("id") Long id){
        this.propertyService.deleteProperty(id);
    }

    private List<PropertyDto> addStockImage(List<PropertyDto> dtos){
        if(dtos == null) {
            return null;
        }
        return dtos.stream().map(x-> addStockImage(x)).toList();
    }
    private PropertyDto addStockImage(PropertyDto dto){
        if(dto == null || dto.getImages().size() == 0) {
            dto.setImages(getStockImageArray());
        }
        return dto;
    }

    private List<ImageFileInfoDto> getStockImageArray(){
        List<ImageFileInfoDto> stockFileInfo = new ArrayList<>();
        stockFileInfo.add(new ImageFileInfoDto(stockImageName, stockImagePath));       
        return stockFileInfo;
    }
}
