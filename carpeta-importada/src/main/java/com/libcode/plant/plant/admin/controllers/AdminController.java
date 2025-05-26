package com.libcode.plant.plant.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.libcode.plant.plant.admin.repository.AdminRepository;

@Controller
@RequestMapping("/Admin")
public class AdminController {
    
    @Autowired
    private AdminRepository adminRepository;

    @GetMapping
    public String indexAdmin(){
        try {
            return "Admin/indexAdmin";
        } catch (Exception e) {
            return "error";
        }
    }
}
