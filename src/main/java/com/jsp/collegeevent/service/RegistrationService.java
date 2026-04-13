package com.jsp.collegeevent.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.collegeevent.entity.Registration;
import com.jsp.collegeevent.repo.RegistrationRepository;

@Service
public class RegistrationService {

	public RegistrationService() {
		// TODO Auto-generated constructor stub
	}

	
	@Autowired
    private RegistrationRepository repo;

    public Registration register(Registration reg) {
        reg.setStatus("PENDING");
        return repo.save(reg);
    }

    public List<Registration> getStudentRegistrations(Long id) {
        return repo.findByStudentId(id);
    }

    public List<Registration> getEventRegistrations(Long id) {
        return repo.findByEventId(id);
    }

    public Registration updateStatus(Long id, String status) {
        Registration reg = repo.findById(id).orElseThrow();
        reg.setStatus(status);
        return repo.save(reg);
    }
    
    public void deleteRegistration(Long id) {
        repo.deleteById(id);
    }
    
//    public List<Registration> getAllRegistrations() {
//    	return repo.findAllWithDetails();
//    }
    
    
 // ADD THESE METHODS
    public List<Registration> getAllPendingRegistrations() {
        return repo.findByStatus("PENDING");
    }

    public List<Registration> getPendingForCoordinator(Long coordinatorId) {
        return repo.findPendingByCoordinatorId(coordinatorId);
    }
    
    public List<Registration> getAllCoordinatorRegistrations(Long coordinatorId) {
        return repo.findAllByCoordinatorId(coordinatorId);
    }
    
    public void markNotificationAsSeen(Long registrationId) {
        Registration reg = repo.findById(registrationId).orElseThrow(() -> new RuntimeException("Not found"));
        reg.setStudentSeen(true);
        repo.save(reg);
    }
    
    
//    public List<Registration> getAllRegistrations() {
//        return repo.findAllWithDetails(); // Uses the optimized JOIN FETCH
//    }
 
    public List<Map<String, Object>> getAllRegistrationsList() {
        return repo.findAllFlattened(); // Navin flattened query call kara
    }
    
    public List<Map<String, Object>> getAllRegistrations() {
        return repo.findAllFlattened(); // Navin query call kar
    }
    
}

