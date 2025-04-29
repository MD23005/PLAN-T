package com.libcode.plant.plant.grupo.controllers;

import com.libcode.plant.plant.grupo.entities.Grupo;
import com.libcode.plant.plant.tutor.entities.Tutor;
import com.libcode.plant.plant.grupo.repository.GrupoRepository;
import com.libcode.plant.plant.tutor.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private TutorRepository TutorRepository;

    @GetMapping
    public String listarGrupos(Model model) {
        try {
            model.addAttribute("grupos", grupoRepository.findAll());
            return "Admin/grupo/list-grupos";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar los grupos");
            return "error";
        }
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("grupo", new Grupo());
        model.addAttribute("tutores", obtenerTutores());
        return "Admin/grupo/form-grupos";
    }

    @PostMapping("/guardar")
    public String guardarGrupo(@ModelAttribute Grupo grupo, RedirectAttributes redirectAttributes) {
        try {
            grupoRepository.save(grupo);
            redirectAttributes.addFlashAttribute("success", "Grupo guardado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el grupo");
        }
        return "redirect:/grupos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Grupo grupo = grupoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Grupo no encontrado con ID: " + id));
            model.addAttribute("grupo", grupo);
            model.addAttribute("tutores", obtenerTutores());
            return "Admin/grupo/form-grupos";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/grupos";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarGrupo(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            grupoRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Grupo eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "No se puede eliminar el grupo porque está siendo utilizado");
        }
        return "redirect:/grupos";
    }

    private List<Tutor> obtenerTutores() {
        return TutorRepository.findAll();
    }
}
