package com.libcode.crud.crud.tutor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.libcode.crud.crud.tutor.entities.Tutor;
import com.libcode.crud.crud.tutor.repository.TutorRepository;

@Controller
@RequestMapping("/tutores")
public class TutorController {
    
    @Autowired
    private TutorRepository tutorRepository;
    
    @GetMapping
    public String listarTutores(Model model) {
        try {
            model.addAttribute("tutores", tutorRepository.findAll());
            return "tutor/list-tutores";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar los tutores");
            return "error";
        }
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("tutor", new Tutor());
        return "tutor/form-tutor";
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
            return "tutor/form-tutor";
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
}     