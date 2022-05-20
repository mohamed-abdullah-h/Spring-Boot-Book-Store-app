package com.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.entity.TokenInfo;
import com.main.repository.TokenInfoRepo;

@Service
public class TokenInfoService {

	@Autowired
	private TokenInfoRepo tokenInfoRepo;

	public TokenInfo findById(Long id) {

		return tokenInfoRepo.findById(id).orElse(null);
	}

	public TokenInfo findByRefreshToken(String refreshToken) {

		return tokenInfoRepo.findByRefreshToken(refreshToken);
	}

	public TokenInfo save(TokenInfo entity) {

		return tokenInfoRepo.save(entity);
	}

	public void deleteById(Long id) {

		tokenInfoRepo.deleteById(id);
	}
}
