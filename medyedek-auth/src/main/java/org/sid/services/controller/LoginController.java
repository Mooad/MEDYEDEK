package org.sid.services.controller;

import org.sid.services.auth.jdbc.MyUserJDBCDetailService;
import org.sid.services.jwt.JwtTokenUtil;
import org.sid.services.jwt.beans.JwtRequest;
import org.sid.services.jwt.beans.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("http://localhost:4200")
public class LoginController {
	private final AuthenticationConfiguration authConfiguration;
	public LoginController(AuthenticationConfiguration authConfiguration) {
		this.authConfiguration = authConfiguration;
	}


	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private MyUserJDBCDetailService userDetailsService;
	@CrossOrigin
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails,authenticationRequest.getKms());
		return ResponseEntity.ok(new JwtResponse(token));

	}


}