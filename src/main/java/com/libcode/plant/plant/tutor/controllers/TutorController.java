package com.libcode.plant.plant.tutor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.libcode.plant.plant.tutor.entities.Tutor;
import com.libcode.plant.plant.tutor.repository.TutorRepository;

@Controller
@RequestMapping("/Tutor")
public class TutorController {
    
    @Autowired
    private TutorRepository tutorRepository;
    
    @GetMapping
    public String indexTutor(){
        try {
            return "Tutor/indexTutor";
        } catch (Exception e) {
            return "error";
        }
    }

    
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("tutor", new Tutor());
        return "Admin/tutor/form-tutor";
    }
    
    @PostMapping("/guardar")
    public String guardarTutor(@ModelAttribute Tutor tutor, RedirectAttributes redirectAttributes) {
        try {
            tutorRepository.save(tutor);
            redirectAttributes.addFlashAttribute("success", "Tutor guardado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el tutor");
        }
        return "redirect:/tutores";
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Tutor tutor = tutorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tutor no encontrado con ID: " + id));
            model.addAttribute("tutor", tutor);
            return "Admin/tutor/form-tutor";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/tutores";
        }
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarTutor(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            tutorRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Tutor eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "No se puede eliminar el tutor porque está siendo utilizado");
        }
        return "redirect:/tutores";
    }

    @GetMapping("/perfil/{id}")
    public String perfilTutor(@PathVariable Long id,Model modelo, RedirectAttributes redirectAttributes){
        try {
            Tutor tutor = tutorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Tutor no encontrado con ID: " + id));
            modelo.addAttribute("tutor", tutor);
            return "Tutor/pefil/perfilTutor";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/Tutor";
        }
    }
}     