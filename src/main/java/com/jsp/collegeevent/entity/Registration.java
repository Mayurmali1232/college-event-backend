package com.jsp.collegeevent.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class Registration {

	
		
		
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne
	@JsonManagedReference // HA badal kara: Registration kadhun student kade ja
	private User student;

	@ManyToOne
	@JsonManagedReference // Registration kadhun event kade ja
	private Event event;
	
		@Column(name = "student_seen")
	    private Boolean studentSeen = false;
	    private String status; // PENDING / APPROVED / REJECTED
	    
	    public Registration() {
			// TODO Auto-generated constructor stub
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public User getStudent() {
			return student;
		}

		public void setStudent(User student) {
			this.student = student;
		}

		public Event getEvent() {
			return event;
		}

		public void setEvent(Event event) {
			this.event = event;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
		
		public Boolean getStudentSeen() { return studentSeen; }
	    public void setStudentSeen(Boolean studentSeen) { this.studentSeen = studentSeen; }

		
		

		
	
}
