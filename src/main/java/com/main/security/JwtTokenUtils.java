package com.main.security;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.main.exceptions.JwtTokenException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class JwtTokenUtils {

	@Value("${jwt.secret-key}")
	private String secretKey;
	@Value("${jwt.access-token.duration}")
	private long accessTokenDuration;
	@Value("${jwt.refresh-token.duration}")
	private long refreshTokenDuration;

	public String createJwtToken(String userName, String tokenId, boolean isRefresh) {
		String createdToken = Jwts.builder().setId(tokenId).setAudience("Mohamed_Shrief").setSubject(userName)
				.setIssuedAt(new Date()).setExpiration(calcExpirationDate(isRefresh))
				.claim("created", Calendar.getInstance().getTime()).signWith(SignatureAlgorithm.HS512, secretKey)
				.compact();
		return createdToken;
	}

	public String getUserName(String Token) {
		return getClaims(Token).getSubject();
	}

	public String getTokenId(String token) {
		return getClaims(token).getId();
	}

	private Claims getClaims(String token) {

		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	}

	public boolean isNonExpired(String token) {
		boolean isNonExpired = getClaims(token).getExpiration().after(new Date());
		return isNonExpired;
	}

	public boolean isTokenValid(String token, SecurityUserDetails userDetails) {
		log.info("isTokenNonExpired >>> " + isNonExpired(token));
		String username = getUserName(token);
		log.info("username from token >>> " + username);
		log.info("userDetails.getUsername >>> " + userDetails.getUsername());
		log.info("username =  >>> userDetails.getUsername >>> " + username.equals(userDetails.getUsername()));
		Boolean isUserNameEqual = username.equalsIgnoreCase(userDetails.getUsername());
		return (isUserNameEqual && !isNonExpired(token));
	}

	public boolean validateToken(String token, HttpServletRequest httpServletRequest) {

		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return true;
		} catch (SignatureException ex) {
			throw new JwtTokenException("Invalid JWT Signature");
			
		} catch (MalformedJwtException ex) {
			throw new JwtTokenException("Invalid JWT token");
			
		} catch (ExpiredJwtException ex) {
			throw new JwtTokenException("Jwt Token Is Expired");
			
		} catch (UnsupportedJwtException ex) {
			throw new JwtTokenException("Unsupported JWT exception");
			
		} catch (IllegalArgumentException ex) {
			throw new JwtTokenException("Jwt claims string is empty");
		}
	}

	private Date calcExpirationDate(boolean isRefresh) {
		Date date = new Date(
				System.currentTimeMillis() + (isRefresh ? refreshTokenDuration : accessTokenDuration) * 1000);
		return date;
	}

}
