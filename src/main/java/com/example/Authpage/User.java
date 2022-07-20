package com.example.Authpage;

public class User 
{
	private String Username;
	private String Password;	
	
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}	
	public void setPassword(String password) {
		Password = password;
	}	
	public User(String username, String password) {
		super();
		Username = username;
		Password = password;
	}
	public User() {
		super();
	}
}