package com.retailstore.retailstoreassignment.application.adapters.in.security;

import com.retailstore.retailstoreassignment.domain.model.exception.UserNotFoundException;
import com.retailstore.retailstoreassignment.domain.ports.in.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

   private UserManagementService userManagementService;

   @Autowired
   public JwtUserDetailsService(UserManagementService userManagementService) {
      this.userManagementService = userManagementService;
   }

   @Override
   public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

      com.retailstore.retailstoreassignment.domain.model.entity.User user = null;
      try {
         user = userManagementService.getUserByEmail(email);
      } catch (UserNotFoundException e) {
         throw new RuntimeException(e);
      }
      return new User(user.getEmail(), user.getPassword(), new ArrayList<>());

   }
}