package com.retailstore.retailstoreassignment.application.adapters.in.security;

import com.retailstore.retailstoreassignment.domain.model.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {
   private static final long serialVersionUID = -2550185165626007488L;

   public static final long JWT_TOKEN_VALIDITY = 5L*60L*60L;

   private final static String secret = "PROJECT_ASSIGNMENT_IA";
   private final static String USER_ID = "userId";
   private final static String USER_TYPE = "userType";

   public String getUsernameFromToken(String token) {
      return getClaimFromToken(token, Claims::getSubject);
   }

   public String getUserIdFromToken(String token){
      final Claims claims = getAllClaimsFromToken(token);
      return (String) claims.get(USER_ID);
   }

   public String getUserTypeFromToken(String token){
      final Claims claims = getAllClaimsFromToken(token);
      return (String) claims.get(USER_TYPE);
   }

   public Date getIssuedAtDateFromToken(String token) {
      return getClaimFromToken(token, Claims::getIssuedAt);
   }

   public Date getExpirationDateFromToken(String token) {
      return getClaimFromToken(token, Claims::getExpiration);
   }

   public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
      final Claims claims = getAllClaimsFromToken(token);
      return claimsResolver.apply(claims);
   }

   private Claims getAllClaimsFromToken(String token) {
      return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
   }

   private Boolean isTokenExpired(String token) {
      final Date expiration = getExpirationDateFromToken(token);
      return expiration.before(new Date());
   }

   private Boolean ignoreTokenExpiration(String token) {
      return false;
   }

   public String generateToken(User userDetails) {
      Map<String, Object> claims = new HashMap<>();
      claims.put(USER_ID, userDetails.getId());
      claims.put(USER_TYPE, userDetails.getUserType());
      return doGenerateToken(claims, userDetails.getEmail());
   }

   private String doGenerateToken(Map<String, Object> claims, String subject) {

      return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
              .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*1000)).signWith(SignatureAlgorithm.HS512, secret).compact();
   }

   public Boolean canTokenBeRefreshed(String token) {
      return (!isTokenExpired(token) || ignoreTokenExpiration(token));
   }

   public Boolean validateToken(String token, UserDetails userDetails) {
      final String username = getUsernameFromToken(token);
      return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
   }
}