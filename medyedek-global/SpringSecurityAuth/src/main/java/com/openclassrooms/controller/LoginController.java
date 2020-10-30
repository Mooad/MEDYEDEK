package com.openclassrooms.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {

	@RequestMapping("/*")
	@RolesAllowed("USER")
	public String getUser()
	{
		return "Welcome , user";
		
	}
	@RequestMapping("/admin")
	@RolesAllowed("ADMIN")
	public String getAdmin()
	{
		return "Welcome , admin";
	}
}
