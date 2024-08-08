package com.example.Event.Management.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.example.Event.Management.Entity.Event;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
@Service

public class PdfService {

    private static final Logger logger = LoggerFactory.getLogger(PdfService.class);

    public static byte[] createEventPdf(Event event) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();

            document.add(new Paragraph("Event Details"));
            document.add(new Paragraph("Event ID: " + event.getId()));
            document.add(new Paragraph("Event Name: " + event.getName()));
            document.add(new Paragraph("Event Date: " + event.getDate()));
            document.add(new Paragraph("Event Location: " + event.getLocation()));
            document.add(new Paragraph("Event Description: " + event.getDescription()));

            document.close();
            logger.info("PDF generated successfully for Event ID: {}", event.getId());
        } catch (Exception e) {
            logger.error("Error generating PDF for Event ID: {}", event.getId(), e);
        }

        return byteArrayOutputStream.toByteArray();
    }
}
