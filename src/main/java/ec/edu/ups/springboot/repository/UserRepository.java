package ec.edu.ups.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.edu.ups.springboot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
}
