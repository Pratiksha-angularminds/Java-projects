package com.pratiksha.socialfeed.security.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


// import java.nio.file.attribute.UserPrincipal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils
{

    private String SECRET_KEY = "secret";

    public String extractUsername(String token) 
    {
        
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) 
    {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) 
    {
        
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) 
    {
        // System.out.println("----------------------"+token);
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) 
    {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(Authentication authentication) 
    {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        Map<String, Object> claims = new HashMap<>();
       
        return createToken(claims, userPrincipal.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) 
    {
       
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS512,SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String generateForgotPasswordToken(String userId)
    {
        String token = Jwts.builder()
                        .setSubject(userId)
                        .setExpiration(new Date(System.currentTimeMillis() + 1000 *60*60))
                        .signWith(SignatureAlgorithm.HS512,SECRET_KEY)
                        .compact();
        return token;
    }
}