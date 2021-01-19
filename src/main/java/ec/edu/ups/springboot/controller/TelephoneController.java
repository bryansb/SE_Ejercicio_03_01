package ec.edu.ups.springboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.ups.springboot.model.Telephone;
import ec.edu.ups.springboot.repository.TelephoneRepository;

@RestController
@RequestMapping("/api")
public class TelephoneController {
	
	@Autowired
	private TelephoneRepository telephoneRepository;
	
	@GetMapping("/telephones")
	public List<Telephone> getTelephones(){
		return this.telephoneRepository.findAll();
	}
	
	@PostMapping("/telephone")
	public ResponseEntity<Telephone> createTelephone(@RequestBody Telephone telephone) {
		Telephone telephoneResponse = telephoneRepository.save(telephone);
		return new ResponseEntity<Telephone>(telephoneResponse, HttpStatus.OK);
	}
	
	@GetMapping("/telephone/{id}")
	public ResponseEntity<Telephone> getTelephone(@PathVariable int id){
		Optional<Telephone> telephone = telephoneRepository.findById(id);
		return telephone.map(response -> ResponseEntity.ok().body(response)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PutMapping("/telephone/{id}")
	public ResponseEntity<Telephone> updateTelephone(@RequestBody Telephone telephone) {
		Telephone result = telephoneRepository.saveAndFlush(telephone);
		return ResponseEntity.ok().body(result);
	}
	
	@DeleteMapping("/telephone/{id}")
	public ResponseEntity<?> deleteTelephone(@PathVariable int id) {
		telephoneRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
}
