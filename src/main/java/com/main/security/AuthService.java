package com.main.security;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.main.dto.JwtResponseDto;
import com.main.entity.SecurityUser;
import com.main.entity.TokenInfo;
import com.main.service.TokenInfoService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class AuthService {

	@Autowired
	private TokenInfoService tokenService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private JwtTokenUtils tokenUtils;
	
	public JwtResponseDto login(String userName,String password) {
		
		Authentication authentication = authManager
										.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
		
		log.info("Valid Authentication ..........\n");
		
		SecurityUserDetails userDetails = (SecurityUserDetails) authentication.getPrincipal();
		TokenInfo tInfo = getTokenInfo(userName,userDetails.getUser() );
		tInfo = tokenService.save(tInfo);
		return new JwtResponseDto(tInfo.getAccessToken(), tInfo.getRefreshToken());
		
	}
	
	private TokenInfo getTokenInfo(String userName,SecurityUser user ) {
		
		String userAgent = request.getHeader(HttpHeaders.USER_AGENT);
		InetAddress ip = null;
		try {
			ip=InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String localAddress = ip.getHostAddress();
		String remoteAddress = request.getRemoteAddr();
		String accessToken = tokenUtils.createJwtToken(userName,"12345", false);
		String refreshToken = tokenUtils.createJwtToken(userName, "12345", true);
		log.info(localAddress," **  ",remoteAddress);
		
		TokenInfo tInfo = new TokenInfo(null, accessToken, refreshToken, userAgent,user);
		return tInfo;
	}
	
	public String refreshAccessToken(String refreshToken) {
        if (!tokenUtils.isNonExpired(refreshToken)) {
        	
        	logoutUser(refreshToken);
            return null;
        }
        String userName = tokenUtils.getUserName(refreshToken);
        TokenInfo refresh = tokenService.findByRefreshToken(refreshToken);
        if (refresh==null) {
            return null;
        }

        return tokenUtils.createJwtToken(userName, UUID.randomUUID().toString(), false);

    }
 
 
 public void logoutUser(String refreshToken) {
	 		TokenInfo tokenInfo = tokenService.findByRefreshToken(refreshToken);
        if (tokenInfo!=null) {
            tokenService.deleteById(tokenInfo.getId());
        }

    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
