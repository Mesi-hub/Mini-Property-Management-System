package edu.miu.cs545.api.controller;

import edu.miu.cs545.api.dto.SavedPropertyDto;
import edu.miu.cs545.api.entity.User;
import edu.miu.cs545.api.service.PropertyService;
import edu.miu.cs545.api.service.SavedPropertyService;
import edu.miu.cs545.api.service.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/savedProperties")
public class SavedPropertyController {
    @Autowired
    SavedPropertyService savedPropertyService;
    @Autowired
    PropertyService propertyService;
    @Autowired
    ControllerSecurityUtil controllerSecurityUtil;
    @Autowired
    UserService userService;
    @Autowired
    ModelMapper modelMapper;
    @GetMapping
    ResponseEntity<List<SavedPropertyDto>> findAll() {
        return ResponseEntity.ok(savedPropertyService.getAll());
    }

    @GetMapping("/user/{id}")
    ResponseEntity<List<SavedPropertyDto>> findAllByUser(@PathVariable Optional<Long> userId) {
        User user = getUser(userId);
        return ResponseEntity.ok(savedPropertyService.getSavedPropertiesForUserOrderByDateTimeDesc(user));
    }



    @PostMapping()
    ResponseEntity<SavedPropertyDto> addMessage(@RequestBody SavedPropertyDto savedPropertyDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body (savedPropertyService.saveProperty(savedPropertyDto, controllerSecurityUtil.getLoggedinUser()));
    }
    private User getUser(Optional<Long> userId) {
        Long paramUserId = userId.orElse(null);
        User user = controllerSecurityUtil.getLoggedinUser();
        if(paramUserId !=
                null) {
            //Only admins can read all saved Items
            if(user.getAuthorities().stream().filter(x->x.getAuthority().equals("ADMIN")).toList().size() == 0){
                paramUserId = user.getId();
            } else {
                user = modelMapper.map(userService.getById(paramUserId), User.class);
            }
        }
        else {
            paramUserId = user.getId();
            user = modelMapper.map(userService.getById(paramUserId), User.class);
        }
        return user;
    }
}
