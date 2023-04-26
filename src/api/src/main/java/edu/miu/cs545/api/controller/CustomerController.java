package edu.miu.cs545.api.controller;

import edu.miu.cs545.api.dto.CustomerDto;
import edu.miu.cs545.api.dto.OfferDto;
import edu.miu.cs545.api.dto.UserDto;
import edu.miu.cs545.api.entity.User;
import edu.miu.cs545.api.service.CustomerService;
import edu.miu.cs545.api.service.OfferService;
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

    @PostMapping()
    ResponseEntity<Boolean> addCustomer(@RequestBody CustomerDto customerDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body (customerService.addNewCustomer(customerDto));
    }

    @PutMapping()
    ResponseEntity<Boolean> updateCustomer(@RequestBody CustomerDto customerDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body (customerService.updateCustomer(customerDto));
    }
    @GetMapping("/{id}/offers")
    public ResponseEntity<List<OfferDto>> checkOfferHistory(@PathVariable long id) {
        List<OfferDto> offers = offerService.checkOfferHistory(id);
        System.out.println("contrller checkOfferHistory: ");
        return ResponseEntity.ok(offers);
    }


    @PostMapping("/black-list/{id}")
    ResponseEntity<Boolean> addCustomerToBlacklist(@PathVariable long id) {
        var user = getLoggedinUser();
        var result = false;
        if(user != null ) {
           result =  customerService.addCustomerToBlacklist(id, user.getId());
        } else {
           result = customerService.addCustomerToBlacklist(id, 1);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/white-list/{id}")
    ResponseEntity<Boolean> addCustomerToWhiteList(@PathVariable long id) {
        var user = getLoggedinUser();
        var result = false;
        if(user != null ) {
            result =  customerService.addCustomerToWhitelist(id, user.getId());
        } else {
            //TODO not necessary when auth is applied - just for dev
            result = customerService.addCustomerToWhitelist(id, 1);
        }
        return ResponseEntity.ok(result);
    }

    private  User getLoggedinUser() {
        if(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal() != null
                && SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal() instanceof UserDetails) {

            return (User) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();
        }
        return null;
    }
}
