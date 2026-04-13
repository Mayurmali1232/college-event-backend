package com.jsp.collegeevent;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.jsp.collegeevent.entity.User;
import com.jsp.collegeevent.repo.UserRepository;

@SpringBootApplication
public class CollegeeventApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollegeeventApplication.class, args);
	}


	@Bean
    public CommandLineRunner createDefaultAdmin(UserRepository userRepository) {
        return args -> {
            // Check if our default admin already exists
            if (userRepository.findByEmail("admin@college.edu") == null) {
                User admin = new User();
                admin.setName("System Admin");
                admin.setEmail("admin@college.edu");
                admin.setPassword("admin123"); // Note: You should hash this in a real production app!
                admin.setRole("ADMIN");

                userRepository.save(admin);
                System.out.println("✅ Default Admin User Created Successfully!");
            }
        };
    }
}
