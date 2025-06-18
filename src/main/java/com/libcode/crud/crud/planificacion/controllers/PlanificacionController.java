package com.libcode.crud.crud.planificacion.controllers;

import com.libcode.crud.crud.util.PDFGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.libcode.crud.crud.planificacion.entities.Planificacion;
import com.libcode.crud.crud.planificacion.repository.PlanificacionRepository;
import com.libcode.crud.crud.tutor.repository.TutorRepository;
import com.libcode.crud.crud.grupo.repository.GrupoRepository;
import com.libcode.crud.crud.grupo.entities.Grupo;

import java.io.ByteArrayInputStream;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.libcode.crud.crud.tutor.entities.Tutor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.stream.IntStream;

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
        return "planificacion/list-planificaciones";
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("planificacion", new Planificacion());
        model.addAttribute("tutores", tutorRepository.findAll());
        model.addAttribute("grupos", grupoRepository.findAll());
        return "planificacion/form-planificacion";
    }
    
   @PostMapping("/guardar")
   public String guardarPlanificacion(@ModelAttribute Planificacion planificacion) {
    if (Boolean.TRUE.equals(planificacion.getRecurrente()) && planificacion.getSemanas() != null) {
        List<Planificacion> sesiones = new ArrayList<>();
        for (int i = 0; i < planificacion.getSemanas(); i++) {
            Planificacion copia = new Planificacion();
            copia.setGrupo(planificacion.getGrupo());
            copia.setTema(planificacion.getTema());
            copia.setModalidad(planificacion.getModalidad());
            copia.setTutor(planificacion.getTutor());
            copia.setRecurrente(true);
            copia.setSemanas(planificacion.getSemanas());
            copia.setPalabraClave(planificacion.getPalabraClave());
            copia.setFechaHora(planificacion.getFechaHora().plusWeeks(i));
            sesiones.add(copia);
        }
        planificacionRepository.saveAll(sesiones);
    } else {
        planificacionRepository.save(planificacion);
    }
    return "redirect:/planificaciones";
    }

    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("planificacion", planificacionRepository.findById(id).orElseThrow());
        model.addAttribute("tutores", tutorRepository.findAll());
        model.addAttribute("grupos", grupoRepository.findAll());
        return "planificacion/form-planificacion";
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

    @GetMapping("/carga-masiva")
    public String mostrarFormularioCargaMasiva(Model model) {
        return "planificacion/carga-masiva";
    }

    @PostMapping("/procesar-carga-masiva")
    public String procesarCargaMasiva(@RequestParam("archivo") MultipartFile archivo, Model model) {
        if (archivo.isEmpty()) {
            model.addAttribute("error", "Por favor seleccione un archivo Excel");
            return "planificacion/carga-masiva";
        }

        try (Workbook workbook = new XSSFWorkbook(archivo.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            List<Planificacion> planificaciones = new ArrayList<>();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                final int currentRow = i;
                Row row = sheet.getRow(currentRow);
                if (row == null) continue;

                Planificacion planificacion = new Planificacion();
                
                String grupoIdStr = getCellStringValue(row.getCell(0), "ID Grupo", currentRow);
                Long grupoId = Long.parseLong(grupoIdStr);
                Grupo grupo = grupoRepository.findById(grupoId)
                    .orElseThrow(() -> new IllegalArgumentException("Grupo no encontrado (Fila " + currentRow + ")"));
                planificacion.setGrupo(grupo);
                
                planificacion.setFechaHora(parseDateTime(
                    getCellStringValue(row.getCell(1), "Fecha", currentRow),
                    getCellStringValue(row.getCell(2), "Hora", currentRow)
                ));
                planificacion.setTema(getCellStringValue(row.getCell(3), "Tema", currentRow));
                planificacion.setModalidad(getCellStringValue(row.getCell(4), "Modalidad", currentRow));
                
                String tutorIdStr = getCellStringValue(row.getCell(5), "ID Tutor", currentRow);
                Long tutorId = Long.parseLong(tutorIdStr);
                Tutor tutor = tutorRepository.findById(tutorId)
                    .orElseThrow(() -> new IllegalArgumentException("Tutor no encontrado (Fila " + currentRow + ")"));
                planificacion.setTutor(tutor);
                
                planificaciones.add(planificacion);
            }

            model.addAttribute("planificaciones", planificaciones);
            model.addAttribute("confirmacion", true);
        } catch (Exception e) {
            model.addAttribute("error", "Error al procesar: " + e.getMessage());
        }

        return "planificacion/carga-masiva";
    }

    @PostMapping("/confirmar-carga-masiva")
    public String confirmarCargaMasiva(
        @RequestParam("gruposIds[]") Long[] gruposIds,
        @RequestParam("fechasHoras[]") String[] fechasHoras,
        @RequestParam("temas[]") String[] temas,
        @RequestParam("modalidades[]") String[] modalidades,
        @RequestParam("tutoresIds[]") Long[] tutoresIds,
        RedirectAttributes redirectAttributes) {

        List<Planificacion> planificaciones = new ArrayList<>();
        
        IntStream.range(0, gruposIds.length).forEach(i -> {
            Planificacion planificacion = new Planificacion();
            
            Grupo grupo = grupoRepository.findById(gruposIds[i])
                .orElseThrow(() -> new IllegalArgumentException("Grupo no encontrado para el ID: " + gruposIds[i]));
            planificacion.setGrupo(grupo);
            
            planificacion.setFechaHora(LocalDateTime.parse(fechasHoras[i]));
            planificacion.setTema(temas[i]);
            planificacion.setModalidad(modalidades[i]);
            
            Tutor tutor = tutorRepository.findById(tutoresIds[i])
                .orElseThrow(() -> new IllegalArgumentException("Tutor no encontrado para el ID: " + tutoresIds[i]));
            planificacion.setTutor(tutor);
            
            planificaciones.add(planificacion);
        });

        planificacionRepository.saveAll(planificaciones);
        redirectAttributes.addFlashAttribute("mensaje", 
            "¡" + planificaciones.size() + " planificaciones importadas correctamente!");
        return "redirect:/planificaciones";
    }

    private String getCellStringValue(Cell cell, String campo, int fila) {
        if (cell == null) {
            throw new IllegalArgumentException(campo + " no puede estar vacío (Fila " + fila + ")");
        }
        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue().trim();
            case NUMERIC: 
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getLocalDateTimeCellValue().toString();
                }
                return String.valueOf((long) cell.getNumericCellValue());
            default: throw new IllegalArgumentException(
                "Formato inválido para " + campo + " (Fila " + fila + ")");
        }
    }

    private LocalDateTime parseDateTime(String fecha, String hora) {
        try {
            return LocalDateTime.parse(fecha + "T" + hora);
        } catch (Exception e) {
            throw new IllegalArgumentException("Formato de fecha/hora inválido. Use YYYY-MM-DD y HH:mm");
        }
    }
}