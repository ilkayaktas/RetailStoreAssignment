package com.retailstore.retailstoreassignment.application.adapters.in.rest.controller;

import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.request.UserLoginRequestDto;
import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.request.UserRequestDto;
import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.response.UserLoginResponseDto;
import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.response.UserResponseDto;
import com.retailstore.retailstoreassignment.application.adapters.in.rest.exception.InvalidCredentialException;
import com.retailstore.retailstoreassignment.application.adapters.in.rest.facade.UserManagementFacade;
import com.retailstore.retailstoreassignment.application.adapters.in.security.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value="/api/v1")
@Validated
public class UserRestController extends BaseRestController{

	private UserManagementFacade userManagementFacade;
	private AuthenticationManager authenticationManager;
	private JwtUserDetailsService userDetailsService;

	@Autowired
	public UserRestController(UserManagementFacade userManagementFacade) {
		this.userManagementFacade = userManagementFacade;
	}

	@PostMapping(value = "/users/register")
	public ResponseEntity<UserResponseDto> registerUser(@Valid
														@NotNull(message = "User can't be empty.")
														@RequestBody UserRequestDto userRequestDto)  {

		return ResponseEntity.ok(null);
	}

	@PostMapping(value = "/users/login")
	public ResponseEntity<UserLoginResponseDto> loginUser(@Valid @RequestBody UserLoginRequestDto userLoginRequestDto ) throws InvalidCredentialException {

		try{
			authenticate(userLoginRequestDto.getEmail(), userLoginRequestDto.getPassword());


			return ResponseEntity.ok(null);

		} catch (Exception e){
			throw new InvalidCredentialException("INVALID_CREDENTIAL");
		}
	}

	@GetMapping(value = "/users")
	public ResponseEntity<UserResponseDto> getUser(@RequestHeader (name="Authorization") String token) {


		return ResponseEntity.ok(null);
	}

	private void authenticate(String username, String password) throws InvalidCredentialException {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new InvalidCredentialException("USER_DISABLED");
		} catch (BadCredentialsException e) {
			throw new InvalidCredentialException("INVALID_CREDENTIALS");
		}
	}
}
