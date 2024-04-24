package com.doctor.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "nurse")
public class Nurse {

	@Id
	String id;
	String name;
	String address;
	String phone;
	String email;
	String gender;
	String salary;
	String username;
	String password;
	public Nurse(String id, String name, String address, String phone, String email, String gender, String salary,
			String username, String password) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.gender = gender;
		this.salary = salary;
		this.username = username;
		this.password = password;
	}
	public Nurse() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Nurse [id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone + ", email=" + email
				+ ", gender=" + gender + ", salary=" + salary + ", username=" + username + ", password=" + password
				+ "]";
	}
	
}
