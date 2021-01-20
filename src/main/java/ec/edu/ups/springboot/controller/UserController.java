package ec.edu.ups.springboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.ups.springboot.model.Telephone;
import ec.edu.ups.springboot.model.User;
import ec.edu.ups.springboot.repository.TelephoneRepository;
import ec.edu.ups.springboot.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class UserController {
		
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/users")
	public List<User> getUser() {
		return this.userRepository.findAll();
	}
	
	@PostMapping("/user")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User userResponse = userRepository.save(user);
		return new ResponseEntity<User>(userResponse, HttpStatus.OK);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUser(@PathVariable Integer id) {
		Optional<User> user = userRepository.findById(id);
		return user.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User result = userRepository.saveAndFlush(user);
        return ResponseEntity.ok().body(result);
    }
	
	@DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
	
	@Autowired
	private TelephoneRepository telephoneRepository;
	
	@GetMapping("/user/telephones/{userId}")
	public List<Telephone> getTelephones(@PathVariable Integer userId) {
		return this.telephoneRepository.findByUserId(userId);
	}
	
	@PostMapping("/user/telephone/{userId}")
	public ResponseEntity<Telephone> createTelephone(@RequestBody Telephone telephone, @PathVariable Integer userId) {
		Optional<User> user = userRepository.findById(userId);
		telephone.setUser(user.get());
		Telephone telephoneResponse = telephoneRepository.save(telephone);
		return new ResponseEntity<Telephone>(telephoneResponse, HttpStatus.OK);
	}
	
	@GetMapping("/user/telephone/{userId}/{id}")
	public ResponseEntity<Telephone> getTelephone(@PathVariable Integer userId, @PathVariable Integer id) {
		Optional<Telephone> telephone = telephoneRepository.findById(id);
		return telephone.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PutMapping("/user/telephone/{userId}/{id}")
    public ResponseEntity<Telephone> updateTelephone(@RequestBody Telephone telephone) {
        Telephone result = telephoneRepository.saveAndFlush(telephone);
        System.out.println("sssssssssssssssssss"+telephone);
        return ResponseEntity.ok().body(result);
    }
	
}
