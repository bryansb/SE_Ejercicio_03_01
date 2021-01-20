package ec.edu.ups.springboot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ec.edu.ups.springboot.model.User;
import ec.edu.ups.springboot.model.Telephone;
import ec.edu.ups.springboot.repository.TelephoneRepository;
import ec.edu.ups.springboot.repository.UserRepository;

@SpringBootApplication
public class SeEjercicio0301Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SeEjercicio0301Application.class, args);
	}

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TelephoneRepository telephoneRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setDni("0000000" + i);
			user.setName("Usuario" + i);
			List<Telephone> telephones = new ArrayList<>();
			for (int j = 0; j < 3; j++) {
				Telephone telephone = new Telephone();
				telephone.setNumber(i + "000" + j);
				telephone.setUser(user);
				telephones.add(telephone);
			}
			user.setTelephones(telephones);
			userRepository.save(user);
			telephoneRepository.saveAll(telephones);
		}

	}
}
