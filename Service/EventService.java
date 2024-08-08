package com.example.Event.Management.Service;

import com.example.Event.Management.Entity.Event;
import com.example.Event.Management.Entity.RegistrationRequest;

import java.util.List;

public interface EventService {
    Event createEvent(Event event);
    List<Event> getAllEvents();
    Event getEventById(Long id);
    Event updateEvent(Long id, Event eventDetails);
    boolean deleteEvent(Long id);
    void registerEvent(Long eventId, RegistrationRequest request);
    byte[] generateEventPdf(Long id);
}
