package ec.edu.ups.springboot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ec.edu.ups.springboot.model.User;
import ec.edu.ups.springboot.model.Telephone;
import ec.edu.ups.springboot.repository.UserRepository;

@SpringBootApplication
public class SeEjercicio0301Application implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SeEjercicio0301Application.class, args);
	}
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 10; i++) {
			List<Telephone> telephones = new ArrayList<>();
			for (int j = 0; j < 3; j++) {
				telephones.add(new Telephone(i + "000" + j));
			}
			this.userRepository.save(new User("000000000" + i, "usuario" + i, false, telephones));
		}
	}

}
