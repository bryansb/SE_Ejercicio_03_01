package ec.edu.ups.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.ups.springboot.model.Telephone;
import ec.edu.ups.springboot.repository.TelephoneRepository;

@RestController
@RequestMapping("api/")
public class TelephoneController {
	
	@Autowired
	private TelephoneRepository telephoneRepository;
	
	@GetMapping("telephoneCreate")
	public void createTelephone(Telephone telephone) {
		this.telephoneRepository.save(telephone);
	}
	@GetMapping("telephoneList")
	public List<Telephone> getTelephone(){
		return this.telephoneRepository.findAll();
	}
	
	@GetMapping("telephoneRead")
	public Telephone readTelephone(int id) {
		return this.telephoneRepository.findById(id).get();
	}
	
	@GetMapping("telephoneUpdate")
	public void updateTelephone(Telephone telephone) {
		this.telephoneRepository.saveAndFlush(telephone);
	}
	
	@GetMapping("telephoneDelete")
	public void deleteTelephone(int id) {
		this.telephoneRepository.deleteById(id);
	}
}
