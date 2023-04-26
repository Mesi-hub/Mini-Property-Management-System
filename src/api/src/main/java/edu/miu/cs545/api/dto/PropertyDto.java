package edu.miu.cs545.api.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.miu.cs545.api.entity.PropertyState;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDto {
    private Long id;
    private Integer noOfBedrooms;
    private Double noOfBathrooms;
    private Double plotSize;
    private Double price;
    private Double area;
    private AddressDto address;
    //private List<OfferDto> offers;
    private PropertyState status;
    //private OwnerDto owner;
    private String title;
    private String description;
}
