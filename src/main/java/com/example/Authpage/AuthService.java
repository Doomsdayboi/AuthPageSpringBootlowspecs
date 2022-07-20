package com.example.Authpage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthService 
{	
	private List<User> Users = new ArrayList<>(Arrays.asList(
			new User("A1", "1234"),
			new User("A2", "1234"),
			new User("A3", "1234")
			));
	
	public List<User> getAllUsers()
	{
		return Users;
	}

	public void deleteUser(String username) 
	{
		try
		{
			User user1 = Users.stream().filter(u -> u.getUsername().equals(username)).findFirst().get();
			log.info("Inside deleteUser {}", username);
			Users.remove(user1);
			log.info("User Removed");
		}
		catch(java.util.NoSuchElementException e)
		{
			log.info("This user does not exist");
		}		
	}

	public ResponseEntity<String> signup(User user)
	{			
		
		log.info("Inside singup {}", user.getUsername());
		try
		{
			Users.stream().filter(u -> u.getUsername().equals(user.getUsername())).findFirst().get();
			return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
		}
		catch(java.util.NoSuchElementException e)
		{
			Users.add(user);
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
	}

	public ResponseEntity<String> login(User user)
	{		
		log.info("Inside login {}", user.getUsername());
		try
		{
			User user1 = Users.stream().filter(u -> u.getUsername().equals(user.getUsername())).findFirst().get();
			if(user1.getPassword().equals(user.getPassword()))
			{
				return new ResponseEntity<>(null, HttpStatus.OK);
			}
			else
				return new ResponseEntity<>("Wrong password", HttpStatus.BAD_REQUEST);
		}
		catch(java.util.NoSuchElementException e)
		{
			return new ResponseEntity<>("This user does not exist", HttpStatus.BAD_REQUEST);
		}
	}
}
