package com.jsp.collegeevent.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.collegeevent.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long>{
	
	List<Event> findByCoordinatorId(Long id);
	
//	List<Event> findByCoordinatorId(Long coordinatorId);

}
