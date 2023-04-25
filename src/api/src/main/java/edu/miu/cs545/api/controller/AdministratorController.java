package edu.miu.cs545.api.controller;

import edu.miu.cs545.api.dto.AdministratorDto;
import edu.miu.cs545.api.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/administrators")
public class AdministratorController {
    @Autowired
    AdministratorService administratorService;

    @GetMapping
    public ResponseEntity<List<AdministratorDto>> getAll(){
        return ResponseEntity.ok(administratorService.getAll());
    }
}
