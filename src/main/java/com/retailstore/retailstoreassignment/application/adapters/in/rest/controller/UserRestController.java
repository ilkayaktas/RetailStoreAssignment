package com.retailstore.retailstoreassignment.application.adapters.in.rest.controller;

import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.request.UserLoginRequestDto;
import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.request.UserRequestDto;
import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.response.UserLoginResponseDto;
import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.response.UserResponseDto;
import com.retailstore.retailstoreassignment.application.adapters.in.rest.exception.InvalidCredentialException;
import com.retailstore.retailstoreassignment.application.adapters.in.rest.exception.UnauthorizedOperationException;
import com.retailstore.retailstoreassignment.application.adapters.in.rest.facade.UserManagementFacade;
import com.retailstore.retailstoreassignment.application.adapters.in.security.JwtUserDetailsService;
import com.retailstore.retailstoreassignment.domain.model.entity.User;
import com.retailstore.retailstoreassignment.domain.model.enums.UserType;
import com.retailstore.retailstoreassignment.domain.model.exception.DuplicateEmailFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value="/api/v1")
@Validated
public class UserRestController extends BaseRestController{

	private UserManagementFacade userManagementFacade;
	private AuthenticationManager authenticationManager;
	private JwtUserDetailsService userDetailsService;

	@Autowired
	public UserRestController(UserManagementFacade userManagementFacade, AuthenticationManager authenticationManager, JwtUserDetailsService userDetailsService) {
		this.userManagementFacade = userManagementFacade;
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
	}

	@PostMapping(value = "/users/register")
	public ResponseEntity<UserResponseDto> registerUser(@Valid
														@NotNull(message = "User can't be empty.")
														@RequestBody UserRequestDto userRequestDto) throws DuplicateEmailFoundException {

		UserResponseDto resVal = userManagementFacade.registerUser(userRequestDto);
		return ResponseEntity.ok(resVal);
	}

	@PostMapping(value = "/users/login")
	public ResponseEntity<UserLoginResponseDto> loginUser(@Valid @RequestBody UserLoginRequestDto userLoginRequestDto ) throws InvalidCredentialException {

		try{
			authenticate(userLoginRequestDto.getEmail(), userLoginRequestDto.getPassword());

			UserDetails userDetails = userDetailsService.loadUserByUsername(userLoginRequestDto.getEmail());

			User user = userManagementFacade.getUserByEmail(userLoginRequestDto.getEmail());

			String token = jwtTokenUtil.generateToken(user);

			return ResponseEntity.ok(new UserLoginResponseDto(token));

		} catch (Exception e){
			throw new InvalidCredentialException("INVALID_CREDENTIAL");
		}
	}

	@GetMapping(value = "/users")
	public ResponseEntity<List<UserResponseDto>> getUser(@RequestHeader (name="Authorization") String token) throws UnauthorizedOperationException {

		checkAuthorization(token);

		List<UserResponseDto> userList = userManagementFacade.getUsers();
		return ResponseEntity.ok(userList);
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


	private void checkAuthorization(String token) throws UnauthorizedOperationException {
		String userType = getUserTypeFromToken(token);

		if(!userType.equals(UserType.ADMIN.name())){
			throw new UnauthorizedOperationException("UNAUTHORIZED OPERATION. Only Admin can do user operations");
		}
	}

}
