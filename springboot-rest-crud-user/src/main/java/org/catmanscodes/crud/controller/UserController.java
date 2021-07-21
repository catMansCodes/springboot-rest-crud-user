package org.catmanscodes.crud.controller;

import java.util.List;

import org.catmanscodes.crud.model.User;
import org.catmanscodes.crud.repository.UserRepository;
import org.catmanscodes.crud.utils.NoResourceFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserRepository userRepositiry;

	// 1. get All users

	@GetMapping
	public List<User> getAllUser() {

		return userRepositiry.findAll();
	}

	// 2. get user by id

	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id") Long userId) {

		return userRepositiry.findById(userId)
				.orElseThrow(() -> new NoResourceFoundException("No Record found by this id" + userId));
	}

	// 3. Create new user

	@PostMapping
	public User createNewUser(@RequestBody User user) {
		return userRepositiry.save(user);
	}

	// 4. update user

	@PutMapping("/{id}")
	public User updateUser(@RequestBody User user, @PathVariable("id") Long userId) {

		final User dbUser = userRepositiry.findById(userId)
				.orElseThrow(() -> new NoResourceFoundException("No Record found by this id" + userId));

		dbUser.setFirstName(user.getFirstName());
		dbUser.setLastName(user.getLastName());
		dbUser.setEmail(user.getEmail());

		return userRepositiry.save(dbUser);

	}

	// 5. delete user

	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") Long userId) {

		userRepositiry.deleteById(userId);

		return ResponseEntity.ok().build();
	}
}
