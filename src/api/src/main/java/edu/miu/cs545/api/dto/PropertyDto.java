
package edu.miu.cs545.api.dto;

import edu.miu.cs545.api.entity.Address;
import lombok.Data;

@Data
public class PropertyDto {
    Long id;
    Integer noOfBedrooms;
    Double noOfBathrooms;
    Double plotSize;
    Double price;
    Double area;
    String description;
    Address address;
}
