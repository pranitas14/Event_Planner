package com.example.Event.Management.Controller;


import com.example.Event.Management.Entity.Event;
import com.example.Event.Management.Entity.RegistrationRequest;
import com.example.Event.Management.Service.EventService;
import com.example.Event.Management.Service.PdfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private EventService eventService;

    @Autowired
    private PdfService pdfService;

    // Create a new event
    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event createdEvent = eventService.createEvent(event);
        logger.info("Created new event with ID: {}", createdEvent.getId());
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    // Get all events
    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        logger.info("Retrieved all events");
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    // Get event by ID
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Event event = eventService.getEventById(id);
        if (event == null) {
            logger.warn("Event with ID {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("Retrieved event with ID: {}", id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    // Update event by ID
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event eventDetails) {
        Event updatedEvent = eventService.updateEvent(id, eventDetails);
        if (updatedEvent == null) {
            logger.warn("Event with ID {} not found for update", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("Updated event with ID: {}", id);
        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }

    // Delete event by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        boolean isDeleted = eventService.deleteEvent(id);
        if (!isDeleted) {
            logger.warn("Event with ID {} not found for deletion", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("Deleted event with ID: {}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Generate PDF for an event
    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> generateEventPdf(@PathVariable Long id) {
        logger.info("Request to generate PDF for Event ID: {}", id);

        Event event = eventService.getEventById(id);
        if (event == null) {
            logger.warn("Event with ID {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        byte[] pdfBytes = pdfService.createEventPdf(event);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=event_" + id + ".pdf");
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_PDF);

        logger.info("PDF successfully generated for Event ID: {}", id);
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

    // Register for an event
    @PostMapping("/{eventId}/register")
    public ResponseEntity<Void> registerEvent(@PathVariable Long eventId, @RequestBody RegistrationRequest request) {
        try {
            eventService.registerEvent(eventId, request);
            logger.info("User with ID {} registered for event with ID {}", request.getUserId(), eventId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            logger.error("Error registering user with ID {} for event with ID {}", request.getUserId(), eventId, e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
