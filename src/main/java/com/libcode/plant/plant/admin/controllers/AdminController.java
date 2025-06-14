package com.libcode.plant.plant.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.libcode.plant.plant.CustomOidcUser;


import com.libcode.plant.plant.admin.entities.Admin;
import com.libcode.plant.plant.admin.repository.AdminRepository;
import com.libcode.plant.plant.tutor.repository.TutorRepository;

@Controller
@RequestMapping("/Admin")
public class AdminController {
    
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private TutorRepository tutorRepo;

    @GetMapping
    public String indexAdmin(){
        try {
            return "Admin/indexAdmin";
        } catch (Exception e) {
            return "error";
        }
    }

    
    @GetMapping("/tutores")
    public String listarTutores(Model model) {
        try {
            model.addAttribute("tutores", tutorRepo.findAll());
            return "Admin/tutor/list-tutores";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar los tutores");
            return "error";
        }
    }

    @GetMapping("/perfil")
    public String perfilAdmin(@AuthenticationPrincipal OidcUser customUser, 
                         Model modelo, 
                         RedirectAttributes redirectAttributes){
        try {
            if (customUser == null) {
                redirectAttributes.addFlashAttribute("error", "Usuario no autenticado");
                return "redirect:/Admin";
            }
            
            String email = customUser.getEmail(); // o customUser.getAttribute("email")
            
            Admin admin = adminRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Admin no encontrado con email: " + email));
            
            modelo.addAttribute("admin", admin);
            return "Admin/perfil/perfilAdmin";
        
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/Admin";
        }
    }
}
