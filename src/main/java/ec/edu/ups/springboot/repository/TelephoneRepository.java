package ec.edu.ups.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.edu.ups.springboot.model.Telephone;

@Repository
public interface TelephoneRepository extends JpaRepository<Telephone, Integer>{

	public List<Telephone> findByUserId(int userId);

}
