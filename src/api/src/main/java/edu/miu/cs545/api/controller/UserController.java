package edu.miu.cs545.api.controller;

import edu.miu.cs545.api.dto.UserDto;
import edu.miu.cs545.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Objects;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping
    public ResponseEntity<Collection<UserDto>> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }
    @PostMapping
    public ResponseEntity<Void> addUser(@RequestBody UserDto user){
        UserDto newUser = userService.add(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id){
        return ResponseEntity.ok(userService.getById(id));
    }
    @PutMapping()
    public ResponseEntity<Void> updateUser(@RequestBody UserDto user){
        Long prevId = user.getId();
        UserDto updatedUser = userService.update(user);
        if(!Objects.equals(prevId, updatedUser.getId())) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(updatedUser.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        }
        else {
            return ResponseEntity.ok().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
