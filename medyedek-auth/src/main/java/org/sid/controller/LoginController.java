package org.sid.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*")
@RestController
public class LoginController {

	@GetMapping("/*")
	@RolesAllowed("USER")
	public String getUser()
	{
		return "Welcome , user";
		
	}
	@GetMapping("/")
	@RolesAllowed("USER")
	public String home() {
		return ("<h1>Welcome</h1>");
	}


}
