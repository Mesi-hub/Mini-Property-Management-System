package edu.miu.cs545.api.controller;

import edu.miu.cs545.api.dto.CustomerDto;
import edu.miu.cs545.api.dto.OfferDto;
import edu.miu.cs545.api.dto.PropertyDto;
import edu.miu.cs545.api.dto.UserDto;
import edu.miu.cs545.api.entity.Customer;
import edu.miu.cs545.api.entity.Offer;
import edu.miu.cs545.api.entity.Property;
import edu.miu.cs545.api.entity.User;
import edu.miu.cs545.api.repository.PropertyRepository;
import edu.miu.cs545.api.service.CustomerService;
import edu.miu.cs545.api.service.OfferService;
import edu.miu.cs545.api.service.PropertyService;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OfferService offerService;

//    @Autowired
//    private PropertyRepository propertyRepository;
    @Autowired
    ControllerSecurityUtil controllerSecurityUtil;

    @GetMapping
    ResponseEntity<List<CustomerDto>> findAll() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<CustomerDto> findById(@PathVariable long id) {
        return ResponseEntity.ok(customerService.findById(id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteById(@PathVariable long id) {
        return ResponseEntity.ok(customerService.deleteById(id));
    }

    @PostMapping("/register")
    ResponseEntity<Boolean> registerCustomer(@RequestBody CustomerDto customerDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body (customerService.addNewCustomer(customerDto));
    }

    @PostMapping()
    ResponseEntity<Boolean> addCustomer(@RequestBody CustomerDto customerDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerService.addNewCustomer(customerDto));
    }

    @PutMapping()
    ResponseEntity<Boolean> updateCustomer(@RequestBody CustomerDto customerDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerService.updateCustomer(customerDto));
    }

    @GetMapping("/{id}/offers")
    public ResponseEntity<List<OfferDto>> checkOfferHistory(@PathVariable long id) {
        List<OfferDto> offers = customerService.findOffersByCustomerId(id);
        System.out.println("contrller checkOfferHistory size: " + offers.size());
        return ResponseEntity.ok(offers);
    }

    @PostMapping("/{id}/offers")
    public ResponseEntity<Boolean> updateOfferStatus(@RequestBody OfferDto offerDto,
                                                     @PathVariable long id) {
        return ResponseEntity.ok(customerService.updateOfferStatus(offerDto, id));
    }


    @PostMapping("/black-list/{id}")
    ResponseEntity<Boolean> addCustomerToBlacklist(@PathVariable long id) {
        var user = controllerSecurityUtil.getLoggedinUser();
        var result = false;
        if (user != null) {
            result = customerService.addCustomerToBlacklist(id, user.getId());
        } else {
            result = customerService.addCustomerToBlacklist(id, 1);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/white-list/{id}")
    ResponseEntity<Boolean> addCustomerToWhiteList(@PathVariable long id) {
        var user = controllerSecurityUtil.getLoggedinUser();
        var result = false;
        if (user != null) {
            result = customerService.addCustomerToWhitelist(id, user.getId());
        } else {
            //TODO not necessary when auth is applied - just for dev
            result = customerService.addCustomerToWhitelist(id, 1);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{customerId}/addoffer")
    public ResponseEntity<Boolean> makeOffer(@PathVariable Long customerId, @RequestBody OfferDto offerDto) {
//        offerDto.setProperty(new PropertyDto(customerId)); // Set the PropertyDto with the provided id
        System.out.println("customer id: " + offerDto.getCustomer().getId());
        System.out.println("property id: " + offerDto.getProperty().getId());
        boolean success = offerService.makeOffer(customerId, offerDto);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).body(true);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }

}
