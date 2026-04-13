package com.jsp.collegeevent.repo;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jsp.collegeevent.entity.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Long>{

    // FIX 1: Removed the broken @Query. Spring Boot will do this automatically now!
    List<Registration> findByStudentId(Long studentId);

    List<Registration> findByEventId(Long eventId);

    // Get all pending for Admin
    List<Registration> findByStatus(String status);

    // Get pending for a specific Coordinator's events
    @Query("SELECT r FROM Registration r WHERE r.event.coordinator.id = :coordinatorId AND r.status = 'PENDING'")
    List<Registration> findPendingByCoordinatorId(@Param("coordinatorId") Long coordinatorId);

    @Query("SELECT r FROM Registration r WHERE r.event.coordinator.id = :coordinatorId")
    List<Registration> findAllByCoordinatorId(@Param("coordinatorId") Long coordinatorId);

    @Query("SELECT r FROM Registration r JOIN FETCH r.student JOIN FETCH r.event")
    List<Registration> findAllWithDetails();

//    @Query("SELECT r FROM Registration r JOIN FETCH r.student JOIN FETCH r.event")
//    List<Registration> findAllForReport();
    
    
    @Query("SELECT r.id as id, r.status as status, s.name as studentName, s.email as studentEmail, " +
    	       "e.title as eventTitle, e.date as eventDate, e.location as eventLocation " +
    	       "FROM Registration r JOIN r.student s JOIN r.event e")
    	List<Map<String, Object>> findAllFlattened();
    
   
}