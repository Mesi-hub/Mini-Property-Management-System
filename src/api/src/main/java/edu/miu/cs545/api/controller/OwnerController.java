package edu.miu.cs545.api.controller;

import edu.miu.cs545.api.dto.AdministratorDto;
import edu.miu.cs545.api.dto.CustomerDto;
import edu.miu.cs545.api.dto.OfferDto;
import edu.miu.cs545.api.dto.OwnerDto;
import edu.miu.cs545.api.entity.Owner;
import edu.miu.cs545.api.entity.User;
import edu.miu.cs545.api.service.AdministratorService;
import edu.miu.cs545.api.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    @Autowired
    OwnerService ownerService;

    @Autowired
    ControllerSecurityUtil controllerSecurityUtil;
    @GetMapping
    public ResponseEntity<List<OwnerDto>> getAll(){
        return ResponseEntity.ok(ownerService.getAll());
    }

    @PostMapping("/register")
    ResponseEntity<Long> register(){
        User user = controllerSecurityUtil.getLoggedinUser();
        return ResponseEntity.ok(ownerService.register(user));
    }

    @PutMapping("/approve/{id}")
    ResponseEntity<Boolean> approveOwner(@RequestBody OwnerDto ownerDto, @PathVariable long id) {
        Owner owner = ownerService.findById(id);
        owner.setApproved(ownerDto.getApproved());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body (ownerService.save(owner));
    }

    @PutMapping("/black-list/{id}")
    ResponseEntity<Boolean> blackListOwner(@RequestBody OwnerDto ownerDto, @PathVariable long id) {
        Owner owner = ownerService.findById(id);
        owner.setBlackListed(ownerDto.getBlackListed());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body (ownerService.save(owner));
    }
}
