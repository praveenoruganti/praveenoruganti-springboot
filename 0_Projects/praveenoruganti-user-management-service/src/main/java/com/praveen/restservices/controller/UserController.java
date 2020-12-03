package com.praveen.restservices.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.praveen.restservices.entities.User;
import com.praveen.restservices.exceptions.UserExistsException;
import com.praveen.restservices.exceptions.UserNotFoundException;
import com.praveen.restservices.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
/*Usecase Introduction

1. Create user -> POST -> /rest/users
2. GetAll users -> GET -> /rest/users
3. Get user by Id -> GET -> /rest/users/{id}
4. Update user by Id -> PUT -> /rest/users/{id}
5. Delete user by Id -> DELETE -> /rest/users/{id}
6. Get user by username -> GET -> /rest/users/byusername/{username}
*/
//Controller -
@Api(tags = "User Management RESTful Services", value = "UserController", description = "Controller for User Management Service")
@RestController
@Validated
@RequestMapping(value = "/rest/users")
public class UserController {

	// Autowire the UserService
	@Autowired
	private UserService userService;

	// getAllUsers Method
	@ApiOperation(value = "Retrieve list of users")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUsers(@RequestParam(value="page", defaultValue = "1") int page, 
			                      @RequestParam(value="limit", defaultValue = "1") int limit,
			                      @RequestParam(value="sort", defaultValue = "desc") String sort) {
		return userService.getAllUsers();
	}

	// Create User Method
	// @RequestBody Annotation
	// @PostMapping Annotation
	@ApiOperation(value = "Creates a new user")
	@PostMapping
	public ResponseEntity<Void> createUser(
			@ApiParam("User information for a new user to be created.") @Valid @RequestBody User user,
			UriComponentsBuilder builder) {
		try {
			userService.createUser(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/rest/users/{id}").buildAndExpand(user.getUserid()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

		} catch (UserExistsException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}

	// getUserById
	@GetMapping("/{id}")
	public User getUser(@PathVariable("id") @Min(1) Long id) {

		try {
			Optional<User> userOptional = userService.getUserById(id);
			return userOptional.get();
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}

	}

	// updateUserById
	@PutMapping("/{id}")
	@CachePut(value = "users", key = "#user.id")
	public ResponseEntity<Void>  updateUser(@PathVariable("id") Long id, @RequestBody User user,UriComponentsBuilder builder) {

		try {
			userService.updateUserById(id, user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/rest/users/{id}").buildAndExpand(user.getUserid()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.OK);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}

	}

	// deleteUserById
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
	}

	@ResponseBody
	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	public String handleHttpMediaTypeNotAcceptableException() {
	    return "acceptable MIME type:" + MediaType.APPLICATION_JSON_VALUE;
	}
	

}
