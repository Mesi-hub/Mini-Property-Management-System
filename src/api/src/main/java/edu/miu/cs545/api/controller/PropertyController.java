
package edu.miu.cs545.api.controller;

import edu.miu.cs545.api.dto.PropertyDto;
import edu.miu.cs545.api.entity.Property;
import edu.miu.cs545.api.service.PropertyService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    @Autowired
    PropertyService propertyService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public List<Property> getProperties(@RequestParam(value = "city" ,required = false) String city,
                                        @RequestParam(value = "max" ,required = false) Double max,
                                        @RequestParam(value = "min" ,required = false) Double min,
                                        @RequestParam(value = "room" ,required = false) Integer room) {

        return this.propertyService.findProperByFilter(city, max, min, room);
    }
    @GetMapping("/{id}")
    public PropertyDto getPropertyById(@PathVariable("id") Long id) {
        return this.propertyService.findPropertyById(id);
    }

    @PostMapping()
    @PreAuthorize("hasRole('OWNER')")
    public PropertyDto createProperty(@RequestBody PropertyDto property) {
        return this.propertyService.createProperty( property);
    }
    @PutMapping
    @PreAuthorize("hasRole('OWNER')")
    public PropertyDto updateProperty(@RequestBody PropertyDto property) {
        return this.propertyService.updateProperty(property);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public void deleteProperty(@PathVariable("id") Long id){
        this.propertyService.deleteProperty(id);
    }
}
