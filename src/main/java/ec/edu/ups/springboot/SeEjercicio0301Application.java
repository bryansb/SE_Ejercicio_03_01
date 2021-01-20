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
//		List<Telephone> telephoneList = new ArrayList<>();
//		Telephone telephone = new Telephone();
//		telephone.setNumber("0983232969");
//		System.out.println("Telefono "+telephone);
//		Telephone telephone2 = new Telephone();
//		telephone2.setNumber("0983232958");
//		System.out.println("Telefono2 "+telephone2);
//		telephoneList.add(telephone);
//		telephoneList.add(telephone2);
//		this.telephoneRepository.save(telephone);
//		this.telephoneRepository.save(telephone2);
//		user.setTelephones(telephoneList);
//		this.userRepository.save(user);
//		System.out.println(user);
//		telephone.setNumber("00000000");
//		telephone.setUser(user);
//		this.telephoneRepository.save(telephone);	
//		System.out.println("Telefono "+telephone);
//		System.out.println("Telefono2 "+telephone2);
		
	}
}
