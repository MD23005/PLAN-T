package com.libcode.plant.plant.planificacion.controllers;

import com.libcode.plant.plant.util.PDFGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.libcode.plant.plant.planificacion.entities.Planificacion;
import com.libcode.plant.plant.planificacion.repository.PlanificacionRepository;
import com.libcode.plant.plant.tutor.repository.TutorRepository;
import com.libcode.plant.plant.grupo.repository.GrupoRepository;

import java.io.ByteArrayInputStream;
import java.util.List;

@Controller
@RequestMapping("/planificaciones")
public class PlanificacionController {
    
    @Autowired
    private PlanificacionRepository planificacionRepository;
    
    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private GrupoRepository grupoRepository;
    
    @Autowired
    private PDFGeneratorService pdfGeneratorService;
    
    @GetMapping
    public String listarPlanificaciones(Model model) {
        model.addAttribute("planificaciones", planificacionRepository.findAll());
        return "Tutor/planificacion/list-planificaciones";
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("planificacion", new Planificacion());
        model.addAttribute("tutores", tutorRepository.findAll());
        model.addAttribute("grupos", grupoRepository.findAll()); 
        return "Tutor/planificacion/form-planificacion";
    }

    @PostMapping("/guardar")
    public String guardarPlanificacion(@ModelAttribute Planificacion planificacion) {
        planificacionRepository.save(planificacion);
        return "redirect:/planificaciones";
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("planificacion", planificacionRepository.findById(id).orElseThrow());
        model.addAttribute("tutores", tutorRepository.findAll());
        model.addAttribute("grupos", grupoRepository.findAll()); 
        return "Tutor/planificacion/form-planificacion";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarPlanificacion(@PathVariable Long id) {
        planificacionRepository.deleteById(id);
        return "redirect:/planificaciones";
    }
    
    @GetMapping("/exportar-pdf")
    public ResponseEntity<InputStreamResource> exportPlanificacionesToPDF() {
        List<Planificacion> planificaciones = planificacionRepository.findAll();
        
        ByteArrayInputStream bis = pdfGeneratorService.generatePlanificacionesPDF(planificaciones);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=planificaciones_tutorias.pdf");
        
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}   