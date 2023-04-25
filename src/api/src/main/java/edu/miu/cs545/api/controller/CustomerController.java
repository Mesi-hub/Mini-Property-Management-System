package edu.miu.cs545.api.controller;

import edu.miu.cs545.api.dto.CustomerDto;
import edu.miu.cs545.api.dto.OfferDto;
import edu.miu.cs545.api.service.CustomerService;
import edu.miu.cs545.api.service.OfferService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
                .body (customerService.save(customerDto));
    }

    @PutMapping()
    ResponseEntity<Boolean> updateCustomer(@RequestBody CustomerDto customerDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body (customerService.save(customerDto));
    }
    @GetMapping("/{id}/offers")
    public ResponseEntity<List<OfferDto>> checkOfferHistory(@PathVariable long id) {
        List<OfferDto> offers = offerService.checkOfferHistory(id);
        System.out.println("contrller checkOfferHistory: ");
        return ResponseEntity.ok(offers);
    }

}
