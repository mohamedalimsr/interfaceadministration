package fr.pfe.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.pfe.entities.AdministratorAccount;
import fr.pfe.entities.Role;
import fr.pfe.entities.ServerClientAccount;
import fr.pfe.entities.User;
import fr.pfe.entities.WebClientAccount;
import fr.pfe.enumeration.ERole;
import fr.pfe.payload.JwtResponse;
import fr.pfe.payload.LoginRequest;
import fr.pfe.payload.MessageResponse;
import fr.pfe.payload.SignupRequest;
import fr.pfe.repositories.AdministratorRepository;
import fr.pfe.repositories.RoleRepository;
import fr.pfe.repositories.ServerClientRepository;
import fr.pfe.repositories.UserRepository;
import fr.pfe.repositories.WebClientRepository;
import fr.pfe.security.jwt.JwtUtils;
import fr.pfe.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	private WebClientRepository webClientRepository;
	@Autowired
	private ServerClientRepository serverClientRepository;
	@Autowired
	private AdministratorRepository administratorRepository;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));
		String role = signUpRequest.getRole();

		switch (role) {
		case "ROLE_ADMIN":
			Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			AdministratorAccount administratorAccount = new AdministratorAccount(user.getUsername(), 
					user.getEmail(),
					user.getPassword());
			administratorAccount.setRole(adminRole);
			administratorRepository.save(administratorAccount);
			break;
		case "ROLE_SERVER":
			Role modRole = roleRepository.findByName(ERole.ROLE_SERVER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			ServerClientAccount serverClientAccount = new ServerClientAccount(user.getUsername(), 
					user.getEmail(),
					user.getPassword());
			serverClientAccount.setRole(modRole);
			serverClientRepository.save(serverClientAccount);
			break;
		default:
			Role userRole = roleRepository.findByName(ERole.ROLE_WEB)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			WebClientAccount webClientAccount = new WebClientAccount(user.getUsername(), 
					user.getEmail(),
					user.getPassword());
			webClientAccount.setRole(userRole);
			webClientRepository.save(webClientAccount);
		}

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
