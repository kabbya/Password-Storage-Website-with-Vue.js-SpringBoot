package com.password.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.password.entity.Password;
import com.password.entity.User;
import com.password.repository.PasswordRepository;
import com.password.repository.UserRepository;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/test")
public class TestController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordRepository passwordRepository;
	
	
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	// get all password by id
	
	@GetMapping("/passwords/user/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<Password>> getAllPasswords(@PathVariable("id") long id) {
		System.out.println("Get All");
		List<Password> list = passwordRepository.findPasswordByUser(id);
		
		if (list.size() == 0)
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	// create new password
	
	@PostMapping("/passwords/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Password> createPassword(@PathVariable("id") long id, @RequestBody Password password) {
		System.out.println("Create");
		User user = userRepository.getById(id);
		password.setUser(user);
		
		return new ResponseEntity<>( passwordRepository.save(password), HttpStatus.CREATED );
	}
	
	
	// get password id
	
	@GetMapping("/passwords/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Password> getPasswordById(@PathVariable("id") long id, Principal principal){
		System.out.println("Get Password");
		Optional<Password> passwordOptional = passwordRepository.findById(id);
		Password password = passwordOptional.get();
		String userName =   principal.getName();
		
		if (passwordOptional.isPresent() && userName.equals(password.getUser().getUsername())) 
			return new ResponseEntity<>(passwordOptional.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    
	}
	
	
}