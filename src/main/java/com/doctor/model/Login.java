package com.doctor.model;

public class Login {
	
	String usertype;
	String username;
	String password;
	
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Login(String usertype, String username, String password) {
		super();
		this.usertype = usertype;
		this.username = username;
		this.password = password;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
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
		return "Login [usertype=" + usertype + ", username=" + username + ", password=" + password + "]";
	}
	
	

}
