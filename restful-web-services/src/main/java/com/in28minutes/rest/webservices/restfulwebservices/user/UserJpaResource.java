package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import com.in28minutes.rest.webservices.restfulwebservices.jpa.PostRepository;
import com.in28minutes.rest.webservices.restfulwebservices.jpa.UserRepository;
import com.in28minutes.rest.webservices.restfulwebservices.post.Post;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {
	private UserRepository userRepository;
	
	private PostRepository postRepository;
	
	public UserJpaResource(UserRepository userRepository, PostRepository postRepository) {
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}
	
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}
	
	
	@GetMapping("/jpa/users/{user_id}")
	public EntityModel<User> retrieveUser(@PathVariable("user_id") int userId) {
		Optional<User> user = userRepository.findById(userId);
		
		if(user.isEmpty()) {
			throw new UserNotFoundException("id=" + userId);
		}
		
		EntityModel<User> entityModel = EntityModel.of(user.get());
		
		WebMvcLinkBuilder builder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(builder.withRel("all-users"));
		
		return entityModel;
	}
	
	@DeleteMapping("/jpa/users/{user_id}")
	public void deleteUser(@PathVariable("user_id") int userId) {
		userRepository.deleteById(userId);
	}
	
	@GetMapping("/jpa/users/{user_id}/posts")
	public List<Post> retrievePostsForUser(@PathVariable("user_id") int userId) {
		Optional<User> user = userRepository.findById(userId);
		
		if(user.isEmpty()) {
			throw new UserNotFoundException("id=" + userId);
		}
		
		return user.get().getPosts();
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user); 
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{user_id}")
						.buildAndExpand(savedUser.getId())
						.toUri();
		return ResponseEntity.created(location ).build();
	}
	
	@PostMapping("/jpa/users/{user_id}/posts")
	public ResponseEntity<Post> createPostForUser(@PathVariable("user_id") int userId, @Valid @RequestBody Post post) {
		Optional<User> user = userRepository.findById(userId);
		
		if(user.isEmpty()) {
			throw new UserNotFoundException("id=" + userId);
		}
		
		post.setUser(user.get());
		
		Post savedPost = postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{user_id}")
				.buildAndExpand(savedPost.getId())
				.toUri();
		return ResponseEntity.created(location ).build();
	}
	
	
}
