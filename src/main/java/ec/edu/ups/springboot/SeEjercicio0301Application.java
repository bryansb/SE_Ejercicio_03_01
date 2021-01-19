package ec.edu.ups.springboot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ec.edu.ups.springboot.model.User;
import ec.edu.ups.springboot.controller.TelephoneController;
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
	private TelephoneController telephoneController;

	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		for (int i = 0; i < 1; i++) {
			user.setDni("000000000" + i);
			user.setName("Usuario" + i);
			user.setDeleted(false);
		}
		List<Telephone> telephoneList = new ArrayList<>();
		Telephone telephone = new Telephone();
		telephone.setNumber("0983232969");
		System.out.println("Telefono "+telephone);
		Telephone telephone2 = new Telephone();
		telephone2.setNumber("0983232958");
		System.out.println("Telefono2 "+telephone2);
		telephoneList.add(telephone);
		telephoneList.add(telephone2);
		this.telephoneRepository.save(telephone);
		this.telephoneRepository.save(telephone2);
		user.setTelephones(telephoneList);
		this.userRepository.save(user);
		System.out.println(user);
		telephone.setNumber("00000000");
		telephone.setUser(user);
		System.out.println("Telefono "+telephone);
		System.out.println("Telefono2 "+telephone2);
	}
}
