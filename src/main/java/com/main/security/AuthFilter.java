package com.main.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.main.service.SecurityUserService;


public class AuthFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenUtils tokenUtils;
	@Autowired
	private SecurityUserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		SecurityContext secContext = SecurityContextHolder.getContext();
		if (secContext.getAuthentication() == null) {
			String authorizationValue = request.getHeader(HttpHeaders.AUTHORIZATION);

			if (authorizationValue != null) {
				String authToken = authorizationValue.substring("Bearer ".length());

				if (tokenUtils.validateToken(authToken, request)) {
					String userName = tokenUtils.getUserName(authToken);

					if (userName != null) {
						SecurityUserDetails userDetails = (SecurityUserDetails) userService
								.loadUserByUsername(userName);

						if (userDetails != null) {
							UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
									userDetails, null, userDetails.getAuthorities());

							authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
							secContext.setAuthentication(authentication);

						}
					}

				}

			}
		}

		filterChain.doFilter(request, response);
	}

}
