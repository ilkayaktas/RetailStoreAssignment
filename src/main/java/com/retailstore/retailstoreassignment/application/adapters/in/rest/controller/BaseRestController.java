package com.retailstore.retailstoreassignment.application.adapters.in.rest.controller;

import com.retailstore.retailstoreassignment.application.adapters.in.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseRestController {
	@Autowired
	protected JwtTokenUtil jwtTokenUtil;


	protected String getUserIdFromToken(String token){
		return jwtTokenUtil.getUserIdFromToken(token.replace("Bearer ", ""));
	}

}
