package com.met.login.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id;

	@Column(name = "email")
	@Email(message = "Email nije validan")
	@NotEmpty(message = "Unesite email")
	private String email;

	@Column(name = "password")
	@Length(min = 6, message = "Sifra mora da sadrzi najmanje 6 slova")
	@NotEmpty(message = "Unesite sifru")
	private String password;

	@Column(name = "first_name")
	@NotEmpty(message = "Unesite vase ime")
	private String name;

	@Column(name = "last_name")
	@NotEmpty(message = "Unesite vase prezime")
	private String lastName;
	

	@Column(name = "username")
	@NotEmpty(message = "Unesite vas username")
	private String username;

	@Column(name = "active")
	private int active;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role",  joinColumns = @JoinColumn(name = "user_id"),
									inverseJoinColumns = @JoinColumn(name = "role_id"))

	private Set<Role> roles;

	public User() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role r){
		if(this.roles == null)
			this.roles = new HashSet<>();
		
		this.roles.add(r);
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return id + " " + email + " " + password + " " + name + " " + lastName + " " + active + " " + roles;
	}

}
