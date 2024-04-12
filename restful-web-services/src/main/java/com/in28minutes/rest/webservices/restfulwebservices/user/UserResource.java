package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.rest.webservices.restfulwebservices.exception.UserNotFoundException;

import jakarta.validation.Valid;

@RestController
public class UserResource {
	private UserDaoService service;
	
	public UserResource(UserDaoService service) {
		this.service = service;
	}
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}
	
	
	@GetMapping("/users/{user_id}")
	public EntityModel<User> retrieveUser(@PathVariable("user_id") int userId) {
		User user = service.findOne(userId);
		
		if(user == null) {
			throw new UserNotFoundException("id=" + userId);
		}
		
		EntityModel<User> entityModel = EntityModel.of(user);
		
		WebMvcLinkBuilder builder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(builder.withRel("all-users"));
		
		return entityModel;
	}
	
	@DeleteMapping("/users/{user_id}")
	public void deleteUser(@PathVariable("user_id") int userId) {
		service.deleteById(userId);
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user); 
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{user_id}")
						.buildAndExpand(savedUser.getId())
						.toUri();
		return ResponseEntity.created(location ).build();
	}
	
	
}
