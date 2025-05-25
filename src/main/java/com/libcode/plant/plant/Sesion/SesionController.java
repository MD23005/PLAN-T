package com.libcode.plant.plant.Sesion;

import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.libcode.plant.plant.admin.repository.*;
import com.libcode.plant.plant.tutor.repository.*;
import com.libcode.plant.plant.admin.entities.*;
import com.libcode.plant.plant.tutor.entities.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;




@Controller
public class SesionController {

    @RequestMapping("/login")
    public String login(Authentication authentication) {
        // Suponiendo que ya has obtenido el token JWT y el rol está en el payload
        String role = (String) authentication.getPrincipal(); // Obtener rol del token JWT

        // Redirigir según el rol
        if ("admin".equals(role)) {
            return "redirect:/Admin";
        } else if ("tutor".equals(role)) {
            return "redirect:/tutores";
        } else {
            return "redirect:/login";
        }
    }
    /*private final TutorRepository tRepository;
    private final AdminRepository aRepository;
    
    Constructores
    public SesionController(TutorRepository tRepository, AdminRepository aRepository) {
       this.tRepository = tRepository;
        this.aRepository = aRepository;
    }

    @PostMapping("/login/admin")
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public String admin(@RequestParam String email, @RequestParam String password, Model modelo){
        Optional<Admin> admin = aRepository.findByEmailAndPassword(email, password);
        if(admin.isPresent()){
            return "redirect:/Admin";
        }
        modelo.addAttribute("error", "Correo o contraseña inválidos");
        return "/"; 
    }

    @PostMapping("/login/tutor")
    @PreAuthorize("hasAutority('SCOPE_tutor')")
    public String tutor(@RequestParam String email, @RequestParam String password, Model modelo){
        Optional<Tutor> tutor = tRepository.findByEmailAndPassword(email, password);
        if(tutor.isPresent()){
            return "redirect:/tutor";
        }
        modelo.addAttribute("error", "Correo o contraseña inválidos");
        return "/"; 
    }
    public String login(@RequestParam String email, @RequestParam String password, Model modelo) {
        Optional<Admin> admin = aRepository.findByEmailAndPassword(email, password);
        if(admin.isPresent()){
            return "redirect:/Admin";
        }

        Optional<Tutor> tutor = tRepository.findByEmailAndPassword(email, password);
        if(tutor.isPresent()){
            return "redirect:/tutor";
        }
        
        modelo.addAttribute("error", "Correo o contraseña inválidos");
        return "/"; 
    }*/
    
}
