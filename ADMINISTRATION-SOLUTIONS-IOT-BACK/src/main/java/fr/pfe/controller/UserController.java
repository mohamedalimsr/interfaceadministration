package fr.pfe.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.pfe.dto.ServerClientAccountDto;
import fr.pfe.entities.Role;
import fr.pfe.entities.ServerClientAccount;
import fr.pfe.entities.User;
import fr.pfe.enumeration.ERole;
import fr.pfe.payload.MessageResponse;
import fr.pfe.payload.SignupRequest;
import fr.pfe.repositories.RoleRepository;
import fr.pfe.repositories.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@PostMapping("/add")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}
		// Create new user's account
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));
		String role = signUpRequest.getRole();

		switch (role) {
		case "ROLE_ADMIN":
			Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			user.setRole(adminRole);
			break;
		case "ROLE_SERVER":
			Role modRole = roleRepository.findByName(ERole.ROLE_SERVER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			user.setRole(modRole);
			break;
		default:
			Role userRole = roleRepository.findByName(ERole.ROLE_WEB)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			user.setRole(userRole);
		}
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	
	public ResponseEntity<?> addServerClientAccount(ServerClientAccountDto serverClientAccountDto){
		return ResponseEntity.ok(new MessageResponse("Server Client Account registered successfully!"));
	}
	public List<ServerClientAccount> getAllServerClientAccount(){
		return null;
	}
	public List<ServerClientAccount> getAllWebClientAccount(){
		return null;
	}
	public ResponseEntity<?> addWebClientAccount(){
		return ResponseEntity.ok(new MessageResponse("Web Client Account registered successfully!"));
	}
	
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	void deleteUser(@PathVariable("id") long id) {
		userRepository.deleteById(id);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public User getUser(@PathVariable("id") long id) {
		return userRepository.findById(id).get();
	}

}
