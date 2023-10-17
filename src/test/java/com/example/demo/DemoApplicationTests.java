package com.example.demo;

import com.example.demo.modal.entity.Role;
import com.example.demo.modal.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Random;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	void save_roles() {
		List<Role> roleList = List.of(
				new Role(null, "ADMIN"),
				new Role(null, "USER")
		);
		roleRepository.saveAll(roleList);
	}

	@Test
	void save_users() {
		List<Role> roleList = roleRepository.findAll();
		Random random = new Random();

		for (int i = 1; i <= 5; i++) {
			User user = new User(
					null,
					"User" + i,
					"user" + i + "@gmail.com",
					passwordEncoder.encode("user" + i),
					List.of(roleList.get(random.nextInt(2)))
			);
			userRepository.save(user);
		}
	}
}
