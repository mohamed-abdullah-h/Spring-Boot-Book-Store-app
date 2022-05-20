package com.main.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.dto.JwtResponseDto;
import com.main.dto.LoginRequestDto;
import com.main.security.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<JwtResponseDto> login(@RequestBody LoginRequestDto loginRequest) {

		JwtResponseDto jwtResponseDto = authService.login(loginRequest.getUserName(), loginRequest.getPassword());

		return ResponseEntity.ok(jwtResponseDto);
	}

	@PostMapping("/refresh-token")
	public ResponseEntity<String> refreshAccessToken(@RequestParam String refreshToken) {

		String dto = authService.refreshAccessToken(refreshToken);

		return ResponseEntity.ok(dto);
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logout(@RequestParam String refreshToken) {

		authService.logoutUser(refreshToken);

		return ResponseEntity.ok(null);
	}

}
