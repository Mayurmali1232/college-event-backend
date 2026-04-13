package com.jsp.collegeevent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.collegeevent.entity.Event;
import com.jsp.collegeevent.service.EventService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService service;

    @PostMapping
    public Event create(@Valid @RequestBody Event event) {
        return service.creatEvent(event);
    }

    @GetMapping
    public List<Event> getAll() {
        return service.getAllEvents();
    }
    
    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable Long id, @RequestBody Event event) {
        return service.updateEvent(id, event);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        service.deleteEvent(id);
    }

    // ONLY ONE ENDPOINT FOR THIS URL NOW!
    @GetMapping("/coordinator/{id}")
    public List<Event> getEventsByCoordinatorId(@PathVariable Long id) {
        // Make sure this matches the method name inside your EventService!
        return service.getEventsByCoordinator(id); 
    }
}