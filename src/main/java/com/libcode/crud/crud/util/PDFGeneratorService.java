package com.libcode.crud.crud.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.libcode.crud.crud.planificacion.entities.Planificacion;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.time.format.DateTimeFormatter;

@Service
public class PDFGeneratorService {

    public ByteArrayInputStream generatePlanificacionesPDF(List<Planificacion> planificaciones) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            // Título del documento
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("Planificaciones Semanales de Tutorías", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20f);
            document.add(title);

            // Tabla de planificaciones
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Encabezados de la tabla
            String[] headers = {"Grupo", "Tema", "Modalidad", "Fecha/Hora", "Tutor"};
            for (String header : headers) {
                PdfPCell cell = new PdfPCell();
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setPadding(5);
                cell.setPhrase(new Phrase(header));
                table.addCell(cell);
            }

            // Datos de las planificaciones
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            
            for (Planificacion planificacion : planificaciones) {
                table.addCell(planificacion.getGrupo());
                table.addCell(planificacion.getTema());
                table.addCell(planificacion.getModalidad());
                table.addCell(planificacion.getFechaHora().format(formatter));
                table.addCell(planificacion.getTutor().getNombre());
            }

            document.add(table);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}  