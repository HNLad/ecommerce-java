package com.assign.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assign.ecommerce.model.JwtResponse;
import com.assign.ecommerce.model.UserModel;
import com.assign.ecommerce.security.JwtTokenProvdr;
import com.assign.ecommerce.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvdr jwtTokenProvider;

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserModel user) {
		if (user != null && user.getUsername() != null && user.getPassword() != null) {
			if (user.getEmail() != null) {
				userService.registerUser(user);
				return ResponseEntity.ok("User registered successfully");
			} else {
				return ResponseEntity.ok("Please enter user email details");
			}
		}
		return ResponseEntity.ok("Please enter valid user details");
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserModel loginRequest) {
		try {
			if (loginRequest != null && loginRequest.getUsername() != null) {
				UserModel user = userService.findByUsername(loginRequest.getUsername());

				if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
					String token = jwtTokenProvider.generateToken(user.getUsername());
					return ResponseEntity.ok(new JwtResponse(token));
				}
			} else {
				System.out.println("logIn request is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials, please provide the proper credetials");
	}
}
