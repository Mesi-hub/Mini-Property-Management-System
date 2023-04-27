package edu.miu.cs545.api.controller;

import edu.miu.cs545.api.dto.MessageDto;
import edu.miu.cs545.api.dto.PropertyDto;
import edu.miu.cs545.api.entity.User;
import edu.miu.cs545.api.service.MessageService;
import edu.miu.cs545.api.service.PropertyService;
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
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    MessageService messageService;
    @Autowired
    PropertyService propertyService;
    @Autowired
    ControllerSecurityUtil controllerSecurityUtil;
    @Autowired
    UserService userService;
    @Autowired
    ModelMapper modelMapper;
    @GetMapping
    ResponseEntity<List<MessageDto>> findAll() {
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%");
        return findAllByUser(Optional.empty());
    }

    @GetMapping("/user/{id}")
    ResponseEntity<List<MessageDto>> findAllByUser(@PathVariable Optional<Long> userId) {
        System.out.println("1%%%%%%%%%%%%%%%%%%%%%%");
        User user = getUser(userId);
        return ResponseEntity.ok(messageService.getMessagesForUserOrderByDateTimeDesc(user));
    }



    @GetMapping("/user/{userId}/property/{propertyId}")
    ResponseEntity<List<MessageDto>> findAllByUserProperty(@PathVariable Optional<Long> userId, @PathVariable Optional<Long> propertyId) {
        System.out.println("2%%%%%%%%%%%%%%%%%%%%%%");
        Long paramPropertyId = propertyId.orElse(null);
        PropertyDto propertyDto = null;
        if(paramPropertyId != null) {
            //Only admins can read all messages
            propertyDto = propertyService.findPropertyById(paramPropertyId);
        }
        else {
            return findAllByUser(userId);
        }
        return ResponseEntity.ok(messageService.getMessagesForUserForPropertyOrderByDateTimeDesc(getUser(userId), propertyDto));
    }
    @PostMapping()
    ResponseEntity<MessageDto> addMessage(@RequestBody MessageDto messageDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body (messageService.postMessage(messageDto, controllerSecurityUtil.getLoggedinUser()));
    }
    private User getUser(Optional<Long> userId) {
        Long paramUserId = userId.orElse(null);
        User user = controllerSecurityUtil.getLoggedinUser();
        if(paramUserId !=
                null) {
            //Only admins can read all messages
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
