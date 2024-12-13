package com.example.financialCalculator.controller;

import com.example.financialCalculator.entities.Admin;
import com.example.financialCalculator.service.imple.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin){
        return ResponseEntity
                .status(HttpStatus.OK).
                body(this.adminService.createAdmin(admin));
    }

    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmin(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.adminService.findAllAdmin());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.adminService.findAdminById(id));
    }
    @PutMapping
    public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.adminService.updateAdmin(admin));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdminById(@PathVariable Long id){
        this.adminService.deleteAdminById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }




}
