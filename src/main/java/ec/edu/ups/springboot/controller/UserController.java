package ec.edu.ups.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.ups.springboot.model.User;
import ec.edu.ups.springboot.repository.UserRepository;

@RestController
@RequestMapping("api/")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("users")
	public List<User> getUser() {
		return this.userRepository.findAll();
	}
}
