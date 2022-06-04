package com.retailstore.retailstoreassignment.application.adapters.in.security;

import com.retailstore.retailstoreassignment.config.AppLogger;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

   @Autowired
   private JwtUserDetailsService jwtUserDetailsService;

   @Autowired
   private JwtTokenUtil jwtTokenUtil;

   @SneakyThrows
   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
           throws ServletException, IOException {
      Logger log = AppLogger.getLogger(JwtRequestFilter.class);

      final String requestTokenHeader = request.getHeader("Authorization");

      String username = null;
      String jwtToken = null;
      // JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token
      if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
         jwtToken = requestTokenHeader.substring(7);
         try {
            username = jwtTokenUtil.getUsernameFromToken(jwtToken);
         } catch (IllegalArgumentException e) {
            log.error("Unable to get JWT Token");
         } catch (ExpiredJwtException e) {
            log.error("JWT Token has expired");
         }
      } else {
         logger.warn("JWT Token does not begin with Bearer String");
      }

      //Once we get the token validate it.
      if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

         UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

         // if token is valid configure Spring Security to manually set authentication
         Boolean res = jwtTokenUtil.validateToken(jwtToken, userDetails);
         if (res) {

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            usernamePasswordAuthenticationToken
                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            // After setting the Authentication in the context, we specify
            // that the current user is authenticated. So it passes the Spring Security Configurations successfully.
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
         }
      }
      chain.doFilter(request, response);
   }

}
