package com.jsp.collegeevent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.collegeevent.entity.Event;
import com.jsp.collegeevent.entity.Registration; // Add this import!
import com.jsp.collegeevent.repo.EventRepository;
import com.jsp.collegeevent.repo.RegistrationRepository; // Add this import!

@Service
public class EventService {

    @Autowired
    private EventRepository repo;
    
    // NEW: We need the Registration Repository to delete the children!
    @Autowired
    private RegistrationRepository regRepo;

    public Event creatEvent(Event event) {
        return repo.save(event);
    }
  
    public List<Event> getAllEvents() {
        return repo.findAll();
    }

    public List<Event> getCoordinatorEvents(Long id) {
        return repo.findByCoordinatorId(id);
    }

    public Event updateEvent(Long id, Event eventDetails) {
        Event event = repo.findById(id).orElseThrow(() -> new RuntimeException("Event not found"));
        event.setTitle(eventDetails.getTitle());
        event.setDescription(eventDetails.getDescription());
        event.setDate(eventDetails.getDate());
        event.setLocation(eventDetails.getLocation());
        return repo.save(event);
    }

    // FIX: Delete children before deleting the parent!
    public void deleteEvent(Long id) {
        // 1. Find all registrations tied to this event
        List<Registration> associatedRegs = regRepo.findByEventId(id);
        
        // 2. Delete all those registrations
        regRepo.deleteAll(associatedRegs);
        
        // 3. Now it is safe to delete the event!
        repo.deleteById(id);
    }
    
    public List<Event> getEventsByCoordinator(Long coordinatorId) {
        return repo.findByCoordinatorId(coordinatorId);
    }
}