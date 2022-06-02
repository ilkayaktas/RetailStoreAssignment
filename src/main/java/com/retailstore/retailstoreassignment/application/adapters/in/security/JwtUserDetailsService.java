package com.retailstore.retailstoreassignment.application.adapters.in.security;

import com.retailstore.retailstoreassignment.domain.ports.in.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

   private UserManagementService userManagementService;

   @Autowired
   public JwtUserDetailsService(UserManagementService userManagementService) {
      this.userManagementService = userManagementService;
   }

   @Override
   public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      // User information will be retrieved from database
      return null;
   }

}