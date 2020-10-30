package org.sid.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedyedekAuthController {
	
	@RequestMapping("/*")
	@RolesAllowed("USER")
	public String getUser()
	{
		return "Welcome , user";
		
	}
	@RequestMapping("/login")
	@RolesAllowed("*")
	public String getAdmin()
	{
		return "Welcome , admin";
	}
}
