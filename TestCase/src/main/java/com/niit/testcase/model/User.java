package com.niit.testcase.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.stereotype.Component;


@Entity
@Table(name="user")
@Component				//context.scan("com.niit")--will scan pkg and create singleton instances
public class User {
	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="name")
	private String name;
	
	@Min(5)
	@Max(10)
	@Column(name="password")
	private String password;
	@Column(name="contact")
    private int contact;
	@Column(name="role")
	private String role;
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int  getContact() {
		return contact;
	}
	public void setContact(int contact) {
		this.contact = contact;
	}

}
