package ec.edu.ups.springboot.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "use_id")
	private int id;
	
	@Column(name = "use_dni", length = 10, nullable = false, unique = true)
	private String dni;
	
	@Column(name = "use_name", length=255, nullable=false)
	private String name;
	
	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Telephone> telephones = new ArrayList<>();
	
	public User() {
		
	}

	public User(String dni, String name, List<Telephone> telephones) {
		super();
		this.dni = dni;
		this.name = name;
		this.telephones = telephones;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDni() {
		return dni;
	}
	
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public List<Telephone> getTelephones() {
		return telephones;
	}

	public void setTelephones(List<Telephone> telephones) {
		this.telephones = telephones;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", dni=" + dni + ", name=" + name + ", telephones="
				+ telephones + "]";
	}
	
}
