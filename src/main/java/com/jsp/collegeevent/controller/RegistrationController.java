package com.jsp.collegeevent.controller;

import java.util.List;
import java.util.Map;

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

import com.jsp.collegeevent.entity.Registration;
import com.jsp.collegeevent.service.RegistrationService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/registrations")
public class RegistrationController {

    @Autowired
    private RegistrationService service;

    @PostMapping
    public Registration register(@RequestBody Registration reg) {
        return service.register(reg);
    }

    @GetMapping("/student/{id}")
    public List<Registration> getRegistrationsByStudentId(@PathVariable("id") Long id) {
        return service.getStudentRegistrations(id);
    }

    @GetMapping("/event/{id}")
    public List<Registration> eventRegs(@PathVariable("id") Long id) {
        return service.getEventRegistrations(id);
    }

    @PutMapping("/{id}/{status}")
    public Registration updateStatus(@PathVariable("id") Long id, @PathVariable("status") String status) {
        return service.updateStatus(id, status);
    }
    
    // FIX 2: This was accidentally @Param("id"), it MUST be @PathVariable
    @DeleteMapping("/{id}")
    public void cancelRegistration(@PathVariable("id") Long id) {
        service.deleteRegistration(id);
    }
    
//    @GetMapping("/all")
//    public List<Registration> getAllRegistrations() {
//        return service.getAllRegistrations();
//    }
    
    @GetMapping("/pending")
    public List<Registration> getAllPending() {
        return service.getAllPendingRegistrations();
    }

    @GetMapping("/pending/coordinator/{id}")
    public List<Registration> getPendingByCoordinator(@PathVariable("id") Long id) {
        return service.getPendingForCoordinator(id);
    }
    
    @GetMapping("/coordinator/{id}/all")
    public List<Registration> getAllByCoordinator(@PathVariable("id") Long id) {
        return service.getAllCoordinatorRegistrations(id);
    }
    
    @PutMapping("/seen/{id}")
    public void markAsSeen(@PathVariable("id") Long id) {
        service.markNotificationAsSeen(id);
    }
    
    @GetMapping("/all")
    public List<Map<String, Object>> getAllRegistrations() {
        return service.getAllRegistrations();
    }
}