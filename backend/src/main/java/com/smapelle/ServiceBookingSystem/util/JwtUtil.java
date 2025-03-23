package com.smapelle.ServiceBookingSystem.util;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	private static final String SECRET_KEY = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";


	public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
    	return Jwts.builder().claims(extraClaims)
    			.subject(userDetails.getUsername())
    			.issuedAt(new Date(System.currentTimeMillis()))
    			.expiration(new Date(System.currentTimeMillis() + 100 * 60 * 100))
    			.signWith(getSigninKey())
    			.compact();
    }
	
	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(getSigninKey()).build()
				.parseClaimsJws(token).getBody();
	}
	
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
	
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	public String generateToken(UserDetails userDetails) {
    	return generateToken(new HashMap<>(), userDetails);
    }
	
	public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
	
	private Key getSigninKey() {
		byte[] KeyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(KeyBytes);
	}
}
